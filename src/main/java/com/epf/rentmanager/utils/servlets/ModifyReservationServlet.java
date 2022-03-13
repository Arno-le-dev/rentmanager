package com.epf.rentmanager.utils.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.service.ReservationService;
import com.epf.rentmanager.service.VehicleService;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;
import com.epf.rentmanager.model.Reservation;

@WebServlet("/rents/edit") //permet de faire le mapping entre site et servlet

public class ModifyReservationServlet extends HttpServlet {

	@Autowired
	private ClientService clientService; 
	
	@Autowired
	private VehicleService vehicleService; 
	
	@Autowired
	private ReservationService reservationService; 

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	private static final long serialVersionUID = 1L;

	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setAttribute("listUsers", this.clientService.findAll());
			request.setAttribute("listCars", this.vehicleService.findAll());

		} catch (ServiceException e) {
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/rents/edit.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idCar = request.getParameter("car");
		String idClient = request.getParameter("client");
		
				
		int addIdCar = Integer.parseInt(idCar);
		int addIdClient = Integer.parseInt(idClient);
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		
		String dateDebut = request.getParameter("debut");
		String dateFin = request.getParameter("fin");
		
		
		LocalDate addDateDebut = LocalDate.parse(dateDebut, formatter);
		LocalDate addDateFin = LocalDate.parse(dateFin, formatter);

		
		Reservation reservation = new Reservation(addIdClient,addIdCar,addDateDebut,addDateFin);

		try {
			request.setAttribute("editResa", this.reservationService.edit(reservation));
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		this.doGet(request, response);

	}
				
	
		}
	


