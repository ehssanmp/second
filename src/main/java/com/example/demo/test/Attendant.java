package com.example.demo.test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@DiscriminatorValue("Attendant")
@Entity
public class Attendant extends WorkshopRole{

	private boolean IsPassed;
	
	private float Score;
	
	private int Absences;

	@ManyToOne(cascade = CascadeType.ALL)
	private WorkshopGroup groups;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<AttendFormAnswerSheet> attendformanswersheet;

	public boolean isIsPassed() {
		return IsPassed;
	}

	public void setIsPassed(boolean isPassed) {
		IsPassed = isPassed;
	}

	public float getScore() {
		return Score;
	}

	public void setScore(float score) {
		Score = score;
	}

	public int getAbsences() {
		return Absences;
	}

	public void setAbsences(int absences) {
		Absences = absences;
	}

	public WorkshopGroup getGroups() {
		return groups;
	}

	public void setGroups(WorkshopGroup groups) {
		this.groups = groups;
	}

	public List<AttendFormAnswerSheet> getAttendformanswersheet() {
		return attendformanswersheet;
	}

	public void setAttendformanswersheet(List<AttendFormAnswerSheet> attendformanswersheet) {
		this.attendformanswersheet = attendformanswersheet;
	}

	
}
