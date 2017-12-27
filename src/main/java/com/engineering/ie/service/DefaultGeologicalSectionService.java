package com.engineering.ie.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.engineering.ie.domain.GeologicalSection;
import com.engineering.ie.exception.ConstraintsViolationException;
import com.engineering.ie.exception.EntityNotFoundException;
import com.engineering.ie.repository.GeologicalSectionRepository;

/**
 * Service to encapsulate the link between domain and controller and to have
 * business logic for some geological section specific things.
 * <p/>
 */
@Service
public class DefaultGeologicalSectionService implements GeologicalSectionService {

	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultGeologicalSectionService.class);

	private final GeologicalSectionRepository geologicalSectionRepository;

	public DefaultGeologicalSectionService(final GeologicalSectionRepository geologicalSectionRepository) {
		this.geologicalSectionRepository = geologicalSectionRepository;
	}

	@Override
	public GeologicalSection find(Long geologicalSectionId) throws EntityNotFoundException {
		return findGeologicalSectionChecked(geologicalSectionId);
	}

	@Override
	public List<GeologicalSection> findAll() {
		List<GeologicalSection> sections = new ArrayList<>();
		geologicalSectionRepository.findAll().iterator().forEachRemaining(sections::add);
		return sections;
	}

	@Override
	public GeologicalSection create(GeologicalSection section) throws ConstraintsViolationException {
		GeologicalSection sectionEntity;
		try {
			sectionEntity = geologicalSectionRepository.save(section);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("Some constraints are thrown due to geological section creation", e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return sectionEntity;
	}

	@Override
	public void create(List<GeologicalSection> sections) throws ConstraintsViolationException {
		try {
			geologicalSectionRepository.save(sections);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("Some constraints are thrown due to geological section creation", e);
			throw new ConstraintsViolationException(e.getMessage());
		}
	}

	@Override
	@Transactional
	public GeologicalSection update(GeologicalSection section)
			throws ConstraintsViolationException, EntityNotFoundException {
		GeologicalSection sectionEntity;
		try {
			sectionEntity = patchGeologicalSection(section);
			sectionEntity = geologicalSectionRepository.save(sectionEntity);
		} catch (DataIntegrityViolationException e) {
			LOG.warn("Some constraints are thrown due to geological section creation", e);
			throw new ConstraintsViolationException(e.getMessage());
		}
		return sectionEntity;
	}

	@Override
	@Transactional
	public void delete(Long geologicalSectionId) throws EntityNotFoundException {
		GeologicalSection section = findGeologicalSectionChecked(geologicalSectionId);
		geologicalSectionRepository.delete(section);
	}

	private GeologicalSection findGeologicalSectionChecked(Long geologicalSectionId) throws EntityNotFoundException {
		GeologicalSection section = geologicalSectionRepository.findOne(geologicalSectionId);
		if (section == null) {
			throw new EntityNotFoundException("Could not find entity with id: " + geologicalSectionId);
		}
		return section;
	}

	private GeologicalSection patchGeologicalSection(GeologicalSection patch) throws EntityNotFoundException {
		GeologicalSection section = findGeologicalSectionChecked(patch.getId());
		if (patch.getName() != null && !patch.getName().trim().isEmpty()) {
			section.setName(patch.getName());
		}
		if (patch.getClasses() != null) {
			section.getClasses().clear();
			section.getClasses().addAll(patch.getClasses());
		}
		return section;
	}

}
