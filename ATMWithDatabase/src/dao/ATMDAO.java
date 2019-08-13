package dao; //Name of the package

//Import methods from classes and library
import java.util.ArrayList;
import model.User;
import model.UserDetails;

public interface ATMDAO //Name of the interface
{
	UserDetails checkEmailAddress(String emailAddress);
	public void addUser(UserDetails refUser);
	boolean isUserDataValid(String refUserID, String refPassword);
	UserDetails getUser(String emailAddress);
	boolean checkUserDetails(String emailAddress, String colour);
	
} //end ATMDAO
