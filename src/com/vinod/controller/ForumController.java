package com.vinod.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.vinod.model.Comment;
import com.vinod.model.Doctor;
import com.vinod.model.ForumPost;
import com.vinod.model.Login;
import com.vinod.model.Notification;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.service.ForumService;
import com.vinod.service.LoginService;

/**
 * Servlet implementation class ForumController
 */
@MultipartConfig
@WebServlet("*.forum")
public class ForumController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ForumController() {
		super();

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
		ForumService service = new ForumService();
		LoginService lService = new LoginService();

		
		try {
		
		//#$FFFFFFFFF$$$$$$$$$#%$%%%%%%%%%%%%%%%%%%%%%%
		if(uri.endsWith("createPost.forum"))
		{
			
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id=login.getId();

				if(login.getType()==1)
				{
					request.setAttribute("doctor", lService.getDoctorById(id));
					List<Doctor> doctors = lService.getAllDoctorsForDoctor(id);
					request.setAttribute("doctors", doctors);
					target="doctorCreatePost.jsp";

				}

				if(login.getType()==2)
				{


					request.setAttribute("patient", lService.getPatientById(id));
				

					target="patientCreatePost.jsp";

				}

				if(login.getType()==3)
				{

					request.setAttribute("pharmacy", lService.getPharmacyById(id));
					target="pharmacyCreatePost.jsp";

				}
			
		}
		//++++++++++++++++++++++++++++++++++++++++++++++++++=
		if(uri.endsWith("deletePost.forum"))
		{
			
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id=login.getId();

				if(login.getType()==1)
				{

					int postId = Integer.parseInt(request.getParameter("id"));
					service.deleteForum(login,postId);



					List<ForumPost> posts = service.getAllDoctorPost(id);

					for(int i=0;i<posts.size();i++)
					{
						Doctor doctor = lService.getDoctorById(posts.get(i).getDoctorId());
						posts.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());

						List<Comment> comments = service.getAllDoctorCommentsById(posts.get(i).getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setDoctorName(lService.getDoctorById(comments.get(j).getDoctorId()).getUserName());
						}
						posts.get(i).setComment(comments);
						posts.get(i).setComments(comments.size());
						posts.get(i).setLiked(service.checkDoctorLikedOrNot(posts.get(i).getId(),id));
					}
					
					request.setAttribute("doctor", lService.getDoctorById(id));
					request.setAttribute("posts", posts);
					target="doctorForum.jsp";

				}

				if(login.getType()==2)
				{

					int postId = Integer.parseInt(request.getParameter("id"));
					service.deleteForum(login,postId);

					List<ForumPost> posts = service.getAllPatientPost();

					for(int i=0;i<posts.size();i++)
					{
						Patient doctor = lService.getPatientById(posts.get(i).getPatientId());
						posts.get(i).setPatientName(doctor.getFirstName()+" "+doctor.getLastName());

						List<Comment> comments = service.getAllPatientCommentsById(posts.get(i).getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setPatientName(lService.getPatientById(comments.get(j).getPatientId()).getUserName());
						}
						posts.get(i).setComment(comments);
						posts.get(i).setComments(comments.size());
						posts.get(i).setLiked(service.checkPatientLikedOrNot(posts.get(i).getId(),id));
					}
					
					
					request.setAttribute("patient", lService.getPatientById(id));
					request.setAttribute("posts", posts);

					target="patientForum.jsp";


				}

				if(login.getType()==3)
				{

					int postId = Integer.parseInt(request.getParameter("id"));
					service.deleteForum(login,postId);

					List<ForumPost> posts = service.getAllPharmacyPost();

					for(int i=0;i<posts.size();i++)
					{
						Pharmacy doctor = lService.getPharmacyById(posts.get(i).getDoctorId());
						posts.get(i).setPharmacyName(doctor.getFirstName()+" "+doctor.getLastName());

						List<Comment> comments = service.getAllPharmacyCommentsById(posts.get(i).getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setPharmacyName(lService.getPharmacyById(comments.get(j).getPharmacyId()).getUserName());
						}
						posts.get(i).setComment(comments);
						posts.get(i).setComments(comments.size());
						posts.get(i).setLiked(service.checkPharmacyLikedOrNot(posts.get(i).getId(),id));
					}
		
					request.setAttribute("pharmacy", lService.getPharmacyById(id));
					request.setAttribute("posts", posts);
					target="pharmacyForum.jsp";

				}

			
		}
			//*********************************************8
			if(uri.endsWith("forum.forum"))
			{
				
					Login login=(Login) request.getSession().getAttribute("login");
					lService.setLastActive(login);
					int id=login.getId();

					if(login.getType()==1)
					{

						List<ForumPost> posts = service.getAllDoctorPost(id);

						for(int i=0;i<posts.size();i++)
						{
							Doctor doctor = lService.getDoctorById(posts.get(i).getDoctorId());
							posts.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());

							List<Comment> comments = service.getAllDoctorCommentsById(posts.get(i).getId());
							for(int j=0;j<comments.size();j++)
							{
								comments.get(j).setDoctorName(lService.getDoctorById(comments.get(j).getDoctorId()).getUserName());
							}
							posts.get(i).setComment(comments);
							posts.get(i).setComments(comments.size());
							posts.get(i).setLiked(service.checkDoctorLikedOrNot(posts.get(i).getId(),id));
						}
						request.setAttribute("doctor", lService.getDoctorById(id));
						request.setAttribute("posts", posts);
						target="doctorForum.jsp";
					} 


