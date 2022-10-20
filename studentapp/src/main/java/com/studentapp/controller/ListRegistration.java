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
import jakarta.servlet.http.HttpSession;

@WebServlet("/list")
public class ListRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListRegistration() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if(session.getAttribute("email")!=null) {
				DAOService dao = new DAOServiceImpl();
				dao.connectDB();
				
				ResultSet result = dao.listAllReg();
				
				request.setAttribute("result", result);
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/search_registration.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("error", "Logged Out!!");
				RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("error", "Session Timed Out!!");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
