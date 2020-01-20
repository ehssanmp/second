package com.example.demo.test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@DiscriminatorValue("Grader")
@Entity
public class Grader extends WorkshopRole{

	@ManyToOne(cascade = CascadeType.ALL)
	private WorkshopGroup group;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<GraderFormAnswerSheet> graderformanswersheet;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<AttendFormAnswerSheet> attendformanswersheet;

	public WorkshopGroup getGroup() {
		return group;
	}

	public void setGroup(WorkshopGroup group) {
		this.group = group;
	}

	public List<GraderFormAnswerSheet> getGraderformanswersheet() {
		return graderformanswersheet;
	}

	public void setGraderformanswersheet(List<GraderFormAnswerSheet> graderformanswersheet) {
		this.graderformanswersheet = graderformanswersheet;
	}

	public List<AttendFormAnswerSheet> getAttendformanswersheet() {
		return attendformanswersheet;
	}

	public void setAttendformanswersheet(List<AttendFormAnswerSheet> attendformanswersheet) {
		this.attendformanswersheet = attendformanswersheet;
	}

	

	
}
