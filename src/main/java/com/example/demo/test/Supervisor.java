package com.example.demo.test;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@DiscriminatorValue("Supervisor")
@Entity
public class Supervisor extends WorkshopRole{
	
	@OneToOne
	private WorkShop workshop;
	
	@OneToMany
	private List<GraderFormAnswerSheet> graderformanswersheet;

	public WorkShop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(WorkShop workshop) {
		this.workshop = workshop;
	}

	public List<GraderFormAnswerSheet> getGraderformanswersheet() {
		return graderformanswersheet;
	}

	public void setGraderformanswersheet(List<GraderFormAnswerSheet> graderformanswersheet) {
		this.graderformanswersheet = graderformanswersheet;
	}

	
	
}
