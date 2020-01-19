package com.example.demo.test;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@DiscriminatorValue("Supervisor")
@Entity
public class Supervisor extends WorkshopRole{

	@OneToMany 
	private List<User> users;
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@OneToOne
	private WorkShop workshop;
	
	@Column(name = "idName")
	private int idName;
	
	public int getIdName() {
		return idName;
	}

	public void setIdName(int idName) {
		this.idName = idName;
	}

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
