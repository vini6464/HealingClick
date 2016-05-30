package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vinod.exception.DaoException;
import com.vinod.model.Comment;
import com.vinod.model.Doctor;
import com.vinod.model.ForumPost;
import com.vinod.model.Login;
import com.vinod.util.DBUtil;

public class ForumDao {

	public List<ForumPost> getAllDoctorPost(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ForumPost> posts = new ArrayList<ForumPost>();
		String query = "select * from doctorpost where doctorid = ?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				ForumPost post = new ForumPost();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setLikes(rs.getInt("likes"));
				post.setComments(rs.getInt("comments"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return posts;
	}

	public List<Comment> getAllDoctorCommentsById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Comment> comments = new ArrayList<Comment>();
		String query = "select * from doctorcomment where postid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Comment comment = new Comment();
				comment.setForumPostid(rs.getInt("postid"));
				comment.setDoctorId(rs.getInt("doctorid"));
				comment.setComment(rs.getString("comment"));
				comment.setCreated(rs.getTimestamp("commentedon"));
				comment.setId(rs.getInt("commentid"));
				
				comments.add(comment);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return comments;
	}

	public int checkDoctorLikedOrNot(int postId, int doctorId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int i = 0;
		String query = "select count(*) from doctorlike where postid=? and doctorid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,postId);
			ps.setInt(2, doctorId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int saveDoctorPost(ForumPost post) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		
		String query = "insert into doctorpost (name,content,postimage,doctorid,likes) values (?,?,?,?,0)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,post.getName());
			ps.setString(2, post.getContent());
			ps.setString(3, post.getForumImage());
			ps.setInt(4, post.getDoctorId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}
	
	//************************************************************************************************************
	
	public List<ForumPost> getAllPatientPost() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ForumPost> posts = new ArrayList<ForumPost>();
		String query = "select * from patientpost";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				ForumPost post = new ForumPost();
				post.setId(rs.getInt("id"));
				post.setPatientId(rs.getInt("patientid"));
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setLikes(rs.getInt("likes"));
				post.setComments(rs.getInt("comments"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return posts;
	}

	public List<Comment> getAllPatientCommentsById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Comment> comments = new ArrayList<Comment>();
		String query = "select * from patientcomment where postid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Comment comment = new Comment();
				comment.setForumPostid(rs.getInt("postid"));
				comment.setPatientId(rs.getInt("patientid"));
				comment.setComment(rs.getString("comment"));
				comment.setCreated(rs.getTimestamp("commentedon"));
				comment.setId(rs.getInt("commentid"));
				
				comments.add(comment);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return comments;
	}

	public int checkPatientLikedOrNot(int postId, int patientId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int i = 0;
		String query = "select count(*) from patientlike where postid=? and patientid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,postId);
			ps.setInt(2, patientId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int savePatientPost(ForumPost post) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		
		String query = "insert into patientpost (name,content,postimage,patientid,likes) values (?,?,?,?,0)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,post.getName());
			ps.setString(2, post.getContent());
			ps.setString(3, post.getForumImage());
			ps.setInt(4, post.getPatientId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}
	//*****************************************************************************************************
	public List<ForumPost> getAllPharmacyPost() throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<ForumPost> posts = new ArrayList<ForumPost>();
		String query = "select * from pharmacypost";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				ForumPost post = new ForumPost();
				post.setId(rs.getInt("id"));
				post.setPharmacyId(rs.getInt("pharmacyid"));
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setLikes(rs.getInt("likes"));
				post.setComments(rs.getInt("comments"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return posts;
	}

	public List<Comment> getAllPharmacyCommentsById(int id) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Comment> comments = new ArrayList<Comment>();
		String query = "select * from pharmacycomment where postid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Comment comment = new Comment();
				comment.setForumPostid(rs.getInt("postid"));
				comment.setPharmacyId(rs.getInt("pharmacyid"));
				comment.setComment(rs.getString("comment"));
				comment.setCreated(rs.getTimestamp("commentedon"));
				comment.setId(rs.getInt("commentid"));
				
				comments.add(comment);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return comments;
	}

	public int checkPharmacyLikedOrNot(int postId, int pharmacyId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		int i = 0;
		String query = "select count(*) from pharmacylike where postid=? and pharmacyid=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1,postId);
			ps.setInt(2, pharmacyId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public int savePharmacyPost(ForumPost post) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		
		String query = "insert into pharmacypost (name,content,postimage,pharmacyid,likes) values (?,?,?,?,0)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,post.getName());
			ps.setString(2, post.getContent());
			ps.setString(3, post.getForumImage());
			ps.setInt(4, post.getPharmacyId());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return i;
	}

	public ForumPost getDoctorPostById(int postId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ForumPost post = new ForumPost();
		String query = "select * from doctorpost where id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setLikes(rs.getInt("likes"));
				post.setComments(rs.getInt("comments"));
				post.setForumImage(rs.getString("postimage"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return post;
	}

	public ForumPost getPatientPostById(int postId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ForumPost post = new ForumPost();
		String query = "select * from patientpost where id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				post.setId(rs.getInt("id"));
				post.setPatientId(rs.getInt("patientid"));
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setLikes(rs.getInt("likes"));
				post.setComments(rs.getInt("comments"));
				post.setForumImage(rs.getString("postimage"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return post;
	}

	public ForumPost getPharmacyPostById(int postId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		ForumPost post = new ForumPost();
		String query = "select * from pharmacypost where id=?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				post.setId(rs.getInt("id"));
				post.setPharmacyId(rs.getInt("pharmacyid"));
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setLikes(rs.getInt("likes"));
				post.setComments(rs.getInt("comments"));
				post.setForumImage(rs.getString("postimage"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return post;
	}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++
	public void saveDoctorComment(int postId, int id, String comment) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "insert into doctorcomment(commentid,postid,doctorid,comment) values(0,?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			ps.setInt(2, id);
			ps.setString(3, comment);
			ps.executeUpdate();
			
					} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		
	}

	public void savePharmacyComment(int postId, int id, String comment) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "insert into pharmacycomment(commentid,postid,pharmacyid,comment) values(0,?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			ps.setInt(2, id);
			ps.setString(3, comment);
			ps.executeUpdate();
			
					} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void savePatientComment(int postId, int id, String comment) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "insert into patientcomment(commentid,postid,patientid,comment) values(0,?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			ps.setInt(2, id);
			ps.setString(3, comment);
			ps.executeUpdate();
			
					} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void deleteForum(Login login, int postId) throws DaoException {
		String query ="";
		if(login.getType()==  1)
		{
			query = "delete from doctorpost where id=?";
		}
		if(login.getType()== 2)
		{
			query = "delete from patientpost where id=?";
		}
		if(login.getType()== 3)
		{
			query = "delete from pharmacypost where id=?";
		}
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			
			ps.setInt(1, postId);
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
	
	public void addDoctorLikePost(int postId, int id,Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query ="";
		if(login.getType()==1)
		{
			query = "insert into doctorlike (postid,doctorid) values (?,?)";
		}
		if(login.getType()==2)
		{
			query = "insert into patientlike (postid,patientid) values (?,?)";
		}
		if(login.getType()==3)
		{
			query = "insert into pharmacylike (postid,pharmacyid) values (?,?)";
		}

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, postId);
			ps.setInt(2, id);
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void updateDoctorLike(int postId, int count,Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query ="";
		if(login.getType()==1)
		{
			query = "update doctorpost set likes=? where id=?";
		}
		if(login.getType()==2)
		{
			query = "update patientpost set likes=? where id=?";
		}
		if(login.getType()==3)
		{
			query = "update pharmacypost set likes=? where id=?";
		}

		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, count);
			ps.setInt(2, postId);
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void removeDoctorLikePost(int postId, int id,Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query ="";
		if(login.getType()==1)
		{
			query = "delete from doctorlike where postid=? and doctorid=?";
		}
		if(login.getType()==2)
		{
			query = "delete from patientlike where postid=? and patientid=?";
		}
		if(login.getType()==3)
		{
			query = "delete from pharmacylike where postid=? and pharmacyid=?";
		}

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, postId);
			ps.setInt(2, id);
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public void saveMembers(int postId, String doctorId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "insert into postmember (postid,doctorid) values (?,?)";
		

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, postId);
			ps.setInt(2, Integer.parseInt(doctorId));
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<Doctor> getGroupMembersByPostId(int postId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		
		String query = "select * from doctor,postmember where (doctor.Id = postmember.doctorid) and postmember.postid=? and doctor.isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, postId);
			
			
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
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}

	public void getGroupMemberPost(int id, List<ForumPost> posts) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	
		String query = "select * from doctorpost,postmember where doctorpost.id = postmember.postid and postmember.doctorid = ?";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setLong(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				ForumPost post = new ForumPost();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setName(rs.getString("name"));
				post.setContent(rs.getString("content"));
				post.setLikes(rs.getInt("likes"));
				post.setComments(rs.getInt("comments"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				posts.add(post);
			}
		} 
		 catch (SQLException e) {
			System.out.println("\n In DAO Error:"+e.getMessage()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		
	}



}
