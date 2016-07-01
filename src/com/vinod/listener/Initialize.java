package com.vinod.listener;


import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.PropertyConfigurator;

import com.google.gson.Gson;
import com.vinod.exception.DaoException;
import com.vinod.model.Medicine;
import com.vinod.model.Symptom;
import com.vinod.service.DoctorService;

import sun.security.util.Cache;


/**
 * Application Lifecycle Listener implementation class Initailize
 *
 */
@WebListener
public class Initialize implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public Initialize() {
       
    }

    private static final String ATTRIBUTE_NAME = "com.vinod.listener.Initialize";
    
	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @SuppressWarnings("null")
	public void contextInitialized(ServletContextEvent event)  { 
    	System.out.println("Initalizing medicine");
    	DoctorService dService = new DoctorService();
    	List<Medicine> medicines;
		try {
			medicines = dService.getAllMedicines();
			int len = medicines.size();
			System.out.println("Medicines:"+len);
			String[] meds  = new String[len];
	    	int i = 0;
	    	for (Medicine m : medicines) {
	    	   meds[i] = m.getName();
	    	   i++;
	    	}
	    	Gson gson = new Gson();
	    	String med = gson.toJson(meds);
	    	event.getServletContext().setAttribute("medicines", med);
		} catch (Exception e1) {
			
			System.out.println("Error while setting Medicines:"+e1.getMessage());
		}
    	
    	
    	
    	
    	try {
    		System.out.println("Initalizing Symptoms");
			List<Symptom> symptoms = dService.getAllSymptoms();
			
			int len = symptoms.size();
			System.out.println("Symptoms:"+len);
			String[] syms = new String[len];
	    	int i = 0;
	    	for (Symptom m : symptoms) {
	    		syms[i] = m.getName();
	    	   i++;
	    	}
	    	Gson gson = new Gson();
	    	String sym = gson.toJson(syms);
	    	event.getServletContext().setAttribute("symptoms", sym);
		} catch (Exception e) {
			System.out.println("Error while setting Symptoms:"+e.getMessage());
		}
    	
    	
    	ServletContext context = event.getServletContext();
        String log4jConfigFile = context.getInitParameter("log4j-config-location");
        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
         
        PropertyConfigurator.configure(fullPath);
    	
    	
    }
    	

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
        
    }
    
    public static Initialize getInstance(ServletContext context) {
        return (Initialize) context.getAttribute(ATTRIBUTE_NAME);
    }


	
}
