package com.vinod.model;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

public class Vaccination {

	long patientId;
	long id;
	String name;
	String patientName;
	Date duedate;
	Time time;
	Timestamp creationdate;
	int status ;
	long mobile;
	
	public Vaccination() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getPatientName() {
		return patientName;
	}


	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public long getMobile() {
		return mobile;
	}


	public void setMobile(long mobile) {
		this.mobile = mobile;
	}


	public Vaccination(long patientId, String name, Date duedate, Time time) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.duedate = duedate;
		this.time = time;
	}

	

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDuedate() {
		return duedate;
	}
	public void setDuedate(Date duedate) {
		this.duedate = duedate;
	}
	public Timestamp getCreationdate() {
		return creationdate;
	}
	public void setCreationdate(Timestamp creationdate) {
		this.creationdate = creationdate;
	}
	@Override
	public String toString() {
		return "Vaccination [patientId=" + patientId + ", id=" + id + ", name="
				+ name + ", duedate=" + duedate + ", creationdate="
				+ creationdate + "]";
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
