package com.vinod.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.vinod.exception.DaoException;
import com.vinod.model.Community;
import com.vinod.model.Doctor;
import com.vinod.model.ForumPost;
import com.vinod.model.Login;
import com.vinod.model.Post;
import com.vinod.util.DBUtil;

public class CommunityDao {
	final static Logger logger = Logger.getLogger(CommunityDao.class);

	public void saveCommunityPost(Post post) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		
		String query = "insert into post (status,posttype,privacy,doctorid,filepath,communityid,createdon) values (?,?,?,?,?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,post.getStatus());
			ps.setInt(2, post.getPostType());
			ps.setInt(3, post.getPrivacy());
			ps.setInt(4, post.getDoctorId());
			ps.setString(5,post.getFilePath());
			ps.setInt(6, post.getCommunityId());
			ps.setTimestamp(7, new Timestamp(new Date().getTime()));
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
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
		
	}

	public int saveCommunity(Community community) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int i=0;
		
		String query = "insert into community (title,description,filepath,doctorid,createdon) values (?,?,?,?,?)";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1,community.getTitle());
			ps.setString(2, community.getDescription());
			ps.setString(3, community.getFilePath());
			ps.setInt(4, community.getDoctorId());
			ps.setTimestamp(5, new Timestamp(new Date().getTime()));
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			
			while(rs.next())
			{
				i = rs.getInt(1);
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
		return i;
	
	}

	public void saveCommunityMembers(int communityId, String doctorId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "insert into communitymember (communityid,doctorid) values (?,?)";
		

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, communityId);
			ps.setInt(2, Integer.parseInt(doctorId));
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public Community getCommunityById(int communityId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Community post = new Community();
		String query = "select * from community where id=? and isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, communityId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setTitle(rs.getString("title"));
				post.setDescription(rs.getString("description"));
				post.setFilePath(rs.getString("filepath"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				
				
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

	public List<Doctor> getDoctorMembers(int communityId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Doctor> doctors = new ArrayList<Doctor>();
		
		String query = "select * from doctor,communitymember where (doctor.Id = communitymember.doctorid) and communitymember.communityid=? and doctor.isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, communityId);
			
			
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
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		return doctors;
	}

	public void deleteCommunity(int communityId, Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query ="";
		
		query = "update community set isdeleted=1 where id=? and doctorid=? ";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, communityId);
			ps.setInt(2, login.getId());
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

	public List<Community> getAllCommunities(Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Community> communities=new ArrayList<Community>();
		String query = "select * from community where doctorid=? and isdeleted=0";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Community post = new Community();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setTitle(rs.getString("title"));
				post.setDescription(rs.getString("description"));
				post.setFilePath(rs.getString("filepath"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				communities.add(post);
				
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
		return communities;
	}

	public void getMemberCommunites(List<Community> communities, Login login) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query = "select * from community,communitymember where communitymember.doctorid=? and community.isdeleted=0 and community.id = communitymember.communityid";
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query);
			ps.setInt(1, login.getId());
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				Community post = new Community();
				post.setId(rs.getInt("id"));
				post.setDoctorId(rs.getInt("doctorid"));
				post.setTitle(rs.getString("title"));
				post.setDescription(rs.getString("description"));
				post.setFilePath(rs.getString("filepath"));
				post.setCreationDate(rs.getTimestamp("createdon"));
				communities.add(post);
				
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
		
	}

	public void editCommunity(Community community) throws DaoException {
		int updateImage = 1;
		if(community.getFilePath().isEmpty()){
			updateImage = 0;
		}
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String query ="";
		if(updateImage == 1){
			query = "update community set title=?,description=?,filepath=? where id=?";
		}else{
			query = "update community set title=?,description=? where id=?";
		}
		
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			if(updateImage == 1){
				ps.setString(1, community.getTitle());
				ps.setString(2, community.getDescription());
				ps.setString(3, community.getFilePath());
				ps.setInt(4, community.getId());
				
			}else{
				ps.setString(1, community.getTitle());
				ps.setString(2, community.getDescription());
				
				ps.setInt(3, community.getId());
			}
			
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
		
	}

	public void removeCommunityMembers(int communityId, String doctorId) throws DaoException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		String query = "delete from communitymember where communityid=? and doctorid=?";
		

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, communityId);
			ps.setInt(2, Integer.parseInt(doctorId));
			
			ps.executeUpdate();
			
		} 
		 catch (SQLException e) {
			logger.error("In DAO:"+e.getStackTrace()); throw new DaoException();
		}finally
		{
			DBUtil.releaseResource(rs);
			DBUtil.releaseResource(ps);
			DBUtil.releaseResource(con);
		}
		
	}

}
