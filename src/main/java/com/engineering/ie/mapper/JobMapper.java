package com.engineering.ie.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.engineering.ie.domain.Job;
import com.engineering.ie.vo.JobVO;

public class JobMapper {

	public static JobVO makeJobVO(Job job) {
		JobVO.JobVOBuilder jobVOBuilder = JobVO.newBuilder().setId(job.getId()).setState(job.getState())
				.setSections(job.getSections());
		return jobVOBuilder.createJobVO();
	}

	public static List<JobVO> makeJobVOList(Collection<Job> classes) {
		return classes.stream().map(JobMapper::makeJobVO).collect(Collectors.toList());
	}
}
