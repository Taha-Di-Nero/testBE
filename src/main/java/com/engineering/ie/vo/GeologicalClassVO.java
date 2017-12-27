package com.engineering.ie.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeologicalClassVO implements Serializable {
	private static final long serialVersionUID = 3984319948860057297L;

	@JsonIgnore
	private Long id;

	@NotNull(message = "Code can not be null!")
	private String code;

	@NotNull(message = "Name can not be null!")
	private String name;

	private GeologicalClassVO() {
	}

	private GeologicalClassVO(Long id, String code, String name) {
		this.id = id;
		this.code = code;
		this.name = name;
	}

	public static GeologicalClassVOBuilder newBuilder() {
		return new GeologicalClassVOBuilder();
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public static class GeologicalClassVOBuilder {
		private Long id;
		private String code;
		private String name;

		public GeologicalClassVOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public GeologicalClassVOBuilder setCode(String code) {
			this.code = code;
			return this;
		}

		public GeologicalClassVOBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public GeologicalClassVO createGeologicalClassVO() {
			return new GeologicalClassVO(id, code, name);
		}

	}
}
