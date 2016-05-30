package com.vinod.service;

import com.vinod.dao.RegisterDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Doctor;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;

public class RegisterService {
	RegisterDao dao = new RegisterDao();

	public int registerDoctor(Doctor doctor) throws DaoException {
		int i = dao.registerDoctor(doctor);
		
		return i;
	}

	public int registerPatient(Patient patient) throws DaoException {
		int i = dao.registerPatient(patient);
		return i;
	}

	public int registerPharmacy(Pharmacy pharmacy) throws DaoException {
		int i = dao.registerPharmacy(pharmacy);
		return i;
	}

	public int checkUserNameDoctor(String userName) throws DaoException {
		int i = dao.checkUserNameDoctor(userName);
		return i;
	}

	public int checkUserNamePatient(String userName) throws DaoException {
		int i = dao.checkUserNamePatient(userName);
		return i;
	}

	public int checkUserNamePharmacy(String userName) throws DaoException {
		int i = dao.checkUserNamePharmacy(userName);
		return i;
	}

	public int checkEmailDoctor(String email) throws DaoException {
		int i = dao.checkEmailDoctor(email);
		return i;
	}

	public int checkEmailPatient(String email) throws DaoException {
		int i = dao.checkEmailPatient(email);
		return i;
	}

	public int checkEmailPharmacy(String email) throws DaoException {
		int i = dao.checkEmailPharmacy(email);
		return i;
	}

	public int checkUserNameDoctor(long mobile) throws DaoException {
		int i = dao.checkUserNameDoctor(mobile);
		return i;
	}

	public int checkUserNamePatient(long mobile) throws DaoException {
		int i = dao.checkUserNamePatient(mobile);
		return i;
	}

	public int checkUserNamePharmacy(long mobile) throws DaoException {
		int i = dao.checkUserNamePharmacy(mobile);
		return i;
	}

}
