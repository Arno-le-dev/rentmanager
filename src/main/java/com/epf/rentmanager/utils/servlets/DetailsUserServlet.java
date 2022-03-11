package com.epf.rentmanager.utils.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;


@WebServlet("/users/details") // permet de faire le mapping entre site et servlet 



public class DetailsUserServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		super.init();
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	private static final long serialVersionUID = 1L; 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse
			response) throws ServletException, IOException {
				
		 this.getServletContext().getRequestDispatcher("/WEB-INF/views/users/details.jsp").forward(request, response); // on pousse la page que l'on veut afficher 
			// request.getRequestDispatcher("./WEB-INF/views/users/list.jsp").forward(request,  response);
			
					
			}
			
}

