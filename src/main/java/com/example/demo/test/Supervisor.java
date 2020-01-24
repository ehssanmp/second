package com.example.demo.test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<GraderFormAnswerSheet> graderformanswersheet;

	public List<GraderFormAnswerSheet> getGraderformanswersheet() {
		return graderformanswersheet;
	}

	public void setGraderformanswersheet(List<GraderFormAnswerSheet> graderformanswersheet) {
		this.graderformanswersheet = graderformanswersheet;
	}

	
	
}