					if(login.getType()==2)
					{

						List<ForumPost> posts = service.getAllPatientPost();

						for(int i=0;i<posts.size();i++)
						{
							Patient doctor = lService.getPatientById(posts.get(i).getPatientId());
							posts.get(i).setPatientName(doctor.getFirstName()+" "+doctor.getLastName());

							List<Comment> comments = service.getAllPatientCommentsById(posts.get(i).getId());
							for(int j=0;j<comments.size();j++)
							{
								comments.get(j).setPatientName(lService.getPatientById(comments.get(j).getPatientId()).getUserName());
							}
							posts.get(i).setComment(comments);
							posts.get(i).setComments(comments.size());
							posts.get(i).setLiked(service.checkPatientLikedOrNot(posts.get(i).getId(),id));
						}
						request.setAttribute("patient", lService.getPatientById(id));
						request.setAttribute("posts", posts);
						
						
						target="patientForum.jsp";
					}


					if(login.getType()==3)
					{

						List<ForumPost> posts = service.getAllPharmacyPost();

						for(int i=0;i<posts.size();i++)
						{
							Pharmacy doctor = lService.getPharmacyById(posts.get(i).getPharmacyId());
							posts.get(i).setPharmacyName(doctor.getPharmacyName());

							List<Comment> comments = service.getAllPharmacyCommentsById(posts.get(i).getId());
							for(int j=0;j<comments.size();j++)
							{
								comments.get(j).setPharmacyName(lService.getPharmacyById(comments.get(j).getPharmacyId()).getPharmacyName());
							}
							posts.get(i).setComment(comments);
							posts.get(i).setComments(comments.size());
							posts.get(i).setLiked(service.checkPharmacyLikedOrNot(posts.get(i).getId(),id));
						}
						
						request.setAttribute("pharmacy", lService.getPharmacyById(id));
						request.setAttribute("posts", posts);
						target="pharmacyForum.jsp";
					}
				
			}
			//********^%^*TRTGFFFFFFFFFFFFFFFFFF

			if(uri.endsWith("getPost.forum"))
			{
				
					Login login=(Login) request.getSession().getAttribute("login");
					lService.setLastActive(login);
					int id=login.getId();

					if(login.getType()==1)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						ForumPost post = service.getDoctorPostById(postId);


						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Post doesnot exist");
						}
						else
						{
						
						Doctor doctor = lService.getDoctorById(post.getDoctorId());
						post.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());

						List<Comment> comments = service.getAllDoctorCommentsById(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							Doctor doctor1 = lService.getDoctorById(comments.get(j).getDoctorId());
							comments.get(j).setDoctorName(doctor1.getFirstName()+" "+doctor1.getLastName());
						}
						post.setComment(comments);
						post.setComments(comments.size());
						post.setLiked(service.checkDoctorLikedOrNot(post.getId(),id));
						
						List<Doctor> doctors = service.getGroupMembersByPostId(postId);
						request.setAttribute("doctors", doctors);
						request.setAttribute("doctor", lService.getDoctorById(id));
						request.setAttribute("post", post);
						target="doctorViewPost.jsp";
						}

					}

					if(login.getType()==2)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						ForumPost post = service.getPatientPostById(postId);

						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Post doesnot exist");
						}
						else
						{
						Patient doctor = lService.getPatientById(post.getPatientId());
						post.setPatientName(doctor.getFirstName()+" "+doctor.getLastName());

						List<Comment> comments = service.getAllPatientCommentsById(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setPatientName(lService.getPatientById(comments.get(j).getPatientId()).getUserName());
						}
						post.setComment(comments);
						post.setComments(comments.size());
						post.setLiked(service.checkPatientLikedOrNot(post.getId(),id));

						request.setAttribute("patient", lService.getPatientById(id));
						request.setAttribute("post", post);
						
						
						target="patientViewPost.jsp";
						}
					}

					if(login.getType()==3)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						ForumPost post = service.getPharmacyPostById(postId);

						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Post doesnot exist");
						}
						else
						{
						Pharmacy doctor = lService.getPharmacyById(post.getPharmacyId());
						post.setPharmacyName(doctor.getPharmacyName());

						List<Comment> comments = service.getAllPharmacyCommentsById(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setPharmacyName(lService.getPharmacyById(comments.get(j).getPharmacyId()).getPharmacyName());
						}
						post.setComment(comments);
						post.setComments(comments.size());
						post.setLiked(service.checkPharmacyLikedOrNot(post.getId(),id));

						request.setAttribute("pharmacy", lService.getPharmacyById(id));
						request.setAttribute("post", post);
						target="pharmacyViewPost.jsp";
						}
					}
				
			}

			//#########################################


			if(uri.endsWith("saveComment.forum"))
			{
				
					Login login=(Login) request.getSession().getAttribute("login");
					lService.setLastActive(login);
					int id=login.getId();

					if(login.getType()==1)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						service.saveDoctorComment(postId,id,request.getParameter("comment"));
						ForumPost post = service.getDoctorPostById(postId);

						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Post doesnot exist");
						}
						else
						{
						Doctor doctor = lService.getDoctorById(post.getDoctorId());
						post.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
						
						
						

						List<Comment> comments = service.getAllDoctorCommentsById(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							Doctor doctor1 = lService.getDoctorById(comments.get(j).getDoctorId());
							comments.get(j).setDoctorName(doctor1.getFirstName()+" "+doctor1.getLastName());
						}
						post.setComments(comments.size());
						post.setComment(comments);

						post.setLiked(service.checkDoctorLikedOrNot(post.getId(),id));
						List<Doctor> doctors = service.getGroupMembersByPostId(postId);
						
						Doctor doctor1 = lService.getDoctorById(id);
						String content1 = "<a href='getPost.forum?id="+postId+" '>Dr."+doctor1.getFirstName()+" "+doctor1.getLastName()+" Has Commented in Community</a>";
						if(doctor1.getId() != doctor.getId()){
						Notification notification = new Notification(0, doctor1.getId(), content1,doctor.getId() , 0,1);
						notification.setImage(doctor1.getImage());
						lService.saveNotification(notification);
						}
						
						
						for(int j=0;j<doctors.size();j++)
						{
							if(doctors.get(j).getId() != doctor1.getId()){
								Notification notification1 = new Notification(0, doctor1.getId(), content1,doctors.get(j).getId() , 0,1);
								notification1.setImage(doctor1.getImage());
								lService.saveNotification(notification1);
							}
							
							
						}
						
						
						request.setAttribute("doctors", doctors);
						request.setAttribute("doctor", doctor1);
						request.setAttribute("post", post);
						target="doctorViewPost.jsp";
						}
					}

					if(login.getType()==2)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						service.savePatientComment(postId,id,request.getParameter("comment"));
						ForumPost post = service.getPatientPostById(postId);

						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Post doesnot exist");
						}
						else
						{
						Patient doctor = lService.getPatientById(post.getPatientId());
						post.setPatientName(doctor.getFirstName()+" "+doctor.getLastName());

						List<Comment> comments = service.getAllPatientCommentsById(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setPatientName(lService.getPatientById(comments.get(j).getPatientId()).getUserName());
						}
						post.setComment(comments);
						post.setComments(comments.size());
						post.setLiked(service.checkPatientLikedOrNot(post.getId(),id));

						request.setAttribute("patient", lService.getPatientById(id));
						request.setAttribute("post", post);
						
						
						target="patientViewPost.jsp";
						}

					}

					if(login.getType()==3)
					{

						int postId = Integer.parseInt(request.getParameter("id"));
						service.savePharmacyComment(postId,id,request.getParameter("comment"));
						ForumPost post = service.getPharmacyPostById(postId);

						if(post.getId() == 0)
						{
							target = lService.setErrorControl(request,target,login1);
							request.setAttribute("error", "This Post doesnot exist");
						}
						else
						{
						Pharmacy doctor = lService.getPharmacyById(post.getPharmacyId());
						post.setPharmacyName(doctor.getPharmacyName());

						List<Comment> comments = service.getAllPharmacyCommentsById(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setPharmacyName(lService.getPharmacyById(comments.get(j).getPharmacyId()).getUserName());
						}
						post.setComment(comments);
						post.setComments(comments.size());
						post.setLiked(service.checkPharmacyLikedOrNot(post.getId(),id));
						
						request.setAttribute("pharmacy", lService.getPharmacyById(id));
						request.setAttribute("post", post);
						
						
						target="pharmacyViewPost.jsp";
						}

					}
					request.setAttribute("insert", 1);
				
			}
			//**********************************************

			if(uri.endsWith("savePost.forum"))
			{
				
					Login login=(Login) request.getSession().getAttribute("login");
					lService.setLastActive(login);
					int id=login.getId();

					if(login.getType()==1)
					{



						String name = request.getParameter("postname");
						String content = request.getParameter("postcontent");

						String[] doctorsId = request.getParameterValues("doctors");
						
						
						
						String forumPath ="";
						Part filePart = request.getPart("file");

						
						
						if(filePart.getSize()!=0)
						{
							String SAVE_DIR = "Images"+ File.separator +"Forum"+ File.separator +"forum"+login.getUserName()+""+new Date().getTime();
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
									forumPath=SAVE_DIR + File.separator + fileName;
								}

								part.write(savePath + File.separator + fileName);

							}
						}
						ForumPost post = new ForumPost();
						post.setDoctorId(id);
						post.setName(name);
						post.setContent(content);
						post.setForumImage(forumPath);
						int postId=service.saveDoctorPost(post);
						post.setId(postId);
						Doctor doctor = lService.getDoctorById(id);
						post.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());

						for(int j=0;j<doctorsId.length;j++)
						{
							service.saveMembers(postId , doctorsId[j]);
						}
						
						List<Doctor> doctors = new ArrayList<Doctor>();
						for(int j=0;j<doctorsId.length;j++)
						{
							Doctor doctor1 = lService.getDoctorById(Integer.parseInt(doctorsId[j]));
							doctors.add(doctor1);
							
							String content1 = "<a href='getPost.forum?id="+postId+" '>Dr."+doctor.getFirstName()+" "+doctor.getLastName()+" Has Created Discussion Community with you </a>";
							Notification notification = new Notification(0, doctor.getId(), content1,doctor1.getId() , 0,1);
							notification.setImage(doctor.getImage());
							lService.saveNotification(notification);
						}
						
						request.setAttribute("doctors", doctors);
						request.setAttribute("doctor", doctor);
						request.setAttribute("msg", "Successfully Created Post");
						request.setAttribute("post", post);

						target="doctorViewPost.jsp";

					}

					if(login.getType()==2)
					{

						String name = request.getParameter("postname");
						String content = request.getParameter("postcontent");

						String forumPath ="";
						Part filePart = request.getPart("file");

						if(filePart.getSize()!=0)
						{
							String SAVE_DIR = "Images"+ File.separator +"Forum"+ File.separator +"forum"+login.getUserName()+""+new Date().getTime();
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
									forumPath=SAVE_DIR + File.separator + fileName;
								}

								part.write(savePath + File.separator + fileName);

							}
						}
						ForumPost post = new ForumPost();
						post.setPatientId(id);
						post.setName(name);
						post.setContent(content);
						post.setForumImage(forumPath);
						int postId=service.savePatientPost(post);
						post.setId(postId);
						Patient doctor = lService.getPatientById(id);
						post.setPatientName(doctor.getFirstName()+" "+doctor.getLastName());
						request.setAttribute("patient", doctor);
						request.setAttribute("msg", "Successfully Created Post");
						request.setAttribute("post", post);
						
						
						
						target="patientViewPost.jsp";

					}

					if(login.getType()==3)
					{

						String name = request.getParameter("postname");
						String content = request.getParameter("postcontent");

						String forumPath ="";
						Part filePart = request.getPart("file");

						if(filePart.getSize()!=0)
						{
							String SAVE_DIR = "Images"+ File.separator +"Forum"+ File.separator +"forum"+login.getUserName()+""+new Date().getTime();
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
									forumPath=SAVE_DIR + File.separator + fileName;
								}

								part.write(savePath + File.separator + fileName);

							}
						}
						ForumPost post = new ForumPost();
						post.setPharmacyId(id);
						post.setName(name);
						post.setContent(content);
						post.setForumImage(forumPath);
						int postId=service.savePharmacyPost(post);
						post.setId(postId);
						Pharmacy doctor = lService.getPharmacyById(id);
						post.setPharmacyName(doctor.getFirstName()+" "+doctor.getLastName());
						request.setAttribute("pharmacy", doctor);
						request.setAttribute("msg", "Successfully Created Post");
						request.setAttribute("post", post);

						target="pharmacyViewPost.jsp";

					}
					request.setAttribute("insert", 1);
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
