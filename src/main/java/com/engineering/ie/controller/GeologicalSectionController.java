package com.engineering.ie.controller;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.engineering.ie.domain.GeologicalSection;
import com.engineering.ie.exception.ConstraintsViolationException;
import com.engineering.ie.exception.EntityNotFoundException;
import com.engineering.ie.mapper.GeologicalSectionMapper;
import com.engineering.ie.service.GeologicalSectionService;
import com.engineering.ie.vo.GeologicalSectionVO;

/**
 * All operations with a geological sections will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/geologicalSection")
public class GeologicalSectionController {

	private final GeologicalSectionService geologicalSectionService;

	@Autowired
	public GeologicalSectionController(final GeologicalSectionService geologicalSectionService) {
		this.geologicalSectionService = geologicalSectionService;
	}

	@GetMapping("/{sectionId}")
	public GeologicalSectionVO getGeologicalSection(@Valid @PathVariable long sectionId)
			throws EntityNotFoundException {
		return GeologicalSectionMapper.makeGeologicalSectionVO(geologicalSectionService.find(sectionId));
	}

	@GetMapping
	public List<GeologicalSectionVO> getGeologicalSections() throws EntityNotFoundException {
		return GeologicalSectionMapper.makeGeologicalSectionVOList(geologicalSectionService.findAll());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GeologicalSectionVO createGeologicalSection(@Valid @RequestBody GeologicalSectionVO sectionVO)
			throws ConstraintsViolationException {
		GeologicalSection section = GeologicalSectionMapper.makeGeologicalSection(sectionVO);
		return GeologicalSectionMapper.makeGeologicalSectionVO(geologicalSectionService.create(section));
	}

	@PatchMapping("/{sectionId}")
	@ResponseStatus(HttpStatus.OK)
	public GeologicalSectionVO updateGeologicalSection(@Valid @PathVariable long sectionId,
			@Valid @RequestBody GeologicalSectionVO sectionVO)
			throws ConstraintsViolationException, EntityNotFoundException {
		GeologicalSection section = GeologicalSectionMapper.makeGeologicalSection(sectionVO);
		section.setId(sectionId);
		return GeologicalSectionMapper.makeGeologicalSectionVO(geologicalSectionService.update(section));
	}

	@DeleteMapping("/{sectionId}")
	public void deleteGeologicalSection(@Valid @PathVariable long sectionId) throws EntityNotFoundException {
		geologicalSectionService.delete(sectionId);
	}
}
