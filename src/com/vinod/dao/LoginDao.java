package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import com.vinod.exception.DaoException;
import com.vinod.model.Appointment;
import com.vinod.model.Doctor;
import com.vinod.model.Log;
import com.vinod.model.Login;
import com.vinod.model.Notification;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Review;
import com.vinod.model.User;
import com.vinod.model.Vaccination;
import com.vinod.model.Vaccine;
import com.vinod.util.DBUtil;

public class LoginDao {
	final static Logger logger = Logger.getLogger(LoginDao.class);
	public int validateLoginDoctor(String userName, String password) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i =0;
		String query = "select Id from doctor where (UserName=? or EmailId=?) and Password=? and isverified=1 and isdeleted=0";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, userName);
			ps.setString(3, password);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		}  catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		
		return i;
	}

	public List<Patient> getAllPatients(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Patient> patients = new ArrayList<Patient>();
		String query = "select * from patient,patientdoctor where patient.Id = patientdoctor.patientId and patientdoctor.doctorId=? and patientdoctor.doctorAccepted=1 and patientdoctor.patientAccepted=1 and patient.isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Patient patient  = new Patient();
				patient.setId(rs.getInt(1));
				patient.setUserName(rs.getString(2));
				patient.setFirstName(rs.getString(4));
				patient.setLastName(rs.getString(5));
				patient.setImage(rs.getString(6));
				
				patients.add(patient);
			}
		}  catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return patients;
	}

	public List<Pharmacy> getAllPharmacies(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		String query = "select * from pharmacy,doctorpharmacy where pharmacy.Id = doctorpharmacy.pharmacyId and doctorpharmacy.doctorId=? and pharmacy.isdeleted = 0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Pharmacy pharmacy  = new Pharmacy();
				pharmacy.setId(rs.getInt(1));
				pharmacy.setUserName(rs.getString(2));
				pharmacy.setFirstName(rs.getString(4));
				pharmacy.setLastName(rs.getString(5));
				pharmacy.setImage(rs.getString(6));
				pharmacy.setAddress1(rs.getString(9));
				pharmacy.setAddress1(rs.getString(10));
				pharmacy.setCity(rs.getString(11));
				pharmacy.setState(rs.getString(12));
				pharmacy.setCountry(rs.getString(13));
				pharmacy.setPinCode(rs.getLong(14));
				pharmacy.setLandMark(rs.getString(15));
				pharmacy.setPharmacyName(rs.getString(17));
				
				pharmacies.add(pharmacy);
			}
		}  catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pharmacies;
	}

	public Doctor getDoctorById(long id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Doctor doctor = new Doctor();
		String query = "select * from doctor where Id=? ";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				doctor.setId(rs.getInt(1));
				doctor.setUserName(rs.getString(2));
				doctor.setFirstName(rs.getString(4));
				doctor.setLastName(rs.getString(5));
				doctor.setImage(rs.getString(6));
				doctor.setGender(rs.getInt(7));
				doctor.setDob(rs.getDate(8));
				doctor.setQualification(rs.getString(9));
				doctor.setSpeciality(rs.getString(10));
				doctor.setWorkLocation(rs.getString(11));
				doctor.setAddress1(rs.getString(12));
				doctor.setAddress2(rs.getString(13));
				doctor.setCity(rs.getString(14));
				doctor.setState(rs.getString(15));
				doctor.setCountry(rs.getString(16));
				doctor.setPinCode(rs.getLong(17));
				doctor.setLandMark(rs.getString(18));
				doctor.setBloodGroup(rs.getInt(19));
				doctor.setLandLine(rs.getLong(20));
				doctor.setMobile(rs.getLong(21));
				doctor.setEmailId(rs.getString(22));
				doctor.setVerified(rs.getInt(25));
				doctor.setAboutMe(rs.getString(27));
				
				doctor.setCreationDate(rs.getTimestamp(29));
				doctor.setLastActive(rs.getTimestamp("lastactive"));
				doctor.setDeleted(rs.getInt("isdeleted"));
				doctor.setPrivacy(rs.getInt("privacy"));
				
			}
		} catch (SQLException e) {
			System.out.println("in derrr");
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctor;
	}


	
	public int validateLoginPatient(String userName, String password) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i =0;
		String query = "select Id from patient where (UserName=? or EmailId=?) and Password=? and isdeleted=0";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, userName);
			ps.setString(3, password);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		
		return i;
	}

	public Patient getPatientById(long id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Patient patient = new Patient();
		String query = "select * from patient where Id=? ";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				patient.setId(rs.getInt(1));
				patient.setUserName(rs.getString(2));
				patient.setFirstName(rs.getString(4));
				patient.setLastName(rs.getString(5));
				patient.setImage(rs.getString(6));
				patient.setGender(rs.getInt(7));
				patient.setDob(rs.getDate(8));
				patient.setAddress1(rs.getString(9));
				patient.setAddress1(rs.getString(10));
				patient.setCity(rs.getString(11));
				patient.setState(rs.getString(12));
				patient.setCountry(rs.getString(13));
				patient.setPinCode(rs.getLong(14));
				patient.setLandMark(rs.getString(15));
				patient.setBloodGroup(rs.getInt(16));
				patient.setLandLine(rs.getLong(17));
				patient.setMobile(rs.getLong(18));
				patient.setEmergency1(rs.getLong(19));
				patient.setEmergency2(rs.getLong(20));
                patient.setEmailId(rs.getString(21));
				
				patient.setAboutMe(rs.getString(26));
				
				patient.setCreationDate(rs.getTimestamp(28));
				patient.setBp(rs.getString(30));
				patient.setSugar(rs.getDouble(31));
				patient.setCholesterol(rs.getDouble(32));
				patient.setBmi(rs.getDouble(33));
				
				patient.setLastActive(rs.getTimestamp("lastactive"));
				
				patient.setVerified(rs.getInt("isverified"));
				patient.setDeleted(rs.getInt("isdeleted"));
				patient.setPrivacy(rs.getInt("privacy"));
				patient.setBpStatus(rs.getString("bpstatus"));
				patient.setSugarStatus(rs.getString("sugarstatus"));
				patient.setCholesterolStatus(rs.getString("cholesterolstatus"));
				patient.setBmiStatus(rs.getString("bmistatus"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return patient;
	}

	public List<Doctor> getAllDoctorsByPatientId(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		String query = "select * from doctor,patientdoctor where doctor.Id = patientdoctor.doctorId and patientdoctor.patientId=? and patientdoctor.doctorAccepted=1 and patientdoctor.patientAccepted=1 and doctor.isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Doctor doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setUserName(rs.getString(2));
				doctor.setFirstName(rs.getString(4));
				doctor.setLastName(rs.getString(5));
				doctor.setImage(rs.getString(6));
				
				doctors.add(doctor);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}

	public List<Pharmacy> getAllPharmaciesByPatientId(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		String query = "select * from pharmacy,patientpharmacy where pharmacy.Id = patientpharmacy.pharmacyId and patientpharmacy.patientId=? and pharmacy.isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Pharmacy pharmacy  = new Pharmacy();
				pharmacy.setId(rs.getInt(1));
				pharmacy.setUserName(rs.getString(2));
				pharmacy.setFirstName(rs.getString(4));
				pharmacy.setLastName(rs.getString(5));
				pharmacy.setImage(rs.getString(6));
				pharmacy.setAddress1(rs.getString(9));
				pharmacy.setAddress1(rs.getString(10));
				pharmacy.setCity(rs.getString(11));
				pharmacy.setState(rs.getString(12));
				pharmacy.setCountry(rs.getString(13));
				pharmacy.setPinCode(rs.getLong(14));
				pharmacy.setLandMark(rs.getString(15));
				pharmacy.setPharmacyName(rs.getString(17));
				pharmacies.add(pharmacy);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pharmacies;
	}

	public int validateLoginPharmacy(String userName, String password) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i =0;
		String query = "select Id from pharmacy where (UserName=? or EmailId=?) and Password=? and isdeleted=0 and isverified=1";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, userName);
			ps.setString(2, userName);
			ps.setString(3, password);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}
		finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		return i;
	}

	public Pharmacy getPharmacyById(long id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Pharmacy pharmacy = new Pharmacy();
		String query = "select * from pharmacy where Id=? ";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				pharmacy.setId(rs.getInt(1));
				pharmacy.setUserName(rs.getString(2));
				pharmacy.setFirstName(rs.getString(4));
				pharmacy.setLastName(rs.getString(5));
				pharmacy.setImage(rs.getString(6));
				pharmacy.setGender(rs.getInt(7));
				//pharmacy.setDob(rs.getDate(8));
				pharmacy.setAddress1(rs.getString(9));
				pharmacy.setAddress1(rs.getString(10));
				pharmacy.setCity(rs.getString(11));
				pharmacy.setState(rs.getString(12));
				pharmacy.setCountry(rs.getString(13));
				pharmacy.setPinCode(rs.getLong(14));
				pharmacy.setLandMark(rs.getString(15));
				pharmacy.setBloodGroup(rs.getInt(16));
				pharmacy.setPharmacyName(rs.getString(17));
				pharmacy.setLicensedTo(rs.getString(18));
				pharmacy.setLandLine(rs.getLong(19));
				pharmacy.setMobile(rs.getLong(20));
				pharmacy.setProprietorName(rs.getString(21));
				pharmacy.setProprietorAddress(rs.getString(22));
				pharmacy.setProprietorContact(rs.getLong(23));
				pharmacy.setProprietorEmail(rs.getString(24));
				pharmacy.setEmailId(rs.getString(25));
                pharmacy.setAboutMe(rs.getString(30));
				pharmacy.setCreationDate(rs.getTimestamp(32));
				pharmacy.setLastActive(rs.getTimestamp("lastactive"));
				pharmacy.setDeliveryCharge(rs.getDouble("deliverycharge"));
				pharmacy.setDiscount(rs.getDouble("discount"));
				
				pharmacy.setVerified(rs.getInt("isverified"));
				pharmacy.setDeleted(rs.getInt("isdeleted"));
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pharmacy;
	}

	public List<User> getPatientRequestNotification(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> patients = new ArrayList<User>();
		String query = "select patient.Id,patient.UserName,patient.FirstName,patient.LastName,patient.ProfileImage,patientdoctor.view from patient,patientdoctor where patient.Id = patientdoctor.patientId and patientdoctor.doctorId=? and doctorAccepted=0 and patientAccepted=1";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User patient  = new User();
				patient.setId(rs.getInt("Id"));
				patient.setUserName(rs.getString("UserName"));
				patient.setFirstName(rs.getString("FirstName"));
				patient.setLastName(rs.getString("LastName"));
				patient.setImage(rs.getString("ProfileImage"));
				patient.setView(rs.getInt("view"));
				patient.setType(2);
				patients.add(patient);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return patients;
	}

	public List<Doctor> getDoctorRequestNotification(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		String query = "select doctor.Id,doctor.UserName,doctor.FirstName,doctor.LastName,doctor.ProfileImage,patientdoctor.view from doctor,patientdoctor where doctor.Id = patientdoctor.doctorId and patientdoctor.patientId=? and doctorAccepted=1 and patientAccepted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Doctor doctor = new Doctor();
				doctor.setId(rs.getInt("Id"));
				doctor.setUserName(rs.getString("UserName"));
				doctor.setFirstName(rs.getString("FirstName"));
				doctor.setLastName(rs.getString("LastName"));
				doctor.setImage(rs.getString("ProfileImage"));
				doctor.setView(rs.getInt("view"));
				doctors.add(doctor);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}


	public int connectedOrNot(int pId, int dId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		String query = "select deleted from patientdoctor where patientid=? and doctorid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, pId);
			ps.setInt(2,dId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public List<Order> getAllNewOrders(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> orders = new ArrayList<Order>();
		String query = "select * from orders where supplierid=? and (status=1 or status=2)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDoctorId(rs.getInt("doctorid"));
				order.setPatientId(rs.getInt("patientid"));
				order.setPharmacyId(rs.getInt("pharmacyid"));
				order.setSupplierPharmacyId(rs.getInt("supplierid"));
				order.setStatus(rs.getInt("status"));
				orders.add(order);	
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return orders;
	}

	public List<Pharmacy> getAllPharmaciesForPharmacy(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		String query = "select * from pharmacy,pharmacypharmacy where (pharmacy.Id = pharmacypharmacy.pharmacyid1 or pharmacy.Id = pharmacypharmacy.pharmacyid2) and (pharmacypharmacy.pharmacyid1=? or pharmacypharmacy.pharmacyid2=?) and pharmacy.id<>? and pharmacy.isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.setInt(3, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Pharmacy pharmacy  = new Pharmacy();
				pharmacy.setId(rs.getInt(1));
				pharmacy.setUserName(rs.getString(2));
				pharmacy.setFirstName(rs.getString(4));
				pharmacy.setLastName(rs.getString(5));
				pharmacy.setImage(rs.getString(6));
				pharmacy.setAddress1(rs.getString(9));
				pharmacy.setAddress1(rs.getString(10));
				pharmacy.setCity(rs.getString(11));
				pharmacy.setState(rs.getString(12));
				pharmacy.setCountry(rs.getString(13));
				pharmacy.setPinCode(rs.getLong(14));
				pharmacy.setLandMark(rs.getString(15));
				pharmacy.setPharmacyName(rs.getString(17));
				pharmacies.add(pharmacy);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pharmacies;
	}

	public void updateProfileImage(String imagePath, Login login) throws DaoException {
		int id=0;
		String query ="";
		if(login.getType()==1)
		{
			id = login.getId();
			query = "update doctor set profileImage=? where Id=?";
		}
		if(login.getType()==2)
		{
			id = login.getId();
			query = "update patient set profileImage=? where Id=?";
		}
		if(login.getType()==3)
		{
			id = login.getId();
			query = "update pharmacy set profileImage=? where Id=?";
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, imagePath);
			ps.setInt(2, id);
			 ps.executeUpdate();
			
			
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public int doctorPharmacyConnectedOrNot(int id, int pId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		String query = "select count(*) from doctorpharmacy where doctorid=? and pharmacyid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2,pId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int patientPharmacyConnectedOrNot(int id, int pId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		String query = "select count(*) from patientpharmacy where patientid=? and pharmacyid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2,pId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int connectedPharmacyToPharmacyOrNot(int pId, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		String query = "select count(*) from pharmacypharmacy where (pharmacyid1=? or pharmacyid1=?) and (pharmacyid2=? or pharmacyid2=?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2,pId);
			ps.setInt(3, id);
			ps.setInt(4,pId);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public void setLastActive(Login login) throws DaoException {
		int id=0;
		String query ="";
		if(login.getType()==1)
		{
			id = login.getId();
			query = "update doctor set lastactive=? where Id=?";
		}
		if(login.getType()==2)
		{
			id = login.getId();
			query = "update patient set lastactive=? where Id=?";
		}
		if(login.getType()==3)
		{
			id = login.getId();
			query = "update pharmacy set lastactive=? where Id=?";
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setTimestamp(1, new Timestamp(new Date().getTime()));
			ps.setInt(2, id);
			 ps.executeUpdate();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}
	
	public int checkPassword(Login login, String pwd) throws DaoException {
		int i=0;
		int id=0;
		String query ="";
		if(login.getType()==1)
		{
			id = login.getId();
			query = "select count(*) from doctor where Id=? and Password=?";
		}
		if(login.getType()==2)
		{
			id = login.getId();
			query = "select count(*) from patient where Id=? and Password=?";
		}
		if(login.getType()==3)
		{
			id = login.getId();
			query = "select count(*) from pharmacy where Id=? and Password=?";
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setString(2, pwd);
			rs=ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public void updatePassword(Login login, String newpwd) throws DaoException {

		System.out.println(newpwd);
		int id=0;
		String query ="";
		if(login.getType()==1)
		{
			id = login.getId();
			query = "update doctor set Password=? where Id=?";
		}
		if(login.getType()==2)
		{
			id = login.getId();
			query = "update patient set Password=? where Id=?";
		}
		if(login.getType()==3)
		{
			id = login.getId();
			query = "update pharmacy set Password=? where Id=?";
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, newpwd);
			ps.setInt(2, id);
			int i = ps.executeUpdate();
			
			System.out.println(i);			
		}catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
	}



	public Vaccine checkVaccine(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vaccine i = new Vaccine();
		String query = "select * from vaccination where patientid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i.setPatientId(rs.getInt("patientid"));
				i.setBCG(rs.getInt("BCG"));
				i.setDose_of_tetanus(rs.getInt("dose_of_tetanus"));
				i.setDPT_Polio_and_Hepatitis_B_1(rs.getInt("DPT_Polio_and_Hepatitis_B_1"));
				i.setDPT_Polio_and_Hepatitis_B_2(rs.getInt("DPT_Polio_and_Hepatitis_B_2"));
				i.setDPT_Polio_and_Hepatitis_B_3(rs.getInt("DPT_Polio_and_Hepatitis_B_3"));
				i.setDPT_Polio_and_Hepatitis_B_booster_1(rs.getInt("DPT_Polio_and_Hepatitis_B_booster_1"));
				i.setDPT_Polio_and_Hepatitis_B_booster_2(rs.getInt("DPT_Polio_and_Hepatitis_B_booster_2"));
				i.setDT(rs.getInt("DT"));
				i.setMeasels(rs.getInt("Measels"));
				i.setMMR(rs.getInt("MMR"));
				i.setSeasonal(rs.getInt("seasonal"));
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public void setVaccine(int id) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "insert into vaccination values (?,0,0,0,0,0,0,0,0,0,0,0)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void saveNotification(Notification notification) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "insert into notification (sender_id,content,type,recipient_id,is_read,image)values (?,?,?,?,?,?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, notification.getSenderId());
			ps.setString(2, notification.getContent());
			ps.setInt(3, notification.getType());
			ps.setLong(4, notification.getRecipientId());
			ps.setInt(5, notification.getHasRead());
			ps.setString(6, notification.getImage());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void saveLog(Log log) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "insert into logs (id,content,type)values (?,?,?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, log.getId());
			ps.setString(2, log.getContent());
			ps.setInt(3, log.getType());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
	}

	public void changeNotificationImage(Login login , String imagePath) throws DaoException {
		String query ="update notification set image=? where sender_id=?";
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, imagePath);
			ps.setInt(2, login.getId());
			 ps.executeUpdate();
			
			
		}catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<Vaccination> getVaccination(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Vaccination> vaccinations = new ArrayList<Vaccination>();
		String query = "select * from patientvaccine where patientid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Vaccination vaccination = new Vaccination();
				
				vaccination.setPatientId(rs.getLong("patientid"));
				vaccination.setId(rs.getLong("id"));
				vaccination.setName(rs.getString("vaccinename"));
				vaccination.setDuedate(rs.getDate("duedate"));
				vaccination.setStatus(rs.getInt("status"));
				vaccination.setTime(rs.getTime("duetime"));
				vaccinations.add(vaccination);
				
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return vaccinations;
	}

	public List<Appointment> getThisDayAppointment(int id) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Date d= new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String today = dateFormat.format(d);
		List<Appointment> appointments = new ArrayList<Appointment>();
		String query = "select * from events where doctorid=? and isdeleted=0 and start like '%"+today+"%'";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Appointment appointment = new Appointment();
				appointment.setId(rs.getInt("id"));
				appointment.setStart(rs.getString("start"));
				appointment.setEnd(rs.getString("end"));
				appointment.setTitle(rs.getString("title"));
				appointment.setDoctorId(rs.getLong("doctorid"));
				appointment.setPatientId(rs.getLong("patientid"));
				appointment.setPharmacyId(rs.getLong("pharmacyid"));
				appointment.setDescription(rs.getString("description"));
				appointment.setSymptom(rs.getString("symptom"));
				appointment.setStatus(rs.getInt("status"));
				
				appointments.add(appointment);
			}
		}catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return appointments;
	}

	public void setPatientVerified(long id) throws DaoException {
		String query ="update patient set isverified=1 where id=?";
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			 ps.executeUpdate();
			
			
		}catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<Review> getAllReviews(int id) throws DaoException {
		List<Review> reviews = new ArrayList<Review>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from review where pharmacyid=? and isdeleted=0";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Review review = new Review();
				review.setPharmacyId(rs.getInt("pharmacyid"));
				review.setReviewerId(rs.getInt("reviewerid"));
				review.setType(rs.getInt("type"));
				review.setComment(rs.getString("comment"));
				review.setCreationDate(rs.getTimestamp("creationdate"));
				review.setRating(rs.getDouble("rating"));
				reviews.add(review);
			}
		}catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		System.out.println(reviews);
		return reviews;	
	}

	public void getDoctorNotification(List<User> patients, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select doctor.Id,doctor.UserName,doctor.FirstName,doctor.LastName,doctor.ProfileImage,doctordoctor.view from doctor,doctordoctor where doctor.Id = doctordoctor.doctorid1 and doctordoctor.doctorid2=? and doctorid2Accepted=0 and doctorid1Accepted=1";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User patient  = new User();
				patient.setId(rs.getInt("Id"));
				patient.setUserName(rs.getString("UserName"));
				patient.setFirstName(rs.getString("FirstName"));
				patient.setLastName(rs.getString("LastName"));
				patient.setImage(rs.getString("ProfileImage"));
				patient.setView(rs.getInt("view"));
				patient.setType(1);
				patients.add(patient);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public int doctorDoctorConnectedOrNot(int dId1, int dId2) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select deleted from doctordoctor where (doctorid1=? or doctorid1=?) and (doctorid2=? or doctorid2=?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, dId1);
			ps.setInt(2,dId2);
			ps.setInt(3, dId1);
			ps.setInt(4,dId2);
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		
	} finally
	{
		DBUtil.releaseResource(rs);
		DBUtil.releaseResource(ps);
		DBUtil.releaseResource(con);
	}

		return i;
	}

	public List<Doctor> getAllDoctorsForDoctor(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		
		String query = "select * from doctor,doctordoctor where (doctor.Id = doctordoctor.doctorid1 or doctor.Id = doctordoctor.doctorid2) and (doctordoctor.doctorid1=? or doctordoctor.doctorid2=?)and doctor.id<>? and doctordoctor.doctorid1Accepted=1 and doctordoctor.doctorid2Accepted=1 and doctor.isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2, id);
			ps.setInt(3, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Doctor doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setUserName(rs.getString(2));
				doctor.setFirstName(rs.getString(4));
				doctor.setLastName(rs.getString(5));
				doctor.setImage(rs.getString(6));
				
				doctors.add(doctor);	
			}
		}  catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}



}
