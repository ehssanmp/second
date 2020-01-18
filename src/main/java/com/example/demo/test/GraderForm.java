package com.example.demo.test;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@DiscriminatorValue("GraderForm")
@Entity
public class GraderForm extends Form {

	@OneToMany
	private List<GraderFormAnswerSheet> graderformanswersheet;

	public List<GraderFormAnswerSheet> getGraderformanswersheet() {
		return graderformanswersheet;
	}

	public void setGraderformanswersheet(List<GraderFormAnswerSheet> graderformanswersheet) {
		this.graderformanswersheet = graderformanswersheet;
	}

	
	
}
