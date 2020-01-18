package com.example.demo.test;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class GroupFormAnswerSheet {
	
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	private GroupForm groupform;
	
	@ManyToOne
	private WorkshopGroup workshopgroup;
	
	@ElementCollection
	private List<String> answers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GroupForm getGroupform() {
		return groupform;
	}

	public void setGroupform(GroupForm groupform) {
		this.groupform = groupform;
	}

	public WorkshopGroup getWorkshopgroup() {
		return workshopgroup;
	}

	public void setWorkshopgroup(WorkshopGroup workshopgroup) {
		this.workshopgroup = workshopgroup;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
	
	
	
}
