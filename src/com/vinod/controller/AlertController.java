package com.vinod.controller;

import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.vinod.model.Appointment;
import com.vinod.model.Doctor;
import com.vinod.model.Login;
import com.vinod.model.Medicine;
import com.vinod.model.Patient;
import com.vinod.model.Pharmacy;
import com.vinod.model.Vaccination;
import com.vinod.service.AlertService;
import com.vinod.service.LoginService;

/**
 * Servlet implementation class AlertController
 */
@WebServlet("*.alert")
public class AlertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlertController() {
        super();
       
    }

    final static Logger logger = Logger.getLogger(AlertController.class);
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
		LoginService lService = new LoginService();
		AlertService service = new AlertService();
		String v = "9738143360";
		Long vin = Long.parseLong(v);
		if(uri.endsWith("vaccination.alert"))
		{


			try {
				
				
				List<Vaccination> vaccinations = service.getVaccinations();
				for(int j=0;j<vaccinations.size();j++)
				{
					 
					String message = "Reminder to take "+vaccinations.get(j).getName()+" vaccination on "+vaccinations.get(j).getTime();
					
				    lService.sendMessage(vaccinations.get(j).getMobile() ,message);
				}
				
				//lService.sendMessage(vin, "Successfully Vaccination SMS Sent");
			} catch (Exception e1) {
				logger.error(Level.SEVERE,e1);
				lService.sendMessage(vin, "Error while sending Vaccination SMS");
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");

			}
		}
		
		
		if(uri.endsWith("medicines.alert"))
		{


			try {
				
				
				List<Medicine> medicines = service.getMedicines();
				
				for(int j=0;j<medicines.size();j++)
				{
				
					Medicine medicine = medicines.get(j);
					int left = medicine.getLeft();
					Time t = null ;
					if(left > 0){
						if(medicine.getMorning() != 0){
							
							String msg="Time to take your "+medicine.getName()+" medicine";
							
							service.sendSMS(msg , medicine.getMobile() ,medicine.getMt());
							left--;
							t=medicine.getMt();
						}
						if(medicine.getAfternoon() != 0 && left!=0){
							String msg="Time to take your "+medicine.getName()+" medicine";
							
							service.sendSMS(msg , medicine.getMobile() ,medicine.getAt());
							left--;
							t=medicine.getAt();
						}
						if(medicine.getNight() != 0 && left!=0){
							String msg="Time to take your "+medicine.getName()+" medicine";
							
							service.sendSMS(msg , medicine.getMobile() ,medicine.getNt());
							left--;
							t=medicine.getNt();
						}
						
						if(left == 0){
							String msg="Time to buy your medicines. Please login to http://www.healingclick.com for a quick purchase";
							
							service.sendSMS(msg , medicine.getMobile() ,t);
						}
						
						service.updateLeftMedicines(medicine.getPrescriptionId() , medicine.getName() , left);
					}
				    
				}
				
				//lService.sendMessage(vin, "Successfully Medicines SMS Sent");
			} catch (Exception e1) {
				logger.error(Level.SEVERE,e1);
				lService.sendMessage(vin, "Error while sending Medicines SMS");
				request.setAttribute("error", "Sorry, Something Went Wrong, Try Again.");

			}
		}
		
	}
	
	public int daysBetween(Date d1, Date d2){
	    return (int)( (d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}

}
