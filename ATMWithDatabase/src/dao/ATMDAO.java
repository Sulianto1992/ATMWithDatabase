package dao; //Name of the package

//Import methods from classes and library
import java.util.ArrayList;
import pojo.User;
import pojo.UserDetails;

public interface ATMDAO //Name of the interface
{
	UserDetails checkEmailAddress(String emailAddress);
	void addUser(UserDetails refUser);
	boolean isUserDataValid(String refUserID, String refPassword);
	UserDetails getUser(String emailAddress);
	boolean checkUserDetails(String emailAddress, String colour);
	
} //end ATMDAO
