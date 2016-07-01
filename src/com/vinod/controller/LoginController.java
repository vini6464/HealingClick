package com.vinod.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.vinod.exception.DaoException;
import com.vinod.model.Appointment;
import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Prescription;
import com.vinod.model.Vaccination;
import com.vinod.model.Vaccine;
import com.vinod.service.LoginService;
import com.vinod.service.PatientService;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("*.login")
@MultipartConfig
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	final static Logger logger = Logger.getLogger(LoginController.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	private String extractFileName(Part part) {
		//	final String partHeader = part.getHeader("content-disposition");


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
					return content.substring(
							content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}


	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String uri=request.getRequestURI();
		LoginService service = new LoginService();
		PatientService pService = new PatientService();
		String target = "home.jsp";
		logger.info(uri);
		if(uri.endsWith("signout.login"))
		{
			try {
				
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();
				if(login.getType()==1)
				{

					service.setLastActive(login);
					request.getSession().removeAttribute("login");
					request.getSession().removeAttribute("symptoms");
					request.getSession().removeAttribute("medicines");
					request.getSession().invalidate();
					Cookie[] cookies = request.getCookies();
					for (Cookie cookie : cookies) {
						cookie.setMaxAge(0);
						cookie.setValue(null);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
					target="home.jsp";


				}

				if(login.getType()==2)
				{

					service.setLastActive(login);
					request.getSession().removeAttribute("login");
					request.getSession().removeAttribute("symptoms");
					request.getSession().removeAttribute("medicines");
					request.getSession().invalidate();
					Cookie[] cookies = request.getCookies();
					for (Cookie cookie : cookies){ 
						cookie.setMaxAge(0);
						cookie.setValue(null);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
					target="home.jsp";


				}


				if(login.getType()==3)
				{

					service.setLastActive(login);
					request.getSession().removeAttribute("login");
					request.getSession().removeAttribute("medicines");
					request.getSession().invalidate();
					Cookie[] cookies = request.getCookies();
					for (Cookie cookie : cookies) {
						cookie.setMaxAge(0);
						cookie.setValue(null);
						cookie.setPath("/");
						response.addCookie(cookie);
					}
					target="home.jsp";


				}}catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}
			request.getRequestDispatcher(target).forward(request, response);

		}

		
		
		
		if(uri.endsWith("changeProfileImage.login"))
		{
			Login login=(Login) request.getSession().getAttribute("login");
			int id=login.getId();

			if(login.getType()==1)
			{
				Part file = request.getPart("file");
				

				String fileName = extractFileName(file);
				String imagePath="";
				if(file.getSize()!=0)
				{

					String SAVE_DIR = "Images"+ File.separator +"ProfileImage"+ File.separator +"doctor"+login.getUserName();
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
						fileName = extractFileName(part);
						if(fileName!=null)
						{
							imagePath=SAVE_DIR + File.separator +new Date().getTime();
						}

						part.write(savePath + File.separator +new Date().getTime());

					}
				}
				try {
					service.updateProfileImage(imagePath,login);
					service.setLastActive(login);
					service.changeNotificationImage(login,imagePath);
				} catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				}
				
			}

			if(login.getType()==2)
			{
				Part file = request.getPart("file");

				String fileName = extractFileName(file);
				String imagePath="";
				if(file.getSize()!=0)
				{

					String SAVE_DIR ="Images"+ File.separator +"ProfileImage"+ File.separator +"patient"+login.getUserName();
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
						fileName = extractFileName(part);
						if(fileName!=null)
						{
							imagePath=SAVE_DIR + File.separator +new Date().getTime();
						}

						part.write(savePath + File.separator +new Date().getTime());

					}
				}
				try {
					service.updateProfileImage(imagePath,login);
					service.setLastActive(login);
					service.changeNotificationImage(login,imagePath);


				} catch (Exception e) {

				}
			}

			if(login.getType()==3)
			{
				Part file = request.getPart("file");

				String fileName = extractFileName(file);
				String imagePath="";
				if(file.getSize()!=0)
				{

					String SAVE_DIR = "Images"+ File.separator +"ProfileImage"+ File.separator +"doctor"+login.getUserName();
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
						fileName = extractFileName(part);
						if(fileName!=null)
						{
							imagePath=SAVE_DIR + File.separator +new Date().getTime();
						}

						part.write(savePath + File.separator +new Date().getTime());

					}
				}
				try {
					service.updateProfileImage(imagePath,login);
					service.setLastActive(login);
					service.changeNotificationImage(login,imagePath);
				} catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}
				
			}

		}


		if(uri.endsWith("login.login"))
		{
			try {

				int type=0;
				if(request.getSession().getAttribute("login")!=null)
				{
					
					Login login = (Login) request.getSession().getAttribute("login");
					int id = login.getId();
					if(login.getType()==1)
					{
						service.setLastActive(login);
						Doctor doctor = service.getDoctorById(id);
						List<Patient> patients = service.getAllPatients(id);
						List<Pharmacy> pharmacies = service.getAllPharmacies(id);
						
						List<Appointment> appointments = service.getThisDayAppointment(id);
						
						for(int i=0;i<appointments.size();i++)
						{
							if(appointments.get(i).getPatientId()!=0)
							{
								Patient patient = service.getPatientById(appointments.get(i).getPatientId());
								appointments.get(i).setPatientName(patient.getFirstName()+" "+patient.getLastName());
							}
							
							String st = appointments.get(i).getStart();
							String lt = appointments.get(i).getEnd();
							
							 String[] res1 = st.split("T");
							 String[] res2 = lt.split("T");
							 //String day = res1[0];
							
							 String[] st1 = res1[1].split(":");
							 String startTime= st1[0]+":"+st1[1];
							
							 String[] lt1 = res2[1].split(":");
							 String endTime= lt1[0]+":"+lt1[1];
							 
							 appointments.get(i).setStartTime(startTime);
							 appointments.get(i).setEndTime(endTime);
						}
						List<Doctor> doctors = service.getAllDoctorsForDoctor(id);
						request.setAttribute("doctors", doctors);
						request.setAttribute("appointments", appointments);
						
						request.setAttribute("patients", patients);
						request.setAttribute("pharmacies", pharmacies);
						request.setAttribute("doctor", doctor);
						target="doctorhome.jsp";
					}
					if(login.getType()==2)
					{
						service.setLastActive(login);
						Patient patient = service.getPatientById(id);

						Calendar calendar1 = Calendar.getInstance();
						Calendar calendar2 = Calendar.getInstance(); 
						calendar1.setTime(new Date());
						calendar2.setTime(patient.getDob());


						long miliSecondForDate1 = calendar1.getTimeInMillis();
						long miliSecondForDate2 = calendar2.getTimeInMillis();

						long diffInMilis = miliSecondForDate1 - miliSecondForDate2;

						long age = diffInMilis / (24 * 60 * 60 * 1000);

						request.setAttribute("age", age);

						Vaccine vaccine = service.getVaccine(id);
						request.setAttribute("vaccine", vaccine); 

						
						List<Vaccination> vaccinations = service.getVaccination(id);
						request.setAttribute("vaccinations", vaccinations);

						List<Doctor> doctors = service.getAllDoctors(id);
						List<Pharmacy> pharmacies = service.getAllPharmaciesBypatientId(id);



						List<Prescription> prescriptions;
						prescriptions = pService.getAllPrescriptionByPatientId(id);

						for(int i=0;i<prescriptions.size();i++)
						{
							Prescription prescription = prescriptions.get(i);
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

							int sort = 1;
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
								Doctor patient1 = service.getDoctorById(prescription.getDoctorId());
								prescription.setDoctorName(patient1.getFirstName()+" "+patient1.getLastName());
							}
							prescription.setMedicines(medicines);

						}
						request.setAttribute("prescriptions", prescriptions);
						request.setAttribute("doctors", doctors);
						request.setAttribute("pharmacies", pharmacies);
						request.setAttribute("patient", patient);
						target="patienthome.jsp";
					}
					if(login.getType()==3)
					{
						service.setLastActive(login);
						Pharmacy pharmacy = service.getPharmacyById(id);
						List<Pharmacy> pharmacies = service.getAllPharmaciesForPharmacy(id);
						List<Order> orders = service.getAllNewOrders(id);
						for(int i=0;i<orders.size();i++)
						{
							if(orders.get(i).getDoctorId()!=0)
							{
								Doctor doctor = service.getDoctorById(orders.get(i).getDoctorId());
								orders.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
							}

							if(orders.get(i).getPatientId()!=0)
							{
								Patient patient = service.getPatientById(orders.get(i).getPatientId());
								orders.get(i).setPatientName(patient.getFirstName()+" "+patient.getLastName());
							}

							if(orders.get(i).getPharmacyId()!=0)
							{
								Pharmacy doctor = service.getPharmacyById(orders.get(i).getPharmacyId());
								orders.get(i).setPharmacyName(doctor.getPharmacyName());
							}
						}
						request.setAttribute("pharmacies", pharmacies);
						request.setAttribute("orders", orders);
						request.setAttribute("pharmacy", pharmacy);
						target="pharmacyhome.jsp";
					}

				}
				else
				{
					type = Integer.parseInt(request.getParameter("type"));
					if(type==1)
					{


						Login login = new Login();
						String userName = request.getParameter("email");
						String password = request.getParameter("password");
							
				
							int id;
							id = service.validateLoginDoctor(userName,password);
							if(id>0)
							{
								login.setType(1);
								login.setId(id);
								login.setUserName(userName);
								HttpSession session = request.getSession();
								session.setAttribute("login", login);
								//session.setMaxInactiveInterval(1000);
								service.setLastActive(login);
								Doctor doctor = service.getDoctorById(id);
								List<Patient> patients = service.getAllPatients(id);
								List<Pharmacy> pharmacies = service.getAllPharmacies(id);
								
								List<Appointment> appointments = service.getThisDayAppointment(id);
								
								for(int i=0;i<appointments.size();i++)
								{
									if(appointments.get(i).getPatientId()!=0)
									{
										Patient patient = service.getPatientById(appointments.get(i).getPatientId());
										appointments.get(i).setPatientName(patient.getFirstName()+" "+patient.getLastName());
									}
									
									String st = appointments.get(i).getStart();
									String lt = appointments.get(i).getEnd();
									
									 String[] res1 = st.split("T");
									 String[] res2 = lt.split("T");
									 //String day = res1[0];
									
									 String[] st1 = res1[1].split(":");
									 String startTime= st1[0]+":"+st1[1];
									
									 String[] lt1 = res2[1].split(":");
									 String endTime= lt1[0]+":"+lt1[1];
									 
									 appointments.get(i).setStartTime(startTime);
									 appointments.get(i).setEndTime(endTime);
								}
								List<Doctor> doctors = service.getAllDoctorsForDoctor(id);
								request.setAttribute("doctors", doctors);
								request.setAttribute("appointments", appointments);
								request.setAttribute("patients", patients);
								request.setAttribute("pharmacies", pharmacies);
								request.setAttribute("doctor", doctor);

								target="doctorhome.jsp";
							}
							else
							{
								request.setAttribute("error", "Invalid username or password");
								target="home.jsp";
							}
						

					}

					if(type==2)
					{
						Login login = new Login();
						String userName = request.getParameter("email");
						String password = request.getParameter("password");
						int id;
						id = service.validateLoginPatient(userName,password);
						if(id>0)
						{
							
							Patient patient = service.getPatientById(id);
							
							if(patient.getVerified() == 0)
							{
								
								
								 
							     
							     Random rn = new Random();
								 Long OTP = (long) (100000 + rn.nextInt(900000));
								 System.out.println(OTP);
								 HttpSession session1 = request.getSession();
							     session1.setAttribute("OTP", OTP);
							    
							     String message = "Dear "+patient.getFirstName()+" "+patient.getLastName()+" , Welcome to Healingclick. Your OTP for login is "+OTP;
							     service.sendMessage(patient.getMobile() ,message);
							     
							     request.setAttribute("patient", patient);
							     target="verify.jsp";
							}
							else
							{
								
								login.setType(2);
								login.setId(id);
								login.setUserName(userName);

								HttpSession session = request.getSession();
								session.setAttribute("login", login);
								service.setLastActive(login);
								Calendar calendar1 = Calendar.getInstance();
								Calendar calendar2 = Calendar.getInstance(); 
								calendar1.setTime(new Date());
								calendar2.setTime(patient.getDob());


								long miliSecondForDate1 = calendar1.getTimeInMillis();
								long miliSecondForDate2 = calendar2.getTimeInMillis();

								long diffInMilis = miliSecondForDate1 - miliSecondForDate2;

								long age = diffInMilis / (24 * 60 * 60 * 1000);

								request.setAttribute("age", age);

								Vaccine vaccine = service.getVaccine(id);
								request.setAttribute("vaccine", vaccine); 

								List<Vaccination> vaccinations = service.getVaccination(id);
								request.setAttribute("vaccinations", vaccinations);

								
								List<Doctor> doctors = service.getAllDoctors(id);
								List<Pharmacy> pharmacies = service.getAllPharmaciesBypatientId(id);



								List<Prescription> prescriptions;
								prescriptions = pService.getAllPrescriptionByPatientId(id);

								for(int i=0;i<prescriptions.size();i++)
								{
									Prescription prescription = prescriptions.get(i);
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

									int sort = 1;
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
										Doctor patient1 = service.getDoctorById(prescription.getDoctorId());
										prescription.setDoctorName(patient1.getFirstName()+" "+patient1.getLastName());
									}
									prescription.setMedicines(medicines);

								}
								request.setAttribute("prescriptions", prescriptions);
								request.setAttribute("doctors", doctors);
								request.setAttribute("pharmacies", pharmacies);
								request.setAttribute("patient", patient);
								target="patienthome.jsp";
							}
							

						}
						else
						{
							request.setAttribute("error", "Invalid username or password");
							target="home.jsp";
						}



					}

					if(type==3)
					{


						Login login = new Login();
						String userName = request.getParameter("email");
						String password = request.getParameter("password");
						int id;
						id = service.validateLoginPharmacy(userName,password);
						if(id>0)
						{
							login.setType(3);
							login.setId(id);
							login.setUserName(userName);
							
							HttpSession session = request.getSession();
							session.setAttribute("login", login);
							//session.setMaxInactiveInterval(1000);
							service.setLastActive(login);
							Pharmacy pharmacy = service.getPharmacyById(id);
							List<Pharmacy> pharmacies = service.getAllPharmaciesForPharmacy(id);
							List<Order> orders = service.getAllNewOrders(id);
							for(int i=0;i<orders.size();i++)
							{
								if(orders.get(i).getDoctorId()!=0)
								{
									Doctor doctor = service.getDoctorById(orders.get(i).getDoctorId());
									orders.get(i).setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());
								}

								if(orders.get(i).getPatientId()!=0)
								{
									Patient patient = service.getPatientById(orders.get(i).getPatientId());
									orders.get(i).setPatientName(patient.getFirstName()+" "+patient.getLastName());
								}

								if(orders.get(i).getPharmacyId()!=0)
								{
									Pharmacy doctor = service.getPharmacyById(orders.get(i).getPharmacyId());
									orders.get(i).setPharmacyName(doctor.getPharmacyName());
								}
							}
							request.setAttribute("pharmacies", pharmacies);
							request.setAttribute("orders", orders);
							request.setAttribute("pharmacy", pharmacy);
							target="pharmacyhome.jsp";


						}
						else
						{
							request.setAttribute("error", "Invalid username or password");
							target="home.jsp";
						}
					}
				}
			} catch (Exception e1) {
				logger.error(Level.SEVERE,e1);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
				target="home.jsp";
			}
			request.getRequestDispatcher(target).forward(request, response);

		}

		
		if(uri.endsWith("settings.login"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();
				if(login.getType()==1)
				{


					Doctor doctor = service.getDoctorById(id);
					request.setAttribute("doctor", doctor);
					target="doctorSettings.jsp";


				}

				if(login.getType()==2)
				{
					

					Patient patient = service.getPatientById(id);
					request.setAttribute("patient", patient);
					target="patientSettings.jsp";

				}

				if(login.getType()==3)
				{
					

					Pharmacy pharmacy = service.getPharmacyById(id);
					request.setAttribute("pharmacy", pharmacy);
					target="pharmacySettings.jsp";

				}}catch (Exception e) {
					request.setAttribute("error", "Please Login To Continue.");
					target="home.jsp";
				}
			request.getRequestDispatcher(target).forward(request, response);


		}
		
		if(uri.endsWith("verifyOTP.login"))
		{
			int msg = 0;
			PrintWriter pw;
			
			pw = response.getWriter();
			response.setContentType("text/html");
			try {
				Long otp = Long.parseLong(request.getParameter("otp"));
				 Long OTP = (Long) request.getSession().getAttribute("OTP");
				 
				 
				 if(otp.equals(OTP))
				 {
					int id = Integer.parseInt(request.getParameter("id"));
					service.setPatientVerified(id);
					Patient patient = service.getPatientById(id);
					Login login = new Login();
					
					
					login.setType(2);
					login.setId(id);
					login.setUserName(patient.getUserName());
					HttpSession session = request.getSession();
					session.setAttribute("login", login);
					msg = 1;
				 }
				 pw.print(msg);
				}catch (Exception e) {
					logger.error(Level.SEVERE,e);
					 pw.print(msg);
					
				}
		}
		


		if(uri.endsWith("checkpassword.login"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();
				if(login.getType()==1)
				{
					String pwd = request.getParameter("text");

					int i=service.checkPassword(login,pwd);
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					if(i==0)
					{
						msg="Wrong Password";
					}
					pw.print(msg);

				}

				if(login.getType()==2)
				{
					String pwd = request.getParameter("text");

					int i=service.checkPassword(login,pwd);
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					if(i==0)
					{
						msg="Wrong Password";
					}
					pw.print(msg);

				}

				if(login.getType()==3)
				{
					String pwd = request.getParameter("text");

					int i=service.checkPassword(login,pwd);
					PrintWriter pw = response.getWriter();
					response.setContentType("text/html");
					String msg="";
					if(i==0)
					{
						msg="Wrong Password";
					}
					pw.print(msg);

				}
			} catch (Exception e) {
				logger.error(Level.SEVERE,e);
				request.setAttribute("error", "Please Login To Continue.");
				target="home.jsp";
			}


		}


		if(uri.endsWith("changepassword.login"))
		{
			try {
				Login login=(Login) request.getSession().getAttribute("login");
				int id=login.getId();
				if(login.getType()==1)
				{
					String oldpwd = request.getParameter("oldpassword");

					int i=service.checkPassword(login,oldpwd);
					if(i==0)
					{
						request.setAttribute("error", "Wrong Password");
						Doctor doctor = service.getDoctorById(id);
						request.setAttribute("doctor", doctor);
						request.setAttribute("insert", 1);
						target="doctorSettings.jsp";
					}
					else
					{
						String newpwd = request.getParameter("newpassword");
						service.updatePassword(login,newpwd);
						Doctor doctor = service.getDoctorById(id);
						request.setAttribute("success", "SuccessFully Password Changed");	
						request.setAttribute("doctor", doctor);
						request.setAttribute("insert", 1);
						target="doctorSettings.jsp";
					}

				}

				if(login.getType()==2)
				{
					String oldpwd = request.getParameter("oldpassword");

					int i=service.checkPassword(login,oldpwd);
					if(i==0)
					{
						request.setAttribute("error", "Wrong Password");
						Patient patient = service.getPatientById(id);
						request.setAttribute("patient", patient);
						request.setAttribute("insert", 1);
						target="patientSettings.jsp";
					}
					else
					{
						String newpwd = request.getParameter("newpassword");
						service.updatePassword(login,newpwd);
						Patient patient = service.getPatientById(id);

						request.setAttribute("success", "SuccessFully Password Changed");	
						request.setAttribute("patient", patient);
						request.setAttribute("insert", 1);
						target="patientSettings.jsp";


					}

				}

				if(login.getType()==3)
				{
					String oldpwd = request.getParameter("oldpassword");

					int j=service.checkPassword(login,oldpwd);
					if(j==0)
					{
						request.setAttribute("error", "Wrong Password");
						Pharmacy pharmacy = service.getPharmacyById(id);
						request.setAttribute("pharmacy", pharmacy);
						request.setAttribute("insert", 1);
						target="pharmacySettings.jsp";
					}
					else
					{
						String newpwd = request.getParameter("newpassword");
						service.updatePassword(login,newpwd);

						Pharmacy pharmacy = service.getPharmacyById(id);

						request.setAttribute("success", "SuccessFully Password Changed");	
						request.setAttribute("pharmacy", pharmacy);
						request.setAttribute("insert", 1);
						target="pharmacySettings.jsp";

					}

				}} catch (Exception e) {
					logger.error(Level.SEVERE,e);
					request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
					target="home.jsp";
				}
			request.getRequestDispatcher(target).forward(request, response);


		}
		
		 

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

}
