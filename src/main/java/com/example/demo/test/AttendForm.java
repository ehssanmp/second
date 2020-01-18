package com.example.demo.test;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
@DiscriminatorValue("AttendForm")
@Entity
public class AttendForm extends Form {
	
	@OneToMany
	private List<AttendFormAnswerSheet> attendformanswersheet;

	public List<AttendFormAnswerSheet> getAttendformanswersheet() {
		return attendformanswersheet;
	}

	public void setAttendformanswersheet(List<AttendFormAnswerSheet> attendformanswersheet) {
		this.attendformanswersheet = attendformanswersheet;
	}

	
	
}
