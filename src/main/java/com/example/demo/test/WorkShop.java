package com.example.demo.test;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.tomcat.jni.Time;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class WorkShop {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "Name")
	private String Name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "Start")
	private Date Start;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "End")
	private Date End;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "StartTime")
	private Date StartTime;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "EndTime")
	private Date EndTime;
	
	@Column(name = "Days")
	private String Days;
	
	@Column(name = "Adress")
	private String Adress;
	
	@Column(name = "Price")
	private int Price;
	
	@Column(name = "PaymentCounts")
	private int PaymentCounts;
	
	@Column(name = "Payments")
	private int Payments;
	
	@OneToMany
	private List<Form> form;
	
	@OneToMany
	private List<WorkshopGroup> EventGroup;

	@OneToOne
	private Supervisor supervisor;
	public List<Form> getForm() {
		return form;
	}

	public void setForm(List<Form> form) {
		this.form = form;
	}

	public List<WorkshopGroup> getEventGroup() {
		return EventGroup;
	}

	public void setEventGroup(List<WorkshopGroup> eventGroup) {
		EventGroup = eventGroup;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

	public Date getStartTime() {
		return StartTime;
	}

	public void setStartTime(Date startTime) {
		StartTime = startTime;
	}

	public Date getEndTime() {
		return EndTime;
	}

	public void setEndTime(Date endTime) {
		EndTime = endTime;
	}

	public String getDays() {
		return Days;
	}

	public void setDays(String days) {
		Days = days;
	}

	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public int getPaymentCounts() {
		return PaymentCounts;
	}

	public void setPaymentCounts(int paymentCounts) {
		PaymentCounts = paymentCounts;
	}

	public int getPayments() {
		return Payments;
	}

	public void setPayments(int payments) {
		Payments = payments;
	}
	
	
	
}
