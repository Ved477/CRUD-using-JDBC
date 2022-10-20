package com.studentapp.controller;

import java.io.IOException;
import java.sql.ResultSet;

import com.studentapp.model.DAOService;
import com.studentapp.model.DAOServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/update")
public class UpdateRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateRegistration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		
		DAOService dao = new DAOServiceImpl();
		dao.connectDB();
		
		ResultSet result = dao.getRegistrationByEmail(email);
		
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/update_registration.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		String email = request.getParameter("email");
		String mobile = request.getParameter("mobile");
		
		DAOService dao = new DAOServiceImpl();
		dao.connectDB();
		
		dao.updateRegistration(name, city, email, mobile);
		
		ResultSet result = dao.listAllReg();
		
		request.setAttribute("updated", "Record is updated!!");
		request.setAttribute("result", result);
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/search_registration.jsp");
		rd.forward(request, response);
	}

}
