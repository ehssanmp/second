package com.example.demo.test;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE)
  private Integer id;
  
  
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "familyname")
  private String familyname;
  
  @Column(name="username")
  private String username;
  
  @Column(name="password")
  private String password;
  
  @Column(name = "nationalcode")
  private String nationalcode;
  
  @Column(name="phonenumber")
  private String phonenumber;
  
  @Column(name = "age")
  private String age;
  
  @Column(name="sex")
  private String sex;
  
  @Column(name = "nationality")
  private String nationality;
  
  @Column(name= "lastdegreestate")
  private String lastdegreestate; 
  
  @OneToMany
  private List<UserRoleRelation> userrelation;

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

public String getFamilyname() {
	return familyname;
}

public void setFamilyname(String familyname) {
	this.familyname = familyname;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getNationalcode() {
	return nationalcode;
}

public void setNationalcode(String nationalcode) {
	this.nationalcode = nationalcode;
}

public String getPhonenumber() {
	return phonenumber;
}

public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}

public String getAge() {
	return age;
}

public void setAge(String age) {
	this.age = age;
}

public String getSex() {
	return sex;
}

public void setSex(String sex) {
	this.sex = sex;
}

public String getNationality() {
	return nationality;
}

public void setNationality(String nationality) {
	this.nationality = nationality;
}

public String getLastdegreestate() {
	return lastdegreestate;
}

public void setLastdegreestate(String lastdegreestate) {
	this.lastdegreestate = lastdegreestate;
}

public List<UserRoleRelation> getUserrelation() {
	return userrelation;
}

public void setUserrelation(List<UserRoleRelation> userrelation) {
	this.userrelation = userrelation;
}


  


}