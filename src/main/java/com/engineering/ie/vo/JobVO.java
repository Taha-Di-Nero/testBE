package com.engineering.ie.vo;

import com.engineering.ie.util.JobState;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobVO implements Serializable {
	private static final long serialVersionUID = 3984319948860057297L;

	@JsonIgnore
	private Long id;

	@NotNull(message = "State can not be null!")
	private JobState state;

	private List<GeologicalSectionVO> sections = new ArrayList<>();

	private JobVO() {
	}

	private JobVO(Long id, JobState state, List<GeologicalSectionVO> sections) {
		this.id = id;
		this.state = state;
		this.sections = sections;
	}

	public static JobVOBuilder newBuilder() {
		return new JobVOBuilder();
	}

	@JsonProperty
	public Long getId() {
		return id;
	}

	public JobState getState() {
		return state;
	}

	public List<GeologicalSectionVO> getSections() {
		return sections;
	}

	public static class JobVOBuilder {
		private Long id;
		private JobState state;
		private List<GeologicalSectionVO> sections;

		public JobVOBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public JobVOBuilder setState(JobState state) {
			this.state = state;
			return this;
		}

		public JobVOBuilder setSections(List<GeologicalSectionVO> sections) {
			this.sections = sections;
			return this;
		}

		public JobVO createJobVO() {
			return new JobVO(id, state, sections);
		}

	}
}
