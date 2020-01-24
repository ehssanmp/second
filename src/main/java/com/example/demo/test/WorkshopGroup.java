package com.example.demo.test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class WorkshopGroup {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<GroupFormAnswerSheet> groupformanswersheet;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn
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


	public List<GroupFormAnswerSheet> getGroupformanswersheet() {
		return groupformanswersheet;
	}

	public void setGroupformanswersheet(List<GroupFormAnswerSheet> groupformanswersheet) {
		this.groupformanswersheet = groupformanswersheet;
	}

	

}
