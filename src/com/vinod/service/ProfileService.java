package com.vinod.service;

import com.vinod.dao.ProfileDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Doctor;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;

public class ProfileService {

	ProfileDao dao = new ProfileDao();

	public int updateDoctorProfile(Doctor doctor) throws DaoException {
		int i = dao.updateDoctorProfile(doctor);
		return i;
	}

	public int updatePatientProfile(Patient patient) throws DaoException {
		int i = dao.updatePatientProfile(patient);
		return i;
	}

	public int updatePharmacyProfile(Pharmacy pharmacy) throws DaoException {
		int i = dao.updatePharmacyProfile(pharmacy);
		return i;
	}
}
