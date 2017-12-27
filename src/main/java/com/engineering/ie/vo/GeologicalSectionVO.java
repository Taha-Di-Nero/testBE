package com.engineering.ie.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeologicalSectionVO implements Serializable {
	private static final long serialVersionUID = -3701127296043990028L;

	@JsonIgnore
	private Long id;

	@NotNull(message = "Name can not be null!")
	private String name;

	private List<GeologicalClassVO> classes = new ArrayList<>();

	private GeologicalSectionVO() {
	}

	private GeologicalSectionVO(Long id, String name, List<GeologicalClassVO> classes) {
		this.id = id;
		this.name = name;
		this.classes = classes;
	}

	public static GeologicalSectionVOBuilder newBuilder() {
		return new GeologicalSectionVOBuilder();
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<GeologicalClassVO> getClasses() {
		return classes;
	}

	public static class GeologicalSectionVOBuilder {
		private Long id;
		private String name;
		private List<GeologicalClassVO> classes = new ArrayList<>();

		public GeologicalSectionVOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public GeologicalSectionVOBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public GeologicalSectionVOBuilder setClasses(List<GeologicalClassVO> classes) {
			this.classes = classes;
			return this;
		}

		public GeologicalSectionVOBuilder addClass(GeologicalClassVO clazz) {
			this.classes.add(clazz);
			return this;
		}

		public GeologicalSectionVO createGeologicalSectionVO() {
			return new GeologicalSectionVO(id, name, classes);
		}

	}
}
