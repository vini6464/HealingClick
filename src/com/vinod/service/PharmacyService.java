package com.vinod.service;

import java.util.Collections;
import java.util.List;

import com.vinod.comparator.MedicineComparator;
import com.vinod.dao.PharmacyDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Order;



public class PharmacyService {

	PharmacyDao dao = new PharmacyDao();

	public Order getOrderById(int oId) throws DaoException {
		Order order = dao.getOrderById(oId);
		return order;
	}

	public List<Medicine> getOrderedMedicinesByOrderId(int oId) throws DaoException {
		List<Medicine> medicines=dao.getOrderedMedicinesByOrderId(oId);
		Collections.sort(medicines, new MedicineComparator());
		return medicines;
	}

	public void updateCost(double deliveryCharge, double discount, Login login) throws DaoException {
		dao.updateCost(deliveryCharge , discount ,login);
		
	}

	public List<Medicine> getPharmacyMedicines(Login login) throws DaoException {
		List<Medicine> pharmacyMedicines = dao.getPharmacyMedicines(login);
		return pharmacyMedicines;
	}

	public void removeOldMedicines(Login login) throws DaoException {
		dao.removeOldMedicines(login);
		
	}

	public void updateNewMedicines(Login login, List<Medicine> medicines) throws DaoException {
		dao.updateNewMedicines(login , medicines);
		
	}
}
