package model; //Name of the package

import model.AccountInfo;
import model.User;

public class UserDetails //Name of the class
{
	//Variable Declaration
	private User refUser;
	private AccountInfo refAccountInfo;
	
	public UserDetails()
	{
		refUser = new User();
		refAccountInfo = new AccountInfo();
	} //end UserDetails method
	
	public User getRefUser()
	{
		return refUser;
	} //end getRefUser method
	
	public AccountInfo getRefAccountInfo() 
	{
		return refAccountInfo;
	} //end getRefAccountInfo method
} //end UserDetails class
