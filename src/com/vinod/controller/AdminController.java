package com.vinod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vinod.exception.DaoException;
import com.vinod.model.Doctor;

import com.vinod.model.Comment;
import com.vinod.model.ForumPost;
import com.vinod.model.Log;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Notification;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Prescription;
import com.vinod.model.Review;
import com.vinod.model.User;
import com.vinod.service.AdminService;
import com.vinod.service.ForumService;
import com.vinod.service.PatientService;
import com.vinod.service.PharmacyService;
import com.vinod.service.SupportService;

import com.vinod.service.LoginService;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("*.admin")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();

	}

	final static Logger logger = Logger.getLogger(AdminController.class);
	
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
		AdminService service = new AdminService();
		LoginService lService = new LoginService();
		logger.info(uri);
		String target="adminlogin.jsp";
 
		
		if(uri.endsWith("login.admin"))
		{
			Login login = new Login();
			String userName = request.getParameter("username");
			String password = request.getParameter("password");

			if(userName.equalsIgnoreCase("healingclick") && password.equalsIgnoreCase("admin"))
			{
				login.setUserName(userName);
				login.setType(4);
				login.setId(1234);
				HttpSession session = request.getSession();
				session.setAttribute("login", login);

				int orderCount;
				try {
					orderCount = service.getOrders(0);
					
					int prescriptionCount =service.getPrescriptions(0);
					
					request.setAttribute("doctorCount", service.getTotalDoctorCount());
					request.setAttribute("patientCount", service.getTotalPatientCount());
					request.setAttribute("pharmacyCount", service.getTotalPharmacyCount());
					request.setAttribute("orderCount", orderCount);
					request.setAttribute("prescriptionCount", prescriptionCount);
					
					
					request.setAttribute("doctorMonth", service.getMonthDoctorCount());
					request.setAttribute("patientMonth", service.getMonthPatientCount());
					request.setAttribute("pharmacyMonth", service.getMonthPharmacyCount());
					
					request.setAttribute("orderMonth", service.getMonthOrder());
					request.setAttribute("prescriptionMonth", service.getMonthPrescription());
					List<Doctor> doctors;
					doctors = service.getAllNotVerifiedDoctors();
					request.setAttribute("doctors", doctors);
					
					List<Pharmacy> pharmacies;
					pharmacies = service.getAllNotVerifiedPharmacies();
					request.setAttribute("pharmacies", pharmacies);
					
					target="adminhome.jsp";
					
				} catch (Exception e) {
					logger.error(e.getMessage());
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="adminlogin.jsp";
				}
				request.getRequestDispatcher(target).forward(request, response);
			}
		}
		
		
		try {
			if(null == request.getSession().getAttribute("login")){  
				request.setAttribute("error", "Please Login to Continue");
				target="adminlogin.jsp";
				request.getRequestDispatcher(target).forward(request, response); 
				return;
				
				}
			
			Login login=(Login) request.getSession().getAttribute("login");
			int i=login.getId();
		}
		catch (Exception e) {
			request.setAttribute("error", "Please Login to Continue");
			target="adminlogin.jsp";
			request.getRequestDispatcher(target).forward(request, response);
			return;
			//response.sendRedirect(target);
		}
		
		if(uri.endsWith("logout.admin"))
		{ 

			
			request.getSession().removeAttribute("login");
				
				target="adminlogin.jsp";
			
			request.getRequestDispatcher(target).forward(request, response);

		}
		
		if(uri.endsWith("healingclick.admin"))
		{ 

			
			   request.getSession().removeAttribute("login");
				
				target="adminlogin.jsp";
			
			request.getRequestDispatcher(target).forward(request, response);

		}
		

		
		
		
		if(uri.endsWith("verifyDoctor.admin"))
		{ 

			List<Doctor> doctors;
			try {
				doctors = service.getAllNotVerifiedDoctors();
				request.setAttribute("doctors", doctors);
				target="adminActivate.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}
		
		if(uri.endsWith("order.admin"))
		{ 

			
			try {
				List<Pharmacy> pharmacies = service.getAllPharmacies();
				request.setAttribute("pharmacies", pharmacies);
				target="orders.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}
		
		if(uri.endsWith("searchOrder.admin"))
		{ 

			
			try {
				
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");

				
				String pharmacyId = request.getParameter("pharmacyId");
				
				
				int orderStatus = Integer.parseInt(request.getParameter("orderStatus"));
				
				List<Order> orders = new ArrayList<Order>();
				
				if(startDate.isEmpty() && endDate.isEmpty() && pharmacyId.isEmpty() && (orderStatus == 0))
				{
					String query = "select * from orders";
					orders = service.getSearchedOrders(query);
				}
				else
				{
					
						String query = "select * from orders where ";
						
						int i = 0;
						
						if(!pharmacyId.isEmpty())
						{
							query = query+" supplierid="+pharmacyId;
							i++;
						}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if(!startDate.isEmpty())
						{
							
							Date date1;
							date1 = sdf.parse(startDate);
							Timestamp t1  = new Timestamp(date1.getTime());
							
							if(i>0)
							{
								query = query+" and createdon >= '"+t1+"'";
							}
							else
							{
								query = query+" createdon >= '"+t1+"'";
								i++;
							}
							
						}
						
						if(!endDate.isEmpty())
						{
							
							Date date1;
							date1 = sdf.parse(endDate);
							Timestamp t1  = new Timestamp(date1.getTime());
							
							if(i>0)
							{
								query = query+" and createdon <= '"+t1+"'";
							}
							else
							{
								query = query+" createdon <= '"+t1+"'";
								i++;
							}
							
							
						}
						
						if(orderStatus != 0)
						{
							
							if(i>0)
							{
								query = query+" and status = "+orderStatus;
							}
							else
							{
								query = query+" status = "+orderStatus;
								i++;
							}
							
						}
						orders = service.getSearchedOrders(query);
						
				}
				
				
				for(int i=0;i<orders.size();i++)
				{
					if(orders.get(i).getDoctorId()!=0)
					{
						Doctor doctor = lService.getDoctorById(orders.get(i).getDoctorId());
						orders.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
					}

					if(orders.get(i).getPatientId()!=0)
					{
						Patient doctor = lService.getPatientById(orders.get(i).getPatientId());
						orders.get(i).setPatientName(doctor.getFirstName()+" "+doctor.getLastName());
					}

					if(orders.get(i).getPharmacyId()!=0)
					{
						Pharmacy doctor = lService.getPharmacyById(orders.get(i).getPharmacyId());
						orders.get(i).setPharmacyName(doctor.getPharmacyName());
					}
					Pharmacy pharmacy = lService.getPharmacyById(orders.get(i).getSupplierPharmacyId());
					orders.get(i).setSupplierPharmacyName(pharmacy.getPharmacyName());
				}
				
				List<Pharmacy> pharmacies = service.getAllPharmacies();
				request.setAttribute("pharmacies", pharmacies);
				
				request.setAttribute("orders", orders);
				target="orders.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		if(uri.endsWith("getOrder.admin"))
		{ 

			PharmacyService phService = new PharmacyService();
			try {
				
				int oId = Integer.parseInt(request.getParameter("id"));

				Order order = phService.getOrderById(oId);
				if(order.getDoctorId()!=0)
				{
					Doctor doctor = lService.getDoctorById(order.getDoctorId());
					order.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
				}

				if(order.getPatientId()!=0)
				{
					Patient patient = lService.getPatientById(order.getPatientId());
					order.setPatientName(patient.getFirstName()+" "+patient.getLastName());
				}

				if(order.getPharmacyId()!=0)
				{
					Pharmacy doctor = lService.getPharmacyById(order.getPharmacyId());
					order.setPharmacyName(doctor.getPharmacyName());
				}
				Pharmacy pharmacy = lService.getPharmacyById(order.getSupplierPharmacyId());
				order.setSupplierPharmacyName(pharmacy.getPharmacyName());

				order.setMedicines(phService.getOrderedMedicinesByOrderId(oId));
				request.setAttribute("order", order);
				target="adminOrder.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		if(uri.endsWith("prescription.admin"))
		{ 

			
			try {
				List<Doctor> doctors = service.getAllDoctors();
				request.setAttribute("doctors", doctors);
				List<Patient> patients = service.getAllPatients();
				request.setAttribute("patients", patients);
				
				target="prescription.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}
		if(uri.endsWith("searchPrescription.admin"))
		{ 

			
			try {
				
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");

				
				String doctorId = request.getParameter("doctorId");
				
				String patientId = request.getParameter("patientId");
				
				List<Prescription> prescriptions = new ArrayList<Prescription>();
				
				if(startDate.isEmpty() && endDate.isEmpty() && doctorId.isEmpty() && patientId.isEmpty())
				{
					String query = "select id , doctorid, patientid , prescribeddate , checkup from prescription where doctorid > 0";
					prescriptions = service.getSearchedPrescriptionss(query);
				}
				else
				{
					
						String query = "select id , doctorid, patientid , prescribeddate , checkup  from prescription where doctorid > 0 ";
						
						
						
						if(!doctorId.isEmpty())
						{
							query = query+" and doctorid="+doctorId;
							
						}
						
						if(!patientId.isEmpty())
						{
							query = query+" and patientid="+patientId;
							
						}
						
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if(!startDate.isEmpty())
						{
							
							Date date1;
							date1 = sdf.parse(startDate);
							Timestamp t1  = new Timestamp(date1.getTime());
							
							
								query = query+" and prescribeddate >= '"+t1+"'";
							
							
						}
						
						if(!endDate.isEmpty())
						{
							
							Date date1;
							date1 = sdf.parse(endDate);
							Timestamp t1  = new Timestamp(date1.getTime());
							
								query = query+" and prescribeddate <= '"+t1+"'";
							
							
						}
						
						
					
					prescriptions = service.getSearchedPrescriptionss(query);
						
				}
				
				for(int i=0;i<prescriptions.size();i++)
				{
					if(prescriptions.get(i).getDoctorId()>0)
					{
						Doctor patient1 = lService.getDoctorById(prescriptions.get(i).getDoctorId());
						prescriptions.get(i).setDoctorName(patient1.getFirstName()+" "+patient1.getLastName());
					}
					
					Patient patient = lService.getPatientById(prescriptions.get(i).getPatientId());
					prescriptions.get(i).setPatientName(patient.getFirstName()+" "+patient.getLastName());
					
				}
					List<Doctor> doctors = service.getAllDoctors();
					request.setAttribute("doctors", doctors);
					
					List<Patient> patients = service.getAllPatients();
					request.setAttribute("patients", patients);
					
					request.setAttribute("prescriptions", prescriptions);
					
					target="prescription.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		
		if(uri.endsWith("getPrescription.admin"))
		{ 

			PatientService pService = new PatientService();
			try {
				int prescriptionId = Integer.parseInt(request.getParameter("id"));

				Prescription prescription = pService.getPrescriptionById(prescriptionId);
				if(prescription.getS1()!=0)
				{
					prescription.setsName1(pService.getSymptomNameById(prescription.getS1()));
				}
				if(prescription.getS2()!=0)
				{
					prescription.setsName2(pService.getSymptomNameById(prescription.getS2()));
				}
				if(prescription.getS3()!=0)
				{
					prescription.setsName3(pService.getSymptomNameById(prescription.getS3()));
				}
				if(prescription.getS4()!=0)
				{
					prescription.setsName4(pService.getSymptomNameById(prescription.getS4()));
				}
				if(prescription.getS5()!=0)
				{
					prescription.setsName5(pService.getSymptomNameById(prescription.getS5()));
				}

				if(prescription.getD1()!=0)
				{
					prescription.setdName1(pService.getDiseaseNameById(prescription.getD1()));
				}
				if(prescription.getD2()!=0)
				{
					prescription.setdName2(pService.getDiseaseNameById(prescription.getD2()));
				}
				if(prescription.getD3()!=0)
				{
					prescription.setdName3(pService.getDiseaseNameById(prescription.getD3()));
				}
				if(prescription.getD4()!=0)
				{
					prescription.setdName4(pService.getDiseaseNameById(prescription.getD4()));
				}
				if(prescription.getD5()!=0)
				{
					prescription.setdName5(pService.getDiseaseNameById(prescription.getD5()));
				}

				List<Medicine> medicines = new ArrayList<Medicine>();

				int sort=0;
				if(prescription.getM1()!=0)
				{
					medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM1(),sort));
				}
				if(prescription.getM2()!=0)
				{
					medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM2(),sort));
				}
				if(prescription.getM3()!=0)
				{
					medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM3(),sort));
				}
				if(prescription.getM4()!=0)
				{
					medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM4(),sort));
				}
				if(prescription.getM5()!=0)
				{
					medicines.add(pService.getPrescribedMedicineById(prescription,prescription.getM5(),sort));
				}

				if(prescription.getDoctorId()>0)
				{
					Doctor patient1 = lService.getDoctorById(prescription.getDoctorId());
					prescription.setDoctorName(patient1.getFirstName()+" "+patient1.getLastName());
				}
				Patient patient = lService.getPatientById(prescription.getPatientId());
				prescription.setPatientName(patient.getFirstName()+" "+patient.getLastName());
				
				prescription.setMedicines(medicines);
				request.setAttribute("prescription", prescription);
					
				target="adminPrescription.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		if(uri.endsWith("searchUser.admin"))
		{ 

			
			try {
				
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");

				
				int type = Integer.parseInt(request.getParameter("type"));
				
				int status = Integer.parseInt(request.getParameter("status"));
				
				List<User> users = new ArrayList<User>();
				
				if(startDate.isEmpty() && endDate.isEmpty() && status == 0 )
				{
					String query = "select * from ";
					
					if(type == 1)
					{
						query = query+"doctor";
					}
					if(type == 2)
					{
						query = query+"patient";
					}
					if(type == 3)
					{
						query = query+"pharmacy";
					}
					
					users = service.getSearchedUsers(query , type);
				}
				else
				{
					
					String query = "select * from ";
					
					if(type == 1)
					{
						query = query+"doctor where ";
					}
					if(type == 2)
					{
						query = query+"patient where ";
					}
					if(type == 3)
					{
						query = query+"pharmacy where ";
					}
					
					int i = 0;
					
					if(status == 1)
					{
						query = query+"isverified = 0 and isdeleted = 0 ";
						i++;
					}
					if(status == 2)
					{
						query = query+"isverified = 1 and isdeleted = 0 ";
						i++;
					}
					if(status == 3)
					{
						query = query+"isdeleted = 1 ";
						i++;
					}
					
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if(!startDate.isEmpty())
						{
							
							Date date1;
							date1 = sdf.parse(startDate);
							Timestamp t1  = new Timestamp(date1.getTime());
							
							if(i>0)
							{
								query = query+" and UserCreationDate >= '"+t1+"'";
								
							}
							else
							{
								query = query+" UserCreationDate >= '"+t1+"'";
								i++;
							}
								
							
							
						}
						
						if(!endDate.isEmpty())
						{
							
							Date date1;
							date1 = sdf.parse(endDate);
							Timestamp t1  = new Timestamp(date1.getTime());
							
							if(i>0)
							{
								query = query+" and UserCreationDate <= '"+t1+"'";
							}
							else
								{
								i++;
								query = query+"UserCreationDate <= '"+t1+"'";
								}
							
						}
						
						
					users = service.getSearchedUsers(query , type);
						
				}
				
				
				request.setAttribute("type", type);
				request.setAttribute("users", users);
					
				target="users.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		
		if(uri.endsWith("searchForum.admin"))
		{ 

			
			try {
				
				String startDate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");

				
				int type = Integer.parseInt(request.getParameter("type"));
				
				
				
				List<ForumPost> posts = new ArrayList<ForumPost>();
				
				if(startDate.isEmpty() && endDate.isEmpty() )
				{
					String query = "select * from ";
					
					if(type == 1)
					{
						query = query+"doctorpost";
					}
					if(type == 2)
					{
						query = query+"patientpost";
					}
					if(type == 3)
					{
						query = query+"pharmacypost";
					}
					
					posts = service.getSearchedPosts(query , type);
				}
				else
				{
					
					String query = "select * from ";
					
					if(type == 1)
					{
						query = query+"doctorpost where ";
					}
					if(type == 2)
					{
						query = query+"patientpost where ";
					}
					if(type == 3)
					{
						query = query+"pharmacypost where ";
					}
					
					int i = 0;
					
					
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						if(!startDate.isEmpty())
						{
							
							Date date1;
							date1 = sdf.parse(startDate);
							Timestamp t1  = new Timestamp(date1.getTime());
							
							if(i>0)
							{
								query = query+" and createdon >= '"+t1+"'";
								
							}
							else
							{
								query = query+" createdon >= '"+t1+"'";
								i++;
							}
								
							
							
						}
						
						if(!endDate.isEmpty())
						{
							
							Date date1;
							date1 = sdf.parse(endDate);
							Timestamp t1  = new Timestamp(date1.getTime());
							
							if(i>0)
							{
								query = query+" and createdon <= '"+t1+"'";
							}
							else
								{
								i++;
								query = query+"createdon <= '"+t1+"'";
								}
							
						}
						
						
					
					posts = service.getSearchedPosts(query , type);
						
				}
				ForumService fService = new ForumService();
				for(int i=0;i<posts.size();i++)
				{
					if(type == 1)
					{
						Doctor doctor = lService.getDoctorById(posts.get(i).getDoctorId());
						posts.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());

						
						List<Comment> comments = fService.getAllDoctorCommentsById(posts.get(i).getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setDoctorName(lService.getDoctorById(comments.get(j).getDoctorId()).getUserName());
						}
						posts.get(i).setComment(comments);
						posts.get(i).setComments(comments.size());
					}
					if(type == 2)
					{
						Patient doctor = lService.getPatientById(posts.get(i).getPatientId());
						posts.get(i).setPatientName(doctor.getFirstName()+" "+doctor.getLastName());

						List<Comment> comments = fService.getAllPatientCommentsById(posts.get(i).getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setPatientName(lService.getPatientById(comments.get(j).getPatientId()).getUserName());
						}
						posts.get(i).setComment(comments);
						posts.get(i).setComments(comments.size());
					}
					if(type == 3)
					{
						Pharmacy doctor = lService.getPharmacyById(posts.get(i).getPharmacyId());
						posts.get(i).setPharmacyName(doctor.getPharmacyName());

						List<Comment> comments = fService.getAllPharmacyCommentsById(posts.get(i).getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setPharmacyName(lService.getPharmacyById(comments.get(j).getPharmacyId()).getPharmacyName());
						}
						posts.get(i).setComment(comments);
						posts.get(i).setComments(comments.size());
					}
					
				}
				
				request.setAttribute("type", type);
				request.setAttribute("posts", posts);
					
				target="forums.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		if(uri.endsWith("getForum.admin"))
		{ 

			
			try {
				
				
				
				int type = Integer.parseInt(request.getParameter("type"));
				
				int postId = Integer.parseInt(request.getParameter("id"));
				
				
				ForumService fService = new ForumService();
				
					if(type == 1)
					{
						ForumPost post = fService.getDoctorPostById(postId);
						Doctor doctor = lService.getDoctorById(post.getDoctorId());
						post.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());

						
						List<Comment> comments = fService.getAllDoctorCommentsById(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setDoctorName(lService.getDoctorById(comments.get(j).getDoctorId()).getUserName());
						}
						post.setComment(comments);
						post.setComments(comments.size());
						request.setAttribute("post", post);
					}
					if(type == 2)
					{
						ForumPost post = fService.getPatientPostById(postId);
						Patient doctor = lService.getPatientById(post.getPatientId());
						post.setPatientName(doctor.getFirstName()+" "+doctor.getLastName());

						List<Comment> comments = fService.getAllPatientCommentsById(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setPatientName(lService.getPatientById(comments.get(j).getPatientId()).getUserName());
						}
						post.setComment(comments);
						post.setComments(comments.size());
						request.setAttribute("post", post);
					}
					if(type == 3)
					{
						ForumPost post = fService.getPharmacyPostById(postId);
						Pharmacy doctor = lService.getPharmacyById(post.getPharmacyId());
						post.setPharmacyName(doctor.getPharmacyName());

						List<Comment> comments = fService.getAllPharmacyCommentsById(post.getId());
						for(int j=0;j<comments.size();j++)
						{
							comments.get(j).setPharmacyName(lService.getPharmacyById(comments.get(j).getPharmacyId()).getPharmacyName());
						}
						post.setComment(comments);
						post.setComments(comments.size());
						request.setAttribute("post", post);
					}
					
				
				
				request.setAttribute("type", type);
				
					
				target="adminForum.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);
		}
		
		if(uri.endsWith("delete.admin"))
		{ 

			
			
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				String type = request.getParameter("type");
			
				service.deleteAccount(id,type);
				
				String content = "Deleted";
				int typeName = 0;
				if(type.equalsIgnoreCase("doctor"))
				{
					typeName = 1;
				}
				if(type.equalsIgnoreCase("patient"))
				{
					
					typeName = 2;
				}
				if(type.equalsIgnoreCase("pharmacy"))
				{
					typeName = 3;
				}
				
				Log log = new Log(id,typeName,content);
				lService.saveLog(log);
				
				if(type.equalsIgnoreCase("doctor"))
				{
					request.setAttribute(type, lService.getDoctorById(id));
					target="adminDoctor.jsp";
					int prescriptionCount = service.getPrescriptionCountForDoctor(id);
					int orderCount = service.getOrderCountForDoctor(id);
					
					request.setAttribute("orderCount", orderCount);
					request.setAttribute("prescriptionCount", prescriptionCount);
				}
				if(type.equalsIgnoreCase("patient"))
				{
					
					request.setAttribute(type, lService.getPatientById(id));
					target="adminPatient.jsp";
					
					int prescriptionCount = service.getPrescriptionCountForPatient(id);
					int orderCount = service.getOrderCountForPatient(id);
					request.setAttribute("orderCount", orderCount);
					request.setAttribute("prescriptionCount", prescriptionCount);
				}
				if(type.equalsIgnoreCase("pharmacy"))
				{
					request.setAttribute(type, lService.getPharmacyById(id));
					target="adminPharmacy.jsp";
					int orderReceived = service.getOrderReceivedCount(id);
					int orderMade = service.getOrderMadeCount(id);
					request.setAttribute("orderReceived", orderReceived);
					request.setAttribute("orderMade", orderMade);
					
				}
				
				
				
			} catch (Exception e) {
				
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}
		
		if(uri.endsWith("deletePost.admin"))
		{ 

			
			
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				int type = Integer.parseInt(request.getParameter("type"));
				
				String query = "";
				if(type == 1)
				{
					query = "delete from doctorpost where id = "+id;
				}
				if(type==2)
				{
					
					query = "delete from patientpost where id = "+id;
				}
				if(type==3)
				{
					query = "delete from pharmacypost where id = "+id;
				}
				
				service.deletePost(query);
				
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				String msg="Deleted";
				pw.print(msg);
				
			} catch (Exception e) {
				
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}

		}
		
		if(uri.endsWith("activateDoctor.admin"))
		{ 

			
			try {
				
				int id = Integer.parseInt(request.getParameter("id"));
				
				service.activateDoctor(id);
				Doctor doctor = lService.getDoctorById(id);
				String message = "Hi Dr,"+doctor.getFirstName()+" "+doctor.getLastName()+", Your Healingclick Account has been activated";
				lService.sendMessage(doctor.getMobile(), message);
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				String msg="Activated";
				pw.print(msg);
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			

		}

		if(uri.endsWith("activatePharmacy.admin"))
		{ 

			
			try {
				
				int id = Integer.parseInt(request.getParameter("id"));
				
				service.activatePharmacy(id);
				Pharmacy pharmacy = lService.getPharmacyById(id);
				String message = "Hi "+pharmacy.getPharmacyName()+", Your Healingclick Account has been activated";
				lService.sendMessage(pharmacy.getMobile(), message);
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				String msg="Activated";
				pw.print(msg);
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			

		}
		

		if(uri.endsWith("getDoctor.admin"))
		{ 


			try {
				int id = Integer.parseInt(request.getParameter("id"));
				
				Doctor doctor = lService.getDoctorById(id);
				int prescriptionCount = service.getPrescriptionCountForDoctor(id);
				int orderCount = service.getOrderCountForDoctor(id);
				
				request.setAttribute("orderCount", orderCount);
				request.setAttribute("prescriptionCount", prescriptionCount);
				
				request.setAttribute("doctor", doctor);
				target="adminDoctor.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}

		if(uri.endsWith("getPatient.admin"))
		{ 


			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Patient doctor = lService.getPatientById(id);

				int prescriptionCount = service.getPrescriptionCountForPatient(id);
				int orderCount = service.getOrderCountForPatient(id);
				request.setAttribute("orderCount", orderCount);
				request.setAttribute("prescriptionCount", prescriptionCount);

				request.setAttribute("patient", doctor);
				target="adminPatient.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}

		if(uri.endsWith("getPharmacy.admin"))
		{ 


			try {
				int id = Integer.parseInt(request.getParameter("id"));
				Pharmacy doctor = lService.getPharmacyById(id);

				int orderReceived = service.getOrderReceivedCount(id);
				int orderMade = service.getOrderMadeCount(id);
				request.setAttribute("orderReceived", orderReceived);
				request.setAttribute("orderMade", orderMade);


				List<Review> reviews = lService.getAllReviews(id);
				double totalRating = 0.0;
				
				for(int i=0;i<reviews.size();i++)
				{
					totalRating = totalRating + reviews.get(i).getRating();
					if(reviews.get(i).getType() == 1)
					{
						Doctor d = lService.getDoctorById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getFirstName()+" "+d.getLastName());
					}
					if(reviews.get(i).getType() == 2)
					{
						Patient d = lService.getPatientById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getFirstName()+" "+d.getLastName());
					}
					if(reviews.get(i).getType() == 3)
					{
						Pharmacy d = lService.getPharmacyById(reviews.get(i).getReviewerId());
						reviews.get(i).setReviewerName(d.getPharmacyName());
					}
				}
				
				totalRating  = totalRating/reviews.size();
				doctor.setRating(totalRating);
				request.setAttribute("reviews", reviews);
				
				request.setAttribute("pharmacy", doctor);
				target="adminPharmacy.jsp";
			} catch (Exception e) {
				logger.error(e.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}
		
		if(uri.endsWith("home.admin"))
		{

			try {
				Login login = (Login) request.getSession().getAttribute("login");
				

				AdminService adminService = new AdminService();
				int orderCount = 0;
				int prescriptionCount = 0;
				orderCount = service.getOrders(0);
				
				prescriptionCount =service.getPrescriptions(0);
				request.setAttribute("doctorCount", adminService.getTotalDoctorCount());
				request.setAttribute("patientCount", adminService.getTotalPatientCount());
				request.setAttribute("pharmacyCount", adminService.getTotalPharmacyCount());
				request.setAttribute("orderCount", orderCount);
				request.setAttribute("prescriptionCount", prescriptionCount);
				request.setAttribute("doctorMonth", adminService.getMonthDoctorCount());
				request.setAttribute("patientMonth", adminService.getMonthPatientCount());
				request.setAttribute("pharmacyMonth", adminService.getMonthPharmacyCount());
				
				request.setAttribute("orderMonth", adminService.getMonthOrder());
				request.setAttribute("prescriptionMonth", adminService.getMonthPrescription());
				
				List<Doctor> doctors;
				doctors = adminService.getAllNotVerifiedDoctors();
				request.setAttribute("doctors", doctors);
				
				List<Pharmacy> pharmacies;
				pharmacies = service.getAllNotVerifiedPharmacies();
				request.setAttribute("pharmacies", pharmacies);
				
				target="adminhome.jsp";
				
			} catch (Exception e1) {
				logger.error(e1.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}
		
		if(uri.endsWith("complaints.admin"))
		{

			try {
				Login login = (Login) request.getSession().getAttribute("login");
				

				AdminService adminService = new AdminService();
				
				List<ForumPost> posts = adminService.getAllQuestions();

				for(int i=0;i<posts.size();i++)
				{
					if(posts.get(i).getType() == 1)
					{
						Doctor doctor = lService.getDoctorById(posts.get(i).getOtherId());
						posts.get(i).setOtherName("Dr. "+doctor.getFirstName()+" "+doctor.getLastName());
					}
					if(posts.get(i).getType() == 2)
					{
						Patient doctor = lService.getPatientById(posts.get(i).getOtherId());
						posts.get(i).setOtherName(doctor.getFirstName()+" "+doctor.getLastName());
					}
					if(posts.get(i).getType() == 3)
					{
						Pharmacy doctor = lService.getPharmacyById(posts.get(i).getOtherId());
						posts.get(i).setOtherName(doctor.getPharmacyName());
					}

					List<Comment> comments = adminService.getAllQuestionCommentsById(posts.get(i).getId());
					posts.get(i).setComment(comments);
					posts.get(i).setComments(comments.size());
					
				}
				request.setAttribute("posts", posts);
				target="complaints.jsp";
				
			} catch (Exception e1) {
				logger.error(e1.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}
		
		if(uri.endsWith("getSupport.admin"))
		{

			try {
				Login login = (Login) request.getSession().getAttribute("login");
				
				AdminService adminService = new AdminService();
				int postId = Integer.parseInt(request.getParameter("id"));
				ForumPost post = adminService.getSupportById(postId);

				
				if(post.getType() == 1)
				{
					Doctor doctor = lService.getDoctorById(post.getOtherId());
					post.setOtherName("Dr. "+doctor.getFirstName()+" "+doctor.getLastName());
				}
				if(post.getType() == 2)
				{
					Patient doctor = lService.getPatientById(post.getOtherId());
					post.setOtherName(doctor.getFirstName()+" "+doctor.getLastName());
				}
				if(post.getType() == 3)
				{
					Pharmacy doctor = lService.getPharmacyById(post.getOtherId());
					post.setOtherName(doctor.getPharmacyName());
				}
				List<Comment> comments = adminService.getAllQuestionCommentsById(post.getId());
				
				post.setComment(comments);
				post.setComments(comments.size());

				request.setAttribute("post", post);
				target="adminComplaint.jsp";
				
				
			} catch (Exception e1) {
				logger.error(e1.getStackTrace());
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="adminlogin.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}
		
		if(uri.endsWith("saveSupportComment.admin"))
		{
			try {
				SupportService supportService = new SupportService();
				int postId = Integer.parseInt(request.getParameter("id"));
				supportService.saveSupportComment(postId,999,request.getParameter("comment"));
				
				ForumPost post = service.getSupportById(postId);
				
				String content = "<a href='getSupport.support?id="+postId+" '>Healingclick Support Has Replied to query</a>";
				Notification notification = new Notification(0, 999, content, post.getOtherId(), 0,post.getType());
				notification.setImage("image/d.jpg");
				lService.saveNotification(notification);
				
				}  catch (Exception e) {
					logger.error(e.getStackTrace());
			}
	
		}
		
	}

}
