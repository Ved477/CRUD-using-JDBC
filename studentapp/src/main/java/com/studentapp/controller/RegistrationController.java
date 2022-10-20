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

@WebServlet("/newReg")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrationController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/new_registration.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			if(session.getAttribute("email")!=null) {
				String name = request.getParameter("name");
				String city = request.getParameter("city");
				String email = request.getParameter("email");
				String mobile = request.getParameter("mobile");
				
				DAOService dao = new DAOServiceImpl();
				dao.connectDB();
				
				dao.saveRegistration(name, city, email, mobile);
				
				request.setAttribute("msg", "Record is Saved!!");
				RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/views/new_registration.jsp");
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

}
