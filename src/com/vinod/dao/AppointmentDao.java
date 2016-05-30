package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vinod.exception.DaoException;
import com.vinod.model.Appointment;
import com.vinod.model.Event;
import com.vinod.model.Login;
import com.vinod.util.DBUtil;

public class AppointmentDao {

	public int saveAppointment(Appointment appointment) throws DaoException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i =0;
		String query="insert into events(doctorid,patientid,pharmacyid,title,description,start,end,symptom) values"+
				"(?,?,?,?,?,?,?,?)";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1,appointment.getDoctorId());
			ps.setLong(2,appointment.getPatientId());
			ps.setLong(3,appointment.getPharmacyId());
			ps.setString(4, appointment.getTitle());
			ps.setString(5,appointment.getDescription());
			ps.setString(6, appointment.getStart());
			ps.setString(7,appointment.getEnd());
			
			ps.setString(8, appointment.getSymptom());
			
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

	public List<Event> getPatientDoctorEvent(int pId, int dId) throws DaoException {
		List<Event> events = new ArrayList<Event>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select id,title,start,end,patientid from events where doctorid=? and isdeleted=0";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setLong(1, dId);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Event event = new Event();
				event.setId(rs.getInt("id"));
				event.setStart(rs.getString("start"));
				event.setEnd(rs.getString("end"));
				int id = rs.getInt("patientid");
				if(id == pId)
				{
					event.setTitle(rs.getString("title"));
				}
				else
				{
					event.setTitle("Busy");
				}
				
				events.add(event);
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
		return events;
	}

	public Appointment getEventById(int eventId) throws DaoException {
		Appointment appointment = new Appointment();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from events where id=?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, eventId);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
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
		return appointment;
	}

	public void updateAppointment(Appointment appointment) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		String query="update events set title=?,description=?,start=?,end=?,symptom=? where id=?";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setString(1, appointment.getTitle());
			ps.setString(2,appointment.getDescription());
			ps.setString(3, appointment.getStart());
			ps.setString(4,appointment.getEnd());
			
			ps.setString(5, appointment.getSymptom());
			ps.setLong(6, appointment.getId());
			

		    int i=ps.executeUpdate();	
		    System.out.println("Successfully event Updated with id "+appointment.getId()+" : "+i);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void deleteAppointment(int eventId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		String query="update events set isdeleted=1 where id=?";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setLong(1, eventId);
			

		    int i=ps.executeUpdate();	
		    System.out.println("Successfully event Deleted with id "+eventId+" : "+i);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<Event> getEvents(Login login) throws DaoException {
		List<Event> events = new ArrayList<Event>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String whereClause="";
		
		if(login.getType()== 1)
		{
			whereClause = "doctorid=?";
		}
		if(login.getType()== 2)
		{
			whereClause = "patientid=?";
		}
		if(login.getType()== 3)
		{
			whereClause = "pharmacyid=?";
		}
		
		String query = "select id,title,start,end from events where isdeleted=0 and "+whereClause;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, login.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Event event = new Event();
				event.setId(rs.getInt("id"));
				event.setStart(rs.getString("start"));
				event.setEnd(rs.getString("end"));
				event.setTitle(rs.getString("title"));
				
				events.add(event);
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
		return events;
	}

	public void acceptAppointment(int eventId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		String query="update events set status=1 where id=?";

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setLong(1, eventId);
			

		    int i=ps.executeUpdate();	
		    System.out.println("Successfully event Accepted with id "+eventId+" : "+i);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
	}

}
