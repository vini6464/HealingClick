package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import java.util.List;

import org.apache.log4j.Logger;

import com.vinod.controller.NotificationController;
import com.vinod.exception.DaoException;
import com.vinod.model.Comment;
import com.vinod.model.Doctor;
import com.vinod.model.ForumPost;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Prescription;
import com.vinod.model.User;
import com.vinod.util.DBUtil;

public class AdminDao {
	final static Logger logger = Logger.getLogger(AdminDao.class);
	public List<Doctor> getAllNotVerifiedDoctors() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		
		String query = "select * from doctor where isverified=0 and isdeleted=0";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Doctor doctor = new Doctor();
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
				
				doctor.setAboutMe(rs.getString(27));
				
				doctor.setCreationDate(rs.getTimestamp(29));
				
				doctors.add(doctor);
			}
		} catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace());
			throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}

	public int getTotalDoctorCount() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "select count(*) from doctor";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getTotalPatientCount() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "select count(*) from patient";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getTotalPharmacyCount() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "select count(*) from pharmacy";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getPrescriptionCountForDoctor(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "select count(*) from prescription where doctorid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getOrderCountForDoctor(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "select count(*) from orders where doctorid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getPrescriptionCountForPatient(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "select count(*) from prescription where patientid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getOrderCountForPatient(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "select count(*) from orders where patientid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getOrderReceivedCount(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "select count(*) from orders where supplierid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getOrderMadeCount(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "select count(*) from orders where pharmacyid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public List<Doctor> getDoctors(String text) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		String query = "select * from doctor where UserName like '%"+text+"%' or EmailId like '%"+text+"%' or MobileNumber like '%"+text+"%'";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
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
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}

	public List<Patient> getPatients(String text) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Patient> patients = new ArrayList<Patient>();
		String query = "select * from patient where UserName like '%"+text+"%' or EmailId like '%"+text+"%' or MobileNumber like '%"+text+"%'";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
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
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return patients;
	}

	public List<Pharmacy> getPharmacies(String text) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		String query = "select * from pharmacy where UserName like '%"+text+"%' or EmailId like '%"+text+"%' or MobileNumber like '%"+text+"%' or PharamacyName like '%"+text+"%' or PinCode like '%"+text+"%'";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Pharmacy pharmacy  = new Pharmacy();
				pharmacy.setId(rs.getInt(1));
				pharmacy.setUserName(rs.getString(2));
				pharmacy.setFirstName(rs.getString(4));
				pharmacy.setLastName(rs.getString(5));
				pharmacy.setImage(rs.getString(6));
				pharmacy.setPharmacyName(rs.getString(17));
				
				pharmacies.add(pharmacy);
			}
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pharmacies;
	}

	public void activateDoctor(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "update doctor set IsVerified=1 where Id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			 ps.executeUpdate();
			
			
		} 
		 catch (SQLException e) {
			 System.out.println(e.getMessage());
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void deleteAccount(int id, String type) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		
		
		String query = "";
		
		if(type.equalsIgnoreCase("doctor"))
		{
			query = " update doctor set IsDeleted = 1 where Id = ? ";
		}
		if(type.equalsIgnoreCase("patient"))
		{
			query = " update patient set IsDeleted = 1 where Id = ? ";
		}
		if(type.equalsIgnoreCase("pharmacy"))
		{
			query = " update pharmacy set IsDeleted = 1 where Id = ? ";
		}
		System.out.println(query);
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			 ps.executeUpdate();
		
		} 
		 catch (SQLException e) {
			 e.printStackTrace();
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<Order> getSearchedOrders(String query) throws DaoException {
		
		List<Order> orders = new ArrayList<Order>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			/*ps.setTimestamp(1, t1);
			ps.setTimestamp(2, t2);*/
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setDoctorId(rs.getInt("doctorid"));
				order.setPatientId(rs.getInt("patientid"));
				order.setPharmacyId(rs.getInt("pharmacyid"));
				order.setSupplierPharmacyId(rs.getInt("supplierid"));
				
				order.setCashType(rs.getInt("paymenttype"));
				
				order.setCreatedOn(rs.getTimestamp("createdon"));
				order.setDeliveredOn(rs.getTimestamp("deliveredon"));
				
				
				order.setOrderType(rs.getInt("ordertype"));
				
				order.setTotalCost(rs.getDouble("totalcost"));
				order.setStatus(rs.getInt("status"));
				
				orders.add(order);
			}
			System.out.println(query);
		}catch (SQLException e) {
			e.printStackTrace();
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return orders;
	}

	public List<Pharmacy> getAllPharmacies() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		String query = "select id , username , pharamacyname from pharmacy";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Pharmacy pharmacy  = new Pharmacy();
				pharmacy.setId(rs.getInt("id"));
				pharmacy.setUserName(rs.getString("username"));
				
				pharmacy.setPharmacyName(rs.getString("pharamacyname"));
				
				pharmacies.add(pharmacy);
			}
		}  catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pharmacies;
	}

	public List<Doctor> getAllDoctors() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		String query = "select id , username , firstname , lastname from doctor";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Doctor doctor  = new Doctor();
				doctor.setId(rs.getInt("id"));
				doctor.setUserName(rs.getString("username"));
				
				doctor.setFirstName(rs.getString("firstname"));
				doctor.setLastName(rs.getString("lastname"));
				doctors.add(doctor);
			}
		}  catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}

	public List<Prescription> getSearchedPrescriptionss(String query) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Prescription> pres = new ArrayList<Prescription>();
		
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Prescription pre = new Prescription();
				pre.setId(rs.getInt("id"));
				pre.setDoctorId(rs.getInt("doctorid"));
				pre.setPatientId(rs.getInt("patientid"));
				
				pre.setPrescribedDate(rs.getTimestamp("prescribeddate"));
				pre.setCheckup(rs.getInt("checkup"));
				
				pres.add(pre);
			}
		}  catch (SQLException e) {
			e.printStackTrace();
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pres;
	}

	public List<Patient> getAllPatients() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Patient> doctors = new ArrayList<Patient>();
		String query = "select id , username , firstname , lastname from patient";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Patient doctor  = new Patient();
				doctor.setId(rs.getInt("id"));
				doctor.setUserName(rs.getString("username"));
				
				doctor.setFirstName(rs.getString("firstname"));
				doctor.setLastName(rs.getString("lastname"));
				doctors.add(doctor);
			}
		}  catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}

	public int getOrders(int orderCount) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select count(*) from orders where status=4";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				orderCount = rs.getInt(1);
				System.out.println(orderCount);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		return orderCount;
	}

	public int getPrescriptions(int prescriptionCount) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select count(*) from prescription where doctorid <> 0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				prescriptionCount = rs.getInt(1);
				System.out.println(prescriptionCount);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return prescriptionCount;
	}

	public int getMonthDoctorCount() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "SELECT count(*) FROM doctor WHERE month(usercreationdate) = EXTRACT(month FROM (NOW())) AND year(usercreationdate) = EXTRACT(year FROM (NOW()))";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getMonthPatientCount() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "SELECT count(*) FROM patient WHERE month(usercreationdate) = EXTRACT(month FROM (NOW())) AND year(usercreationdate) = EXTRACT(year FROM (NOW()))";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getMonthPharmacyCount() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "SELECT count(*) FROM pharmacy WHERE month(usercreationdate) = EXTRACT(month FROM (NOW())) AND year(usercreationdate) = EXTRACT(year FROM (NOW()))";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getMonthOrder() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "SELECT count(*) FROM orders WHERE status=4 and (month(createdon) = EXTRACT(month FROM (NOW())) AND year(createdon) = EXTRACT(year FROM (NOW())))";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public int getMonthPrescription() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		String query = "SELECT count(*) FROM prescription WHERE doctorid <> 0 and (month(prescribeddate) = EXTRACT(month FROM (NOW())) AND year(prescribeddate) = EXTRACT(year FROM (NOW())))";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				count = rs.getInt(1);
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return count;
	}

	public List<Pharmacy> getAllNotVerifiedPharmacies() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pharmacy> doctors = new ArrayList<Pharmacy>();
		
		String query = "select * from pharmacy where isverified=0 and isdeleted=0";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Pharmacy doctor = new Pharmacy();
				doctor.setId(rs.getInt(1));
				doctor.setUserName(rs.getString("username"));
				doctor.setImage(rs.getString("profileimage"));
				
				doctor.setPharmacyName(rs.getString("pharamacyname"));
				doctors.add(doctor);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}

	public void activatePharmacy(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "update pharmacy set IsVerified=1 where Id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			 ps.executeUpdate();
			
			
		} 
		 catch (SQLException e) {
			 System.out.println(e.getMessage());
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<User> getSearchedUsers(String query, int type) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<User> users = new ArrayList<User>();
		
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user  = new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setImage(rs.getString("ProfileImage"));
				user.setMail(rs.getString("EmailId"));
				user.setMobile(rs.getLong("MobileNumber"));
				if(type == 3)
				{
					user.setPharmacyName(rs.getString("PharamacyName"));
				}
				user.setLastActive(rs.getTimestamp("lastactive"));
				user.setCreationDate(rs.getDate("UserCreationDate"));
				users.add(user);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return users;
	}

	public List<ForumPost> getSearchedPosts(String query , int type) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ForumPost> posts = new ArrayList<ForumPost>();
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				ForumPost post = new ForumPost();
				post.setId(rs.getInt("id"));
				if(type==1)
				{
					post.setDoctorId(rs.getInt("doctorid"));
				}
				if(type==2)
				{
					post.setPatientId(rs.getInt("patientid"));
				}
				if(type==3)
				{
					post.setPharmacyId(rs.getInt("pharmacyid"));
				}
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setLikes(rs.getInt("likes"));
				post.setComments(rs.getInt("comments"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return posts;
	}

	public void deletePost(String query) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.executeUpdate();
			
			
		} 
		 catch (SQLException e) {
			 System.out.println(e.getMessage());
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<ForumPost> getAllQuestions() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		List<ForumPost> posts = new ArrayList<ForumPost>();
		String query = "select * from support ";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				ForumPost post = new ForumPost();
				post.setId(rs.getInt("id"));
				post.setOtherId(rs.getInt("otherid"));
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				post.setType(rs.getInt("type"));
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return posts;
	}

	public List<Comment> getAllQuestionCommentsById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Comment> comments = new ArrayList<Comment>();
		String query = "select * from supportcomment where supportid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Comment comment = new Comment();
				comment.setForumPostid(rs.getInt("supportid"));
				comment.setOtherId(rs.getInt("id"));
				comment.setComment(rs.getString("comment"));
				comment.setCreated(rs.getTimestamp("commentedon"));
				comment.setId(rs.getInt("commentid"));
				
				comments.add(comment);
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return comments;
	}

	public ForumPost getSupportById(int postId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		ForumPost post = new ForumPost();
		String query = "select * from support where id=? ";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				post.setId(rs.getInt("id"));
				post.setOtherId(rs.getInt("otherid"));
				post.setContent(rs.getString("content"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				post.setType(rs.getInt("type"));
				
			}
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return post;
	}

}
