package com.engineering.ie.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.engineering.ie.exception.EntityNotFoundException;
import com.engineering.ie.mapper.JobMapper;
import com.engineering.ie.service.JobService;
import com.engineering.ie.vo.GeologicalSectionVO;
import com.engineering.ie.vo.JobVO;

/**
 * All operations with a job will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/job")
public class JobController {

	private final JobService jobService;

	@Autowired
	public JobController(final JobService jobService) {
		this.jobService = jobService;
	}

	@PostMapping("/register")
	@ResponseStatus(HttpStatus.CREATED)
	public long registerJob(@RequestParam("file") MultipartFile file) throws IOException {
		return jobService.registerJob(file);
	}

	@GetMapping("/{id}/result")
	@ResponseStatus(HttpStatus.OK)
	public List<GeologicalSectionVO> getJobResult(@Valid @PathVariable long id) throws EntityNotFoundException {
		return jobService.getJobResult(id);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<JobVO> getAllJob() {
		return JobMapper.makeJobVOList(jobService.getAllJob());
	}

	@GetMapping("/geologicalSectionClass/{name}")
	@ResponseStatus(HttpStatus.OK)
	public List<JobVO> searchJobResultByName(@Valid @PathVariable String name) {
		return JobMapper.makeJobVOList(jobService.searchJobResultByName(name));
	}

	@GetMapping("/geologicalClass/{code}")
	@ResponseStatus(HttpStatus.OK)
	public List<JobVO> searchJobResultCode(@Valid @PathVariable String code) {
		return JobMapper.makeJobVOList(jobService.searchJobResultByCode(code));
	}
}
