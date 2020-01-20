package com.example.demo.test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@DiscriminatorValue("Supervisor")
@Entity
public class Supervisor extends WorkshopRole{

	@OneToMany (cascade = CascadeType.ALL)
	private List<User> users;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToOne(cascade = CascadeType.ALL)
	private WorkShop workshop;
	

	@OneToMany(cascade = CascadeType.ALL)
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
