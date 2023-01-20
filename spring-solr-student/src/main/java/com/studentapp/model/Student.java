package com.studentapp.model;

import org.apache.solr.client.solrj.beans.Field;

public class Student {
	@Field
	private String name;
	@Field
	private String id;
	@Field
	private String department;
	@Field
	private String batch;
	@Field
	private String email;
	@Field
	private long mobileNum;
	@Field
	private String bloodGroup;
	
	public Student() {
		super();
	}

	public Student(String name, String id, String department, String batch, String email, long mobileNum,
			String bloodGroup) {
		super();
		this.name = name;
		this.id = id;
		this.department = department;
		this.batch = batch;
		this.email = email;
		this.mobileNum = mobileNum;
		this.bloodGroup = bloodGroup;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(long mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", department=" + department + ", batch=" + batch
				+ ", email=" + email + ", mobileNum=" + mobileNum + ", bloodGroup=" + bloodGroup + "]";
	}
	
	
}
