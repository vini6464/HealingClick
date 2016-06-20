package com.vinod.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.vinod.model.Appointment;
import com.vinod.model.Disease;
import com.vinod.model.Doctor;
import com.vinod.model.Log;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Prescription;
import com.vinod.model.Symptom;
import com.vinod.model.Vaccination;
import com.vinod.service.AlertService;
import com.vinod.service.AppointmentService;
import com.vinod.service.DoctorService;
import com.vinod.service.LoginService;
import com.vinod.service.OrderService;
import com.vinod.service.PatientService;

/**
 * Servlet implementation class PatientController
 */
@MultipartConfig
@WebServlet("*.patient")
public class PatientController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PatientController() {
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
		DoctorService dService= new DoctorService();
		PatientService service = new PatientService();
		LoginService lService = new LoginService();
		String uri=request.getRequestURI();
		String target = "home.jsp";
		System.out.println(uri);
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
			if(uri.endsWith("fixAppointment.patient"))
			{


				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				int dId = Integer.parseInt(request.getParameter("id"));
				Patient patient = lService.getPatientById(id);
				
				request.setAttribute("doctor", lService.getDoctorById(dId));
				
				request.setAttribute("patient", patient);
				target="patientDoctorPlanner.jsp";


			}

			if(uri.endsWith("appointment.patient"))
			{


				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				Patient patient = lService.getPatientById(id);
				

				List<Doctor> doctors = lService.getAllDoctors(id);
				request.setAttribute("doctors", doctors);

				
				request.setAttribute("patient", patient);
				
				String event = request.getParameter("eId");
				if(event!=null  && !event.isEmpty()){
					
					
					int eventId = Integer.parseInt(event);

					AppointmentService aService = new AppointmentService();
					Appointment appointment = aService.getEventById(eventId);

					if(appointment.getPatientId() == patient.getId())
					{
						request.setAttribute("event", event);
						
					}	
				}
				
				target="patientPlanner.jsp";


			}


			if(uri.endsWith("viewPrescriptionBy.patient"))
			{
				int prescriptionId = Integer.parseInt(request.getParameter("id"));

				Prescription prescription = service.getPrescriptionById(prescriptionId);
				
				if(prescription.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This Prescription doesnot exist");
				}
				else
				{
					if(prescription.getPatientId() != login1.getId())
					{
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "You can't view this prescription");
					}
					else
					{
					
					
				if(prescription.getS1()!=0)
				{
					prescription.setsName1(service.getSymptomNameById(prescription.getS1()));
				}
				if(prescription.getS2()!=0)
				{
					prescription.setsName2(service.getSymptomNameById(prescription.getS2()));
				}
				if(prescription.getS3()!=0)
				{
					prescription.setsName3(service.getSymptomNameById(prescription.getS3()));
				}
				if(prescription.getS4()!=0)
				{
					prescription.setsName4(service.getSymptomNameById(prescription.getS4()));
				}
				if(prescription.getS5()!=0)
				{
					prescription.setsName5(service.getSymptomNameById(prescription.getS5()));
				}

				if(prescription.getD1()!=0)
				{
					prescription.setdName1(service.getDiseaseNameById(prescription.getD1()));
				}
				if(prescription.getD2()!=0)
				{
					prescription.setdName2(service.getDiseaseNameById(prescription.getD2()));
				}
				if(prescription.getD3()!=0)
				{
					prescription.setdName3(service.getDiseaseNameById(prescription.getD3()));
				}
				if(prescription.getD4()!=0)
				{
					prescription.setdName4(service.getDiseaseNameById(prescription.getD4()));
				}
				if(prescription.getD5()!=0)
				{
					prescription.setdName5(service.getDiseaseNameById(prescription.getD5()));
				}

				List<Medicine> medicines = new ArrayList<Medicine>();

				int sort=0;
				if(prescription.getM1()!=0)
				{
					medicines.add(service.getPrescribedMedicineById(prescription,prescription.getM1(),sort));
				}
				if(prescription.getM2()!=0)
				{
					medicines.add(service.getPrescribedMedicineById(prescription,prescription.getM2(),sort));
				}
				if(prescription.getM3()!=0)
				{
					medicines.add(service.getPrescribedMedicineById(prescription,prescription.getM3(),sort));
				}
				if(prescription.getM4()!=0)
				{
					medicines.add(service.getPrescribedMedicineById(prescription,prescription.getM4(),sort));
				}
				if(prescription.getM5()!=0)
				{
					medicines.add(service.getPrescribedMedicineById(prescription,prescription.getM5(),sort));
				}

				if(prescription.getDoctorId()>0)
				{
					Doctor patient1 = lService.getDoctorById(prescription.getDoctorId());
					prescription.setDoctorName(patient1.getFirstName()+" "+patient1.getLastName());
				}
				prescription.setMedicines(medicines);

				request.setAttribute("prescription", prescription);

				Login login = (Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				Patient patient = lService.getPatientById(id);
				List<Doctor> doctors = lService.getAllDoctors(id);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(id);
				request.setAttribute("doctors", doctors);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("patient", patient);
				target="patientViewPrescription.jsp";
				}
				}
			}

