package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import com.vinod.exception.DaoException;
import com.vinod.model.Disease;
import com.vinod.model.Medicine;
import com.vinod.model.Post;
import com.vinod.model.Prescription;
import com.vinod.model.Symptom;
import com.vinod.util.DBUtil;

public class DoctorDao {
	final static Logger logger = Logger.getLogger(DoctorDao.class);
	public List<Symptom> getAllSymptoms() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Symptom> symptoms = new ArrayList<Symptom>();
		String query = "select * from symptom";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Symptom symptom = new Symptom();
				symptom.setId(rs.getInt(1));
				symptom.setName(rs.getString(2));
				
				
				symptoms.add(symptom);
			}
		} 
		 catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return symptoms;
	}

	public List<Disease> getAllDiseases() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Disease> symptoms = new ArrayList<Disease>();
		String query = "select * from disease";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Disease symptom = new Disease();
				symptom.setId(rs.getInt(1));
				symptom.setName(rs.getString(2));
				
				
				symptoms.add(symptom);
			}
		} catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return symptoms;
	}

	public List<Medicine> getAllMedicines() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Medicine> symptoms = new ArrayList<Medicine>();
		String query = "select * from medicine";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Medicine symptom = new Medicine();
				symptom.setId(rs.getInt(1));
				symptom.setName(rs.getString(2));
				
				
				symptoms.add(symptom);
			}
		}  catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return symptoms;
	}

	public Medicine getMedicineCostById(int mId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Medicine i = new Medicine();
		String query = "select * from medicine where id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, mId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i.setId(rs.getInt(1));
				i.setName(rs.getString(2));
				i.setCost(rs.getDouble(3));
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

	public int savePrescription(Prescription prescription, int j) throws DaoException {
		System.out.println(prescription);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "insert into prescription(id,doctorid,patientid,s1,s2,s3,s4,s5,d1,d2,d3,d4,d5,m1,m2,m3,m4,m5,prescriptionPath,suggestion,bplevel,sugarlevel,cholesterol,bmi,updatedtime,checkup,bpstatus,sugarstatus,cholesterolstatus,prescribeddate) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1, prescription.getId());
			ps.setInt(2, prescription.getDoctorId());
			ps.setInt(3, prescription.getPatientId());
			
			ps.setInt(4, prescription.getS1());
			ps.setInt(5, prescription.getS2());
			ps.setInt(6, prescription.getS3());
			ps.setInt(7, prescription.getS4());
			ps.setInt(8, prescription.getS5());
			
			ps.setInt(9, prescription.getD1());
			ps.setInt(10, prescription.getD2());
			ps.setInt(11, prescription.getD3());
			ps.setInt(12, prescription.getD4());
			ps.setInt(13, prescription.getD5());
			
			ps.setInt(14, prescription.getM1());
			ps.setInt(15, prescription.getM2());
			ps.setInt(16, prescription.getM3());
			ps.setInt(17, prescription.getM4());
			ps.setInt(18, prescription.getM5());
			
			ps.setString(19, prescription.getPrescriptionPath());
			ps.setString(20, prescription.getSuggestion());
			
			ps.setString(21, prescription.getBp());
			ps.setDouble(22, prescription.getSugar());
			ps.setDouble(23, prescription.getCholesterol());
			
			if(j==1)
			{
				ps.setDouble(24, 0);
			}
			else
			{
				ps.setDouble(24, prescription.getBmi());
			}
			
			ps.setTimestamp(25, new Timestamp(new Date().getTime()));
			ps.setInt(26, prescription.getCheckup());
			ps.setString(27, prescription.getBpStatus());
			ps.setString(28, prescription.getSugarStatus());
			ps.setString(29, prescription.getCholesterolStatus());
			ps.setTimestamp(30, new Timestamp(new Date().getTime()));
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
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

	public void savePrescriptionMedicine(Medicine medicine) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "insert into prescriptionmedicine(prescriptionid,medicineid,name,quantity,morning,afternoon,night,morningtime,afternoontime,nighttime,avail) values (?,?,?,?,?,?,?,?,?,?,?);";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, medicine.getPrescriptionId());
			ps.setInt(2, medicine.getId());
			ps.setString(3, medicine.getName());
			ps.setInt(4, medicine.getQuantity());
			ps.setInt(5, medicine.getMorning());
			ps.setInt(6, medicine.getAfternoon());
			ps.setInt(7, medicine.getNight());
			ps.setTime(8, medicine.getMt());
			ps.setTime(9, medicine.getAt());
			ps.setTime(10, medicine.getNt());
			ps.setInt(11, medicine.getQuantity());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(Level.SEVERE,e); throw new DaoException();		}
		finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}


	public List<Prescription> getAllPrescriptionByDocorId(int dId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Prescription> pres = new ArrayList<Prescription>();
		String query = "select * from prescription where doctorid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,dId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Prescription pre = new Prescription();
				pre.setId(rs.getInt(1));
				pre.setDoctorId(rs.getInt(2));
				pre.setPatientId(rs.getInt(3));
				pre.setS1(rs.getInt(4));
				pre.setS2(rs.getInt(5));
				pre.setS3(rs.getInt(6));
				pre.setS4(rs.getInt(7));
				pre.setS5(rs.getInt(8));
				
				pre.setD1(rs.getInt(9));
				pre.setD2(rs.getInt(10));
				pre.setD3(rs.getInt(11));
				pre.setD4(rs.getInt(12));
				pre.setD5(rs.getInt(13));
				
				pre.setM1(rs.getInt(14));
				pre.setM2(rs.getInt(15));
				pre.setM3(rs.getInt(16));
				pre.setM4(rs.getInt(17));
				pre.setM5(rs.getInt(18));
				
				pre.setPrescriptionPath(rs.getString(19));
				pre.setSuggestion(rs.getString(20));
				pre.setPrescribedDate(rs.getTimestamp(21));
				
				pre.setBp(rs.getString(22));
				pre.setSugar(rs.getDouble(23));
				pre.setCholesterol(rs.getDouble(24));
				pre.setBmi(rs.getDouble(25));
				pre.setUpdatedtime(rs.getTimestamp(26));
				pre.setCheckup(rs.getInt("checkup"));
				pre.setBpStatus(rs.getString("bpstatus"));
				pre.setSugarStatus(rs.getString("sugarstatus"));
				pre.setCholesterolStatus(rs.getString("cholesterolstatus"));
				pre.setBmiStatus(rs.getString("bmistatus"));
				pres.add(pre);
			}
		}  catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pres;
	}

	public void updatePatient(Prescription prescription, int i) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		if(i == 0){
			query = "update patient set bplevel=?,sugarlevel=?,cholesterol=?,bmi=?,bpstatus=?,sugarstatus=?,cholesterolstatus=?,bmistatus=? where Id=?";
		}else{
			query = "update patient set bplevel=?,sugarlevel=?,cholesterol=?,bpstatus=?,sugarstatus=?,cholesterolstatus=? where Id=?";
		}
	    
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			if(i == 0){
			ps.setString(1, prescription.getBp());
			ps.setDouble(2, prescription.getSugar());
			ps.setDouble(3, prescription.getCholesterol());
			ps.setDouble(4, prescription.getBmi());
			ps.setString(5, prescription.getBpStatus());
			ps.setString(6, prescription.getSugarStatus());
			ps.setString(7, prescription.getCholesterolStatus());
			ps.setString(8, prescription.getBmiStatus());
			ps.setInt(9, prescription.getPatientId());
			}else{
				ps.setString(1, prescription.getBp());
				ps.setDouble(2, prescription.getSugar());
				ps.setDouble(3, prescription.getCholesterol());
				
				ps.setString(4, prescription.getBpStatus());
				ps.setString(5, prescription.getSugarStatus());
				ps.setString(6, prescription.getCholesterolStatus());
				
				ps.setInt(7, prescription.getPatientId());
			}
			
			
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public Medicine getMedicineCostByName(String mId, int supplierId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Medicine i = new Medicine();
		String query = "select pharmacymedicine.medicinename,pharmacymedicine.cost from pharmacymedicine where pharmacymedicine.medicinename=? and pharmacymedicine.pharmacyid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, mId);
			ps.setInt(2, supplierId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				i.setName(rs.getString("medicinename"));
				i.setCost(rs.getDouble("cost"));
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

	public int getSymptomIdByName(String symptom) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select * from symptom where name=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, symptom);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i=rs.getInt(1);
				
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

	public int getDiseaseIdByName(String disease) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select * from disease where name=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, disease);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i=rs.getInt(1);
				
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

	public void savePost(Post post) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		
		String query = "insert into post (status,posttype,privacy,doctorid,filepath,communityid) values (?,?,?,?,?,0)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,post.getStatus());
			ps.setInt(2, post.getPostType());
			ps.setInt(3, post.getPrivacy());
			ps.setInt(4, post.getDoctorId());
			ps.setString(5,post.getFilePath());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} 
		 catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public Medicine getMedicineByName(String parameter) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Medicine i = new Medicine();
		String query = "select * from medicine where name=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1, parameter);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i.setId(rs.getInt(1));
				i.setName(rs.getString(2));
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

}
