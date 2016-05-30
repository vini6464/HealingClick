package com.vinod.service;

import java.util.Collections;
import java.util.List;

import com.vinod.comparator.DoctorComparator;
import com.vinod.comparator.PatientComparator;
import com.vinod.comparator.PharmacyComparator;
import com.vinod.dao.SearchDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;

public class SearchService {

	SearchDao dao = new SearchDao();
	public List<Doctor> getAllDoctorsForSearch(String text,int id) throws DaoException {
		List<Doctor> doctors = dao.getAllDoctorsForDoctorSearch(text,id);
		Collections.sort(doctors, new DoctorComparator());
		return doctors;
	}

	public List<Patient> getAllPatientsForSearch(String text,int id) throws DaoException {
		List<Patient> patients = dao.getAllPatientsForSearch(text,id);
		Collections.sort(patients, new PatientComparator());
		return patients;
	}

	public List<Pharmacy> getAllPharmaciesForSearch(String text,int id) throws DaoException {
		List<Pharmacy> pharmacies = dao.getAllPharmaciesForSearch(text,id);
		Collections.sort(pharmacies, new PharmacyComparator());
		return pharmacies;
	}

	public int requestSentByDoctor(int Pid, int Did) throws DaoException {
		int i = dao.requestSentByDoctor(Pid,Did);
		return i;
	}

	public int requestSentByPatient(int Did, int Pid) throws DaoException {
		int i = dao.requestSentByPatient(Did,Pid);
		return i;
	}

	public int doctorPharmacy(int pId, int dId) throws DaoException {
		int i = dao.doctorPharmacy(pId,dId);
		return i;
	}

	public int patientPharmacy(int pId, int patId) throws DaoException {
		int i = dao.patientPharmacy(pId,patId);
		return i;
	}

	public int acceptByDoctor(int pId, int dId) throws DaoException {
		int i = dao.acceptByDoctor(pId,dId);
		return i;
	}

	public int acceptByPatient(int dId, int pId) throws DaoException {
		int i = dao.acceptByPatient(dId,pId);
		return i;
	}

	public int declineByDoctor(int pId, int dId) throws DaoException {
		int i = dao.declineByDoctor(pId,dId);
		return i;
	}

	public int declineByPatient(int dId, int pId) throws DaoException {
		int i = dao.declineByPatient(dId,pId);
		return i;
	}

	public List<Patient> getAllPatientsForDoctorSearch(String text, int id) throws DaoException{
		List<Patient> patients = dao.getAllPatientsForDoctorSearch(text,id);
		Collections.sort(patients, new PatientComparator());
		return patients;
	}

	public List<Pharmacy> getAllPharmaciesForDoctorSearch(String text, int id) throws DaoException {
		List<Pharmacy> pharmacies = dao.getAllPharmaciesForDoctorSearch(text,id);
		Collections.sort(pharmacies, new PharmacyComparator());
		return pharmacies;
	}

	public List<Doctor> getAllDoctorsForPatientSearch(String text, int id) throws DaoException {
		List<Doctor> doctors = dao.getAllDoctorsForPatientSearch(text,id);
		Collections.sort(doctors, new DoctorComparator());
		return doctors;
	}

	public List<Pharmacy> getAllPharmaciesForPatientSearch(String text, int id) throws DaoException {
		List<Pharmacy> pharmacies = dao.getAllPharmaciesForPatientSearch(text,id);
		Collections.sort(pharmacies, new PharmacyComparator());
		return pharmacies;
	}

	public int pharmacyPharmacy(int pId1, int pId2) throws DaoException {
		int i = dao.pharmacyPharmacy(pId1,pId2);
		return i;	}

	public List<Doctor> getAllDoctorsForSearch(String text) throws DaoException {
		List<Doctor> doctors = dao.getAllDoctorsForSearch(text);
		Collections.sort(doctors, new DoctorComparator());
		return doctors;
	}

	public List<Patient> getAllPatientsForSearch(String text) throws DaoException {
		List<Patient> patients = dao.getAllPatientsForSearch(text);
		Collections.sort(patients, new PatientComparator());
		return patients;
	}

	public List<Medicine> searchMedicines(String medicine) throws DaoException {
		List<Medicine> medicines = dao.searchMedicines(medicine);
		return medicines;
	}

	public List<Doctor> getAllDoctorsForDoctorSearch(String text, int id) throws DaoException {
		List<Doctor> doctors = dao.getAllDoctorsForDoctorSearch(text,id);
		return doctors;
	}

	public int deletePatientDoctorConnection(int pId, int dId) throws DaoException {
		int i = dao.deletePatientDoctorConnection(pId,dId);
		return i;
	}

	public int deleteDoctorPharmacy(int dId, int phId) throws DaoException {
		int i = dao.deleteDoctorPharmacy(dId , phId);
		return i;
	}

	public int deletePatientPharmacy(int pId, int phId) throws DaoException {
		int i = dao.deletePatientPharmacy(pId , phId);
		return i;
	}

	public int deletePharmacyPharmacy(int phId1, int phId2) throws DaoException {
		int i = dao.deletePharmacyPharmacy(phId1 , phId2);
		return i;
	}

	public int addDoctorDoctorConnection(int dId1, int dId2) throws DaoException {
		int i = dao.addDoctorDoctorConnection(dId1 , dId2);
		return i;
	}

	public int deleteDoctorDoctorConnection(int dId1, int dId2) throws DaoException {
		int i = dao.deleteDoctorDoctorConnection(dId1 , dId2);
		return i;
	}

	public int acceptDoctor(int dId1, int dId2) throws DaoException {
		int i = dao.acceptDoctor(dId1 , dId2);
		return i;
	}

	public List<Medicine> getSearchedMedicines(Login login, String text) throws DaoException {
		List<Medicine> pharmacyMedicines = dao.getSearchedMedicines(login , text);
		return pharmacyMedicines;
	}

	public void addMedicine(Login login, String name) throws DaoException {
		dao.addMedicine(login , name);
		
	}

	public void updateMedicine(Login login, String name, double cost) throws DaoException {
		dao.updateMedicine(login , name ,cost);
		
	}

	public void deleteMedicine(Login login, String name) throws DaoException {
		dao.deleteMedicine(login , name);
		
	}

	public Medicine getMedicineById(int mId) throws DaoException {
		Medicine m = dao.getMedicineById(mId);
		return m;
	}

	



}
