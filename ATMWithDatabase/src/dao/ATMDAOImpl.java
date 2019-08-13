package dao; //Name of the package

//Import methods from classes and library
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.DBConnection;
import java.util.ArrayList;
import model.User;
import model.UserDetails;

public class ATMDAOImpl implements ATMDAO //Name of the class
{
	//Variable Connection
	Connection conn;
	PreparedStatement stmt;
	
	void getConnection()
	{
		try 
		{
			conn = DBConnection.prepareConnection();
		}
		catch (ClassNotFoundException | SQLException e) 
		{
			System.out.println("DB Connection Error");
		} //end try catch 
	} //end getConnection
	
	
	public void addUser(UserDetails ref)
	{
		try
		{
			//Establish the database connection
			getConnection();
		
			String sql = "INSERT INTO customer (emailAddress,password,securityKey)" + "VALUES (?,?,?)";
			stmt = conn.prepareStatement(sql);
			
			//Obtain the details of the user to be added 
			stmt.setString(1, ref.getRefUser().getEmailAddress());
			stmt.setString(2,  ref.getRefUser().getPassword());
			stmt.setString(3, ref.getRefUser().getSecurityKey());
			
			//Add user to the database
			stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Cannot insert records.");
		}
		finally
		{
			try
			{
				
				if (stmt != null)
				{
					stmt.close();
				}
				
				
				if (conn != null)
				{
					conn.close();
				}
			}
			catch (SQLException e)
			{
				System.out.println("Caught exception");
			} //end try catch
		} //end finally 
		
		
	}
	//Declare Arraylist
	//ArrayList <UserDetails> userList = new ArrayList<UserDetails>();
	
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
				if (user.getRefUser().getSecurityKey().equals(colour))
				{
					return true;
				} //end if 
		    } //end if
	    }//end for
		return false;
	} //end ATMDAOImpl
} //end ATMDAOImpl
