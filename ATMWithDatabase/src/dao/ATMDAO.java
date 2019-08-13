package dao; //Name of the package

//Import methods from classes and library

import model.User;
import model.UserDetails;

public interface ATMDAO //Name of the interface
{
	boolean checkEmailAddress(String emailAddress);
	public void addUser(UserDetails refUser);
	boolean isUserExists(String refUserID, String refPassword);
	UserDetails getUser(String emailAddress);
	boolean checkUserDetails(String emailAddress, String securityKey);
	
} //end ATMDAO
