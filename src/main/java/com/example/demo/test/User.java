package com.example.demo.test;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity // This tells Hibernate to make a table out of this class
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  
  @Column(name = "name")
  private String name;
  
  @Column(name = "family_name")
  private String FamilyName;
  
  @Column(name="user_name")
  private String UserName;
  
  @Column(name="password")
  private String Password;
  
  @Column(name = "national_code")
  private String NationalCode;
  
  @Column(name="phone_number")
  private String PhoneNumber;
  
  @Column(name = "age")
  private String Age;
  
  @Column(name="sex")
  private String Sex;
  
  @Column(name = "nationality")
  private String Nationality;
  
  @Column(name= "last_dgree_state")
  private String LastDegreeState; 
  
  @OneToMany
  private List<UserRoleRelation> userrelation;
  
  public User(String name , String username ,String familyname ,String password , String nationalcode ,String phonenumber,String age ,String sex,String natinality,String lastdegreestate, String LastDegreeState) {
	 this.name = name;
	 this.FamilyName = familyname;
	 this.Age = age ;
	 this.LastDegreeState= LastDegreeState;
	 this.NationalCode=nationalcode;
	 this.Nationality= natinality;
	 this.Password=password;
	 this.PhoneNumber= phonenumber;
	 this.Sex = sex;
	 this.UserName = username;
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

public String getFamilyName() {
	return FamilyName;
}

public void setFamilyName(String familyName) {
	FamilyName = familyName;
}

public String getUserName() {
	return UserName;
}

public void setUserName(String userName) {
	UserName = userName;
}

public String getPassword() {
	return Password;
}

public void setPassword(String password) {
	Password = password;
}

public String getNationalCode() {
	return NationalCode;
}

public void setNationalCode(String nationalCode) {
	NationalCode = nationalCode;
}

public String getPhoneNumber() {
	return PhoneNumber;
}

public void setPhoneNumber(String phoneNumber) {
	PhoneNumber = phoneNumber;
}

public String getAge() {
	return Age;
}

public void setAge(String age) {
	Age = age;
}

public String getSex() {
	return Sex;
}

public void setSex(String sex) {
	Sex = sex;
}

public String getNationality() {
	return Nationality;
}

public void setNationality(String nationality) {
	Nationality = nationality;
}

public String getLastDegreeState() {
	return LastDegreeState;
}

public void setLastDegreeState(String lastDegreeState) {
	LastDegreeState = lastDegreeState;
}

public List<UserRoleRelation> getUserrelation() {
	return userrelation;
}

public void setUserrelation(List<UserRoleRelation> userrelation) {
	this.userrelation = userrelation;
}
  
  
}