			if(uri.endsWith("allPrescriptions.patient"))
			{


				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int pId = login.getId();
				List<Prescription> prescriptions;
				prescriptions = service.getAllPrescriptionByPatientId(pId);

				for(int i=0;i<prescriptions.size();i++)
				{
					Prescription prescription = prescriptions.get(i);
					if(prescription.getS1()!=0)
					{
						prescription.setsName1(service.getSymptomNameById(prescription.getS1()));
					}
					if(prescription.getS2()!=0)
					{
						prescription.setsName2(service.getSymptomNameById(prescription.getS2()));
					}
					if(prescription.getS3()!=0)
					{
						prescription.setsName3(service.getSymptomNameById(prescription.getS3()));
					}
					if(prescription.getS4()!=0)
					{
						prescription.setsName4(service.getSymptomNameById(prescription.getS4()));
					}
					if(prescription.getS5()!=0)
					{
						prescription.setsName5(service.getSymptomNameById(prescription.getS5()));
					}

					if(prescription.getD1()!=0)
					{
						prescription.setdName1(service.getDiseaseNameById(prescription.getD1()));
					}
					if(prescription.getD2()!=0)
					{
						prescription.setdName2(service.getDiseaseNameById(prescription.getD2()));
					}
					if(prescription.getD3()!=0)
					{
						prescription.setdName3(service.getDiseaseNameById(prescription.getD3()));
					}
					if(prescription.getD4()!=0)
					{
						prescription.setdName4(service.getDiseaseNameById(prescription.getD4()));
					}
					if(prescription.getD5()!=0)
					{
						prescription.setdName5(service.getDiseaseNameById(prescription.getD5()));
					}

					List<Medicine> medicines = new ArrayList<Medicine>();


					int sort=0;
					if(prescription.getM1()!=0)
					{
						medicines.add(service.getPrescribedMedicineById(prescription,prescription.getM1(),sort));
					}
					if(prescription.getM2()!=0)
					{
						medicines.add(service.getPrescribedMedicineById(prescription,prescription.getM2(),sort));
					}
					if(prescription.getM3()!=0)
					{
						medicines.add(service.getPrescribedMedicineById(prescription,prescription.getM3(),sort));
					}
					if(prescription.getM4()!=0)
					{
						medicines.add(service.getPrescribedMedicineById(prescription,prescription.getM4(),sort));
					}
					if(prescription.getM5()!=0)
					{
						medicines.add(service.getPrescribedMedicineById(prescription,prescription.getM5(),sort));
					}
					if(prescription.getDoctorId()>0)
					{
						Doctor patient1 = lService.getDoctorById(prescription.getDoctorId());
						prescription.setDoctorName(patient1.getFirstName()+" "+patient1.getLastName());
					}
					prescription.setMedicines(medicines);

				}



				Patient patient = lService.getPatientById(pId);
				List<Doctor> doctors = lService.getAllDoctors(pId);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(pId);

				request.setAttribute("doctors", doctors);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("patient", patient);


				request.setAttribute("prescriptions", prescriptions);
				target="patientViewAllPrescription.jsp";


			}
			if(uri.endsWith("order.patient"))
			{


				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				int pId = Integer.parseInt(request.getParameter("id"));
				Pharmacy pharmacy = lService.getPharmacyById(pId);
				Patient patient = lService.getPatientById(id);
				List<Doctor> doctors = lService.getAllDoctors(id);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(id);

				request.setAttribute("doctors", doctors);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("patient", patient);
				request.setAttribute("pharmacy", pharmacy);
				
				target="patientOrder.jsp";

			}

