package com.example.demo.test;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.tomcat.jni.Time;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class WorkShop {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "start")
	private Date start;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "end")
	private Date end;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "starttime")
	private Date starttime;
	
	@Temporal(TemporalType.TIME)
	@Column(name = "endtime")
	private Date endtime;
	
	@Column(name = "days")
	private String days;
	
	@Column(name = "adress")
	private String adress;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "paymentcounts")
	private int paymentcounts;
	
	@Column(name = "payments")
	private int payments;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Form> form;

	@OneToOne(cascade = CascadeType.ALL)
	private Supervisor supervisor;
	public List<Form> getForm() {
		return form;
	}

	public void setForm(List<Form> form) {
		this.form = form;
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
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPaymentcounts() {
		return paymentcounts;
	}

	public void setPaymentcounts(int paymentcounts) {
		this.paymentcounts = paymentcounts;
	}

	public int getPayments() {
		return payments;
	}

	public void setPayments(int payments) {
		this.payments = payments;
	}	
	
	
}
