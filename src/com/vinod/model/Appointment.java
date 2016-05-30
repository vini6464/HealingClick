package com.vinod.model;

import java.util.Date;

public class Appointment {

	
	long id;
	String title;
	String description;
	String start;
	String end;
	
	long doctorId;
	String doctorName;
	long patientId;
	String patientName;
	long pharmacyId;
	String pharmacyName;
	
	int status;
	String symptom;
	
	Date day;
	
	String startTime;
	String endTime;
	
	
	
	
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getSymptom() {
		return symptom;
	}
	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Appointment() {
		super();
		
	}
	public Appointment(long id, String title, String description, String start,
			String end) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.start = start;
		this.end = end;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public long getPatientId() {
		return patientId;
	}
	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public long getPharmacyId() {
		return pharmacyId;
	}
	public void setPharmacyId(long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}
	public String getPharmacyName() {
		return pharmacyName;
	}
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", title=" + title + ", description="
				+ description + ", start=" + start + ", end=" + end
				+ ", doctorId=" + doctorId + ", doctorName=" + doctorName
				+ ", patientId=" + patientId + ", patientName=" + patientName
				+ ", pharmacyId=" + pharmacyId + ", pharmacyName="
				+ pharmacyName + ", status=" + status + ", symptom=" + symptom
				+ ", day=" + day + ", startTime=" + startTime + ", endTime="
				+ endTime + "]";
	}
	

	
	
	
}
