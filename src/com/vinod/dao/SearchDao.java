package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vinod.exception.DaoException;
import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.util.DBUtil;

public class SearchDao {

	public List<Doctor> getAllDoctorsForSearch(String text) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		String query = "select * from doctor where (UserName like '%"+text+"%' or FirstName like '%"+text+"%' or LastName like '%"+text+"%') and isverified=1 and isdeleted=0";
		
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
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}

	
	public List<Patient> getAllPatientsForSearch(String text) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Patient> patients = new ArrayList<Patient>();
		String query = "select * from patient where (UserName like '%"+text+"%' or FirstName like '%"+text+"%' or LastName like '%"+text+"%' ) and isverified=1 and isdeleted=0";
		
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
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return patients;
	}

	public List<Pharmacy> getAllPharmaciesForSearch(String text,int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		String query = "select * from pharmacy where Id <> ? and(UserName like '%"+text+"%' or FirstName like '%"+text+"%' or LastName like '%"+text+"%' or PharamacyName like '%"+text+"%' or PinCode like '%"+text+"%' or Address1 like '%"+text+"%' or Address2 like '%"+text+"%' or LandMark like '%"+text+"%') and isverified=1 and isdeleted=0";
		
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
				pharmacy.setPharmacyName(rs.getString(17));
				pharmacy.setConnect(getPharmacyPharmacyConnection(id,pharmacy.getId()));
				pharmacies.add(pharmacy);
			}
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pharmacies;
	}

	private int getPharmacyPharmacyConnection(int id, int id2) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from pharmacypharmacy where (pharmacyid1=? or pharmacyid1=?) and (pharmacyid2=? or pharmacyid2=?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			ps.setInt(2,id2);
			ps.setInt(3, id);
			ps.setInt(4,id2);
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		
	} finally
	{
		DBUtil.releaseResource(rs);
		DBUtil.releaseResource(ps);
		DBUtil.releaseResource(con);
	}

		return i;
	}


	public int requestSentByDoctor(int pId, int dId) throws DaoException {
		
		if(getDoctorPatientConnectionAvailable(dId,pId) == 1){
			return 0;
			
		}else{
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int i = 0;
			String query = "insert into patientdoctor values (?,?,?,?,?,0)";
			try {
				con = DBUtil.getConnection();
				ps = con.prepareStatement(query);
				ps.setInt(1, pId);
				ps.setInt(2, dId);
				ps.setInt(3, 0);
				ps.setInt(4, 1);
				ps.setInt(5, 1);
				i = ps.executeUpdate();
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
		
		
	}

	private int getDoctorPatientConnectionAvailable(int dId, int pId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from patientdoctor where patientid=? and doctorid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, pId);
			ps.setInt(2, dId);
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		
	} finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}

		return i;
	}


	public int requestSentByPatient(int dId, int pId) throws DaoException {
		
		
		if(getDoctorPatientConnectionAvailable(dId,pId) == 1){
			return 0;
			
		}else{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "insert into patientdoctor values (?,?,?,?,?,0)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, pId);
			ps.setInt(2, dId);
			ps.setInt(3, 1);
			ps.setInt(4, 0);
			ps.setInt(5, 1);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();		}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
		return i;
		}
	}

	public int patientPharmacy(int pId, int patId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = getPatientPharmacyConnection(patId,pId);
		if(i==0)
		{
			String query = "insert into patientpharmacy values (?,?,?)";
			try {
				con = DBUtil.getConnection();
				ps = con.prepareStatement(query);
				ps.setInt(1, patId);
				ps.setInt(2, pId);
				ps.setInt(3, 0);
				
				i = ps.executeUpdate();
			}catch (SQLException e) {
				System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
			}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
		}
		
		return i;
	}

	public int doctorPharmacy(int pId, int dId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = getDoctorPharmacyConnection(dId,pId);
		if(i==0)
		{
			String query = "insert into doctorpharmacy values (?,?,?)";
			try {
				con = DBUtil.getConnection();
				ps = con.prepareStatement(query);
				ps.setInt(1, dId);
				ps.setInt(2, pId);
				ps.setInt(3, 0);
				
				i = ps.executeUpdate();
			}catch (SQLException e) {
				System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
			}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
		}
		
		return i;
	}

	public int acceptByDoctor(int pId, int dId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		System.out.println(pId);
		String query = "update patientdoctor set doctoraccepted = ?,deleted = 2 where patientid = ? and doctorid = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, 1);
			ps.setInt(2, pId);
			ps.setInt(3, dId);
			
			i = ps.executeUpdate();
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

	public int acceptByPatient(int dId, int pId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "update patientdoctor set patientaccepted = ?,deleted = 2 where patientid = ? and doctorid = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, 1);
			ps.setInt(2, pId);
			ps.setInt(3, dId);
			
			i = ps.executeUpdate();
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

	public int declineByDoctor(int pId, int dId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "delete from patientdoctor where patientid = ? and doctorid = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, pId);
			ps.setInt(2, dId);
			
			
			i = ps.executeUpdate();
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

	public int declineByPatient(int dId, int pId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "delete from patientdoctor where patientid = ? and doctorid = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, pId);
			ps.setInt(2, dId);
		
			
			i = ps.executeUpdate();
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

	public List<Patient> getAllPatientsForDoctorSearch(String text, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Patient> patients = new ArrayList<Patient>();
		String query = "select * from patient where (UserName like '%"+text+"%' or FirstName like '%"+text+"%' or LastName like '%"+text+"%') and isverified=1 and isdeleted=0";
		
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
				patient.setConnect(getDoctorPatientConnection(id, patient.getId()));
				patients.add(patient);
			}
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return patients;
	}


	private int getDoctorPatientConnection(int dId, int pId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select deleted from patientdoctor where patientid=? and doctorid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, pId);
			ps.setInt(2, dId);
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		
	} finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}

		return i;
	}


	public List<Pharmacy> getAllPharmaciesForDoctorSearch(String text, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		String query = "select * from pharmacy where (UserName like '%"+text+"%' or FirstName like '%"+text+"%' or LastName like '%"+text+"%' or PharamacyName like '%"+text+"%' or PinCode like '%"+text+"%' or Address1 like '%"+text+"%' or Address2 like '%"+text+"%' or LandMark like '%"+text+"%') and isverified=1 and isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Pharmacy pharmacy  = new Pharmacy();
				pharmacy.setId(rs.getInt(1));
				pharmacy.setConnect(getDoctorPharmacyConnection(id,pharmacy.getId()));
				pharmacy.setUserName(rs.getString(2));
				pharmacy.setFirstName(rs.getString(4));
				pharmacy.setLastName(rs.getString(5));
				pharmacy.setImage(rs.getString(6));
				pharmacy.setPharmacyName(rs.getString(17));
				pharmacies.add(pharmacy);
			}
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pharmacies;
	}


	private int getDoctorPharmacyConnection(int dId, int phId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from doctorpharmacy where pharmacyid=? and doctorid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, phId);
			ps.setInt(2, dId);
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		
	} finally
	{
		DBUtil.releaseResource(rs);
		DBUtil.releaseResource(ps);
		DBUtil.releaseResource(con);
	}

		return i;
	}


	public List<Doctor> getAllDoctorsForPatientSearch(String text, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		String query = "select * from doctor where (UserName like '%"+text+"%' or FirstName like '%"+text+"%' or LastName like '%"+text+"%') and isverified=1 and isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Doctor doctor = new Doctor();
				doctor.setId(rs.getInt(1));
				doctor.setConnect(getDoctorPatientConnection(doctor.getId(),id));
				doctor.setUserName(rs.getString(2));
				doctor.setFirstName(rs.getString(4));
				doctor.setLastName(rs.getString(5));
				doctor.setImage(rs.getString(6));
				
				doctors.add(doctor);
			}
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}


	public List<Pharmacy> getAllPharmaciesForPatientSearch(String text, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();
		String query = "select * from pharmacy where (UserName like '%"+text+"%' or FirstName like '%"+text+"%' or LastName like '%"+text+"%' or PharamacyName like '%"+text+"%' or PinCode like '%"+text+"%' or Address1 like '%"+text+"%' or Address2 like '%"+text+"%' or LandMark like '%"+text+"%') and isverified=1 and isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Pharmacy pharmacy  = new Pharmacy();
				pharmacy.setId(rs.getInt(1));
				pharmacy.setConnect(getPatientPharmacyConnection(id,pharmacy.getId()));
				pharmacy.setUserName(rs.getString(2));
				pharmacy.setFirstName(rs.getString(4));
				pharmacy.setLastName(rs.getString(5));
				pharmacy.setImage(rs.getString(6));
				pharmacy.setPharmacyName(rs.getString(17));
				pharmacies.add(pharmacy);
			}
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return pharmacies;
	}


	private int getPatientPharmacyConnection(int pId, int phId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from patientpharmacy where pharmacyid=? and patientid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, phId);
			ps.setInt(2, pId);
			
			rs = ps.executeQuery();
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


	public int pharmacyPharmacy(int pId1, int pId2) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = getPharmacyPharmacyConnection(pId1,pId2);
		if(i==0){
			String query = "insert into pharmacypharmacy values (?,?,?)";
			try {
				con = DBUtil.getConnection();
				ps = con.prepareStatement(query);
				ps.setInt(1, pId1);
				ps.setInt(2, pId2);
				ps.setInt(3, 0);
				i = ps.executeUpdate();
			}catch (SQLException e) {
				System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
			}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
		}
		
		return i;
	}


	public List<Doctor> getAllDoctorsForSearch(String text, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		String query = "select * from doctor where Id <> ? and (UserName like '%"+text+"%' or FirstName like '%"+text+"%' or LastName like '%"+text+"%') and isverified=1 and isdeleted=0";
		
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
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}


	public List<Patient> getAllPatientsForSearch(String text, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Patient> patients = new ArrayList<Patient>();
		String query = "select * from patient where Id<>? and (UserName like '%"+text+"%' or FirstName like '%"+text+"%' or LastName like '%"+text+"%') and isverified=1 and isdeleted=0";
		
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
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return patients;
	}


	public List<Medicine> searchMedicines(String medicine) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Medicine> symptoms = new ArrayList<Medicine>();
		String query = "select * from medicine where name like '%"+medicine+"%'";
		
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
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return symptoms;
	}


	public List<Doctor> getAllDoctorsForDoctorSearch(String text, int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		String query = "select * from doctor where Id <> ? and (UserName like '%"+text+"%' or FirstName like '%"+text+"%' or LastName like '%"+text+"%') and isverified=1 and isdeleted=0";
		
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
				doctor.setConnect(getDoctorDoctorConnection(id,doctor.getId()));
				doctors.add(doctor);
			}
		}catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}


	private int getDoctorDoctorConnection(int dId1, int dId2) throws DaoException {
		
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
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		
	} finally
	{
		DBUtil.releaseResource(rs);
		DBUtil.releaseResource(ps);
		DBUtil.releaseResource(con);
	}

		return i;
	}


	public int deletePatientDoctorConnection(int pId, int dId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		
		String query = "delete from patientdoctor  where patientid = ? and doctorid = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, pId);
			ps.setInt(2, dId);
			
			i = ps.executeUpdate();
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


	public int deleteDoctorPharmacy(int dId, int phId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		
		String query = "delete from doctorpharmacy  where doctorid = ? and pharmacyid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, dId);
			ps.setInt(2, phId);
			
			i = ps.executeUpdate();
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


	public int deletePatientPharmacy(int pId, int phId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		
		String query = "delete from patientpharmacy  where patientid = ? and pharmacyid=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, pId);
			ps.setInt(2, phId);
			
			i = ps.executeUpdate();
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


	public int deletePharmacyPharmacy(int phId1, int phId2) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		
		String query = "delete from pharmacypharmacy  where (pharmacyid1 = ? and pharmacyid2=?) or (pharmacyid1 = ? and pharmacyid2=?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, phId1);
			ps.setInt(2, phId2);
			
			ps.setInt(3, phId2);
			ps.setInt(4, phId1);
			
			i = ps.executeUpdate();
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


	public int addDoctorDoctorConnection(int dId1, int dId2) throws DaoException {
		if(checkDoctorConnection(dId1 , dId2) == 1){
			return 0;
		}else{
			Connection con = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			int i = 0;
			String query = "insert into doctordoctor values (?,?,?,?,?,0)";
			try {
				con = DBUtil.getConnection();
				ps = con.prepareStatement(query);
				ps.setInt(1, dId1);
				ps.setInt(2, dId2);
				ps.setInt(3, 1);
				ps.setInt(4, 0);
				ps.setInt(5, 1);
				i = ps.executeUpdate();
			} catch (SQLException e) {
				System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();		}finally
				{
					DBUtil.releaseResource(rs);
					DBUtil.releaseResource(ps);
					DBUtil.releaseResource(con);
				}
			return i;
		}
		
	}


	private int checkDoctorConnection(int dId1, int dId2) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		
		String query = "select count(*) from doctordoctor  where (doctorid1 = ? and doctorid2=?) or (doctorid2 = ? and doctorid1=?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, dId1);
			ps.setInt(2, dId2);
			
			ps.setInt(3, dId1);
			ps.setInt(4, dId2);
			
			i = ps.executeUpdate();
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


	public int deleteDoctorDoctorConnection(int dId1, int dId2) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		
		String query = "delete from doctordoctor  where (doctorid1 = ? and doctorid2=?) or (doctorid2 = ? and doctorid1=?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, dId1);
			ps.setInt(2, dId2);
			
			ps.setInt(3, dId1);
			ps.setInt(4, dId2);
			
			i = ps.executeUpdate();
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


	public int acceptDoctor(int dId1, int dId2) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "update doctordoctor set doctorid2Accepted = ?,deleted = 2 where (doctorid1 = ? and doctorid2=?) or (doctorid1 = ? and doctorid2=?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, 1);
			ps.setInt(2, dId1);
			ps.setInt(3, dId2);
			
			ps.setInt(4, dId1);
			ps.setInt(5, dId2);

			
			i = ps.executeUpdate();
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


	public List<Medicine> getSearchedMedicines(Login login, String text) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Medicine> medicines = new ArrayList<Medicine>();
		String query = "select medicine.name,medicine.id from medicine where medicine.name like '%"+text+"%'";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Medicine medicine = new Medicine();
				medicine.setId(rs.getInt("id"));
				medicine.setName(rs.getString("name"));
				medicine.setStatus(checkAlreadyInList(medicine.getName() , login));
				medicines.add(medicine);
			}
		}  catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return medicines;
	}


	private int checkAlreadyInList(String string, Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "select count(*) from pharmacymedicine where pharmacyid=? and medicinename=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			ps.setString(2,string);
			
			
			rs = ps.executeQuery();
			while(rs.next())
			{
				i = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		
	} finally
	{
		DBUtil.releaseResource(rs);
		DBUtil.releaseResource(ps);
		DBUtil.releaseResource(con);
	}

		return i;
	}


	public void addMedicine(Login login, String name) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "insert into pharmacymedicine values (?,?,0.0)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			ps.setString(2, name);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();		}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
		
	}


	public void updateMedicine(Login login, String name, double cost) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "update pharmacymedicine set cost=? where pharmacyid=? and medicinename=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setDouble(1, cost);
			ps.setInt(2, login.getId());
			ps.setString(3, name);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();		}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
		
	}


	public void deleteMedicine(Login login, String name) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i = 0;
		String query = "delete from pharmacymedicine where pharmacyid=? and medicinename=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			ps.setString(2, name);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();		}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
		
	}


	public Medicine getMedicineById(int mId) throws DaoException {
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
			}
		}  catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}
}
