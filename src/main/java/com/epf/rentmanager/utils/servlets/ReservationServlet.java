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
import com.epf.rentmanager.service.ReservationService;

@WebServlet("/rents") // permet de faire le mapping entre site et servlet 

public class ReservationServlet extends HttpServlet {
	
	@Autowired
	ReservationService reservationService; 
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	private static final long serialVersionUID = 1L; 
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
	
		
		try {
			request.setAttribute("reservations", this.reservationService.affichageWeb());
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/WEB-INF/views/rents/list.jsp").forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
			
			String idStr = request.getParameter("idResa"); 				
			int id = Integer.parseInt(idStr);
			
			
			try {
				request.setAttribute("deleteClients", this.reservationService.delete(id)); 
			} catch (ServiceException e){
				e.printStackTrace(); 
			}
			// on récupère la réponse à notre requête 
		this.doGet(request, response); 
			}
	
	
	
}

