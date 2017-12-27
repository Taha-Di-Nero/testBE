package com.engineering.ie.domain;

import java.util.List;

import com.engineering.ie.vo.GeologicalSectionVO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.vladmihalcea.hibernate.type.json.TypeReferenceFactory;

public class GeologicalSectionsTypeReference implements TypeReferenceFactory {

	@Override
	public TypeReference<?> newTypeReference() {
		return new TypeReference<List<GeologicalSectionVO>>() {
		};
	}
}