package com.example.demo.test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GraderFormAnswerSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Supervisor superVisor;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Grader grader;
	
	@ElementCollection
	private List<String> Answer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Supervisor getSuperVisor() {
		return superVisor;
	}

	public void setSuperVisor(Supervisor superVisor) {
		this.superVisor = superVisor;
	}

	public Grader getGrader() {
		return grader;
	}

	public void setGrader(Grader grader) {
		this.grader = grader;
	}

	public List<String> getAnswer() {
		return Answer;
	}

	public void setAnswer(List<String> answer) {
		Answer = answer;
	}
	
}
