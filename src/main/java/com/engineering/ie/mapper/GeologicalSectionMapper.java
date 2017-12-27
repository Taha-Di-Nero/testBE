package com.engineering.ie.mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.engineering.ie.domain.GeologicalClass;
import com.engineering.ie.domain.GeologicalSection;
import com.engineering.ie.vo.GeologicalSectionVO;

public class GeologicalSectionMapper {
	public static GeologicalSection makeGeologicalSection(GeologicalSectionVO sectionVO) {
		return new GeologicalSection(sectionVO.getName(), sectionVO.getClasses().stream()
				.map(s -> GeologicalClassMapper.makeGeologicalClass(s)).collect(Collectors.toSet()));
	}

	public static GeologicalSectionVO makeGeologicalSectionVO(GeologicalSection section) {
		GeologicalSectionVO.GeologicalSectionVOBuilder sectionVOBuilder = GeologicalSectionVO.newBuilder()
				.setId(section.getId()).setName(section.getName());

		Set<GeologicalClass> classes = section.getClasses();
		if (classes != null) {
			sectionVOBuilder.setClasses(GeologicalClassMapper.makeGeologicalClassVOList(classes));
		}

		return sectionVOBuilder.createGeologicalSectionVO();
	}

	public static List<GeologicalSectionVO> makeGeologicalSectionVOList(Collection<GeologicalSection> sections) {
		return sections.stream().map(GeologicalSectionMapper::makeGeologicalSectionVO).collect(Collectors.toList());
	}
}
