package com.engineering.ie.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.engineering.ie.domain.GeologicalSection;
import com.engineering.ie.domain.Job;
import com.engineering.ie.exception.EntityNotFoundException;
import com.engineering.ie.mapper.GeologicalSectionMapper;
import com.engineering.ie.repository.JobRepository;
import com.engineering.ie.util.JobState;
import com.engineering.ie.util.XlsManager;
import com.engineering.ie.vo.GeologicalSectionVO;

/**
 * Service to encapsulate the link between domain and controller and to have
 * business logic for some jobs specific things.
 * <p/>
 */
@Service
public class DefaultJobService implements JobService {
	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultJobService.class);

	private final JobRepository jobRepository;
	private final GeologicalSectionService geologicalSectionService;

	public DefaultJobService(final JobRepository jobRepository,
			final GeologicalSectionService geologicalSectionService) {
		this.jobRepository = jobRepository;
		this.geologicalSectionService = geologicalSectionService;
	}

	@Override
	public long registerJob(MultipartFile file) throws IOException {
		Job job = new Job(JobState.UPLODED, file.getBytes(), null);
		job = jobRepository.saveAndFlush(job);
		parseAndSaveJob(job).thenAccept(this::persistSections);
		return job.getId();
	}

	@Override
	public List<GeologicalSectionVO> getJobResult(long id) throws EntityNotFoundException {
		return findJobChecked(id).getSections();
	}

	@Override
	public List<Job> getAllJob() {
		return jobRepository.findAll();
	}

	@Override
	public List<Job> searchJobResultByName(String name) {
		name = "%" + name + "%";
		return jobRepository.searchJobResultByName(name);
	}

	@Override
	public List<Job> searchJobResultByCode(String code) {
		code = "%" + code + "%";
		return jobRepository.searchJobResultByCode(code);
	}

	private CompletableFuture<Job> parseAndSaveJob(Job job) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				job.setSections(XlsManager.parseFile(job.getXls()));
				job.setState(JobState.PARSED);
			} catch (Throwable e) {
				LOG.error("Generic parsing Error", e);
				job.setState(JobState.ERROR);
			}
			return jobRepository.saveAndFlush(job);
		});
	}

	private void persistSections(Job job) {
		if (job.getState() != JobState.ERROR) {
			List<GeologicalSection> sections = job.getSections().stream()
					.map(GeologicalSectionMapper::makeGeologicalSection).collect(Collectors.toList());

			try {
				geologicalSectionService.create(sections);
				job.setState(JobState.PERSISTED);

			} catch (Throwable e) {
				LOG.error("Generic Error while persisting section", e);
				job.setState(JobState.ERROR);
			}
		}
		jobRepository.saveAndFlush(job);
	}
	
	private Job findJobChecked(Long jobId) throws EntityNotFoundException {
		Job job = jobRepository.findOne(jobId);
		if (job == null) {
			throw new EntityNotFoundException("Could not find entity with id: " + jobId);
		}
		return job;
	}

}
