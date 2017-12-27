package com.engineering.ie.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.format.annotation.DateTimeFormat;

import com.engineering.ie.util.JobState;
import com.engineering.ie.vo.GeologicalSectionVO;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.TypeReferenceFactory;

@TypeDefs({ @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) })
@Entity
@Table(name = "jobs")
public class Job implements Serializable {
	private static final long serialVersionUID = -5375612530884300409L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private ZonedDateTime dateCreated = ZonedDateTime.now();

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull(message = "State can not be null!")
	private JobState state;

	@Column
	private byte[] xls;

	@Type(type = "jsonb", parameters = {
			@org.hibernate.annotations.Parameter(name = TypeReferenceFactory.FACTORY_CLASS, value = "com.engineering.ie.domain.GeologicalSectionsTypeReference") })
	@Column(columnDefinition = "jsonb")
	private List<GeologicalSectionVO> sections = new ArrayList<>();

	public Job() {
	}

	public Job(JobState state, byte[] xls, List<GeologicalSectionVO> sections) {
		super();
		this.state = state;
		this.xls = xls;
		this.sections = sections;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(ZonedDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public JobState getState() {
		return state;
	}

	public void setState(JobState state) {
		this.state = state;
	}

	public byte[] getXls() {
		return xls;
	}

	public void setXls(byte[] xls) {
		this.xls = xls;
	}

	public List<GeologicalSectionVO> getSections() {
		return sections;
	}

	public void setSections(List<GeologicalSectionVO> sections) {
		this.sections = sections;
	}

}
