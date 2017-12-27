package com.engineering.ie.mapper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.engineering.ie.domain.GeologicalClass;
import com.engineering.ie.vo.GeologicalClassVO;

public class GeologicalClassMapper {
	public static GeologicalClass makeGeologicalClass(GeologicalClassVO classVO) {
		return new GeologicalClass(classVO.getCode(), classVO.getName());
	}

	public static GeologicalClassVO makeGeologicalClassVO(GeologicalClass clazz) {
		GeologicalClassVO.GeologicalClassVOBuilder classVOBuilder = GeologicalClassVO.newBuilder().setId(clazz.getId())
				.setCode(clazz.getCode()).setName(clazz.getName());
		return classVOBuilder.createGeologicalClassVO();
	}

	public static List<GeologicalClassVO> makeGeologicalClassVOList(Collection<GeologicalClass> classes) {
		return classes.stream().map(GeologicalClassMapper::makeGeologicalClassVO).collect(Collectors.toList());
	}
}
