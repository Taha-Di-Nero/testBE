package com.engineering.ie.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.engineering.ie.domain.Job;

public interface JobRepository extends JpaRepository<Job, Long> {

	@Query(value = "SELECT DISTINCT j.* FROM JOBS j, jsonb_array_elements(j.sections) s, jsonb_array_elements(s -> 'classes') c WHERE s ->> 'name' LIKE :sectionName OR c ->> 'name' LIKE :sectionName", nativeQuery = true)
	List<Job> searchJobResultByName(@Param("sectionName") String sectionName);

	@Query(value = "SELECT DISTINCT j.* FROM JOBS j, jsonb_array_elements(j.sections) s, jsonb_array_elements(s -> 'classes') c WHERE c ->> 'code' LIKE :classCode", nativeQuery = true)
	List<Job> searchJobResultByCode(@Param("classCode") String classCode);
}
