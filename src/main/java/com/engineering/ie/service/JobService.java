package com.engineering.ie.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.engineering.ie.domain.Job;
import com.engineering.ie.exception.EntityNotFoundException;
import com.engineering.ie.vo.GeologicalSectionVO;

public interface JobService {

	/**
	 * Register a parsing job
	 * 
	 * @param MultipartFile
	 * @return jobId
	 * @throws IOException
	 */
	long registerJob(MultipartFile file) throws IOException;

	/**
	 * Get the job parsing result
	 * 
	 * @param jobId
	 * @return List<GeologicalSectionVO>
	 * @throws EntityNotFoundException 
	 */
	List<GeologicalSectionVO> getJobResult(long id) throws EntityNotFoundException;

	/**
	 * Get all jobs
	 * 
	 * @return List<Job>
	 */
	List<Job> getAllJob();

	/**
	 * Search jobs where parsing result section or class name contains 'name'
	 * 
	 * @param name
	 * @return List<Job>
	 */
	List<Job> searchJobResultByName(String name);
	
	/**
	 * Search jobs where parsing result class code contains 'code'
	 * 
	 * @param code
	 * @return List<Job>
	 */
	List<Job> searchJobResultByCode(String code);

}
