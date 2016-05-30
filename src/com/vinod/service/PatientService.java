package com.vinod.service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.vinod.comparator.PrescriptionComparator;
import com.vinod.dao.PatientDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Medicine;
import com.vinod.model.Prescription;
import com.vinod.model.Vaccination;

public class PatientService {
PatientDao dao = new PatientDao();

public void insertAppointmentNotification(String string, int dId, int pId, int eId) {
	
	
}

public void deleteAppointmentNotification(String string, int dId, int pId,int eId) {
	
	
}

public void updateAppointmentNotification(String string, int dId, int pId,int eId) {
	
	
}

public Prescription getPrescriptionById(int prescriptionId) throws DaoException {
	Prescription prescription = dao.getPrescriptionById(prescriptionId);
	return prescription;
}

public String getSymptomNameById(long s1) throws DaoException {
	String name = dao.getSymptomNameById(s1);
	return name;
}

public String getDiseaseNameById(int d1) throws DaoException {
	String name = dao.getDiseaseNameById(d1);
	return name;
}

public Medicine getPrescribedMedicineById(Prescription p, int m,int sort) throws DaoException {
	Medicine medicine = dao.getPrescribedMedicineById(p.getId(),m);
	if(sort==1)
	{
		java.sql.Date d = new java.sql.Date(p.getUpdatedtime().getTime()); 
		int day =daysBetween(new Date(),d);
		System.out.println(day);
		if(day>0 )
		{
			
			System.out.println(day);
			int qty = medicine.getQuantity();
			while(day>0)
			{
				if(medicine.getMorning()==1)
				{
					qty--;
				}
				
				if(medicine.getAfternoon()==1)
				{
					qty--;
				}
				
				if(medicine.getNight()==1)
				{
					qty--;
				}
				day--;
			}
			if(qty<=0)
			{
				medicine.setQuantity(0);
			}
			else
			{
				medicine.setQuantity(qty);
			}
			
		}
	}
	return medicine;
}

public List<Prescription> getAllPrescriptionByPatientId(int pId) throws DaoException {
	List<Prescription> prescriptions = dao.getAllPrescriptionByPatientId(pId);
	Collections.sort(prescriptions, new PrescriptionComparator());
	return prescriptions;
}

public void removeNotification(int prescriptionId) throws DaoException {
	dao.removeNotification(prescriptionId);
	
}
public int daysBetween(Date d1, Date d2){
    return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
}

public void saveVaccine(int patientId, int id) throws DaoException {
	dao.saveVaccine(patientId,id);
	
}

public void removeVaccine(int patientId, int id) throws DaoException {
	dao.removeVaccine(patientId,id);
	
}

public void addVaccination(Vaccination vaccination) throws DaoException {
	dao.addVaccination(vaccination);
	
}

public void falseVaccine(int patientId, int id) throws DaoException {
	dao.falseVaccine(patientId,id);
	
}

public void trueVaccine(int patientId, int id) throws DaoException {
	dao.trueVaccine(patientId,id);
	
}

public void deleteVaccine(int patientId, int id) throws DaoException {
	dao.deleteVaccine(patientId,id);
	
}
}
