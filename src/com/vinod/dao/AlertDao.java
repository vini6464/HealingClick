package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import com.vinod.exception.DaoException;
import com.vinod.model.Medicine;
import com.vinod.model.Vaccination;
import com.vinod.util.DBUtil;

public class AlertDao {
	final static Logger logger = Logger.getLogger(AlertDao.class);
	public List<Vaccination> getVaccinations() throws DaoException {
	
		Date date = new Date();
		Time time = new Time(date.getTime());
		int mins = time.getMinutes();
		String startTime = time.getHours()+":"+mins+":00";
		String endTime = time.getHours()+1+":"+mins+":00";
		System.out.println("startTime="+startTime);
		System.out.println("endTime="+endTime);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Vaccination> vaccinations = new ArrayList<Vaccination>();
		//String query = "select patient.firstname,patient.lastname,patient.mobilenumber,patientvaccine.patientid,patientvaccine.id,patientvaccine.vaccinename,patientvaccine.duedate,patientvaccine.status,patientvaccine.duetime from patient,patientvaccine where patientvaccine.duetime>='"+startTime+"' and patientvaccine.duetime<='"+endTime+"' and patientvaccine.duedate=? and patientvaccine.patientid=patient.id and patient.isdeleted=0";
		String query = "select patient.firstname,patient.lastname,patient.mobilenumber,patientvaccine.patientid,patientvaccine.id,patientvaccine.vaccinename,patientvaccine.duedate,patientvaccine.status,patientvaccine.duetime from patient,patientvaccine where patientvaccine.duedate=? and patientvaccine.patientid=patient.id and patient.isdeleted=0";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setDate(1, new java.sql.Date(date.getTime()));
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Vaccination vaccination = new Vaccination();
				vaccination.setPatientName(rs.getString("firstname")+" "+rs.getString("lastname"));
				vaccination.setMobile(rs.getLong("mobilenumber"));
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

	public List<Medicine> getMedicines() throws DaoException {
		Date date = new Date();
		Time time = new Time(date.getTime());
		int mins = time.getMinutes();
		String startTime = time.getHours()+":"+mins+":00";
		String endTime = time.getHours()+1+":"+mins+":00";
		System.out.println("startTime="+startTime);
		System.out.println("endTime="+endTime);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Medicine> vaccinations = new ArrayList<Medicine>();
		String query = "select patient.firstname,patient.lastname,patient.mobilenumber,prescription.id,prescription.patientid,patient.id,prescriptionmedicine.name,prescriptionmedicine.morningtime,prescriptionmedicine.afternoontime,prescriptionmedicine.nighttime,prescriptionmedicine.morning,prescriptionmedicine.afternoon,prescriptionmedicine.night,prescriptionmedicine.quantity,prescription.prescribeddate,prescriptionmedicine.avail "+
				"from patient,prescription,prescriptionmedicine where prescription.patientid=patient.id and prescription.id=prescriptionmedicine.prescriptionid and prescriptionmedicine.avail>0 and patient.isdeleted=0";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Medicine vaccination = new Medicine();
				
				vaccination.setPatientId(rs.getInt("patientid"));
				vaccination.setPrescriptionId(rs.getInt("id"));
				vaccination.setName(rs.getString("name"));
				vaccination.setPatientName(rs.getString("firstname")+" "+rs.getString("lastname"));
				vaccination.setMobile(rs.getLong("mobilenumber"));
				vaccination.setQuantity(rs.getInt("quantity"));
				vaccination.setMt(rs.getTime("morningtime"));
				vaccination.setAt(rs.getTime("afternoontime"));
				vaccination.setNt(rs.getTime("nighttime"));
				vaccination.setMorning(rs.getInt("morning"));
				vaccination.setAfternoon(rs.getInt("afternoon"));
				vaccination.setNight(rs.getInt("night"));
				vaccination.setPrescribedDate(rs.getTimestamp("prescribeddate"));
				vaccination.setLeft(rs.getInt("avail"));
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

	public void updateLeftMedicines(int prescriptionId, String name, int left) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query ="";
		
		query = "update prescriptionmedicine set avail=? where prescriptionid=? and name=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, left);
			ps.setInt(2, prescriptionId);
			ps.setString(3, name);
			ps.executeUpdate();
			
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



}
