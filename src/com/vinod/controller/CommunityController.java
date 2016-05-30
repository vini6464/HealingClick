package com.vinod.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.vinod.model.Community;
import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Notification;
import com.vinod.model.Post;
import com.vinod.service.CommunityService;
import com.vinod.service.LoginService;

/**
 * Servlet implementation class CommunityController
 */
@WebServlet("*.community")
@MultipartConfig
public class CommunityController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String uri=request.getRequestURI();
		System.out.println(uri);
		CommunityService service = new CommunityService();
		LoginService lService = new LoginService();


		String target = "home.jsp";

		Login login1 = new Login();
		try {
			if(null == request.getSession().getAttribute("login")){  
				request.setAttribute("error", "Please Login To Continue.");
				target="home.jsp";
				request.getRequestDispatcher(target).forward(request, response); 
				return;

			}

			login1=(Login) request.getSession().getAttribute("login");
			int i=login1.getId();
		}
		catch (Exception e) {
			request.setAttribute("error", "Please Login To Continue.");
			target="home.jsp";
			request.getRequestDispatcher(target).forward(request, response);
			return;
			//response.sendRedirect(target);
		}

		try {
			if(uri.endsWith("createCommunity.community"))
			{
				
					Login login=(Login) request.getSession().getAttribute("login");
					lService.setLastActive(login);
					int id=login.getId();

					if(login.getType()==1)
					{
						request.setAttribute("doctor", lService.getDoctorById(id));
						List<Doctor> doctors = lService.getAllDoctorsForDoctor(id);
						request.setAttribute("doctors", doctors);
						target="doctorCreateCommunity.jsp";

					}
			}
			
			if(uri.endsWith("addCommunity.community"))
			{ 
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
			
				
				String title = request.getParameter("title");
				String description = request.getParameter("description");
				String[] doctorsId = request.getParameterValues("doctors");
				String filePath = "";

				Part filePart = request.getPart("file");

				if(filePart.getSize()!=0)
				{
					String SAVE_DIR = "Images"+ File.separator +"Post"+ File.separator +login.getUserName()+new Date().getTime();
					// gets absolute path of the web application
					String appPath = request.getServletContext().getRealPath("");
					// constructs path of the directory to save uploaded file
					String savePath = appPath + File.separator + SAVE_DIR;

					// creates the save directory if it does not exists
					File fileSaveDir = new File(savePath);
					if (!fileSaveDir.exists()) {
						fileSaveDir.mkdir();
					}

					for (Part part : request.getParts()) {
						String fileName = extractFileName(part);
						if(fileName!=null)
						{
							filePath= SAVE_DIR+ File.separator + fileName;
						}

						part.write(savePath + File.separator + fileName);


					}
				}
				
				Community community = new Community();
				community.setDoctorId(id);
				community.setTitle(title);
				community.setFilePath(filePath);
				community.setDescription(description);
				int communityId = service.saveCommunity(community);
				community.setId(communityId);
				Doctor doctor = lService.getDoctorById(id);
				List<Doctor> doctors = new ArrayList<Doctor>();
				
				for(int j=0;j<doctorsId.length;j++)
				{
					service.saveCommunityMembers(communityId , doctorsId[j]);
					Doctor doctor1 = lService.getDoctorById(Integer.parseInt(doctorsId[j]));
					doctors.add(doctor1);
					
					String content1 = "<a href='getCommunity.community?id="+communityId+" '>Dr."+doctor.getFirstName()+" "+doctor.getLastName()+" Has Created "+title+" Community with you </a>";
					Notification notification = new Notification(0, doctor.getId(), content1,doctor1.getId() , 0,1);
					notification.setImage(doctor.getImage());
					lService.saveNotification(notification);
				}
				
				doctors.add(doctor);
				community.setDoctors(doctors);
				request.setAttribute("community", community);
				request.setAttribute("insert", 1);
				request.setAttribute("doctor", doctor);
				
				List<Doctor> doctors1 = lService.getAllDoctorsForDoctor(id);
				for(int j=0;j<doctors.size();j++)
				{
					int stop = 0;
					for(int i=0;i<doctors1.size() && stop==0;j++)
					{
						if(doctors.get(j).getId() == doctors1.get(j).getId()){
							stop=1;
							doctors1.remove(i);
						}
					}
				}
				request.setAttribute("doctors", doctors1);
				
				request.setAttribute("error", "Successfully created Community");
				
				target="doctorViewCommunity.jsp";

			}
			
			
			if(uri.endsWith("editCommunity.community"))
			{ 
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
			
				
				String title = request.getParameter("title");
				String description = request.getParameter("description");
				String filePath = "";
				int communityId = Integer.parseInt(request.getParameter("communityId"));
				Part filePart = request.getPart("file");

				if(filePart.getSize()!=0)
				{
					String SAVE_DIR = "Images"+ File.separator +"Post"+ File.separator +login.getUserName()+new Date().getTime();
					// gets absolute path of the web application
					String appPath = request.getServletContext().getRealPath("");
					// constructs path of the directory to save uploaded file
					String savePath = appPath + File.separator + SAVE_DIR;

					// creates the save directory if it does not exists
					File fileSaveDir = new File(savePath);
					if (!fileSaveDir.exists()) {
						fileSaveDir.mkdir();
					}

					for (Part part : request.getParts()) {
						String fileName = extractFileName(part);
						if(fileName!=null)
						{
							filePath= SAVE_DIR+ File.separator + fileName;
						}

						part.write(savePath + File.separator + fileName);


					}
				}
				
				Community community = new Community();
				community.setDoctorId(id);
				community.setTitle(title);
				community.setFilePath(filePath);
				community.setDescription(description);
				community.setId(communityId);
				service.editCommunity(community);
				
				Community community1 = service.getCommunityById(communityId);
				
				Doctor doctor = lService.getDoctorById(id);
				List<Doctor> doctors = service.getDoctorMembers(communityId);
				
				List<Doctor> doctors1 = lService.getAllDoctorsForDoctor(id);
				for(int j=0;j<doctors.size();j++)
				{
					int stop = 0;
					for(int i=0;i<doctors1.size() && stop==0;i++)
					{
						if(doctors.get(j).getId() == doctors1.get(i).getId()){
							stop=1;
							doctors1.remove(i);
						}
					}
				}
				
				doctors.add(doctor);
				community1.setDoctors(doctors);
				request.setAttribute("doctors", doctors1);
				request.setAttribute("doctor", doctor);
				request.setAttribute("error", "Successfully Edited Community");
				request.setAttribute("community", community1);
				target="doctorViewCommunity.jsp";

			}
			
			if(uri.endsWith("getCommunity.community"))
			{ 

				int connected = 0;
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				int communityId = Integer.parseInt(request.getParameter("id"));
				//View Community
				Doctor doctor = lService.getDoctorById(id);
				request.setAttribute("doctor", doctor);
				Community community = service.getCommunityById(communityId);
				if(community.getId() != 0){
				
					if(community.getDoctorId()==id){
						
						connected = 1;
						List<Doctor> doctors = service.getDoctorMembers(communityId);
						
						List<Doctor> doctors1 = lService.getAllDoctorsForDoctor(id);
						for(int j=0;j<doctors.size();j++)
						{
							int stop = 0;
							for(int i=0;i<doctors1.size() && stop==0;i++)
							{
								if(doctors.get(j).getId() == doctors1.get(i).getId()){
									stop=1;
									doctors1.remove(i);
								}
							}
						}
						
						doctors.add(doctor);
						community.setDoctors(doctors);
						request.setAttribute("doctors", doctors1);
						
					}
					else{
						
						List<Doctor> doctors = service.getDoctorMembers(communityId);
						doctors.add(lService.getDoctorById(community.getDoctorId()));
						community.setDoctors(doctors);
						for(int j=0;j<doctors.size();j++)
						{
							if(login.getId() == doctors.get(j).getId()){
								connected = 1;
							}
						
						}
					}
					
					
					
					Doctor doctor1 = lService.getDoctorById(community.getDoctorId());
					community.setDoctorName("Dr."+doctor1.getFirstName()+" "+doctor1.getLastName());
					community.setDoctorImage(doctor1.getImage());
					
					request.setAttribute("community", community);
					target="doctorViewCommunity.jsp";
				}
				
				if(connected == 0){
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This Community doesnot exist or You are not member");
				}
				

			}
			
			if(uri.endsWith("community.community"))
			{ 

				
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				//View Community
				Doctor doctor = lService.getDoctorById(id);
				request.setAttribute("doctor", doctor);
				List<Community> communities = service.getAllCommunities(login);
				for(int j=0;j<communities.size();j++)
				{
					if(login.getId()==communities.get(j).getDoctorId()){
						communities.get(j).setDoctorName("Dr."+doctor.getFirstName()+" "+doctor.getLastName());
						communities.get(j).setDoctorImage(doctor.getImage());
					}else
					{
						Doctor doctor1 = lService.getDoctorById(communities.get(j).getDoctorId());
						communities.get(j).setDoctorName("Dr."+doctor1.getFirstName()+" "+doctor1.getLastName());
						communities.get(j).setDoctorImage(doctor1.getImage());
					}
				
				}
				request.setAttribute("communities", communities);
				target="doctorCommunity.jsp";
				
				

			}
			
			if(uri.endsWith("delete.community"))
			{ 

				
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				int communityId = Integer.parseInt(request.getParameter("id"));
				
				Doctor doctor = lService.getDoctorById(id);
				Community community = service.getCommunityById(communityId);
				List<Doctor> doctors = service.getDoctorMembers(communityId);
				
				for(int j=0;j<doctors.size();j++)
				{
					String content1 = "Dr."+doctor.getFirstName()+" "+doctor.getLastName()+" Has Deleted "+community.getTitle()+" Community";
					Notification notification = new Notification(0, doctor.getId(), content1,doctors.get(j).getId() , 0,1);
					notification.setImage(doctor.getImage());
					lService.saveNotification(notification);
				
				}
				
				
				List<Community> communities = service.getAllCommunities(login);
				for(int j=0;j<communities.size();j++)
				{
					if(login.getId()==communities.get(j).getDoctorId()){
						communities.get(j).setDoctorName("Dr."+doctor.getFirstName()+" "+doctor.getLastName());
						communities.get(j).setDoctorImage(doctor.getImage());
					}else
					{
						Doctor doctor1 = lService.getDoctorById(communities.get(j).getDoctorId());
						communities.get(j).setDoctorName("Dr."+doctor1.getFirstName()+" "+doctor1.getLastName());
						communities.get(j).setDoctorImage(doctor1.getImage());
					}
				
				}
				request.setAttribute("communities", communities);
				
				request.setAttribute("doctor", doctor);
				service.deleteCommunity(communityId , login);
				target="doctorCommunity.jsp";

			}
			
			if(uri.endsWith("addPost.community"))
			{ 


				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				
				int postType = Integer.parseInt(request.getParameter("postType"));
				int communityId = Integer.parseInt(request.getParameter("communityId"));
				
				String status = request.getParameter("status");
				String filePath = "";
				if(postType == 1){
					filePath = "";
				}
				if(postType == 2 || postType == 3){
					Part filePart = request.getPart("file");

					if(filePart.getSize()!=0)
					{
						String SAVE_DIR = "Images"+ File.separator +"Post"+ File.separator +login.getUserName()+new Date().getTime();
						// gets absolute path of the web application
						String appPath = request.getServletContext().getRealPath("");
						// constructs path of the directory to save uploaded file
						String savePath = appPath + File.separator + SAVE_DIR;

						// creates the save directory if it does not exists
						File fileSaveDir = new File(savePath);
						if (!fileSaveDir.exists()) {
							fileSaveDir.mkdir();
						}

						for (Part part : request.getParts()) {
							String fileName = extractFileName(part);
							if(fileName!=null)
							{
								filePath= SAVE_DIR+ File.separator + fileName;
							}

							part.write(savePath + File.separator + fileName);

						}
					}
					else
					{
						if(postType == 3){
							filePath = request.getParameter("videourl");
						}
					}
				}
				
				Post post = new Post();
				post.setDoctorId(id);
				post.setPrivacy(0);
				post.setPostType(postType);
				post.setStatus(status);
				post.setFilePath(filePath);
				post.setCommunityId(communityId);
				service.saveCommunityPost(post);
				
				request.setAttribute("insert", 1);

				//View Community
				Doctor doctor = lService.getDoctorById(id);
				request.setAttribute("doctor", doctor);
				Community community = service.getCommunityById(communityId);
				List<Doctor> doctors = service.getDoctorMembers(communityId);
				
				Doctor doctor1 = lService.getDoctorById(community.getDoctorId());
				community.setDoctorName("Dr."+doctor1.getFirstName()+" "+doctor1.getLastName());
				community.setDoctorImage(doctor1.getImage());
				
				request.setAttribute("community", community);
				for(int j=0;j<doctors.size();j++)
				{
					if(doctors.get(j).getId() != login.getId()){
					String content1 = "<a href='getCommunity.community?id="+communityId+" '>Dr."+doctor.getFirstName()+" "+doctor.getLastName()+" Has Created Post in "+community.getTitle()+" Community  </a>";
					Notification notification = new Notification(0, doctor.getId(), content1,doctors.get(j).getId() , 0,1);
					notification.setImage(doctor.getImage());
					lService.saveNotification(notification);
					}
				
				}
				
				if(community.getDoctorId() != login.getId()){
					String content1 = "<a href='getCommunity.community?id="+communityId+" '>Dr."+doctor.getFirstName()+" "+doctor.getLastName()+" Has Created Post in "+community.getTitle()+" Community  </a>";
					Notification notification = new Notification(0, doctor.getId(), content1,community.getDoctorId() , 0,1);
					notification.setImage(doctor.getImage());
					lService.saveNotification(notification);
				}
				
				
				List<Doctor> doctors1 = lService.getAllDoctorsForDoctor(id);
				for(int j=0;j<doctors.size();j++)
				{
					int stop = 0;
					for(int i=0;i<doctors1.size() && stop==0;i++)
					{
						if(doctors.get(j).getId() == doctors1.get(i).getId()){
							stop=1;
							doctors1.remove(i);
						}
					}
				}
				doctors.add(doctor);
				community.setDoctors(doctors);
				request.setAttribute("doctors", doctors1);
				request.setAttribute("error", "Successfully created Post");
				
				target="doctorViewCommunity.jsp";
			}

		} catch (Exception e) {
			try {
				
				System.out.println("\n In controller Error:"+e.getMessage());
				target = lService.setErrorControl(request,target,login1);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
			} catch (Exception e1) {

				request.setAttribute("error", "Please Login To Continue.");
				target="home.jsp";
			}

		}
		request.getRequestDispatcher(target).forward(request, response);
		
	}
	
	private String extractFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");


		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {


				if(content.contains(":"))
				{

					content = content.substring(
							content.indexOf('=') + 1).trim().replace("\"", "");
					File file = new File(content);
					String name = file.getName();
					return name;
				}
				else
				{
					return content.substring(
							content.indexOf('=') + 1).trim().replace("\"", "");
				}

			}
		}
		return null;
	}

}
