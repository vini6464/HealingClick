package com.vinod.service;

import java.util.Collections;
import java.util.List;

import com.vinod.comparator.OrderComparator;
import com.vinod.dao.OrderDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Order;
import com.vinod.model.Review;

public class OrderService {
	OrderDao dao = new OrderDao();

	public int saveOrder(Order order) throws DaoException  {
		int i = dao.saveOrder(order);
		return i;
	}

	public void saveMedicine(long orderId, Medicine medicine) throws DaoException {
		dao.saveMedicine(orderId, medicine);
		
	}

	public void verifyOrder(int oId) throws DaoException {
		dao.verifyOrder(oId);
		
	}

	public void deliverOrder(int oId) throws DaoException {
		dao.deliverOrder(oId);
		
	}

	public List<Order> getAllUrgentOrder(Login login) throws DaoException {
		List<Order> orders =dao.getAllUrgentOrder(login);
		Collections.sort(orders, new OrderComparator());
		return orders;
	}

	public List<Medicine> getAllMedicinesByOrderId(long l) throws DaoException {
		List<Medicine> medicines = dao.getAllMedicinesByOrderId(l);
		return medicines;
	}

	public List<Order> getAllNormalOrder(Login login) throws DaoException {
		List<Order> orders =dao.getAllNormalOrder(login);
		Collections.sort(orders, new OrderComparator());
		return orders;
	}

	public List<Order> getAllDeliveredOrder(Login login) throws DaoException {
		List<Order> orders =dao.getAllDeliveredOrder(login);
		Collections.sort(orders, new OrderComparator());
		return orders;
	}

	public List<Order> getAllUrgentOrderForPharmacy(Login login) throws DaoException {
		List<Order> orders =dao.getAllUrgentOrderForPharmacy(login);
		Collections.sort(orders, new OrderComparator());
		return orders;
	}

	public List<Order> getAllNormalOrderForPharmacy(Login login) throws DaoException {
		List<Order> orders =dao.getAllNormalOrderForPharmacy(login);
		Collections.sort(orders, new OrderComparator());
		return orders;
	}

	public List<Order> getAllDeliveredOrderForPharmacy(Login login) throws DaoException {
		List<Order> orders =dao.getAllDeliveredOrderForPharmacy(login);
		Collections.sort(orders, new OrderComparator());
		return orders;
	}

	public List<Order> getAllMyOrderForPharmacy(Login login) throws DaoException {
		List<Order> orders =dao.getAllMyOrderForPharmacy(login);
		Collections.sort(orders, new OrderComparator());
		return orders;
	}

	public int cancelOrderDoctor(int oId ,String comment) throws DaoException {
		int i = dao.cancelOrderDoctor(oId , comment);
		return i;
	}

	public int cancelOrderPatient(int oId ,String comment) throws DaoException {
		int i = dao.cancelOrderPatient(oId , comment);
		return i;
	}

	public int cancelOrderPharmacy(int oId, int status ,String comment) throws DaoException {
		int i = dao.cancelOrderPharmacy(oId,status ,comment);
		return i;
	}

	public int saveReview(Review review) throws DaoException {
		int i = dao.saveReview(review);
		return i;
	}

	public int checkIfReviewExist(Review review) throws DaoException {
		int dup = dao.checkIfReviewExist(review);
		return dup;
	}


	public void updateReview(Review review) throws DaoException {
		dao.updateReview(review);
		
	}

	

}
