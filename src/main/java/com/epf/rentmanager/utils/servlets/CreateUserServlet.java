package com.epf.rentmanager.utils.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.epf.rentmanager.service.ClientService;
import com.epf.rentmanager.exception.ServiceException;
import com.epf.rentmanager.model.Client;

@WebServlet("/users/create") //permet de faire le mapping entre site et servlet

public class CreateUserServlet extends HttpServlet {

	@Autowired
	private ClientService clientService;

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/create.jsp").forward(request, response);
	}

	
	// on définit la méthode doPost qui sera appeler lors d'un ajout 
		protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
			//on récupère les entrées du site web (en se référant aux nom des différents champ du .jsp
			String firstName = request.getParameter("first_name"); 
			String lastName = request.getParameter("last_name");
			String email = request.getParameter("email");
			String birthdate = request.getParameter("date"); 
			LocalDate date = LocalDate.parse(birthdate); 
			
			// on crée un objet de type client avec les paramètres récupérés
			Client client = new Client(firstName, lastName, email, date);
			
			System.out.println(client); 
			
			// on envoie une requête avec en paramètre le retour de la fonction create
			try {
				request.setAttribute("addClients", this.clientService.create(client)); 
			} catch (ServiceException e){
				e.printStackTrace(); 
			}
			// on récupère la réponse à notre requête 
		this.doGet(request, response); 
			}
				
	
		}
	

