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
import com.epf.rentmanager.service.VehicleService;

@WebServlet("/cars") // permet de faire le mapping entre site et servlet

public class VehicleServlet extends HttpServlet {

	@Autowired
	VehicleService vehiculeService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			request.setAttribute("listCars", this.vehiculeService.findAll());
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/vehicles/list.jsp").forward(request, response); 

		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}
	
	// on définit la méthode doPost qui sera appeler lors d'une suppression
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
				
						
		String idStr = request.getParameter("deleteVehicle"); 	
		System.out.println(idStr); 
		int id = Integer.parseInt(idStr);
					
		// on crée un objet de type client avec les paramètres récupérés
		//	Client client = new Client(firstName, lastName, email, date);
				
		// on envoie une requête avec en paramètre le retour de la fonction create
		try {
		request.setAttribute("deleteClients", this.vehiculeService.delete(id)); 
		} catch (ServiceException e){
			e.printStackTrace(); 
				}
			// on récupère la réponse à notre requête 
			this.doGet(request, response); 
					}
}