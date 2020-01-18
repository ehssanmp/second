package com.example.demo.test;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class WorkshopGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToMany
	private List<Grader> graders ;
	
	@OneToMany
	private List<Attendant> attendant;
	
	@OneToMany
	private List<GraderFormAnswerSheet> groupformanswersheet;
	
	@ManyToOne
	private WorkShop workshop;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Grader> getGraders() {
		return graders;
	}

	public void setGraders(List<Grader> graders) {
		this.graders = graders;
	}

	public WorkShop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(WorkShop workshop) {
		this.workshop = workshop;
	}
	

}
