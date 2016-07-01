package com.vinod.controller;

import java.io.File;

import java.io.IOException;


import java.io.PrintWriter;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.vinod.model.Doctor;
import com.vinod.model.Log;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.service.LoginService;
import com.vinod.service.RegisterService;
import com.vinod.service.SearchService;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("*.register")
@MultipartConfig/*(fileSizeThreshold=1024*1024*10, // 10 MB
maxFileSize=1024*1024*50, // 50 MB
maxRequestSize=1024*1024*100 // 100 MB
		)*/
public class RegisterController extends javax.servlet.http.HttpServlet implements
javax.servlet.Servlet {
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(RegisterController.class);


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RegisterService service = new RegisterService();
		LoginService lService = new LoginService();
		String uri=request.getRequestURI();
		logger.info(uri);

		if(uri.endsWith("home.register"))
		{

			request.getRequestDispatcher("home.jsp").forward(request, response);

		}

		if(uri.endsWith("register.register"))
		{

			int type = Integer.parseInt(request.getParameter("type1"));
			String target = "home.jsp";
			String message = "";
			if(type==1)
			{

				try {

					int i = service.checkUserNameDoctor(Long.parseLong(request.getParameter("mobile")));
					if(i==1)
					{
						target="home.jsp";
						request.setAttribute("error", "This Number Is Already Registered For Doctor");
					}
					else
					{
						int j=service.checkEmailDoctor(request.getParameter("email"));
						if(j==1)
						{
							target="home.jsp";
							request.setAttribute("error", "This Email Is Already Registered For Doctor");
						}
						else
						{
							
							/*String firstName = request.getParameter("firstname");
							String lastName = request.getParameter("lastname");
							
							String mobile = request.getParameter("mobile");
							String email = request.getParameter("email");
							
							if(!firstName.matches("/^[a-z]+$/i"))
							{
								target="home.jsp";
								message = "First Name is Incorrect";
							}
							else
							{*/
								Doctor doctor = new Doctor();

								doctor.setFirstName(request.getParameter("firstname"));
								doctor.setLastName(request.getParameter("lastname"));


								doctor.setMobile(Long.parseLong(request.getParameter("mobile")));

								doctor.setEmailId(request.getParameter("email"));

								request.getSession().setAttribute("doctor", doctor);

								target="doctor.jsp";
							/*}
							request.setAttribute("error", message);*/
							
						}
					}

				} catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				}



			}

			if(type==2)
			{
				try {
					int i = service.checkUserNamePatient(Long.parseLong(request.getParameter("mobile")));
					if(i==1)
					{
						target="home.jsp";
						request.setAttribute("error", "This Number Is Already Registered For Patient");
					}
					else
					{
						int j=service.checkEmailPatient(request.getParameter("email"));
						if(j==1)
						{
							target="home.jsp";
							request.setAttribute("error", "This Email Is Already Registered For Patient");
						}
						else
						{
							Patient doctor = new Patient();

							doctor.setFirstName(request.getParameter("firstname"));
							doctor.setLastName(request.getParameter("lastname"));

							doctor.setMobile(Long.parseLong(request.getParameter("mobile")));
							doctor.setEmailId(request.getParameter("email"));

							request.getSession().setAttribute("patient", doctor);

							target="patient.jsp";
						}
					}
				}catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				}
			}

			if(type==3)
			{
				try {
					int i = service.checkUserNamePharmacy(Long.parseLong(request.getParameter("mobile")));
					if(i==1)
					{
						target="home.jsp";
						request.setAttribute("error", "This Number Is Already Registered For Pharmacy");
					}
					else
					{
						int j=service.checkEmailPharmacy(request.getParameter("email"));
						if(j==1)
						{
							target="home.jsp";
							request.setAttribute("error", "This Email Is Already Registered For Pharmacy");
						}
						else
						{
							Pharmacy doctor = new Pharmacy();
							doctor.setFirstName(request.getParameter("firstname"));
							doctor.setLastName(request.getParameter("lastname"));
							/*String dob = request.getParameter("dob");
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
							Date date;
							date = sdf.parse(dob);
							doctor.setDob(date);*/
							doctor.setMobile(Long.parseLong(request.getParameter("mobile")));
							doctor.setEmailId(request.getParameter("email"));

							request.getSession().setAttribute("pharmacy", doctor);

							target="pharmacy.jsp";
						} 
					}
				} catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				} 
			}

			request.getRequestDispatcher(target).forward(request, response);

		}


		if(uri.endsWith("checkUserName.register"))
		{

			String userName = request.getParameter("q");
			int id = Integer.parseInt(request.getParameter("p"));
			if(id==1)
			{
				try {
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					int i = service.checkUserNameDoctor(userName);

					pw.print(i);

				}  catch (Exception e) {
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					logger.error(Level.SEVERE,e);
				}
			}

			if(id==2)
			{
				try {
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					int i = service.checkUserNamePatient(userName);
					pw.print(i);

				}catch (Exception e) {
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					logger.error(Level.SEVERE,e);
				}
			}
			if(id==3)
			{
				try {
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					int i = service.checkUserNamePharmacy(userName);
					
					pw.print(i);

				} catch (Exception e) {
					logger.error(Level.SEVERE,e);
				}
			}
		}

		if(uri.endsWith("checkPhone.register"))
		{

			long mobile = Long.parseLong(request.getParameter("q"));
			int id = Integer.parseInt(request.getParameter("p"));
			if(id==1)
			{
				try {
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					int i = service.checkUserNameDoctor(mobile);

					if(i==1)
					{
						msg="This Contact already Registered as Doctor";
					}

					pw.print(msg);

				}  catch (Exception e) {
					logger.error(Level.SEVERE,e);
				}
			}

			if(id==2)
			{
				try {
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					int i = service.checkUserNamePatient(mobile);
					if(i==1)
					{
						msg="This Contact already Registered as Patient";
					}

					pw.print(msg);

				}catch (Exception e) {
					logger.error(Level.SEVERE,e);
				}
			}
			if(id==3)
			{
				try {
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					int i = service.checkUserNamePharmacy(mobile);
					if(i==1)
					{
						msg="This Contact already Registered as Pharmacy";
					}

					pw.print(msg);

				} catch (Exception e) {
					logger.error(Level.SEVERE,e);
				}
			}
		}


		if(uri.endsWith("checkEmail.register"))
		{

			String userName = request.getParameter("q");
			int id = Integer.parseInt(request.getParameter("p"));
			if(id==1)
			{
				try {
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					int i = service.checkEmailDoctor(userName);

					if(i==1)
					{
						msg="This Email already Registered as Doctor";
					}

					pw.print(msg);

				} catch (Exception e) {

					logger.error(Level.SEVERE,e);
				} 
			}

			if(id==2)
			{
				try {
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					int i = service.checkEmailPatient(userName);
					if(i==1)
					{
						msg="This Email already Registered as Patient";
					}

					pw.print(msg);

				}  catch (Exception e) {
					logger.error(Level.SEVERE,e);
				}
			}
			if(id==3)
			{
				try {
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					int i = service.checkEmailPharmacy(userName);
					if(i==1)
					{
						msg="This Email already Registered as Pharmacy";
					}

					pw.print(msg);

				} catch (Exception e) {
					logger.error(Level.SEVERE,e);
				}
			}
		}



		if(uri.endsWith("doctorRegister.register"))
		{

			try {
				if(service.checkUserNameDoctor(request.getParameter("username"))==1)
				{

					request.setAttribute("error", "This UserName Is Already Registered For Doctor");

				}
				else
				{
					Doctor doctor = (Doctor) request.getSession().getAttribute("doctor");
					request.getSession().removeAttribute("doctor");


					doctor.setUserName(request.getParameter("username"));
					doctor.setPassWord(request.getParameter("password"));
					String dob = request.getParameter("dob");

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date;
					date = sdf.parse(dob);

					doctor.setDob(date);

					doctor.setImage("image"+File.separator+"d.jpg");
					doctor.setGender(Integer.parseInt(request.getParameter("gender")));



					doctor.setQualification(request.getParameter("qualification"));
					doctor.setSpeciality(request.getParameter("speciality"));
					doctor.setWorkLocation(request.getParameter("worklocation"));
					doctor.setAddress1(request.getParameter("address1"));
					doctor.setAddress2(request.getParameter("address2"));

					doctor.setCity(request.getParameter("city"));
					doctor.setState(request.getParameter("state"));
					doctor.setCountry(request.getParameter("country"));
					doctor.setPinCode(Long.parseLong(request.getParameter("pincode")));
					doctor.setLandMark(request.getParameter("landmark"));
					//doctor.setBloodGroup(Integer.parseInt(request.getParameter("bloodgroup")));
					//doctor.setLandLine(Long.parseLong(request.getParameter("landline")));
					doctor.setLandLine(0);
					//doctor.setAboutMe(request.getParameter("aboutme"));
					doctor.setCreationDate(new Timestamp(new Date().getTime()));

					int i=service.registerDoctor(doctor);
					if(i>0)
					{
						request.setAttribute("msg", "SuccessFully Registered, Please wait for our Confirmation.");
						
						SearchService sService= new SearchService();
						List<Pharmacy> pharmacies = service.getAllPharmacies();
						for (Pharmacy p : pharmacies) {
							sService.doctorPharmacy(p.getId(),i);
						}
					}
					String content = "Created";
					Log log = new Log(i,1,content);
					lService.saveLog(log);
				}

			}
			catch (Exception e) {
				logger.error(Level.SEVERE,e);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
			}
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}

		if(uri.endsWith("patientRegister.register"))
		{

			try {
				if(service.checkUserNamePatient(request.getParameter("username"))==1)
				{

					request.setAttribute("error", "This UserName Is Already Registered For Patient");

				}
				else
				{
					Patient patient = (Patient) request.getSession().getAttribute("patient");
					request.getSession().removeAttribute("patient");
					patient.setUserName(request.getParameter("username"));
					patient.setPassWord(request.getParameter("password"));

					String dob = request.getParameter("dob");
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date;
					date = sdf.parse(dob);
					patient.setDob(date);




					patient.setImage("image"+File.separator+"d.jpg");
					patient.setGender(Integer.parseInt(request.getParameter("gender")));



					patient.setEmergency1(0);
					patient.setEmergency2(0);
					patient.setAddress1(request.getParameter("address1"));
					patient.setAddress2(request.getParameter("address2"));

					patient.setCity(request.getParameter("city"));
					patient.setState(request.getParameter("state"));
					patient.setCountry(request.getParameter("country"));
					patient.setPinCode(Long.parseLong(request.getParameter("pincode")));
					patient.setLandMark(request.getParameter("landmark"));
					//patient.setBloodGroup(Integer.parseInt(request.getParameter("bloodgroup")));
					patient.setLandLine(0);

					//patient.setAboutMe(request.getParameter("aboutme"));
					patient.setCreationDate(new Timestamp(new Date().getTime()));

					int i = service.registerPatient(patient);
					if(i>0)
					{
						
						request.setAttribute("msg", "SuccessFully Registered");
						SearchService sService= new SearchService();
						List<Pharmacy> pharmacies = service.getAllPharmacies();
						for (Pharmacy p : pharmacies) {
							sService.patientPharmacy(p.getId(),i);
						}
						

					}
					String content = "Created";
					Log log = new Log(i,2,content);
					lService.saveLog(log);
				}
			}
			catch (Exception e) {
				logger.error(Level.SEVERE,e);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");

			}
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}

		if(uri.endsWith("pharmacyRegister.register"))
		{

			try {
				if(service.checkUserNamePharmacy(request.getParameter("username"))==1)
				{

					request.setAttribute("error", "This UserName Is Already Registered For Pharmacy");

				}
				else
				{
					Pharmacy pharmacy = (Pharmacy) request.getSession().getAttribute("pharmacy");
					request.getSession().removeAttribute("pharmacy");

					pharmacy.setUserName(request.getParameter("username"));
					pharmacy.setPassWord(request.getParameter("password"));



					//pharmacy.setGender(Integer.parseInt(request.getParameter("gender")));

					pharmacy.setImage("image"+File.separator+"d.jpg");

					pharmacy.setPharmacyName(request.getParameter("pharmacy"));
					pharmacy.setLicensedTo(request.getParameter("licensedto"));
					pharmacy.setProprietorName(request.getParameter("proprietorname"));
					pharmacy.setProprietorAddress(request.getParameter("proprietoraddress"));
					pharmacy.setProprietorContact(0);
					pharmacy.setProprietorEmail(request.getParameter("proprietoremail"));

					pharmacy.setAddress1(request.getParameter("address1"));
					pharmacy.setAddress2(request.getParameter("address2"));

					pharmacy.setCity(request.getParameter("city"));
					pharmacy.setState(request.getParameter("state"));
					pharmacy.setCountry(request.getParameter("country"));
					pharmacy.setPinCode(Long.parseLong(request.getParameter("pincode")));
					pharmacy.setLandMark(request.getParameter("landmark"));
					//pharmacy.setBloodGroup(Integer.parseInt(request.getParameter("bloodgroup")));
					pharmacy.setLandLine(0);

					//pharmacy.setAboutMe(request.getParameter("aboutme"));
					pharmacy.setCreationDate(new Timestamp(new Date().getTime()));

					int i = service.registerPharmacy(pharmacy);
					if(i>0)
					{
						request.setAttribute("msg", "SuccessFully Registered, Please wait for our Confirmation.");
					}
					String content = "Created";
					Log log = new Log(i,3,content);
					lService.saveLog(log);
				}
			}
			catch (Exception e) {
				logger.error(Level.SEVERE,e);
				request.setAttribute("error", e.getMessage());
			}
			request.getRequestDispatcher("home.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
