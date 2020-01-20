package com.example.demo.test;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
@DiscriminatorValue("AttendForm")
@Entity
public class AttendForm extends Form {
	
}
