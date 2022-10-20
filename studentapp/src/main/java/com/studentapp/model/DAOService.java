package com.studentapp.model;

import java.sql.ResultSet;

public interface DAOService {

	public void connectDB();
	public void saveRegistration(String name, String city, String email, String mobile);
	public boolean verifyCredentials(String email, String password);
	public ResultSet listAllReg();
	public void deleteRegistration(String email);
	public ResultSet getRegistrationByEmail(String email);
	public void updateRegistration(String name, String city, String email, String mobile);
}
