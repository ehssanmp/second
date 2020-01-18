package com.example.demo.test;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@DiscriminatorValue("GroupForm")
@Entity
public class GroupForm extends Form {

	@OneToMany
	private List<GroupFormAnswerSheet> groupformanswersheet;

	public List<GroupFormAnswerSheet> getGroupformanswersheet() {
		return groupformanswersheet;
	}

	public void setGroupformanswersheet(List<GroupFormAnswerSheet> groupformanswersheet) {
		this.groupformanswersheet = groupformanswersheet;
	}
	
	
}
