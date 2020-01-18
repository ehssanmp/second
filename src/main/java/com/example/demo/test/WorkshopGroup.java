package com.example.demo.test;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class WorkshopGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToOne
	private Grader graders ;

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


	public WorkShop getWorkshop() {
		return workshop;
	}

	public void setWorkshop(WorkShop workshop) {
		this.workshop = workshop;
	}

	public List<Attendant> getAttendant() {
		return attendant;
	}

	public void setAttendant(List<Attendant> attendant) {
		this.attendant = attendant;
	}

	public List<GraderFormAnswerSheet> getGroupformanswersheet() {
		return groupformanswersheet;
	}

	public void setGroupformanswersheet(List<GraderFormAnswerSheet> groupformanswersheet) {
		this.groupformanswersheet = groupformanswersheet;
	}

	public Grader getGraders() {
		return graders;
	}

	public void setGraders(Grader graders) {
		this.graders = graders;
	}
	

}
