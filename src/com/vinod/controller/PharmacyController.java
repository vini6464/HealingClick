package com.vinod.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Order;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.service.DoctorService;
import com.vinod.service.LoginService;
import com.vinod.service.OrderService;
import com.vinod.service.PharmacyService;

/**
 * Servlet implementation class PharmacyController
 */
@WebServlet("*.pharmacy")
@MultipartConfig
public class PharmacyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	final static Logger logger = Logger.getLogger(PharmacyController.class);
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PharmacyController() {
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
		logger.info(uri);
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

		PharmacyService service = new PharmacyService();
		DoctorService dService = new DoctorService();
		LoginService lService = new LoginService();

		try {

			if(uri.endsWith("appointment.pharmacy"))
			{
				Pharmacy pharmacy;

				Login login=(Login) request.getSession().getAttribute("login");
				int id = login.getId();
				pharmacy = lService.getPharmacyById(id);
				request.setAttribute("pharmacy", pharmacy);
				target = "pharmacyPlanner.jsp";


			}
			
			if(uri.endsWith("medicines.pharmacy"))
			{
				Pharmacy pharmacy;

				Login login=(Login) request.getSession().getAttribute("login");
				int id = login.getId();
				pharmacy = lService.getPharmacyById(id);
				
				List<Medicine> pharmacyMedicines = service.getPharmacyMedicines(login);
				request.setAttribute("pharmacyMedicines", pharmacyMedicines);
				request.setAttribute("pharmacy", pharmacy);
				target = "pharmacyMedicines.jsp";


			}

			if(uri.endsWith("viewOrder.pharmacy"))
			{


				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				int oId = Integer.parseInt(request.getParameter("id"));

				Order order = service.getOrderById(oId);
				
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

				order.setMedicines(service.getOrderedMedicinesByOrderId(oId));
				Pharmacy pharmacy1 = lService.getPharmacyById(order.getSupplierPharmacyId());
				order.setSupplierPharmacyName(pharmacy1.getPharmacyName());


				Pharmacy pharmacy = lService.getPharmacyById(id);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
				request.setAttribute("pharmacies", pharmacies);

				request.setAttribute("pharmacy", pharmacy);
				request.setAttribute("order", order);
				target="pharmacyViewOrder.jsp";
				}
				}


			}

			if(uri.endsWith("order.pharmacy"))
			{
				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				int pId = Integer.parseInt(request.getParameter("id"));
				Pharmacy spharmacy = lService.getPharmacyById(pId);
				Pharmacy pharmacy = lService.getPharmacyById(id);
				
				List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
				request.setAttribute("pharmacies", pharmacies);
				request.setAttribute("spharmacy", spharmacy);
				request.setAttribute("pharmacy", pharmacy);
				

				target="pharmacyOrder.jsp";

			}

			if(uri.endsWith("getAllUrgentOrder.pharmacy"))
			{


				OrderService oService = new OrderService();

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				List<Order> orders = oService.getAllUrgentOrderForPharmacy(login);

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


					orders.get(i).setMedicines(oService.getAllMedicinesByOrderId(orders.get(i).getId()));
				}
				Pharmacy pharmacy = lService.getPharmacyById(id);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
				request.setAttribute("pharmacies", pharmacies);

				request.setAttribute("pharmacy", pharmacy);

				request.setAttribute("type", 1);
				request.setAttribute("orders", orders);


				target="pharmacyViewAllOrder.jsp";


			}

			if(uri.endsWith("getAllNormalOrder.pharmacy"))
			{


				OrderService oService = new OrderService();

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				List<Order> orders = oService.getAllNormalOrderForPharmacy(login);

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


					orders.get(i).setMedicines(oService.getAllMedicinesByOrderId(orders.get(i).getId()));
				}
				Pharmacy pharmacy = lService.getPharmacyById(id);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
				request.setAttribute("pharmacies", pharmacies);

				request.setAttribute("pharmacy", pharmacy);

				request.setAttribute("type", 2);
				request.setAttribute("orders", orders);

				target="pharmacyViewAllOrder.jsp";


			}

			if(uri.endsWith("getAllDeliveredOrder.pharmacy"))
			{


				OrderService oService = new OrderService();

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				List<Order> orders = oService.getAllDeliveredOrderForPharmacy(login);

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


					orders.get(i).setMedicines(oService.getAllMedicinesByOrderId(orders.get(i).getId()));
				}

				Pharmacy pharmacy = lService.getPharmacyById(id);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
				request.setAttribute("pharmacies", pharmacies);

				request.setAttribute("pharmacy", pharmacy);

				request.setAttribute("type", 3);
				request.setAttribute("orders", orders);

				target="pharmacyViewAllOrder.jsp";


			}


			if(uri.endsWith("getAllMyOrder.pharmacy"))
			{


				OrderService oService = new OrderService();

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				List<Order> orders = oService.getAllMyOrderForPharmacy(login);

				for(int i=0;i<orders.size();i++)
				{
					Pharmacy pharmacy = lService.getPharmacyById(orders.get(i).getSupplierPharmacyId());
					orders.get(i).setSupplierPharmacyName(pharmacy.getPharmacyName());

					orders.get(i).setMedicines(oService.getAllMedicinesByOrderId(orders.get(i).getId()));
				}

				Pharmacy pharmacy = lService.getPharmacyById(id);
				List<Pharmacy> pharmacies = lService.getAllPharmaciesForPharmacy(id);
				request.setAttribute("pharmacies", pharmacies);

				request.setAttribute("pharmacy", pharmacy);

				request.setAttribute("type", 4);
				request.setAttribute("orders", orders);
				target="pharmacyViewAllOrder.jsp";


			}
			
			
			if(uri.endsWith("pharmacyCostEdit.pharmacy"))
			{


				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				Pharmacy pharmacy = lService.getPharmacyById(id);
				

				request.setAttribute("pharmacy", pharmacy);

				target="pharmacyCost.jsp";


			}
			
			if(uri.endsWith("updateDeliveryCost.pharmacy"))
			{


				

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				int id = login.getId();
				
				double deliveryCharge = Double.parseDouble(request.getParameter("deliveryCharge"));
				
				
				double discount = Double.parseDouble(request.getParameter("disount"));
				
				service.updateCost(deliveryCharge , discount ,login);
				
				
				
				Pharmacy pharmacy = lService.getPharmacyById(id);
				

				request.setAttribute("pharmacy", pharmacy);

				
				target="pharmacyProfile.jsp";


			}
			
			
			
			
			if(uri.endsWith("saveMedicines.pharmacy"))
			{

				Login login=(Login) request.getSession().getAttribute("login");
				lService.setLastActive(login);
				Part file = request.getPart("file");
				
				String filePath="";
				if(file.getSize()!=0)
				{

					String SAVE_DIR = "Excel"+ File.separator +"medcines"+new Date().getTime();
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
							
							filePath=savePath + File.separator + fileName;
						}

						part.write(savePath + File.separator + fileName);
				}
				}
				
				

				   List<Medicine> medicines = new ArrayList<Medicine>();
				   FileInputStream file1 = new FileInputStream(new File(filePath));
				 
		            //Create Workbook instance holding reference to .xlsx file
		            XSSFWorkbook workbook = new XSSFWorkbook(file1);
		 
		            //Get first/desired sheet from the workbook
		            XSSFSheet sheet = workbook.getSheetAt(0);
		 
		            //Iterate through each rows one by one
		            Iterator<Row> rowIterator = sheet.iterator();
		            while (rowIterator.hasNext())
		            {
		                Row row = rowIterator.next();
		                //For each row, iterate through all the columns
		                Iterator<Cell> cellIterator = row.cellIterator();
		                Medicine medicine =  new Medicine();
		                while (cellIterator.hasNext())
		                {
		                    Cell cell = cellIterator.next();
		                    
		                    //Check the cell type and format accordingly
		                    switch (cell.getCellType())
		                    {
		                        case Cell.CELL_TYPE_NUMERIC:
		                            medicine.setCost(cell.getNumericCellValue());
		                            break;
		                        case Cell.CELL_TYPE_STRING:
		                        	medicine.setName(cell.getStringCellValue());
		                            break;
		                    }
		 
		                }
		                medicines.add(medicine);
	                    
		            }
		            file1.close();
		            
		            service.removeOldMedicines(login);
		            
		            service.updateNewMedicines(login , medicines);
		            Pharmacy pharmacy = lService.getPharmacyById(login.getId());
					
		            request.setAttribute("insert", 1);
					request.setAttribute("pharmacy", pharmacy);
					request.setAttribute("pharmacyMedicines", medicines);
					target = "pharmacyMedicines.jsp";
		           
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			
			try {
				logger.error(e.getStackTrace());
				target = lService.setErrorControl(request,target,login1);
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");
			} catch (Exception e1) {
				logger.error(e1.getStackTrace());
				request.setAttribute("error", "Please Login To Continue.");
				target="home.jsp";
			}

		}
		request.getRequestDispatcher(target).forward(request, response);
	}

	private String extractFileName(Part part) {
//		final String partHeader = part.getHeader("content-disposition");


			for (String content : part.getHeader("content-disposition").split(";")) {
				if (content.trim().startsWith("filename")) {

					if(content.contains(":"))
					{

						content = content.substring(
								content.indexOf('=') + 1).trim().replace("\"", "");
						File file1 = new File(content);
						String name = file1.getName();
						return name;
					}
					else
						return content.substring(
								content.indexOf('=') + 1).trim().replace("\"", "");
				}
			}
			return null;
	}

}
