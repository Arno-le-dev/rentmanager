package com.epf.rentmanager.utils.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;

@WebServlet("/cars/edit") //permet de faire le mapping entre site et servlet

public class ModifyCarsServlet extends HttpServlet {

	@Autowired
	private VehicleService vehicleService; 

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/edit.jsp").forward(request, response);
	}

	
	// on définit la méthode doPost qui sera appeler lors d'un ajout 
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
			String idStr = request.getQueryString(); 
			String idDef = idStr.substring(3);
			int id = Integer.parseInt(idDef); 
			
			//on récupère les entrées du site web (en se référant aux noms des différents champ du .jsp
			String manufacturer = request.getParameter("manufacturer"); 
			String seatsStr = request.getParameter("seats");
			int seats = Integer.parseInt(seatsStr);
			
			// on crée un objet de type client avec les paramètres récupérés
			Vehicle vehicle = new Vehicle(id, manufacturer, seats);
			
			
			
			System.out.println("mon doPost fonctionne");
		
			System.out.println(vehicle.getIdVehicule()); 
			System.out.println(vehicle.getConstructeur());
			System.out.println(vehicle.getNbPlaces());
			
			// on envoie une requête avec en paramètre le retour de la fonction create
			try {
				System.out.println("TRYYYYYY"); 
				System.out.println(vehicle.getIdVehicule());
				request.setAttribute("modifyCars", this.vehicleService.edit(vehicle)); 
				
				
				 
				System.out.println(vehicle.getConstructeur());
				System.out.println(vehicle.getNbPlaces()); 
				
				
			} catch (ServiceException e){
				e.printStackTrace(); 
			}
			// on récupère la réponse à notre requête 
		this.doGet(request, response); 
			}
				
	
		}
	



