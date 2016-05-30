package com.vinod.service;

import java.util.List;

import com.vinod.dao.CommunityDao;
import com.vinod.exception.DaoException;
import com.vinod.model.Community;
import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Post;

public class CommunityService {
	
	CommunityDao dao = new CommunityDao();
	
	public void saveCommunityPost(Post post) throws DaoException {
		dao.saveCommunityPost(post);
		
	}

	public int saveCommunity(Community community) throws DaoException {
		int communityId = dao.saveCommunity(community);
		return communityId;
	}

	public void saveCommunityMembers(int communityId, String doctorId) throws DaoException {
		dao.saveCommunityMembers(communityId , doctorId);
		
	}

	public Community getCommunityById(int communityId) throws DaoException {
		Community community = dao.getCommunityById(communityId);
		return community;
	}

	public List<Doctor> getDoctorMembers(int communityId) throws DaoException {
		List<Doctor> doctors = dao.getDoctorMembers(communityId);
		return doctors;
	}

	public void deleteCommunity(int communityId, Login login) throws DaoException {
		dao.deleteCommunity(communityId , login);
		
	}

	public List<Community> getAllCommunities(Login login) throws DaoException {
		List<Community> communities = dao.getAllCommunities(login);
		dao.getMemberCommunites(communities , login);
		return communities;
	}

	public void editCommunity(Community community) throws DaoException {
		dao.editCommunity(community);
		
	}

	public void removeCommunityMembers(int communityId, String doctorId) throws DaoException {
		dao.removeCommunityMembers(communityId , doctorId);
		
	}

}