			if(uri.endsWith("getAllUrgentOrder.patient"))
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
				Patient patient = lService.getPatientById(id);
				List<Doctor> doctors = lService.getAllDoctors(id);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(id);

				request.setAttribute("doctors", doctors);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("patient", patient);

				request.setAttribute("type", 1);
				request.setAttribute("orders", orders);

				target="patientViewAllOrder.jsp";


			}

			if(uri.endsWith("getAllNormalOrder.patient"))
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
				Patient patient = lService.getPatientById(id);
				List<Doctor> doctors = lService.getAllDoctors(id);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(id);

				request.setAttribute("doctors", doctors);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("patient", patient);

				request.setAttribute("type", 2);
				request.setAttribute("orders", orders);

				target="patientViewAllOrder.jsp";


			}

			if(uri.endsWith("getAllDeliveredOrder.patient"))
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
				Patient patient = lService.getPatientById(id);
				List<Doctor> doctors = lService.getAllDoctors(id);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(id);

				request.setAttribute("doctors", doctors);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("patient", patient);

				request.setAttribute("type", 3);
				request.setAttribute("orders", orders);

				target="patientViewAllOrder.jsp";


			}


			if(uri.endsWith("prescriptionSave.patient"))
			{


				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int pId = login.getId();

				int dId = 0;

				int s[]= new int[10];
				for(int i=1;i<=5;i++)
				{
					if(!request.getParameter("s"+i).isEmpty())
					{
						s[i] = dService.getSymptomIdByName(request.getParameter("s"+i));
					}
				}



				int d[]= new int[10];
				for(int i=1;i<=5;i++)
				{
					if(!request.getParameter("d"+i).isEmpty())
					{
						d[i] = dService.getDiseaseIdByName(request.getParameter("d"+i));
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
						Medicine med=dService.getMedicineByName(request.getParameter("m"+i));
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
				
				double bmi = 0.0;

				double heightInFeet = 0.0;
				double heightInMeter = 0.0;
				
				
				
				double weight = 0.0;
				
				if(request.getParameter("height")!=null && !request.getParameter("height").isEmpty() && request.getParameter("weight")!=null && !request.getParameter("weight").isEmpty())
				{
					
					heightInFeet = Double.parseDouble(request.getParameter("height"));
					
					weight = Double.parseDouble(request.getParameter("weight"));
					if(heightInFeet !=0 && weight!=0 ){
						heightInMeter = heightInFeet/3.2808;
						bmi = weight/(heightInMeter*heightInMeter);
						
						String textBmi = new DecimalFormat("##.##").format(bmi);
						bmi = Double.parseDouble(textBmi);
						updatePatient = 1;
					}
					
				}
				

				
				
				
				String prescriptionPath ="";
				Part filePart = request.getPart("file");

				if(filePart.getSize()!=0)
				{
					String SAVE_DIR = "Images"+ File.separator +"Prescription"+ File.separator +"prescriptionSelf"+login.getUserName();
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
				int checkup=0;
				prescription.setCheckup(checkup);

				Patient p = lService.getPatientById(prescription.getPatientId());

				prescription.setBp(bp);
				prescription.setSugar(sugar);
				prescription.setCholesterol(cholesterol);
				prescription.setBmi(bmi);
				prescription.setBpStatus(bpStatus);
				prescription.setSugarStatus(sugarStatus);
				prescription.setCholesterolStatus(cholesterolStatus);
				
				int prescriptionId = dService.savePrescription(prescription,0);
				AlertService alert = new AlertService();
				Date da = new Date();
				for(int i=1;i<=5;i++)
				{

					if(m[i]!=0)
					{
						Medicine temp = dService.getMedicineCostById(m[i]);

						Medicine medicine = new Medicine(m[i],prescriptionId,temp.getName(),q[i],morning[i],afternoon[i],night[i],mt[i],at[i],nt[i]);

						dService.savePrescriptionMedicine(medicine);
						
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
				dService.updatePatient(prescription, 0);
				}
				
				String content = "Self Prescription with ID="+prescriptionId;
				Log log = new Log(pId,2,content );
				lService.saveLog(log);
				request.setAttribute("insert", 1);
				target = lService.setErrorControl(request,target,login1);
				request.setAttribute("error", "Successfully created prescription");


			}

			if(uri.endsWith("prescription.patient"))
			{
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				Patient patient = lService.getPatientById(id);

				List<Doctor> patients = lService.getAllDoctors(id);
				List<Pharmacy> pharmacies = lService.getAllPharmacies(id);


				request.setAttribute("doctors", patients);
				request.setAttribute("pharmacies", pharmacies);

				request.setAttribute("patient", patient);

				Calendar calendar1 = Calendar.getInstance();
				Calendar calendar2 = Calendar.getInstance(); 
				calendar1.setTime(new Date());
				calendar2.setTime(patient.getDob());


				long miliSecondForDate1 = calendar1.getTimeInMillis();
				long miliSecondForDate2 = calendar2.getTimeInMillis();

				long diffInMilis = miliSecondForDate1 - miliSecondForDate2;

				long age = diffInMilis / (24 * 60 * 60 * 1000);

				request.setAttribute("age", age);

				target="patientPrescription.jsp";

			}

			if(uri.endsWith("saveVaccine.patient"))
			{




				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int patientId = login.getId();
				int id = Integer.parseInt(request.getParameter("id"));

				service.saveVaccine(patientId,id);
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				pw.print("success");


			}

			if(uri.endsWith("removeVaccine.patient"))
			{

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int patientId = login.getId();
				int id = Integer.parseInt(request.getParameter("id"));

				service.removeVaccine(patientId,id);
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				pw.print("success");


			}
			
			if(uri.endsWith("addVaccine.patient"))
			{

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int patientId = login.getId();
				String name = request.getParameter("vaccineName");
				String dueDate = request.getParameter("dueDate");
				String dueTime = request.getParameter("dueTime");
				SimpleDateFormat sdfTime = new SimpleDateFormat("hh:mm");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date1 = sdfTime.parse(dueTime); 
				Time time = new Time(date1.getTime());
				
				Date date;
				date = sdf.parse(dueDate);
				
				Vaccination vaccination = new Vaccination(patientId, name, date ,time);
				service.addVaccination(vaccination);
				
				
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				pw.print("1");


			}
			if(uri.endsWith("statusTrue.patient"))
			{

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int patientId = login.getId();
				int id = Integer.parseInt(request.getParameter("id"));

				service.trueVaccine(patientId,id);
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				pw.print("success");


			}

			if(uri.endsWith("statusFalse.patient"))
			{

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int patientId = login.getId();
				int id = Integer.parseInt(request.getParameter("id"));

				service.falseVaccine(patientId,id);
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				pw.print("success");


			}
			
			if(uri.endsWith("deleteVaccine.patient"))
			{




				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int patientId = login.getId();
				int id = Integer.parseInt(request.getParameter("id"));

				service.deleteVaccine(patientId,id);
				PrintWriter pw = response.getWriter();
				response.setContentType("text/html");
				pw.print("success");


			}
			

		} catch (Exception e) {
			try {
				System.out.println("\n In controller Error:"+e.getMessage());
				target = lService.setErrorControl(request,target,login1);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
			} catch (Exception e1) {
				System.out.println("\n In controller Error:"+e1.getMessage());
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
