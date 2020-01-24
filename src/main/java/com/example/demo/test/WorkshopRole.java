package com.example.demo.test;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public class WorkshopRole {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected int iD;

	public int getiD() {
		return iD;
	}

	public void setiD(int iD) {
		this.iD = iD;
	}
}
