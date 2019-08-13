package dao; //Name of the package

//Import methods from classes and library
import java.util.ArrayList;
import pojo.User;
import pojo.UserDetails;

public class ATMDAOImpl implements ATMDAO //Name of the class
{
	//Declare Arraylist
	ArrayList <UserDetails> userList = new ArrayList<UserDetails>();
	
	//Determine whether the email address exists in ArrayList
	@Override
	public UserDetails checkEmailAddress(String emailAddress)
	{
		for (UserDetails user : userList) 
		{
			if (user.getRefUser().getEmailAddress().equals(emailAddress))
			{
				return user;
			} //end if 
		} //end for
		return null;
	} //end checkEmailAddress method
	
	@Override
	public void addUser(UserDetails refUser)
	{
		userList.add(refUser);
	} //end checkEmailAddress method

	
	//Determine whether the email address and password are valid for login
	@Override
	public boolean isUserDataValid(String refUserID, String refPassword)
	{
		for (UserDetails user : userList) 
		{
			if (user.getRefUser().getEmailAddress().equals(refUserID))
			{
				if (user.getRefUser().getPassword().equals(refPassword))
				{
					return true;
				} //end if
			} //end if
		} //end for
		return false;
	} //end isUserDataValid method

	//Retrieve the user that is logged in
	@Override
	public UserDetails getUser(String emailAddress)
	{
		//Iterate through the list
		for (UserDetails user : userList)
		{
			if (user.getRefUser().getEmailAddress().equals(emailAddress))
			{
				return user;
			} //end if
		} //end for
		
		return null;
	} //end getUser method
	
	@Override
	public boolean checkUserDetails(String emailAddress, String colour)
	{
		for (UserDetails user : userList) 
		{
			if (user.getRefUser().getEmailAddress().equals(emailAddress))
			{
				if (user.getRefUser().getFavouriteColour().equals(colour))
				{
					return true;
				} //end if 
		    } //end if
	    }//end for
		return false;
	} //end ATMDAOImpl
} //end ATMDAOImpl
