package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.vinod.exception.DaoException;
import com.vinod.model.Medicine;
import com.vinod.model.Prescription;
import com.vinod.model.Vaccination;

import com.vinod.util.DBUtil;

public class PatientDao {
	final static Logger logger = Logger.getLogger(PatientDao.class);
	public Prescription getPrescriptionById(int prescriptionId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Prescription pre = new Prescription();
		String query = "select * from prescription where id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,prescriptionId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
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
			}
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pre;
	}

	public String getSymptomNameById(long s1) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		String query = "select name from symptom where id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1,s1);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return name;
	}

	public String getDiseaseNameById(int d1) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String name = "";
		String query = "select name from disease where id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,d1);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return name;
	}

	public Medicine getPrescribedMedicineById(long l, int m) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Medicine medicine = new Medicine();
		String query = "select * from prescriptionmedicine where prescriptionid=? and medicineid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1,l);
			ps.setInt(2,m);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				medicine.setId(rs.getInt(2));
				medicine.setName(rs.getString(3));
				medicine.setQuantity(rs.getInt(4));
				medicine.setMorning(rs.getInt(5));
				medicine.setAfternoon(rs.getInt(6));
				medicine.setNight(rs.getInt(7));
				medicine.setMt(rs.getTime(8));
				medicine.setAt(rs.getTime(9));
				medicine.setNt(rs.getTime(10));
			}
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return medicine;
	}

	public List<Prescription> getAllPrescriptionByPatientId(int pId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Prescription> pres = new ArrayList<Prescription>();
		String query = "select * from prescription where patientid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,pId);
			
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
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pres;
	}

	public void removeNotification(int prescriptionId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "delete from prescriptionnotification where prescriptionid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,prescriptionId);
			
			
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		
	}

	public void saveVaccine(int patientId, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String vaccine ="";
		if(id==1)
		{
			vaccine="BCG";
		}
		if(id==2)
		{
			vaccine="DPT_Polio_and_Hepatitis_B_1";
		}
		if(id==3)
		{
			vaccine="DPT_Polio_and_Hepatitis_B_2";
		}
		if(id==4)
		{
			vaccine="DPT_Polio_and_Hepatitis_B_3";
		}
		if(id==5)
		{
			vaccine="Measels";
		}
		if(id==6)
		{
			vaccine="MMR";
		}
		if(id==7)
		{
			vaccine="DPT_Polio_and_Hepatitis_B_booster_1";
		}
		if(id==8)
		{
			vaccine="DPT_Polio_and_Hepatitis_B_booster_2";
		}
		if(id==9)
		{
			vaccine="DT";
		}
		if(id==10)
		{
			vaccine="dose_of_tetanus";
		}
		if(id==11)
		{
			vaccine="seasonal";
		}
		
		String query = "update vaccination set "+vaccine+" = 1 where patientid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,patientId);
			
			
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void removeVaccine(int patientId, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String vaccine ="";
		if(id==1)
		{
			vaccine="BCG";
		}
		if(id==2)
		{
			vaccine="DPT_Polio_and_Hepatitis_B_1";
		}
		if(id==3)
		{
			vaccine="DPT_Polio_and_Hepatitis_B_2";
		}
		if(id==4)
		{
			vaccine="DPT_Polio_and_Hepatitis_B_3";
		}
		if(id==5)
		{
			vaccine="Measels";
		}
		if(id==6)
		{
			vaccine="MMR";
		}
		if(id==7)
		{
			vaccine="DPT_Polio_and_Hepatitis_B_booster_1";
		}
		if(id==8)
		{
			vaccine="DPT_Polio_and_Hepatitis_B_booster_2";
		}
		if(id==9)
		{
			vaccine="DT";
		}
		if(id==10)
		{
			vaccine="dose_of_tetanus";
		}
		if(id==11)
		{
			vaccine="seasonal";
		}
		
		String query = "update vaccination set "+vaccine+" = 0 where patientid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,patientId);
			
			
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void addVaccination(Vaccination vaccination) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "insert into patientvaccine(patientid,vaccinename,duedate ,status ,duetime) values (?,?,?,0,?)";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, vaccination.getPatientId());
			ps.setString(2,vaccination.getName());
			ps.setDate(3, new java.sql.Date(vaccination.getDuedate().getTime()));
			ps.setTime(4, vaccination.getTime());
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void trueVaccine(int patientId, int id) throws DaoException {
		
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "update patientvaccine set status = 1 where patientid=? and id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1,patientId);
			ps.setInt(2,id);
			
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void falseVaccine(int patientId, int id) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "update patientvaccine set status = 0 where patientid=? and id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1,patientId);
			ps.setInt(2,id);
			
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void deleteVaccine(int patientId, int id) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "delete from patientvaccine where patientid=? and id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1,patientId);
			ps.setInt(2,id);
			
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
	}

}
