package com.example.demo.test;

import java.util.Date;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class AttendFormAnswerSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ElementCollection
	private List<String>  answers ;
	
	@DateTimeFormat
	private Date date;
	
	@ManyToOne
	private Grader grader;
	
	@ManyToOne
	private Attendant attendat;
	
	@ManyToOne
	private AttendForm attendantForm;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Grader getGrader() {
		return grader;
	}

	public void setGrader(Grader grader) {
		this.grader = grader;
	}

	public Attendant getAttendat() {
		return attendat;
	}

	public void setAttendat(Attendant attendat) {
		this.attendat = attendat;
	}

	public AttendForm getAttendantForm() {
		return attendantForm;
	}

	public void setAttendantForm(AttendForm attendantForm) {
		this.attendantForm = attendantForm;
	}
	
	
}
