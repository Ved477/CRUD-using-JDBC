package com.studentapp.controller;

import java.io.IOException;

import com.studentapp.model.DAOService;
import com.studentapp.model.DAOServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/verifyLogin")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		DAOService dao = new DAOServiceImpl();
		dao.connectDB();
		
		boolean status = dao.verifyCredentials(email, password);
		
		if(status==true) {
			HttpSession session = request.getSession(true);
			session.setAttribute("email", email);
			session.setMaxInactiveInterval(10);
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/new_registration.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("error", "Invalid username / password");
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			rd.forward(request, response);
		}
	}

}
