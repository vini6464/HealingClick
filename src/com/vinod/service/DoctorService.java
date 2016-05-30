package com.vinod.service;

import java.util.Collections;
import java.util.List;

import com.vinod.comparator.DiseaseComparator;
import com.vinod.comparator.MedicineComparator;
import com.vinod.comparator.PrescriptionComparator;
import com.vinod.comparator.SymptomComparator;
import com.vinod.dao.DoctorDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Disease;
import com.vinod.model.Medicine;
import com.vinod.model.Post;
import com.vinod.model.Prescription;
import com.vinod.model.Symptom;



public class DoctorService {

	DoctorDao dao = new DoctorDao();
	public List<Symptom> getAllSymptoms() throws DaoException {
	
		List<Symptom> symptoms = dao.getAllSymptoms();
		Collections.sort(symptoms, new SymptomComparator());
		return symptoms;
	}

	public List<Disease> getAllDisesaes() throws DaoException {
		List<Disease> diseases = dao.getAllDiseases();
		Collections.sort(diseases, new DiseaseComparator());
		return diseases;
	}

	public List<Medicine> getAllMedicines() throws DaoException {
		List<Medicine> medicines = dao.getAllMedicines();
		Collections.sort(medicines, new MedicineComparator());
		return medicines;
	}

	public Medicine getMedicineCostById(int mId) throws DaoException {
		Medicine i = dao.getMedicineCostById(mId);
		return i;
	}

	public int savePrescription(Prescription prescription, int i) throws DaoException {
		int id = dao.savePrescription(prescription,i);
		return id;
	}

	public void savePrescriptionMedicine(Medicine medicine) throws DaoException {
		dao.savePrescriptionMedicine(medicine);
		
	}

	public List<Prescription> getAllPrescriptionByDocorId(int dId) throws DaoException {
		List<Prescription> prescriptions = dao.getAllPrescriptionByDocorId(dId);
		Collections.sort(prescriptions, new PrescriptionComparator());
		return prescriptions;
	}

	public void updatePatient(Prescription prescription, int i) throws DaoException {
		
		dao.updatePatient(prescription,i);
	}

	public Medicine getMedicineCostByName(String mId, int supplierId) throws DaoException {
		Medicine i = dao.getMedicineCostByName(mId , supplierId);
		return i;
	}

	public int getSymptomIdByName(String parameter) throws DaoException {
		int i = dao.getSymptomIdByName(parameter);
		return i;
	}

	public int getDiseaseIdByName(String parameter) throws DaoException {
		int i = dao.getDiseaseIdByName(parameter);
		return i;
	}

	public void savePost(Post post) throws DaoException {
		dao.savePost(post);
		
	}

	public Medicine getMedicineByName(String parameter) throws DaoException {
		Medicine i = dao.getMedicineByName(parameter);
		return i;
	}

}
