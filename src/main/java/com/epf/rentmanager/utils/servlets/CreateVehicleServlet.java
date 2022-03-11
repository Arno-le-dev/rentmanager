package com.epf.rentmanager.utils.servlets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Vehicle;
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars/create") //permet de faire le mapping entre site et servlet

public class CreateVehicleServlet extends HttpServlet {

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

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/create.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
			//on récupère les entrées du site web (en se référant aux nom des différents champ du .jsp
			String marque = request.getParameter("manufacturer"); 
			String modele = request.getParameter("modele");
			String nbSeat = request.getParameter("seats");
			
			int nbSeats = Integer.parseInt(nbSeat); 
			
			// on crée un objet de type client avec les paramètres récupérés
			Vehicle vehicle = new Vehicle(marque, modele, nbSeats);
		
			// on envoie une requête avec en paramètre le retour de la fonction create
			try {
				request.setAttribute("addVehicles", this.vehicleService.create(vehicle)); 
			} catch (ServiceException e){
				e.printStackTrace(); 
			}
			// on récupère la réponse à notre requête 
		this.doGet(request, response); 
			}
	
		}
	