package com.engineering.ie.service;

import java.util.List;

import com.engineering.ie.domain.GeologicalSection;
import com.engineering.ie.exception.ConstraintsViolationException;
import com.engineering.ie.exception.EntityNotFoundException;

public interface GeologicalSectionService {
	
	/**
	 * Selects a geological section by id.
	 *
	 * @param geologicalSectionId
	 * @return found geological section
	 * @throws EntityNotFoundException
	 */
	GeologicalSection find(Long manufacturerId) throws EntityNotFoundException;

	/**
	 * Selects all geological sections.
	 *
	 * @return all geological sections
	 * @throws EntityNotFoundException
	 */
	List<GeologicalSection> findAll() throws EntityNotFoundException;

	/**
	 * Creates a new geological section.
	 *
	 * @param GeologicalSection
	 * @return the geological section created
	 * @throws ConstraintsViolationException
	 */
	GeologicalSection create(GeologicalSection geologicalSection) throws ConstraintsViolationException;

	/**
	 * Creates a list of new geological section.
	 *
	 * @param List<GeologicalSection>
	 * @throws ConstraintsViolationException
	 */
	void create(List<GeologicalSection> geologicalSection) throws ConstraintsViolationException;

	/**
	 * Update a geological section.
	 *
	 * @param geologicalSection
	 * @return the updated geological section
	 * @throws ConstraintsViolationException,
	 *             EntityNotFoundException
	 */
	GeologicalSection update(GeologicalSection geologicalSection)
			throws ConstraintsViolationException, EntityNotFoundException;

	/**
	 * Deletes an existing geological section by id.
	 *
	 * @param geologicalSectionId
	 * @throws EntityNotFoundException
	 */
	void delete(Long manufacturerId) throws EntityNotFoundException;
}
