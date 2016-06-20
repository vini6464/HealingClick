package com.vinod.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
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



import com.vinod.model.Doctor;
import com.vinod.model.Log;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Notification;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.service.DoctorService;
import com.vinod.service.LoginService;
import com.vinod.service.OrderService;
import com.vinod.service.PharmacyService;



/**
 * Servlet implementation class OrderController
 */
@MultipartConfig
@WebServlet("*.order")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderController() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	private void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String uri=request.getRequestURI();
		OrderService service = new OrderService();
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
		DoctorService dService = new DoctorService();
		LoginService lService = new LoginService();
		try {
		


		if(uri.endsWith("confirm.order"))
		{
			
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				lService.setLastActive(login);
				int id=login.getId();
				int phId = Integer.parseInt(request.getParameter("id"));
				double totalCost = 0.0;
				String m1 ;
				int q1 = 0;
				List<Medicine> medicines = new ArrayList<Medicine>();
				if(!request.getParameter("m1").isEmpty() && !request.getParameter("q1").isEmpty())
				{
					m1=request.getParameter("m1");
					q1 = Integer.parseInt(request.getParameter("q1"));
					Medicine temp = dService.getMedicineCostByName(m1, phId);
					Medicine medicine = new Medicine();
					medicine.setId(0);
					medicine.setName(temp.getName());
					medicine.setQuantity(q1);
					totalCost=totalCost+(q1*temp.getCost());
					medicine.setCost(q1*temp.getCost());
					medicines.add(medicine);

				}
				
				String m2;
				int q2 = 0;

				if(!request.getParameter("m2").isEmpty() && !request.getParameter("q2").isEmpty())
				{
					m2=request.getParameter("m2");
					q2 = Integer.parseInt(request.getParameter("q2"));
					Medicine temp = dService.getMedicineCostByName(m2,phId);
					Medicine medicine = new Medicine();
					medicine.setId(0);
					medicine.setName(temp.getName());
					medicine.setQuantity(q2);
					totalCost=totalCost+(q2*temp.getCost());
					medicine.setCost(q2*temp.getCost());
					medicines.add(medicine);

				}
			
				String m3 ;
				int q3 = 0;

				if(!request.getParameter("m3").isEmpty() && !request.getParameter("q3").isEmpty())
				{
					m3=request.getParameter("m3");
					q3 = Integer.parseInt(request.getParameter("q3"));
					Medicine temp = dService.getMedicineCostByName(m3,phId);
					Medicine medicine = new Medicine();
					medicine.setId(0);
					medicine.setName(temp.getName());
					medicine.setQuantity(q3);
					totalCost=totalCost+(q3*temp.getCost());
					medicine.setCost(q3*temp.getCost());
					medicines.add(medicine);

				}
				String m4 ;
				int q4 = 0;

				if(!request.getParameter("m4").isEmpty() && !request.getParameter("q4").isEmpty())
				{
					m4=request.getParameter("m4");
					q4 = Integer.parseInt(request.getParameter("q4"));
					Medicine temp = dService.getMedicineCostByName(m4,phId);
					Medicine medicine = new Medicine();
					medicine.setId(0);
					medicine.setName(temp.getName());
					medicine.setQuantity(q4);
					totalCost=totalCost+(q4*temp.getCost());
					medicine.setCost(q4*temp.getCost());
					medicines.add(medicine);

				}
				String m5 ;
				int q5 = 0;

				if(!request.getParameter("m5").isEmpty() && !request.getParameter("q5").isEmpty())
				{
					m5=request.getParameter("m5");
					q5 = Integer.parseInt(request.getParameter("q5"));
					Medicine temp = dService.getMedicineCostByName(m5,phId);
					Medicine medicine = new Medicine();
					medicine.setId(0);
					medicine.setName(temp.getName());
					medicine.setQuantity(q5);
					totalCost=totalCost+(q5*temp.getCost());
					medicine.setCost(q5*temp.getCost());
					medicines.add(medicine);

				}
				String m6 ;
				int q6 = 0;

				if(!request.getParameter("m6").isEmpty() && !request.getParameter("q6").isEmpty())
				{
					m6=request.getParameter("m6");
					q6 = Integer.parseInt(request.getParameter("q6"));
					Medicine temp = dService.getMedicineCostByName(m6,phId);
					Medicine medicine = new Medicine();
					medicine.setId(0);
					medicine.setName(temp.getName());
					medicine.setQuantity(q6);
					totalCost=totalCost+(q6*temp.getCost());
					medicine.setCost(q6*temp.getCost());
					medicines.add(medicine);

				}
				String m7 ;
				int q7 = 0;

				if(!request.getParameter("m7").isEmpty() && !request.getParameter("q7").isEmpty())
				{
					m7=request.getParameter("m7");
					q7 = Integer.parseInt(request.getParameter("q7"));
					Medicine temp = dService.getMedicineCostByName(m7,phId);
					Medicine medicine = new Medicine();
					medicine.setId(0);
					medicine.setName(temp.getName());
					medicine.setQuantity(q7);
					totalCost=totalCost+(q7*temp.getCost());
					medicine.setCost(q7*temp.getCost());
					medicines.add(medicine);

				}
				String m8 ;
				int q8 = 0;

				if(!request.getParameter("m8").isEmpty() && !request.getParameter("q8").isEmpty())
				{
					m8=request.getParameter("m8");
					q8 = Integer.parseInt(request.getParameter("q8"));
					Medicine temp = dService.getMedicineCostByName(m8,phId);
					Medicine medicine = new Medicine();
					medicine.setId(0);
					medicine.setName(temp.getName());
					medicine.setQuantity(q8);
					totalCost=totalCost+(q8*temp.getCost());
					medicine.setCost(q8*temp.getCost());
					medicines.add(medicine);

				}
				String m9;
				int q9 = 0;

				if(!request.getParameter("m9").isEmpty() && !request.getParameter("q9").isEmpty())
				{
					m9=request.getParameter("m9");
					q9 = Integer.parseInt(request.getParameter("q9"));
					Medicine temp = dService.getMedicineCostByName(m9,phId);
					Medicine medicine = new Medicine();
					medicine.setId(0);
					medicine.setName(temp.getName());
					medicine.setQuantity(q9);
					totalCost=totalCost+(q9*temp.getCost());
					medicine.setCost(q9*temp.getCost());
					medicines.add(medicine);

				}

				String m10 ;
				int q10 = 0;

				if(!request.getParameter("m10").isEmpty() && !request.getParameter("q10").isEmpty())
				{
					m10=request.getParameter("m10");
					q10 = Integer.parseInt(request.getParameter("q10"));
					Medicine temp = dService.getMedicineCostByName(m10,phId);
					Medicine medicine = new Medicine();
					medicine.setId(0);
					medicine.setName(temp.getName());
					medicine.setQuantity(q10);
					totalCost=totalCost+(q10*temp.getCost());
					medicine.setCost(q10*temp.getCost());
					medicines.add(medicine);
				}

				
				
				
				Pharmacy pharmacy = lService.getPharmacyById(phId);
				
				
				double discount = totalCost * (pharmacy.getDiscount()/100) ;
				
				totalCost = totalCost - discount;
				
				totalCost = totalCost + pharmacy.getDeliveryCharge();
				
				
				if(login.getType()==1)
				{

					
					int oType = Integer.parseInt(request.getParameter("oType"));

					int pType = Integer.parseInt(request.getParameter("pType"));
					

					
					

					int address = Integer.parseInt(request.getParameter("address"));

					String forumPath ="";
					Part filePart = request.getPart("file");

					if(filePart.getSize()!=0)
					{
						String SAVE_DIR = "Images"+ File.separator +"Order"+ File.separator +"doctororder"+login.getUserName();
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
								System.out.println(fileName);
								forumPath=SAVE_DIR + File.separator +new Date().getTime();
							}

							part.write(savePath + File.separator +new Date().getTime());

						}
					}
					
					if(address == 2)
					{
						
						Doctor doctor = lService.getDoctorById(id);
						

						Order order = new Order();

						order.setDoctorId(doctor.getId());
						order.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());

						order.setSupplierPharmacyId(pharmacy.getId());
						order.setSupplierPharmacyName(pharmacy.getPharmacyName());
						 order.setImage(forumPath);
						order.setOrderType(oType);
						order.setCashType(pType);

						order.setMedicines(medicines);
						order.setTotalCost(totalCost);

						List<Patient> patients = lService.getAllPatients(id);
						List<Pharmacy> pharmacies = lService.getAllPharmacies(id);
						request.setAttribute("patients", patients);
						request.setAttribute("pharmacies", pharmacies);

						List<Doctor> doctors = lService.getAllDoctorsForDoctor(id);
						request.setAttribute("doctors", doctors);
						
						request.getSession().setAttribute("orders", order);
						request.setAttribute("order", order);
						request.setAttribute("doctor", doctor);
						target = "doctorOrderAddress.jsp";

					}
					else
					{
						
						Doctor doctor = lService.getDoctorById(id);
						

						Order order = new Order();

						order.setDoctorId(doctor.getId());
						order.setDoctorName(doctor.getFirstName()+" "+doctor.getLastName());

						order.setSupplierPharmacyId(pharmacy.getId());
						order.setSupplierPharmacyName(pharmacy.getPharmacyName());

						order.setOrderType(oType);
						order.setCashType(pType);
						if(order.getOrderType()==1)
						{
							Timestamp t= new Timestamp(new Date().getTime());
							Calendar cal = Calendar.getInstance();
							cal.setTime(t);
							cal.add(Calendar.DAY_OF_WEEK, 7);
							t.setTime(cal.getTime().getTime()); // or
							t = new Timestamp(cal.getTime().getTime());
							order.setDeliveredOn(t);
						}
						else
						{
							Timestamp t= new Timestamp(new Date().getTime());
							Calendar cal = Calendar.getInstance();
							cal.setTime(t);
							cal.add(Calendar.DAY_OF_WEEK, 3);
							t.setTime(cal.getTime().getTime()); // or
							t = new Timestamp(cal.getTime().getTime());
							order.setDeliveredOn(t);
						}
						order.setMedicines(medicines);
						order.setTotalCost(totalCost);
						 order.setImage(forumPath);
						order.setAddress1(doctor.getAddress1());
						order.setAddress2(doctor.getAddress2());
						order.setLandMark(doctor.getLandMark());
						order.setCity(doctor.getCity());
						order.setState(doctor.getState());
						order.setCountry(doctor.getCountry());
						order.setPinCode(doctor.getPinCode());
						order.setPhoneNumber(doctor.getMobile());
						order.setEmail(doctor.getEmailId());

						List<Patient> patients = lService.getAllPatients(id);
						List<Pharmacy> pharmacies = lService.getAllPharmacies(id);
						request.setAttribute("patients", patients);
						request.setAttribute("pharmacies", pharmacies);
						request.getSession().setAttribute("orders", order);
						List<Doctor> doctors = lService.getAllDoctorsForDoctor(id);
						request.setAttribute("doctors", doctors);
						request.setAttribute("pharmacy", pharmacy);
						
						request.setAttribute("order", order);
						request.setAttribute("doctor", doctor);
						target = "doctorOrderConfirm.jsp";
					}
				}


				if(login.getType()==2)
				{

					
					int oType = Integer.parseInt(request.getParameter("oType"));

					int pType = Integer.parseInt(request.getParameter("pType"));
					
					String forumPath ="";
					Part filePart = request.getPart("file");

					if(filePart.getSize()!=0)
					{
						String SAVE_DIR = "Images"+ File.separator +"Order"+ File.separator +"patientorder"+login.getUserName();
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
								forumPath=SAVE_DIR + File.separator +new Date().getTime();
							}

							part.write(savePath + File.separator +new Date().getTime());

						}
					}
					int address = Integer.parseInt(request.getParameter("address"));
					if(address == 2)
					{
						
						Patient doctor = lService.getPatientById(id);
						

						Order order = new Order();

						order.setPatientId(doctor.getId());
						order.setPatientName(doctor.getFirstName()+" "+doctor.getLastName());
						 order.setImage(forumPath);
						order.setSupplierPharmacyId(pharmacy.getId());
						order.setSupplierPharmacyName(pharmacy.getPharmacyName());

						order.setOrderType(oType);
						order.setCashType(pType);

						order.setMedicines(medicines);
						order.setTotalCost(totalCost);

						List<Doctor> doctors = lService.getAllDoctors(id);
						List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(id);
						request.setAttribute("doctors", doctors);
						request.setAttribute("pharmacies", pharmacies);
						request.getSession().setAttribute("orders", order);

						request.setAttribute("order", order);
						request.setAttribute("patient", doctor);
						target = "patientOrderAddress.jsp";
					}
					else
					{

						
						Patient doctor = lService.getPatientById(id);
						

						Order order = new Order();

						order.setPatientId(doctor.getId());
						order.setPatientName(doctor.getFirstName()+" "+doctor.getLastName());

						order.setSupplierPharmacyId(pharmacy.getId());
						order.setSupplierPharmacyName(pharmacy.getPharmacyName());

						order.setOrderType(oType);
						order.setCashType(pType);
						if(order.getOrderType()==1)
						{
							Timestamp t= new Timestamp(new Date().getTime());
							Calendar cal = Calendar.getInstance();
							cal.setTime(t);
							cal.add(Calendar.DAY_OF_WEEK, 7);
							t.setTime(cal.getTime().getTime()); // or
							t = new Timestamp(cal.getTime().getTime());
							order.setDeliveredOn(t);
						}
						else
						{
							Timestamp t= new Timestamp(new Date().getTime());
							Calendar cal = Calendar.getInstance();
							cal.setTime(t);
							cal.add(Calendar.DAY_OF_WEEK, 3);
							t.setTime(cal.getTime().getTime()); // or
							t = new Timestamp(cal.getTime().getTime());
							order.setDeliveredOn(t);
						}
						order.setMedicines(medicines);
						order.setTotalCost(totalCost);
						 order.setImage(forumPath);
						order.setAddress1(doctor.getAddress1());
						order.setAddress2(doctor.getAddress2());
						order.setLandMark(doctor.getLandMark());
						order.setCity(doctor.getCity());
						order.setState(doctor.getState());
						order.setCountry(doctor.getCountry());
						order.setPinCode(doctor.getPinCode());
						order.setPhoneNumber(doctor.getMobile());
						order.setEmail(doctor.getEmailId());

						
						request.getSession().setAttribute("orders", order);
						List<Doctor> doctors = lService.getAllDoctors(id);
						List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(id);
						request.setAttribute("doctors", doctors);
						request.setAttribute("pharmacies", pharmacies);

						request.setAttribute("pharmacy", pharmacy);
						request.setAttribute("order", order);
						request.setAttribute("patient", doctor);
						target = "patientOrderConfirm.jsp";
					}


				}

				if(login.getType()==3)
				{

					
					int oType = Integer.parseInt(request.getParameter("oType"));

					int pType = Integer.parseInt(request.getParameter("pType"));
					
					String forumPath ="";
					Part filePart = request.getPart("file");

					if(filePart.getSize()!=0)
					{
						String SAVE_DIR = "Images"+ File.separator +"Order"+ File.separator +"pharmacyorder"+login.getUserName();
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
								forumPath=SAVE_DIR + File.separator +new Date().getTime();
							}

							part.write(savePath + File.separator +new Date().getTime());

						}
					}
					int address = Integer.parseInt(request.getParameter("address"));
					if(address == 2)
					{
						
						Pharmacy doctor = lService.getPharmacyById(id);
						

						Order order = new Order();
                        order.setImage(forumPath);
						order.setPharmacyId(doctor.getId());
						order.setPharmacyName(doctor.getPharmacyName());

						order.setSupplierPharmacyId(pharmacy.getId());
						order.setSupplierPharmacyName(pharmacy.getPharmacyName());

						order.setOrderType(oType);
						order.setCashType(pType);

						order.setMedicines(medicines);
						order.setTotalCost(totalCost);

						List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
						request.setAttribute("pharmacies", pharmacies);
						request.setAttribute("pharmacy", doctor);
						request.getSession().setAttribute("orders", order);

						
						request.setAttribute("order", order);
						request.setAttribute("patient", doctor);
						target = "pharmacyOrderAddress.jsp";
					}
					else
					{

						
						Pharmacy doctor = lService.getPharmacyById(id);
						

						Order order = new Order();

						order.setPharmacyId(doctor.getId());
						order.setPharmacyName(doctor.getPharmacyName());

						 order.setImage(forumPath);
						order.setSupplierPharmacyId(pharmacy.getId());
						order.setSupplierPharmacyName(pharmacy.getPharmacyName());

						order.setOrderType(oType);
						order.setCashType(pType);

						if(order.getOrderType()==1)
						{
							Timestamp t= new Timestamp(new Date().getTime());
							Calendar cal = Calendar.getInstance();
							cal.setTime(t);
							cal.add(Calendar.DAY_OF_WEEK, 7);
							t.setTime(cal.getTime().getTime()); // or
							t = new Timestamp(cal.getTime().getTime());
							order.setDeliveredOn(t);
						}
						else
						{
							Timestamp t= new Timestamp(new Date().getTime());
							Calendar cal = Calendar.getInstance();
							cal.setTime(t);
							cal.add(Calendar.DAY_OF_WEEK, 3);
							t.setTime(cal.getTime().getTime()); // or
							t = new Timestamp(cal.getTime().getTime());
							order.setDeliveredOn(t);
						}
						order.setMedicines(medicines);
						order.setTotalCost(totalCost);

						order.setAddress1(doctor.getAddress1());
						order.setAddress2(doctor.getAddress2());
						order.setLandMark(doctor.getLandMark());
						order.setCity(doctor.getCity());
						order.setState(doctor.getState());
						order.setCountry(doctor.getCountry());
						order.setPinCode(doctor.getPinCode());
						order.setPhoneNumber(doctor.getMobile());
						order.setEmail(doctor.getEmailId());

						request.getSession().setAttribute("orders", order);

						List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
						request.setAttribute("pharmacies", pharmacies);
						request.setAttribute("pharmacy", doctor);

						
						request.setAttribute("spharmacy", pharmacy);
						request.setAttribute("order", order);
						request.setAttribute("patient", doctor);
						target = "pharmacyOrderConfirm.jsp";
					}


				}

		}

		if(uri.endsWith("orderAddress.order"))
		{
			
			Login login=(Login) request.getSession().getAttribute("login");
			lService.setLastActive(login);
			int id=login.getId();


			if(login.getType()==1)
			{

				
					Order order = (Order) request.getSession().getAttribute("orders");
					request.getSession().removeAttribute("orders");

					order.setAddress1(request.getParameter("address1"));
					order.setAddress2(request.getParameter("address2"));
					order.setLandMark(request.getParameter("landmark"));
					order.setCity(request.getParameter("city"));
					order.setState(request.getParameter("state"));
					order.setCountry(request.getParameter("country"));
					if(order.getOrderType()==1)
					{
						Timestamp t= new Timestamp(new Date().getTime());
						Calendar cal = Calendar.getInstance();
						cal.setTime(t);
						cal.add(Calendar.DAY_OF_WEEK, 7);
						t.setTime(cal.getTime().getTime()); // or
						t = new Timestamp(cal.getTime().getTime());
						order.setDeliveredOn(t);
					}
					else
					{
						Timestamp t= new Timestamp(new Date().getTime());
						Calendar cal = Calendar.getInstance();
						cal.setTime(t);
						cal.add(Calendar.DAY_OF_WEEK, 3);
						t.setTime(cal.getTime().getTime()); // or
						t = new Timestamp(cal.getTime().getTime());
						order.setDeliveredOn(t);
					}
					order.setPinCode(Long.parseLong(request.getParameter("pincode")));
					order.setPhoneNumber(Long.parseLong(request.getParameter("mobile")));
					order.setEmail(request.getParameter("email"));
					
					Doctor doctor = lService.getDoctorById(id);

					List<Patient> patients = lService.getAllPatients(id);
					List<Pharmacy> pharmacies = lService.getAllPharmacies(id);
					request.setAttribute("patients", patients);
					request.setAttribute("pharmacies", pharmacies);

					List<Doctor> doctors = lService.getAllDoctorsForDoctor(id);
					request.setAttribute("doctors", doctors);
					request.getSession().setAttribute("orders", order);
					
					request.setAttribute("pharmacy", lService.getPharmacyById(order.getSupplierPharmacyId()));
					
					request.setAttribute("order", order);
					request.setAttribute("doctor", doctor);
					target="doctorOrderConfirm.jsp";
				

			}


			if(login.getType()==2)
			{
				
					Order order = (Order) request.getSession().getAttribute("orders");
					request.getSession().removeAttribute("orders");

					order.setAddress1(request.getParameter("address1"));
					order.setAddress2(request.getParameter("address2"));
					order.setLandMark(request.getParameter("landmark"));
					order.setCity(request.getParameter("city"));
					order.setState(request.getParameter("state"));
					order.setCountry(request.getParameter("country"));
					order.setPinCode(Long.parseLong(request.getParameter("pincode")));
					order.setPhoneNumber(Long.parseLong(request.getParameter("mobile")));
					order.setEmail(request.getParameter("email"));

					if(order.getOrderType()==1)
					{
						Timestamp t= new Timestamp(new Date().getTime());
						Calendar cal = Calendar.getInstance();
						cal.setTime(t);
						cal.add(Calendar.DAY_OF_WEEK, 7);
						t.setTime(cal.getTime().getTime()); // or
						t = new Timestamp(cal.getTime().getTime());
						order.setDeliveredOn(t);
					}
					else
					{
						Timestamp t= new Timestamp(new Date().getTime());
						Calendar cal = Calendar.getInstance();
						cal.setTime(t);
						cal.add(Calendar.DAY_OF_WEEK, 3);
						t.setTime(cal.getTime().getTime()); // or
						t = new Timestamp(cal.getTime().getTime());
						order.setDeliveredOn(t);
					}
					
					Patient doctor = lService.getPatientById(id);

					List<Doctor> doctors = lService.getAllDoctors(id);
					List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(id);
					request.setAttribute("doctors", doctors);
					request.setAttribute("pharmacies", pharmacies);

					request.getSession().setAttribute("orders", order);
					request.setAttribute("order", order);
					request.setAttribute("patient", doctor);
					
					request.setAttribute("pharmacy", lService.getPharmacyById(order.getSupplierPharmacyId()));
					
					target="patientOrderConfirm.jsp";
				
			}

			if(login.getType()==3)
			{
				
					Order order = (Order) request.getSession().getAttribute("orders");
					request.getSession().removeAttribute("orders");

					order.setAddress1(request.getParameter("address1"));
					order.setAddress2(request.getParameter("address2"));
					order.setLandMark(request.getParameter("landmark"));
					order.setCity(request.getParameter("city"));
					order.setState(request.getParameter("state"));
					order.setCountry(request.getParameter("country"));
					order.setPinCode(Long.parseLong(request.getParameter("pincode")));
					order.setPhoneNumber(Long.parseLong(request.getParameter("mobile")));
					order.setEmail(request.getParameter("email"));

					if(order.getOrderType()==1)
					{
						Timestamp t= new Timestamp(new Date().getTime());
						Calendar cal = Calendar.getInstance();
						cal.setTime(t);
						cal.add(Calendar.DAY_OF_WEEK, 7);
						t.setTime(cal.getTime().getTime()); // or
						t = new Timestamp(cal.getTime().getTime());
						order.setDeliveredOn(t);
					}
					else
					{
						Timestamp t= new Timestamp(new Date().getTime());
						Calendar cal = Calendar.getInstance();
						cal.setTime(t);
						cal.add(Calendar.DAY_OF_WEEK, 3);
						t.setTime(cal.getTime().getTime()); // or
						t = new Timestamp(cal.getTime().getTime());
						order.setDeliveredOn(t);
					}
					
					Pharmacy pharmacy = lService.getPharmacyById(id);
					List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
					request.setAttribute("pharmacies", pharmacies);
					request.setAttribute("pharmacy", pharmacy);

					request.setAttribute("spharmacy", lService.getPharmacyById(order.getSupplierPharmacyId()));
					
					request.getSession().setAttribute("orders", order);
					request.setAttribute("order", order);
					target="pharmacyOrderConfirm.jsp";
				
			}
			
		}
		
		
		if(uri.endsWith("fail.order"))
		{
			
			
			Login login=(Login) request.getSession().getAttribute("login");
			lService.setLastActive(login);
			int id=login.getId();

			
			if(login.getType()==1)
			{

				
					Order order = (Order) request.getSession().getAttribute("orders");
					
						
						request.setAttribute("error", "Payment Failed, Please Try Again");
						Doctor doctor = lService.getDoctorById(id);

						List<Patient> patients = lService.getAllPatients(id);
						List<Pharmacy> pharmacies = lService.getAllPharmacies(id);
						request.setAttribute("patients", patients);
						request.setAttribute("pharmacies", pharmacies);

						List<Doctor> doctors = lService.getAllDoctorsForDoctor(id);
						request.setAttribute("doctors", doctors);
						request.getSession().setAttribute("orders", order);
						
						request.setAttribute("pharmacy", lService.getPharmacyById(order.getSupplierPharmacyId()));
						
						request.setAttribute("order", order);
						request.setAttribute("doctor", doctor);
						target="doctorOrderConfirm.jsp";
					
				
			}

			if(login.getType()==2)
			{
				
					Order order = (Order) request.getSession().getAttribute("orders");
					Patient doctor = lService.getPatientById(id);

					List<Doctor> doctors = lService.getAllDoctors(id);
					List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(id);
					request.setAttribute("doctors", doctors);
					request.setAttribute("pharmacies", pharmacies);

					request.getSession().setAttribute("orders", order);
					request.setAttribute("order", order);
					request.setAttribute("patient", doctor);
					
					request.setAttribute("pharmacy", lService.getPharmacyById(order.getSupplierPharmacyId()));
					
					target="patientOrderConfirm.jsp";
						request.setAttribute("error", "Payment Failed, Please Try Again");
					
					
				
			}

			if(login.getType()==3)
			{
				
					Order order = (Order) request.getSession().getAttribute("orders");
					Pharmacy pharmacy = lService.getPharmacyById(id);
					List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
					request.setAttribute("pharmacies", pharmacies);
					request.setAttribute("pharmacy", pharmacy);

					request.setAttribute("spharmacy", lService.getPharmacyById(order.getSupplierPharmacyId()));
					
					request.getSession().setAttribute("orders", order);
					request.setAttribute("order", order);
					target="pharmacyOrderConfirm.jsp";
						request.setAttribute("error", "Payment Failed, Please Try Again");
					
				
			}

		}
		
		if(uri.endsWith("order.order"))
		{
			
			
			Login login=(Login) request.getSession().getAttribute("login");
			lService.setLastActive(login);
			int id=login.getId();

			
			if(login.getType()==1)
			{

				
					Order order = (Order) request.getSession().getAttribute("orders");
					String txnId = request.getParameter("tId");
					order.setTransactionId(txnId);
						int orderId = service.saveOrder(order);

						for(int i=0;i<order.getMedicines().size();i++)
						{
							service.saveMedicine(orderId,order.getMedicines().get(i));

						}
						
						String doctorcontentlog = "Created ORDER with ID="+orderId+"For Pharmacy ID="+order.getSupplierPharmacyId();
						Log logdoctor = new Log(id,1,doctorcontentlog );
						lService.saveLog(logdoctor);
						
						
						String pharmacycontentlog = "Received ORDER with ID="+orderId+"BY Doctor ID="+id;
						Log logpharmacy = new Log(order.getSupplierPharmacyId(),3,pharmacycontentlog );
						lService.saveLog(logpharmacy);
						
						request.getSession().removeAttribute("orders");
						Doctor doctor = lService.getDoctorById(id);
						
						String content = "<a href='viewOrder.pharmacy?id="+orderId+" '>"+doctor.getFirstName()+"&nbsp; "+doctor.getLastName()+" Has Made An Order</a>";
						Notification notification = new Notification(0, id, content, order.getSupplierPharmacyId(), 0,3);
						notification.setImage(doctor.getImage());
						lService.saveNotification(notification);
						
						String message = "Hi " +doctor.getFirstName()+" "+doctor.getLastName()+",We received your order, please note the order ID:"+orderId; 
					    lService.sendMessage(doctor.getMobile() ,message);
					    
					    Pharmacy pharmacy = lService.getPharmacyById(order.getSupplierPharmacyId());
					    
					    String message1 = "Hi " +pharmacy.getPharmacyName()+",you got an order with order ID:"+orderId; 
					    lService.sendMessage(doctor.getMobile() ,message1);
					    request.setAttribute("insert", 1);
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "Successfully created Order");
						target="doctorhome.jsp";
					
				
			}

			if(login.getType()==2)
			{
				
					Order order = (Order) request.getSession().getAttribute("orders");
					String txnId = request.getParameter("tId");
					order.setTransactionId(txnId);
						int orderId = service.saveOrder(order);
						for(int i=0;i<order.getMedicines().size();i++)
						{
							service.saveMedicine(orderId,order.getMedicines().get(i));
						}	

						String patientcontentlog = "Created ORDER with ID="+orderId+"For Pharmacy ID="+order.getSupplierPharmacyId();
						Log logpatient = new Log(id,2,patientcontentlog );
						lService.saveLog(logpatient);
						
						
						String pharmacycontentlog = "Received ORDER with ID="+orderId+"BY Patient ID="+id;
						Log logpharmacy = new Log(order.getSupplierPharmacyId(),3,pharmacycontentlog );
						lService.saveLog(logpharmacy);
						
						request.getSession().removeAttribute("orders");
						Patient patient  = lService.getPatientById(id);
						List<Doctor> doctors = lService.getAllDoctors(id);
						List<Pharmacy> pharmacies = lService.getAllPharmaciesBypatientId(id);
						
						String content = "<a href='viewOrder.pharmacy?id="+orderId+" '>"+patient.getFirstName()+"&nbsp; "+patient.getLastName()+" Has Made An Order</a>";
						Notification notification = new Notification(0, id, content, order.getSupplierPharmacyId(), 0,3);
						notification.setImage(patient.getImage());
						lService.saveNotification(notification);
						
						String message = "Hi " +patient.getFirstName()+" "+patient.getLastName()+",We received your order, please note the order ID:"+orderId; 
					    lService.sendMessage(patient.getMobile() ,message);
					    
					    Pharmacy pharmacy = lService.getPharmacyById(order.getSupplierPharmacyId());
					    
					    String message1 = "Hi " +pharmacy.getPharmacyName()+",you got an order with order ID:"+orderId; 
					    lService.sendMessage(patient.getMobile() ,message1);
					    request.setAttribute("insert", 1);
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "Successfully created Order");
					
					
				
			}

			if(login.getType()==3)
			{
				
					Order order = (Order) request.getSession().getAttribute("orders");
					String txnId = request.getParameter("tId");
					order.setTransactionId(txnId);
						long orderId = service.saveOrder(order);
						for(int i=0;i<order.getMedicines().size();i++)
						{
							service.saveMedicine(orderId,order.getMedicines().get(i));
						}	

						
						String pharmacycontentlog1 = "Created ORDER with ID="+orderId+"For Pharmacy ID="+order.getSupplierPharmacyId();
						Log logpharmacy1 = new Log(id,3,pharmacycontentlog1 );
						lService.saveLog(logpharmacy1);
						
						
						String pharmacycontentlog2 = "Received ORDER with ID="+orderId+"BY Pharmacy ID="+id;
						Log logpharmacy2 = new Log(order.getSupplierPharmacyId(),3,pharmacycontentlog2 );
						lService.saveLog(logpharmacy2);
						
						request.getSession().removeAttribute("orders");
						Pharmacy pharmacy = lService.getPharmacyById(id);
						
						String content = "<a href='viewOrder.pharmacy?id="+orderId+" '>"+pharmacy.getPharmacyName()+" Has Made An Order</a>";
						Notification notification = new Notification(0, id, content, order.getSupplierPharmacyId(), 0,3);
						notification.setImage(pharmacy.getImage());
						lService.saveNotification(notification);
						
						String message = "Hi " +pharmacy.getPharmacyName()+",We received your order, please note the order ID:"+orderId; 
					    lService.sendMessage(pharmacy.getMobile() ,message);
					    
					    Pharmacy pharmacy1 = lService.getPharmacyById(order.getSupplierPharmacyId());
					    
					    String message1 = "Hi " +pharmacy1.getPharmacyName()+",you got an order with order ID:"+orderId; 
					    lService.sendMessage(pharmacy1.getMobile() ,message1);
					    request.setAttribute("insert", 1);
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "Successfully created Order");
					
				
			}

		}
		
		
		if(uri.endsWith("getOrder.order"))
		{
			
			
			Login login=(Login) request.getSession().getAttribute("login");
			lService.setLastActive(login);
			int id=login.getId();

			if(login.getType()==1)
			{

				PharmacyService phService = new PharmacyService();
				int oId = Integer.parseInt(request.getParameter("id"));

				Order order = phService.getOrderById(oId);
				
				if(order.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This Order doesnot exist");
				}
				else
				{
				
					if(order.getDoctorId()!= id)
					{
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "You Can't View this order");
					}
					else
					{
						Pharmacy pharmacy = lService.getPharmacyById(order.getSupplierPharmacyId());
						order.setSupplierPharmacyName(pharmacy.getPharmacyName());

						order.setMedicines(phService.getOrderedMedicinesByOrderId(oId));
						System.out.println(order);
						Doctor doctor = lService.getDoctorById(id);
						
						request.setAttribute("doctor", doctor);
						request.setAttribute("order", order);
						target="doctorViewOrder.jsp";
					}
					
				
				}
			}

			if(login.getType()==2)
			{
				
				PharmacyService phService = new PharmacyService();
				int oId = Integer.parseInt(request.getParameter("id"));

				Order order = phService.getOrderById(oId);
				
				if(order.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This Order doesnot exist");
				}
				else
				{
					
					if(order.getPatientId()!= id)
					{
						target = lService.setErrorControl(request,target,login1);
						request.setAttribute("error", "You Can't View this order");
					}
					else
					{
					
				Pharmacy pharmacy = lService.getPharmacyById(order.getSupplierPharmacyId());
				order.setSupplierPharmacyName(pharmacy.getPharmacyName());

				order.setMedicines(phService.getOrderedMedicinesByOrderId(oId));
				
				Patient patient = lService.getPatientById(id);
				request.setAttribute("patient", patient);
				request.setAttribute("order", order);
				target="patientViewOrder.jsp";
					}
				}
			}

			if(login.getType()==3)
			{
				
				PharmacyService phService = new PharmacyService();
				int oId = Integer.parseInt(request.getParameter("id"));

				Order order = phService.getOrderById(oId);
				
				if(order.getId() == 0)
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "This Order doesnot exist");
				}
				else
				{
					
					
				if(order.getPharmacyId()!=id && order.getSupplierPharmacyId()!=id )
				{
					target = lService.setErrorControl(request,target,login1);
					request.setAttribute("error", "You Can't View this order");
				}
				else
				{
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
					if(order.getPharmacyId()== id)
					{
						request.setAttribute("type", 1);
					}
					else
					{
						request.setAttribute("type", 0);
					}
					

					Pharmacy pharmacy1 = lService.getPharmacyById(order.getSupplierPharmacyId());
					order.setSupplierPharmacyName(pharmacy1.getPharmacyName());
					
					order.setMedicines(phService.getOrderedMedicinesByOrderId(oId));



					Pharmacy pharmacy = lService.getPharmacyById(id);
					List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
					request.setAttribute("pharmacies", pharmacies);

					request.setAttribute("pharmacy", pharmacy);
					request.setAttribute("order", order);
					target="pharmacyViewOrder.jsp";
				}
				
				}
				
			}

		}
		
		
		} catch (Exception e) {
			e.printStackTrace();
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
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
