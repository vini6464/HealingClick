package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import com.vinod.exception.DaoException;
import com.vinod.model.Doctor;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.util.DBUtil;

public class ProfileDao {
	final static Logger logger = Logger.getLogger(ProfileDao.class);
	public int updateDoctorProfile(Doctor doctor) throws DaoException {
		int i=0;
		Connection con = null;
		PreparedStatement ps = null;
		String query="update doctor set FirstName=?,LastName=?,Gender=?,DOB=?,Qualification=?,Speciality=?,WorkLocation=?,Address1=?,"+
				"Address2=?,City=?,State=?,Country=?,PinCode=?,LandMark=?,BloodGroup=?,LandLineNumber=?,AboutMe=? where Id=?";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1,doctor.getFirstName());
			ps.setString(2, doctor.getLastName());
			
			
			ps.setInt(3, doctor.getGender());
			ps.setDate(4, new java.sql.Date(doctor.getDob().getTime()));
			ps.setString(5, doctor.getQualification());
			ps.setString(6, doctor.getSpeciality());
			ps.setString(7, doctor.getWorkLocation());
			ps.setString(8, doctor.getAddress1());
			ps.setString(9, doctor.getAddress2());
			ps.setString(10, doctor.getCity());
			ps.setString(11, doctor.getState());
			ps.setString(12, doctor.getCountry());
			ps.setLong(13, doctor.getPinCode());
			ps.setString(14, doctor.getLandMark());
			ps.setInt(15, doctor.getBloodGroup());
			ps.setLong(16, doctor.getLandLine());
			
			ps.setString(17, doctor.getAboutMe());
			ps.setInt(18, doctor.getId());

			i=ps.executeUpdate();
		}catch (SQLException e) {
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	
	public int updatePatientProfile(Patient doctor) throws DaoException {
		int i=0;
		Connection con = null;
		PreparedStatement ps = null;
		String query="update patient set FirstName=?,LastName=?,Gender=?,DOB=?,EmergencyContact1=?,EmergencyContact2=?,Address1=?,"+
				"Address2=?,City=?,State=?,Country=?,PinCode=?,LandMark=?,BloodGroup=?,LandLineNumber=?,AboutMe=? where Id=?";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1,doctor.getFirstName());
			ps.setString(2, doctor.getLastName());
			
			
			ps.setInt(3, doctor.getGender());
			ps.setDate(4, new java.sql.Date(doctor.getDob().getTime()));
			ps.setLong(5, doctor.getEmergency1());
			ps.setLong(6, doctor.getEmergency2());
			
			ps.setString(7, doctor.getAddress1());
			ps.setString(8, doctor.getAddress2());
			ps.setString(9, doctor.getCity());
			ps.setString(10, doctor.getState());
			ps.setString(11, doctor.getCountry());
			ps.setLong(12, doctor.getPinCode());
			ps.setString(13, doctor.getLandMark());
			ps.setInt(14, doctor.getBloodGroup());
			ps.setLong(15, doctor.getLandLine());
			
			ps.setString(16, doctor.getAboutMe());
			ps.setInt(17, doctor.getId());

			i=ps.executeUpdate();
		}catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}


	public int updatePharmacyProfile(Pharmacy doctor) throws DaoException {
		int i=0;
		Connection con = null;
		PreparedStatement ps = null;
		String query="update pharmacy set PharamacyName=?,Address1=?,"+
				"Address2=?,City=?,State=?,Country=?,PinCode=?,LandMark=?,LandLineNumber=?,AboutMe=? where Id=?";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1,doctor.getPharmacyName());
			ps.setString(2, doctor.getAddress1());
			ps.setString(3, doctor.getAddress2());
			ps.setString(4, doctor.getCity());
			ps.setString(5, doctor.getState());
			ps.setString(6, doctor.getCountry());
			ps.setLong(7, doctor.getPinCode());
			ps.setString(8, doctor.getLandMark());
			ps.setLong(9, doctor.getLandLine());
			
			ps.setString(10, doctor.getAboutMe());
			ps.setInt(11, doctor.getId());

			i=ps.executeUpdate();
		}catch (SQLException e) {
			
			logger.error(Level.SEVERE,e); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

}
