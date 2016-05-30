package com.vinod.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.vinod.comparator.CommentComparator;
import com.vinod.comparator.ForumPostComparator;
import com.vinod.dao.AdminDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Comment;
import com.vinod.model.Doctor;
import com.vinod.model.ForumPost;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Prescription;
import com.vinod.model.User;

public class AdminService {

	AdminDao dao = new AdminDao();

	public List<Doctor> getAllNotVerifiedDoctors() throws DaoException {
		List<Doctor> doctors = dao.getAllNotVerifiedDoctors();
		return doctors;
	}

	public int getTotalDoctorCount() throws DaoException {
		int count = dao.getTotalDoctorCount();
		return count;
	}

	public int getTotalPatientCount() throws DaoException {
		int count = dao.getTotalPatientCount();
		return count;
	}

	public int getTotalPharmacyCount() throws DaoException {
		int count = dao.getTotalPharmacyCount();
		return count;
	}

	public int getTotalCount() throws DaoException {
		int count = dao.getTotalDoctorCount() + dao.getTotalPharmacyCount() + dao.getTotalPatientCount();
		return count;
	}

	public int getPrescriptionCountForDoctor(int id) throws DaoException {
		int prescriptionCount = dao.getPrescriptionCountForDoctor(id);
		return prescriptionCount;
	}

	public int getOrderCountForDoctor(int id) throws DaoException {
		int orderCount = dao.getOrderCountForDoctor(id);
		return orderCount;
	}

	public int getOrderCountForPatient(int id) throws DaoException {
		int prescriptionCount = dao.getPrescriptionCountForPatient(id);
		
		return prescriptionCount;
	}

	public int getPrescriptionCountForPatient(int id) throws DaoException {
		int orderCount = dao.getOrderCountForPatient(id);
		return orderCount;
	}

	public int getOrderReceivedCount(int id) throws DaoException {
		int orderReceived = dao.getOrderReceivedCount(id);
		
		return orderReceived;
	}

	public int getOrderMadeCount(int id) throws DaoException {
		int orderMade = dao.getOrderMadeCount(id);
		return orderMade;
	}

	public List<Doctor> getDoctors(String text) throws DaoException {
		List<Doctor> doctors = dao.getDoctors(text);
		return doctors;
	}

	public List<Patient> getPatients(String text) throws DaoException {
		List<Patient> patients = dao.getPatients(text);
		return patients;
	}

	public List<Pharmacy> getPharmacies(String text) throws DaoException {
		List<Pharmacy> pharmacies = dao.getPharmacies(text);
		return pharmacies;
	}

	public void activateDoctor(int id) throws DaoException {
		dao.activateDoctor(id);
		
	}

	public void deleteAccount(int id, String type) throws DaoException {
		dao.deleteAccount(id,type);
		
	}

	public List<Order> getSearchedOrders(String query) throws DaoException {
		List<Order> orders = dao.getSearchedOrders(query );
		return orders;
	}

	public List<Pharmacy> getAllPharmacies() throws DaoException {
		List<Pharmacy> pharmacies = dao.getAllPharmacies();
		return pharmacies;
	}

	public List<Doctor> getAllDoctors() throws DaoException {
		List<Doctor> doctors = dao.getAllDoctors();
		return doctors;
	}

	public List<Prescription> getSearchedPrescriptionss(String query) throws DaoException {
		List<Prescription> prescriptions = dao.getSearchedPrescriptionss(query);
		return prescriptions;
	}

	public List<Patient> getAllPatients() throws DaoException {
		List<Patient> patients = dao.getAllPatients();
		return patients;
	}

	public int getOrders(int orderCount) throws DaoException {
		return dao.getOrders(orderCount);
		
	}

	public int getPrescriptions(int prescriptionCount) throws DaoException {
		return dao.getPrescriptions(prescriptionCount);
		
		
	}

	public int  getMonthDoctorCount() throws DaoException {
		int count = dao.getMonthDoctorCount();
		return count;
	}

	public int getMonthPatientCount() throws DaoException {
		int count = dao.getMonthPatientCount();
		return count;
	}

	public int getMonthPharmacyCount() throws DaoException {
		int count = dao.getMonthPharmacyCount();
		return count;
	}

	public int getMonthOrder() throws DaoException {
		return dao.getMonthOrder();
	}

	public int getMonthPrescription() throws DaoException {
		return dao.getMonthPrescription();
	}

	public List<Pharmacy> getAllNotVerifiedPharmacies() throws DaoException {
		List<Pharmacy> pharmacies;
		pharmacies = dao.getAllNotVerifiedPharmacies();
		return pharmacies;
	}

	public void activatePharmacy(int id) throws DaoException {
		dao.activatePharmacy(id);
		
	}

	public List<User> getSearchedUsers(String query, int type) throws DaoException {
		List<User> users = new ArrayList<User>();
		users = dao.getSearchedUsers(query , type);
		return users;
	}

	public List<ForumPost> getSearchedPosts(String query , int type) throws DaoException {
		List<ForumPost> posts = dao.getSearchedPosts(query,type);
		Collections.sort(posts, new ForumPostComparator());
		return posts;
	}

	public void deletePost(String query) throws DaoException {
		dao.deletePost(query);
		
	}

	public List<ForumPost> getAllQuestions() throws DaoException {
		List<ForumPost> posts = dao.getAllQuestions();
		Collections.sort(posts, new ForumPostComparator());
		return posts;
	}

	public List<Comment> getAllQuestionCommentsById(int id) throws DaoException {
		List<Comment> comments = dao.getAllQuestionCommentsById(id);
		Collections.sort(comments, new CommentComparator());
		return comments;
	}

	public ForumPost getSupportById(int postId) throws DaoException {
		ForumPost post = dao.getSupportById(postId);
		return post;
	}
}
