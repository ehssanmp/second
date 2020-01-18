package com.example.demo.test;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class UserRoleRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer iD;
	
	@DateTimeFormat
	private Date Start;
	
	@DateTimeFormat
	private Date End;
	
	private boolean Active;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private WorkshopRole workshoprole;

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public Date getStart() {
		return Start;
	}

	public void setStart(Date start) {
		Start = start;
	}

	public Date getEnd() {
		return End;
	}

	public void setEnd(Date end) {
		End = end;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public WorkshopRole getWorkshoprole() {
		return workshoprole;
	}

	public void setWorkshoprole(WorkshopRole workshoprole) {
		this.workshoprole = workshoprole;
	}	
}
