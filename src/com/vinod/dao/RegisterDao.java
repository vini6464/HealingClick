package com.vinod.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vinod.exception.DaoException;
import com.vinod.model.Doctor;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.util.DBUtil;

public class RegisterDao {

	public int registerDoctor(Doctor doctor) throws DaoException {
		int i=0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query="insert into doctor(UserName,Password,FirstName,LastName,ProfileImage,Gender,DOB,Qualification,Speciality,WorkLocation,Address1,"+
				"Address2,City,State,Country,PinCode,LandMark,BloodGroup,LandLineNumber,MobileNumber,EmailId,AboutMe,UserCreationDate,lastactive) values"+
				"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,doctor.getUserName());
			ps.setString(2, doctor.getPassWord());
			ps.setString(3,doctor.getFirstName());
			ps.setString(4, doctor.getLastName());
			ps.setString(5,doctor.getImage());
			
			ps.setInt(6, doctor.getGender());
			ps.setDate(7, new java.sql.Date(doctor.getDob().getTime()));
			ps.setString(8, doctor.getQualification());
			ps.setString(9, doctor.getSpeciality());
			ps.setString(10, doctor.getWorkLocation());
			ps.setString(11, doctor.getAddress1());
			ps.setString(12, doctor.getAddress2());
			ps.setString(13, doctor.getCity());
			ps.setString(14, doctor.getState());
			ps.setString(15, doctor.getCountry());
			ps.setLong(16, doctor.getPinCode());
			ps.setString(17, doctor.getLandMark());
			ps.setInt(18, doctor.getBloodGroup());
			ps.setLong(19, doctor.getLandLine());
			ps.setLong(20, doctor.getMobile());
			ps.setString(21, doctor.getEmailId());
			ps.setString(22, doctor.getAboutMe());
			ps.setTimestamp(23, doctor.getCreationDate());
			ps.setTimestamp(24, doctor.getCreationDate() );
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int registerPatient(Patient patient) throws DaoException {
		int i=0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query="insert into patient(UserName,Password,FirstName,LastName,ProfileImage,Gender,DOB,Address1,"+
				"Address2,City,State,Country,PinCode,LandMark,BloodGroup,LandLineNumber,MobileNumber,EmergencyContact1,EmergencyContact2,EmailId,AboutMe,UserCreationDate,lastactive) values"+
				"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,patient.getUserName());
			ps.setString(2, patient.getPassWord());
			ps.setString(3,patient.getFirstName());
			ps.setString(4, patient.getLastName());
			ps.setString(5, patient.getImage());
		
			ps.setInt(6, patient.getGender());
			ps.setDate(7, new java.sql.Date(patient.getDob().getTime()));
			ps.setString(8, patient.getAddress1());
			ps.setString(9, patient.getAddress2());
			ps.setString(10, patient.getCity());
			ps.setString(11, patient.getState());
			ps.setString(12, patient.getCountry());
			ps.setLong(13,patient.getPinCode());
			ps.setString(14, patient.getLandMark());
			ps.setInt(15, patient.getBloodGroup());
			ps.setLong(16, patient.getLandLine());
			ps.setLong(17, patient.getMobile());
			ps.setLong(18, patient.getEmergency1());
			ps.setLong(19, patient.getEmergency2());
			ps.setString(20, patient.getEmailId());
			ps.setString(21, patient.getAboutMe());
			ps.setTimestamp(22, patient.getCreationDate());
			ps.setTimestamp(23, patient.getCreationDate() );
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i; 

	}

	public int registerPharmacy(Pharmacy pharmacy) throws DaoException {
		int i=0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query="insert into pharmacy(UserName,Password,FirstName,LastName,ProfileImage,Gender,DOB,PharamacyName,LicensedTo,Address1,"+
				"Address2,City,State,Country,PinCode,LandMark,BloodGroup,LandLineNumber,MobileNumber,ProprietorName,ProprietorAddress,ProprietorContactNumber,ProprietorEmailAddress,EmailId,AboutMe,UserCreationDate,lastactive) values"+
				"(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,pharmacy.getUserName());
			ps.setString(2, pharmacy.getPassWord());
			ps.setString(3,pharmacy.getFirstName());
			ps.setString(4, pharmacy.getLastName());
			ps.setString(5, pharmacy.getImage());
			
			ps.setInt(6, pharmacy.getGender());
			ps.setDate(7, new java.sql.Date(new Date().getTime()));
			ps.setString(8, pharmacy.getPharmacyName());
			ps.setString(9, pharmacy.getLicensedTo());
			ps.setString(10, pharmacy.getAddress1());
			ps.setString(11, pharmacy.getAddress2());
			ps.setString(12, pharmacy.getCity());
			ps.setString(13, pharmacy.getState());
			ps.setString(14, pharmacy.getCountry());
			ps.setLong(15, pharmacy.getPinCode());
			ps.setString(16, pharmacy.getLandMark());
			ps.setInt(17, pharmacy.getBloodGroup());
			ps.setLong(18, pharmacy.getLandLine());
			ps.setLong(19, pharmacy.getMobile());

			ps.setString(20,pharmacy.getProprietorName());
			ps.setString(21, pharmacy.getProprietorAddress());
			ps.setLong(22, pharmacy.getProprietorContact());
			ps.setString(23, pharmacy.getProprietorEmail());
			ps.setString(24, pharmacy.getEmailId());
			ps.setString(25, pharmacy.getAboutMe());
			ps.setTimestamp(26, pharmacy.getCreationDate());

			ps.setTimestamp(27, pharmacy.getCreationDate());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int checkUserNameDoctor(String userName) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from doctor where UserName=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,userName);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int checkUserNamePatient(String userName) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from patient where UserName=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,userName);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int checkUserNamePharmacy(String userName) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from pharmacy where UserName=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,userName);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int checkEmailDoctor(String email) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from doctor where EmailId=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,email);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int checkEmailPatient(String email) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from patient where EmailId=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,email);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}
	public int checkEmailPharmacy(String email) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from pharmacy where EmailId=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setString(1,email);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int checkUserNameDoctor(long mobile) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from doctor where MobileNumber=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, mobile);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int checkUserNamePatient(long mobile) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from patient where MobileNumber=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, mobile);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int checkUserNamePharmacy(long mobile) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from pharmacy where MobileNumber=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, mobile);
		
			rs= ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public List<Pharmacy> getAllPharmacies() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		String query = "select Id from pharmacy where isdeleted = 0 LIMIT 5";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Pharmacy pharmacy  = new Pharmacy();
				pharmacy.setId(rs.getInt(1));
				/*pharmacy.setUserName(rs.getString(2));
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
				pharmacy.setPharmacyName(rs.getString(17));*/
				
				pharmacies.add(pharmacy);
			}
		}  catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pharmacies;
	}
	

}
