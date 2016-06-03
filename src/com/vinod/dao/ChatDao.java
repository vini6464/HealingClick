package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vinod.exception.DaoException;
import com.vinod.model.Chat;
import com.vinod.model.Login;
import com.vinod.model.Message;
import com.vinod.model.User;
import com.vinod.util.DBUtil;

public class ChatDao {

	

	public int getChatID(Login login, long otherId, int type) throws DaoException {
		int chatId = 0;
		String query = "";
		 if(type==1)
 	    {
 	    	query = "select cid from conversation where (doctor=? or otherdoctor=?) and (doctor=? or otherdoctor=?)";
 	    }
 	    if(type==2)
 	    {
 	    	if(login.getType() == 1)
 	    	{
 	    		query = "select cid from conversation where doctor=?  and patient=? ";
 	    	}
 	    	if(login.getType() == 2)
 	    	{
 	    		query = "select cid from conversation where patient=?  and doctor=? ";
 	    	}
 	    	
 	    }
 	    if(type==3)
 	    {
 	    	if(login.getType() == 1)
 	    	{
 	    		query = "select cid from conversation where doctor=?  and pharmacy=? ";
 	    	}
 	    	if(login.getType() == 3)
 	    	{
 	    		query = "select cid from conversation where pharmacy=?  and doctor=? ";
 	    	}
 	    }
 	   if(type==4)
	    {
 		  if(login.getType() == 2)
	    	{
	    		query = "select cid from conversation where patient=?  and pharmacy=? ";
	    	}
	    	if(login.getType() == 3)
	    	{
	    		query = "select cid from conversation where pharmacy=?  and patient=? ";
	    	}
	    }
 	  if(type==5)
	    {
 		 query = "select cid from conversation where (pharmacy=? or otherpharmacy=?) and (pharmacy=? or otherpharmacy=?)";
	    }
		
 	  	Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			if(type==1)
	 	    {
				ps.setLong(1, login.getId());
				ps.setLong(2, login.getId());
				ps.setLong(3, otherId);
				ps.setLong(4, otherId);
	 	    }
	 	    if(type==2)
	 	    {
	 	    	ps.setLong(1, login.getId());
				ps.setLong(2, otherId);
	 	    	
	 	    }
	 	    if(type==3)
	 	    {
	 	    	ps.setLong(1, login.getId());
				ps.setLong(2, otherId);
	 	    }
	 	   if(type==4)
		    {
	 		   	ps.setLong(1, login.getId());
				ps.setLong(2, otherId);
		    }
	 	  if(type==5)
		    {
	 		  	ps.setLong(1, login.getId());
				ps.setLong(2, login.getId());
				ps.setLong(3, otherId);
				ps.setLong(4, otherId);
		    }
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				chatId = rs.getInt("cid");
				
			}
		}catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}	
		return chatId;
	}

	public List<Message> getMessagesByChatId(int chatId) throws DaoException {
		List<Message> messages = new ArrayList<Message>();
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select cid,reply,replier_id,time from conversation_reply where cid = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, chatId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Message message = new Message();
				message.setChatId(rs.getInt("cid"));
				message.setReply(rs.getString("reply"));
				message.setReplierId(rs.getInt("replier_id"));
				message.setCreationDate(rs.getTimestamp("time"));
				
				messages.add(message);
			}
		}catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return messages;
	}

	public int createConversation(Login login, long otherId, int type) throws DaoException {
		String query = "";
		 if(type==1)
	    {
	    	query = "insert into conversation (doctor,otherdoctor) values (?,?)";
	    }
	    if(type==2)
	    {
	    	if(login.getType() == 1)
	    	{
	    		query = "insert into conversation (doctor,patient) values (?,?)";
	    	}
	    	if(login.getType() == 2)
	    	{
	    		query = "insert into conversation (patient,doctor) values (?,?)";
	    	}
	    	
	    }
	    if(type==3)
	    {
	    	if(login.getType() == 1)
	    	{
	    		query = "insert into conversation (doctor,pharmacy) values (?,?)";
	    	}
	    	if(login.getType() == 3)
	    	{
	    		query = "insert into conversation (pharmacy,doctor) values (?,?)";
	    	}
	    }
	   if(type==4)
	    {
		  if(login.getType() == 2)
	    	{
			  	query = "insert into conversation (patient,pharmacy) values (?,?)";
	    	}
	    	if(login.getType() == 3)
	    	{
	    		query = "insert into conversation (pharmacy,patient) values (?,?)";
	    	}
	    }
	  if(type==5)
	    {
		  	query = "insert into conversation (pharmacy,otherpharmacy) values (?,?)";
	    }
		
	  Connection con = null;
	  PreparedStatement ps = null;
	  ResultSet rs = null;
	  int i =0;
	  
	  
	  try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setLong(1,login.getId());
			ps.setLong(2,otherId);
			
			
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

	public void saveMessage(Login login, int chatId, String message) throws DaoException {
		Connection con = null;
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  String query = "insert into conversation_reply (cid , reply , replier_id ,time ) values (?,?,?,?)";
		  
		  
		  try {
				con = DBUtil.getConnection();
				ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setLong(1,chatId);
				ps.setString(2, message);
				ps.setLong(3,login.getId());
				ps.setTimestamp(4, new Timestamp(new Date().getTime()));
				
				
				ps.executeUpdate();
				

			}catch (SQLException e) {
				
				System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
			}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
		
	}

	public void getActivePatients(Login login, List<User> users) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select patient.Id,patient.UserName,patient.FirstName,patient.LastName,patient.ProfileImage from patient,patientdoctor where patient.Id = patientdoctor.patientId and patient.isdeleted=0 and patientdoctor.doctorId=? and doctorAccepted=1 and patientAccepted=1 and patient.lastactive>= NOW() - INTERVAL 5 MINUTE";
		
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user  = new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setImage(rs.getString("ProfileImage"));
				user.setType(2);
				users.add(user);
			}
		} catch (SQLException e) {
		e.printStackTrace();
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void getActivePharmacies(Login login, List<User> users) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "";
		
		if(login.getType() == 1)
    	{
    		query = "select pharmacy.Id,pharmacy.UserName,pharmacy.pharamacyName,pharmacy.ProfileImage from pharmacy,doctorpharmacy where pharmacy.Id = doctorpharmacy.pharmacyId and doctorpharmacy.doctorId=? and pharmacy.isdeleted=0 and pharmacy.lastactive>= NOW() - INTERVAL 5 MINUTE";
    	}
    	if(login.getType() == 2)
    	{
    		query = "select pharmacy.Id,pharmacy.UserName,pharmacy.pharamacyName,pharmacy.ProfileImage from pharmacy,patientpharmacy where pharmacy.Id = patientpharmacy.pharmacyId and patientpharmacy.patientId=? and pharmacy.isdeleted=0 and pharmacy.lastactive>= NOW() - INTERVAL 5 MINUTE";
    	}
    	if(login.getType() == 3)
    	{
    		query = "select pharmacy.Id,pharmacy.UserName,pharmacy.pharamacyName,pharmacy.ProfileImage from pharmacy,pharmacypharmacy where (pharmacy.Id = pharmacypharmacy.pharmacyid1 or pharmacy.Id = pharmacypharmacy.pharmacyid2) and (pharmacypharmacy.pharmacyid1=? or pharmacypharmacy.pharmacyid2=?) and pharmacy.id<>? and pharmacy.isdeleted=0 and pharmacy.lastactive>= NOW() - INTERVAL 5 MINUTE";
    	}
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			if(login.getType() == 3)
	    	{
			ps.setInt(1, login.getId());
			ps.setInt(2, login.getId());
			ps.setInt(3, login.getId());
	    	}
			else
			{
				ps.setInt(1, login.getId());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user  = new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setPharmacyName(rs.getString("pharamacyName"));
				user.setImage(rs.getString("ProfileImage"));
				user.setType(3);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void getActiveDoctors(Login login, List<User> users) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		if(login.getType() == 1)
    	{
    		query = "select * from doctor,doctordoctor where (doctor.Id = doctordoctor.doctorid1 or doctor.Id = doctordoctor.doctorid2) and (doctordoctor.doctorid1=? or doctordoctor.doctorid2=?)and doctor.id<>? and doctordoctor.doctorid1Accepted=1 and doctordoctor.doctorid2Accepted=1 and doctor.isdeleted=0 and doctor.lastactive>= NOW() - INTERVAL 5 MINUTE";
    	}
    	if(login.getType() == 2)
    	{
    		query = "select doctor.Id,doctor.UserName,doctor.FirstName,doctor.LastName,doctor.ProfileImage from doctor,patientdoctor where doctor.Id = patientdoctor.doctorId and patientdoctor.patientId=? and doctorAccepted=1 and patientAccepted=1 and doctor.isdeleted=0 and doctor.lastactive>= NOW() - INTERVAL 5 MINUTE";
    	}
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			if(login.getType() == 1)
	    	{
				ps.setInt(1, login.getId());
				ps.setInt(2, login.getId());
				ps.setInt(3, login.getId());
	    	}
			if(login.getType() == 2)
	    	{
				ps.setInt(1, login.getId());
	    	}
	
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user  = new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setImage(rs.getString("ProfileImage"));
				user.setType(1);
				users.add(user);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<Chat> getNewMessages(Login login) throws DaoException {
		List<Chat> chats = new ArrayList<Chat>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "";
		
		if(login.getType() == 1)
    	{
		   query = "select DISTINCT conversation.cid , conversation.patient , conversation.doctor ,conversation.pharmacy , conversation.otherdoctor , conversation.otherpharmacy , conversation.otherpatient"
					+" from conversation , conversation_reply , doctor  "
					+" where conversation.cid =conversation_reply.cid and conversation_reply.isread=0 and ";
			
    		query = query + "(doctor.id=conversation.doctor  or  doctor.id=conversation.otherdoctor) and  (doctor.id=?) and(doctor.id <> conversation_reply.replier_id)";
    	}
    	if(login.getType() == 2)
    	{
    		
    	   query = "select DISTINCT conversation.cid , conversation.patient , conversation.doctor ,conversation.pharmacy , conversation.otherdoctor , conversation.otherpharmacy , conversation.otherpatient"
					+" from conversation , conversation_reply , patient  "
					+" where conversation.cid =conversation_reply.cid and conversation_reply.isread=0 and ";
    		query = query +" patient.id=conversation.patient and  patient.id=? and patient.id <> conversation_reply.replier_id";
    	}
    	if(login.getType() == 3)
    	{
    	   query = "select DISTINCT conversation.cid , conversation.patient , conversation.doctor ,conversation.pharmacy , conversation.otherdoctor , conversation.otherpharmacy , conversation.otherpatient"
					+" from conversation , conversation_reply , pharmacy "
					+" where conversation.cid =conversation_reply.cid and conversation_reply.isread=0 and ";
    		
    		query = query +" (pharmacy.id=conversation.pharmacy or pharmacy.id=conversation.otherpharmacy) and  pharmacy.id=? and(pharmacy.id <> conversation_reply.replier_id)";
    	}
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, login.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Chat chat  = new Chat();
				chat.setChatId(rs.getInt("cid"));
				chat.setDoctorId(rs.getInt("doctor"));
				chat.setPatientId(rs.getInt("patient"));
				chat.setPharmacyId(rs.getInt("pharmacy"));
				chat.setOtherDoctorId(rs.getInt("otherdoctor"));
				chat.setOtherPatientId(rs.getInt("otherpatient"));
				chat.setOtherPharmacyId(rs.getInt("otherpharmacy"));
				
				chats.add(chat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return chats;
	}

	public void setRead(int chatId, Login login) throws DaoException {
		
			Connection con = null;
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  String query = "update conversation_reply set isread=1 where cid=? and replier_id <> ?";
		  
		  
		  try {
				con = DBUtil.getConnection();
				ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
				ps.setLong(1,chatId);
				ps.setLong(2,login.getId());
				
				
				
				ps.executeUpdate();
				

			}catch (SQLException e) {
				
				System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
			}finally
			{
				DBUtil.releaseResource(rs);
				DBUtil.releaseResource(ps);
				DBUtil.releaseResource(con);
			}
	}

	public List<Chat> getLatestChat(Login login) throws DaoException {
		List<Chat> chats = new ArrayList<Chat>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "";
		
		if(login.getType() == 1)
    	{
    		query = query + "select * from conversation where doctor="+login.getId()+" or otherdoctor="+login.getId()+"";
    	}
    	if(login.getType() == 2)
    	{
    		query = query + "select * from conversation where patient="+login.getId()+"";
    	}
    	if(login.getType() == 3)
    	{
    		query = query + "select * from conversation where pharmacy="+login.getId()+" or otherpharmacy="+login.getId()+"";
    	}
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Chat chat  = new Chat();
				chat.setChatId(rs.getInt("cid"));
				chat.setDoctorId(rs.getInt("doctor"));
				chat.setPatientId(rs.getInt("patient"));
				chat.setPharmacyId(rs.getInt("pharmacy"));
				chat.setOtherDoctorId(rs.getInt("otherdoctor"));
				chat.setOtherPatientId(rs.getInt("otherpatient"));
				chat.setOtherPharmacyId(rs.getInt("otherpharmacy"));
				
				chats.add(chat);
			}
		} catch (SQLException e) {
		
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return chats;
	}

	public void getPatients(Login login, List<User> users) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select patient.Id,patient.UserName,patient.FirstName,patient.LastName,patient.ProfileImage from patient,patientdoctor where patient.Id = patientdoctor.patientId and patientdoctor.doctorId=? and patientdoctor.doctorAccepted=1 and patientdoctor.patientAccepted=1 and patient.isdeleted=0";
		
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user  = new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setImage(rs.getString("ProfileImage"));
				user.setType(2);
				users.add(user);
			}
		} catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void getPharmacies(Login login, List<User> users) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "";
		
		if(login.getType() == 1)
    	{
    		query = "select pharmacy.Id,pharmacy.UserName,pharmacy.pharamacyName,pharmacy.ProfileImage from pharmacy,doctorpharmacy where pharmacy.Id = doctorpharmacy.pharmacyId and doctorpharmacy.doctorId=? and pharmacy.isdeleted=0";
    	}
    	if(login.getType() == 2)
    	{
    		query = "select pharmacy.Id,pharmacy.UserName,pharmacy.pharamacyName,pharmacy.ProfileImage from pharmacy,patientpharmacy where pharmacy.Id = patientpharmacy.pharmacyId and patientpharmacy.patientId=? and pharmacy.isdeleted=0";
    	}
    	if(login.getType() == 3)
    	{
    		query = "select pharmacy.Id,pharmacy.UserName,pharmacy.pharamacyName,pharmacy.ProfileImage from pharmacy,pharmacypharmacy where (pharmacy.Id = pharmacypharmacy.pharmacyid1 or pharmacy.Id = pharmacypharmacy.pharmacyid2) and (pharmacypharmacy.pharmacyid1=? or pharmacypharmacy.pharmacyid2=?) and pharmacy.id<>? and pharmacy.isdeleted=0";
    	}
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			if(login.getType() == 3)
	    	{
			ps.setInt(1, login.getId());
			ps.setInt(2, login.getId());
			ps.setInt(3, login.getId());
	    	}
			else
			{
				ps.setInt(1, login.getId());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user  = new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setPharmacyName(rs.getString("pharamacyName"));
				user.setImage(rs.getString("ProfileImage"));
				user.setType(3);
				System.out.println(user);
				users.add(user);
			}
			
		} catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void getDoctors(Login login, List<User> users) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select doctor.Id,doctor.UserName,doctor.FirstName,doctor.LastName,doctor.ProfileImage from doctor,patientdoctor where doctor.Id = patientdoctor.doctorId and patientdoctor.patientId=? and patientdoctor.doctorAccepted=1 and patientdoctor.patientAccepted=1 and doctor.isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
	
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user  = new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setImage(rs.getString("ProfileImage"));
				user.setType(1);
				users.add(user);
			}
		}catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public Chat getChatById(int chatId) throws DaoException {
		Chat chat  = new Chat();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select * from conversation where cid=?";
		
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, chatId);
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				chat.setChatId(rs.getInt("cid"));
				chat.setDoctorId(rs.getInt("doctor"));
				chat.setPatientId(rs.getInt("patient"));
				chat.setPharmacyId(rs.getInt("pharmacy"));
				chat.setOtherDoctorId(rs.getInt("otherdoctor"));
				chat.setOtherPatientId(rs.getInt("otherpatient"));
				chat.setOtherPharmacyId(rs.getInt("otherpharmacy"));
				
				
			}
		} catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return chat;
	}

	public void getConnectedPatients(Login login, List<User> users) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select patient.Id,patient.UserName,patient.FirstName,patient.LastName,patient.ProfileImage,patient.lastactive from patient,patientdoctor where patient.Id = patientdoctor.patientId and patientdoctor.doctorId=? and patientdoctor.doctorAccepted=1 and patientdoctor.patientAccepted=1 and patient.isdeleted=0";
		
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user  = new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setImage(rs.getString("ProfileImage"));
				user.setType(2);
				user.setLastActive(rs.getTimestamp("lastactive"));
				users.add(user);
			}
		} catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void getConnectedPharmacies(Login login, List<User> users) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "";
		
		if(login.getType() == 1)
    	{
    		query = "select pharmacy.Id,pharmacy.UserName,pharmacy.pharamacyName,pharmacy.ProfileImage,pharmacy.lastactive from pharmacy,doctorpharmacy where pharmacy.Id = doctorpharmacy.pharmacyId and doctorpharmacy.doctorId=? and pharmacy.isdeleted=0 ";
    	}
    	if(login.getType() == 2)
    	{
    		query = "select pharmacy.Id,pharmacy.UserName,pharmacy.pharamacyName,pharmacy.ProfileImage,pharmacy.lastactive from pharmacy,patientpharmacy where pharmacy.Id = patientpharmacy.pharmacyId and patientpharmacy.patientId=? and pharmacy.isdeleted=0 ";
    	}
    	if(login.getType() == 3)
    	{
    		query = "select pharmacy.Id,pharmacy.UserName,pharmacy.pharamacyName,pharmacy.ProfileImage,pharmacy.lastactive from pharmacy,pharmacypharmacy where (pharmacy.Id = pharmacypharmacy.pharmacyid1 or pharmacy.Id = pharmacypharmacy.pharmacyid2) and (pharmacypharmacy.pharmacyid1=? or pharmacypharmacy.pharmacyid2=?) and pharmacy.id<>? and pharmacy.isdeleted=0 ";
    	}
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			if(login.getType() == 3)
	    	{
			ps.setInt(1, login.getId());
			ps.setInt(2, login.getId());
			ps.setInt(3, login.getId());
	    	}
			else
			{
				ps.setInt(1, login.getId());
			}
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user  = new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setPharmacyName(rs.getString("pharamacyName"));
				user.setImage(rs.getString("ProfileImage"));
				user.setType(3);
				user.setLastActive(rs.getTimestamp("lastactive"));
				users.add(user);
			}
		} catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void getConnectedDoctors(Login login, List<User> users) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "";
		if(login.getType() == 1)
    	{
    		query = "select * from doctor,doctordoctor where (doctor.Id = doctordoctor.doctorid1 or doctor.Id = doctordoctor.doctorid2) and (doctordoctor.doctorid1=? or doctordoctor.doctorid2=?)and doctor.id<>? and doctordoctor.doctorid1Accepted=1 and doctordoctor.doctorid2Accepted=1 and doctor.isdeleted=0";
    	}
    	if(login.getType() == 2)
    	{
    		query = "select doctor.Id,doctor.UserName,doctor.FirstName,doctor.LastName,doctor.ProfileImage,doctor.lastactive from doctor,patientdoctor where doctor.Id = patientdoctor.doctorId and patientdoctor.patientId=? and patientdoctor.doctorAccepted=1 and patientdoctor.patientAccepted=1 and doctor.isdeleted=0 ";
    	}
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			if(login.getType() == 1)
	    	{
				ps.setInt(1, login.getId());
				ps.setInt(2, login.getId());
				ps.setInt(3, login.getId());
	    	}
			if(login.getType() == 2)
	    	{
				ps.setInt(1, login.getId());
	    	}
	
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				User user  = new User();
				user.setId(rs.getInt("Id"));
				user.setUserName(rs.getString("UserName"));
				user.setFirstName(rs.getString("FirstName"));
				user.setLastName(rs.getString("LastName"));
				user.setImage(rs.getString("ProfileImage"));
				user.setType(1);
				user.setLastActive(rs.getTimestamp("lastactive"));
				users.add(user);
			}
		}catch (SQLException e) {
			
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

}
