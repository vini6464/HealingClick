package com.vinod.controller;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import com.vinod.model.Appointment;
import com.vinod.model.Disease;
import com.vinod.model.Doctor;
import com.vinod.model.Log;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Notification;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Post;
import com.vinod.model.Prescription;
import com.vinod.model.Symptom;
import com.vinod.service.AlertService;
import com.vinod.service.AppointmentService;
import com.vinod.service.DoctorService;
import com.vinod.service.LoginService;
import com.vinod.service.OrderService;
import com.vinod.service.PatientService;

import sun.security.util.Cache;

/**
 * Servlet implementation class DoctorController
 */
@WebServlet("*.doctor")
@MultipartConfig
public class DoctorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DoctorController() {
		super();

	}
	final static Logger logger = Logger.getLogger(DoctorController.class);
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
		logger.info(uri);
		DoctorService service = new DoctorService();
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
			if(uri.endsWith("appointment.doctor"))
			{ 


				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				Doctor doctor;
				doctor = lService.getDoctorById(id);
				
				request.setAttribute("doctor", doctor);
				
				String event = request.getParameter("eId");
				if(event!=null  && !event.isEmpty()){
					
					
					int eventId = Integer.parseInt(event);

					AppointmentService aService = new AppointmentService();
					Appointment appointment = aService.getEventById(eventId);

					if(appointment.getDoctorId() == doctor.getId())
					{
						request.setAttribute("event", event);
						
					}	
				}
				target ="doctorPlanner.jsp";

			}
			
			if(uri.endsWith("viewMedicalTracker.doctor"))
			{ 


				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				Doctor doctor;
				doctor = lService.getDoctorById(id);
				
				int pId = Integer.parseInt(request.getParameter("id"));
				PatientService pService = new PatientService();
				List<Prescription> prescriptions = pService.getAllPrescriptionByPatientId(pId);
				
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


					int sort = 0;
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
					Patient patient = lService.getPatientById(prescription.getPatientId());
					prescription.setPatientName(patient.getFirstName()+" "+patient.getLastName());
					if(prescription.getDoctorId()!=0)
					{
					Doctor d = lService.getDoctorById(prescription.getDoctorId());
					prescription.setDoctorName("Dr."+d.getFirstName()+" "+d.getLastName());
					}
					prescription.setMedicines(medicines);

				}
				request.setAttribute("prescriptions", prescriptions);
				request.setAttribute("doctor", doctor);
				
				List<Patient> patients = lService.getAllPatients(id);
				List<Pharmacy> pharmacies = lService.getAllPharmacies(id);


				request.setAttribute("patients", patients);
				request.setAttribute("pharmacies", pharmacies);
				
				
				List<Doctor> doctors = lService.getAllDoctorsForDoctor(id);
				request.setAttribute("doctors", doctors);
				target ="doctorViewPatientTracker.jsp";

			}

			if(uri.endsWith("prescription.doctor"))
			{


				int pId = Integer.parseInt(request.getParameter("id"));
				Patient patient = lService.getPatientById(pId);

				Calendar calendar1 = Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance(); 
				calendar1.setTime(new Date());
				calendar2.setTime(patient.getDob());


				long miliSecondForDate1 = calendar1.getTimeInMillis();
				long miliSecondForDate2 = calendar2.getTimeInMillis();

				long diffInMilis = miliSecondForDate1 - miliSecondForDate2;

				long age = diffInMilis / (24 * 60 * 60 * 1000);

				request.setAttribute("age", age);


				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				Doctor doctor = lService.getDoctorById(id);
				List<Patient> patients = lService.getAllPatients(id);
				List<Pharmacy> pharmacies = lService.getAllPharmacies(id);


				request.setAttribute("patients", patients);
				request.setAttribute("pharmacies", pharmacies);
				
				
				List<Doctor> doctors = lService.getAllDoctorsForDoctor(id);
				request.setAttribute("doctors", doctors);
				
				request.setAttribute("doctor", doctor);
				request.setAttribute("patient", patient);
				target="doctorPrescription.jsp";

			}

			if(uri.endsWith("prescriptionSave.doctor"))
			{



				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int dId = login.getId();


				int pId = Integer.parseInt(request.getParameter("id"));
				int checkup=Integer.parseInt(request.getParameter("checkup"));



				int s[]= new int[10];
				for(int i=1;i<=5;i++)
				{
					if(!request.getParameter("s"+i).isEmpty())
					{
						s[i] = service.getSymptomIdByName(request.getParameter("s"+i));
					}
				}



				int d[]= new int[10];
				for(int i=1;i<=5;i++)
				{
					if(!request.getParameter("d"+i).isEmpty())
					{
						d[i] = service.getDiseaseIdByName(request.getParameter("d"+i));
					}
				}


				int m[]= new int[10];
				int q[]= new int[10];
				int morning[]= new int[10];
				int afternoon[]= new int[10];
				int night[]= new int[10];

				Time mt[] = new Time[10];
				Time at[] = new Time[10];
				Time nt[] = new Time[10];



				for(int i=1;i<=5;i++)
				{
					if(!request.getParameter("m"+i).isEmpty() && !request.getParameter("q"+i).isEmpty())
					{
						SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
						Medicine med=service.getMedicineByName(request.getParameter("m"+i));
						m[i]=med.getId();
						q[i] = Integer.parseInt(request.getParameter("q"+i));
						if(request.getParameter("morning"+i)!=null)
						{
							morning[i] = Integer.parseInt(request.getParameter("morning"+i));
							if(!request.getParameter("mt"+i).isEmpty())

							{
								Date date1 = sdf.parse(request.getParameter("mt"+i)); 
								mt[i] = new Time(date1.getTime());
							}
						}
						if(request.getParameter("afternoon"+i)!=null)
						{
							afternoon[i] = Integer.parseInt(request.getParameter("afternoon"+i));
							if(!request.getParameter("at"+i).isEmpty())
							{
								Date date2 = sdf.parse(request.getParameter("at"+i)); 
								at[i] = new Time(date2.getTime());
							}
						}
						if(request.getParameter("night"+i)!=null)
						{
							night[i] = Integer.parseInt(request.getParameter("night"+i));
							if(!request.getParameter("nt"+i).isEmpty())
							{
								Date date3 = sdf.parse(request.getParameter("nt"+i)); 
								nt[i] = new Time(date3.getTime());
							}
						}

					}
				}



				int updatePatient = 0;
				String bp="";
				String bpStatus = "";
				if(request.getParameter("bp")!=null && !request.getParameter("bp").isEmpty())
				{
					bpStatus = request.getParameter("bpstatus");
					bp = request.getParameter("bp");
					updatePatient = 1;
				}

				double sugar = 0.0;
				String sugarStatus = "";
				if(request.getParameter("sugar")!=null && !request.getParameter("sugar").isEmpty())
				{
					sugar = Double.parseDouble(request.getParameter("sugar"));
					if(sugar != 0.0)
					{
						sugarStatus = request.getParameter("sugarstatus");
						updatePatient = 1;
					}
				}

				double cholesterol = 0.0;
				String cholesterolStatus = "";

				if(request.getParameter("cholesterol")!=null && !request.getParameter("cholesterol").isEmpty())
				{
					cholesterol = Double.parseDouble(request.getParameter("cholesterol"));
					if(cholesterol != 0.0)
					{
						cholesterolStatus = request.getParameter("cholesterolstatus");
						updatePatient = 1;
					}
				}


				String prescriptionPath ="";
				Part filePart = request.getPart("file");

				if(filePart.getSize()!=0)
				{
					String SAVE_DIR = "Images"+ File.separator +"Prescription"+ File.separator +"prescription"+login.getUserName()+"to"+pId;
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
							prescriptionPath= SAVE_DIR+ File.separator +new Date().getTime();
						}

						part.write(savePath + File.separator +new Date().getTime());

					}
				}

				String suggestion = request.getParameter("suggestion");

				Prescription prescription = new Prescription( dId, pId, s[1], s[2], s[3], s[4], s[5], d[1], d[2], d[3], d[4], d[5], m[1], m[2], m[3], m[4], m[5], prescriptionPath, suggestion);
				prescription.setCheckup(checkup);
				Patient p = lService.getPatientById(prescription.getPatientId());

				prescription.setBp(bp);
				prescription.setSugar(sugar);
				prescription.setCholesterol(cholesterol);
				prescription.setBmi(p.getBmi());

				prescription.setBpStatus(bpStatus);
				prescription.setSugarStatus(sugarStatus);
				prescription.setCholesterolStatus(cholesterolStatus);

				int prescriptionId = service.savePrescription(prescription,1);
				AlertService alert = new AlertService();
				Date da = new Date();
				for(int i=1;i<=5;i++)
				{

					if(m[i]!=0)
					{
						Medicine temp = service.getMedicineCostById(m[i]);

						Medicine medicine = new Medicine(m[i],prescriptionId,temp.getName(),q[i],morning[i],afternoon[i],night[i],mt[i],at[i],nt[i]);

						service.savePrescriptionMedicine(medicine);
						int left = q[i];
						@SuppressWarnings("deprecation")
						Time ts = new Time(da.getHours(), da.getMinutes(), da.getSeconds());
						if(morning[i]!=0){
						if(ts.getTime() < mt[i].getTime()){
							String msg="Time to take your "+temp.getName()+" medicine";
							
							alert.sendSMS(msg , p.getMobile() ,mt[i]);
							left--;
						}
						}
						if(afternoon[i]!=0){
						if(ts.getTime() < at[i].getTime()){
							
							String msg="Time to take your "+temp.getName()+" medicine";
							
							alert.sendSMS(msg , p.getMobile() ,at[i]);
							left--;
						}
						}
						if(night[i]!=0){
						if(ts.getTime() < nt[i].getTime()){
							
							String msg="Time to take your "+temp.getName()+" medicine";
							
							alert.sendSMS(msg , p.getMobile() ,nt[i]);
							left--;
						}
						}
						alert.updateLeftMedicines(prescriptionId , temp.getName() , left);
					}
				}

				if(updatePatient == 1){
					service.updatePatient(prescription, 0);
					}
				
				
				

				String doctorcontentlog = "Created Prescription with ID="+prescriptionId+"For Patient id="+p.getId()+"and Name="+p.getFirstName()+" "+p.getLastName();
				Log logdoctor = new Log(pId,1,doctorcontentlog );
				lService.saveLog(logdoctor);




				Doctor doctor = lService.getDoctorById(dId);
				String doctorName = doctor.getFirstName()+" "+doctor.getLastName();

				String patientcontentlog = "Received Prescription with ID="+prescriptionId+"By Doctor id="+dId+"and Name="+doctorName;
				Log logpatient = new Log(dId,2,patientcontentlog );
				lService.saveLog(logpatient);


				String content = "<a href='viewPrescriptionBy.patient?id="+prescriptionId+" '>Dr. "+doctorName+"  Has Written A Prescription For You</a>";
				Notification notification = new Notification(0, dId, content, prescription.getPatientId(), 0,2);
				notification.setImage(doctor.getImage());
				lService.saveNotification(notification);
				
				Patient patient = lService.getPatientById(prescription.getPatientId());
			
				String message = "Dr."+doctor.getFirstName()+" "+doctor.getLastName()+" is sent you a new prescription. Kindly login to see your prescription"; 
				lService.sendMessage(patient.getMobile() ,message);
			    
			    
			    request.setAttribute("insert", 1);
			    
			    
				
				target = lService.setErrorControl(request,target,login1);
				request.setAttribute("error", "Successfully created prescription");
			}

			if(uri.endsWith("order.doctor"))
			{




				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				int pId = Integer.parseInt(request.getParameter("id"));
				Pharmacy pharmacy = lService.getPharmacyById(pId);
				
				request.setAttribute("pharmacy", pharmacy);
				
				Doctor doctor = lService.getDoctorById(id);
				List<Patient> patients = lService.getAllPatients(id);
				List<Pharmacy> pharmacies = lService.getAllPharmacies(id);
				
				List<Doctor> doctors = lService.getAllDoctorsForDoctor(id);
				request.setAttribute("doctors", doctors);
				
				request.setAttribute("patients", patients);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("doctor", doctor);
				target="doctorOrder.jsp";

			}



			if(uri.endsWith("allPrescriptions.doctor"))
			{



				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int dId = login.getId();
				List<Prescription> prescriptions = service.getAllPrescriptionByDocorId(dId);
				PatientService pService = new PatientService();
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


					int sort = 0;
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
					Patient patient = lService.getPatientById(prescription.getPatientId());
					prescription.setPatientName(patient.getFirstName()+" "+patient.getLastName());
					prescription.setMedicines(medicines);

				}


				
				Doctor doctor = lService.getDoctorById(dId);
				List<Patient> patients = lService.getAllPatients(dId);
				List<Pharmacy> pharmacies = lService.getAllPharmacies(dId);

				request.setAttribute("prescriptions", prescriptions);
				request.setAttribute("patients", patients);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("doctor", doctor);
				target="doctorViewAllPrescription.jsp";




			}

			if(uri.endsWith("getAllUrgentOrder.doctor"))
			{




				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				OrderService oService = new OrderService();
				List<Order> orders = oService.getAllUrgentOrder(login);

				for(int i=0;i<orders.size();i++)
				{
					Pharmacy pharmacy = lService.getPharmacyById(orders.get(i).getSupplierPharmacyId());
					orders.get(i).setSupplierPharmacyName(pharmacy.getPharmacyName());

					orders.get(i).setMedicines(oService.getAllMedicinesByOrderId(orders.get(i).getId()));
				}
				Doctor doctor = lService.getDoctorById(id);
				List<Patient> patients = lService.getAllPatients(id);
				List<Pharmacy> pharmacies = lService.getAllPharmacies(id);


				request.setAttribute("type", 1);
				request.setAttribute("orders", orders);
				request.setAttribute("patients", patients);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("doctor", doctor);
				target="doctorViewAllOrder.jsp";


			}

			if(uri.endsWith("getAllNormalOrder.doctor"))
			{



				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				OrderService oService = new OrderService();
				List<Order> orders = oService.getAllNormalOrder(login);

				for(int i=0;i<orders.size();i++)
				{
					Pharmacy pharmacy = lService.getPharmacyById(orders.get(i).getSupplierPharmacyId());
					orders.get(i).setSupplierPharmacyName(pharmacy.getPharmacyName());

					orders.get(i).setMedicines(oService.getAllMedicinesByOrderId(orders.get(i).getId()));
				}
				Doctor doctor = lService.getDoctorById(id);
				List<Patient> patients = lService.getAllPatients(id);
				List<Pharmacy> pharmacies = lService.getAllPharmacies(id);

				request.setAttribute("type", 2);
				request.setAttribute("orders", orders);
				request.setAttribute("patients", patients);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("doctor", doctor);

				target="doctorViewAllOrder.jsp";

			}

			if(uri.endsWith("getAllDeliveredOrder.doctor"))
			{



				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				OrderService oService = new OrderService();
				List<Order> orders = oService.getAllDeliveredOrder(login);

				for(int i=0;i<orders.size();i++)
				{
					Pharmacy pharmacy = lService.getPharmacyById(orders.get(i).getSupplierPharmacyId());
					orders.get(i).setSupplierPharmacyName(pharmacy.getPharmacyName());

					orders.get(i).setMedicines(oService.getAllMedicinesByOrderId(orders.get(i).getId()));
				}
				Doctor doctor = lService.getDoctorById(id);
				List<Patient> patients = lService.getAllPatients(id);
				List<Pharmacy> pharmacies = lService.getAllPharmacies(id);


				request.setAttribute("type", 3);
				request.setAttribute("orders", orders);
				request.setAttribute("patients", patients);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("doctor", doctor);
				target="doctorViewAllOrder.jsp";


			}
			
			if(uri.endsWith("addPost.doctor"))
			{



				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				
				int postType = Integer.parseInt(request.getParameter("postType"));
				int privacy = Integer.parseInt(request.getParameter("privacy"));
				String status = request.getParameter("status");
				String filePath = "";
				if(postType == 1){
					filePath = "";
				}
				if(postType == 2 || postType == 3){
					Part filePart = request.getPart("file");

					if(filePart.getSize()!=0)
					{
						String SAVE_DIR = "Images"+ File.separator +"Post"+ File.separator +login.getUserName();
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
								
								
								filePath= SAVE_DIR+ File.separator + +new Date().getTime();
							}

							part.write(savePath + File.separator + +new Date().getTime());

						}
					}
					else
					{
						if(postType == 3){
							
							filePath = request.getParameter("videourl");
							
							String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";

						    Pattern compiledPattern = Pattern.compile(pattern);
						    Matcher matcher = compiledPattern.matcher(filePath);

						    if(matcher.find()){
						        String s =  matcher.group();
						        filePath = "https://www.youtube.com/embed/"+s;
						    }
							
							
						}
					}
				}
				
				Post post = new Post();
				post.setDoctorId(id);
				post.setPrivacy(privacy);
				post.setPostType(postType);
				post.setStatus(status);
				post.setFilePath(filePath);
				
				service.savePost(post);
				
				request.setAttribute("insert", 1);
				
				target = lService.setErrorControl(request,target,login1);
				request.setAttribute("error", "Successfully created Post");

			}
			
		} catch (Exception e) {
			try {
				
				logger.error(e.getStackTrace());
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