package com.vinod.model;

import java.sql.Time;
import java.sql.Timestamp;

public class Medicine {
	int id;
	int orderId;
	int prescriptionId;
	String name;
	int morning;
	int afternoon;
	int night;
	int quantity;
	double cost;
	Time mt;
	Time at;
	Time nt;
	int status;
	int patientId;
	String patientName;
	Timestamp prescribedDate;
	long mobile;
	int left ;
	
	
	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left = left;
	}

	public long getMobile() {
		return mobile;
	}

	public void setMobile(long mobile) {
		this.mobile = mobile;
	}

	public Timestamp getPrescribedDate() {
		return prescribedDate;
	}

	public void setPrescribedDate(Timestamp prescribedDate) {
		this.prescribedDate = prescribedDate;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	

	public Medicine() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	

	

	
	public Medicine( int id,int prescriptionId,String name, int quantity,
			int morning, int afternoon, int night, 
			Time mt, Time at, Time nt) {
		super();
		this.id=id;
		this.prescriptionId = prescriptionId;
		this.name = name;
		this.morning = morning;
		this.afternoon = afternoon;
		this.night = night;
		this.quantity = quantity;
		
		this.mt = mt;
		this.at = at;
		this.nt = nt;
	}

	public Time getMt() {
		return mt;
	}

	public void setMt(Time mt) {
		this.mt = mt;
	}

	public Time getAt() {
		return at;
	}

	public void setAt(Time at) {
		this.at = at;
	}

	public Time getNt() {
		return nt;
	}

	public void setNt(Time nt) {
		this.nt = nt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMorning() {
		return morning;
	}

	public void setMorning(int morning) {
		this.morning = morning;
	}

	public int getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(int afternoon) {
		this.afternoon = afternoon;
	}

	public int getNight() {
		return night;
	}

	public void setNight(int night) {
		this.night = night;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Medicine [id=" + id + ", orderId=" + orderId
				+ ", prescriptionId=" + prescriptionId + ", name=" + name
				+ ", morning=" + morning + ", afternoon=" + afternoon
				+ ", night=" + night + ", quantity=" + quantity + ", cost="
				+ cost + ", mt=" + mt + ", at=" + at + ", nt=" + nt + "]";
	}

	

	
	
	
}
