package dao; //Name of the package

//Import methods from classes and library
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.DBConnection;
import java.util.ArrayList;
import model.User;
import model.UserDetails;

public class ATMDAOImpl implements ATMDAO //Name of the class
{
	ArrayList <UserDetails> userList = new ArrayList<UserDetails>();
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
	
	@Override
	public boolean checkEmailAddress(String refUserID)
	{
		getConnection();
		
		try
		{
		   stmt = conn.prepareStatement("SELECT * from customer WHERE emailAddress = ?");	
		   stmt.setString(1, refUserID);
		   ResultSet rs = stmt.executeQuery();
		   
		   if (rs.next())
			{
				do 
				{
					if (rs.getString(1).compareTo(refUserID) == 0)
				    {
					   return true;
				    } //end if
				} while(rs.next());
				
			} //end if 
		   
		}
		catch (SQLException e)
		{
			System.out.println("This email address " + refUserID + " doesn't exist.");
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
				System.out.println("Caught Exception");
			} //end catch
	    } //end finally
		
		return false;
		
	} //end checkEmailAddress method
	
	
	//Determine whether the email address and password are valid for login
	@Override
	public boolean isUserExists(String refUserID, String refPassword)
	{
		getConnection();
		
		try
		{
		   stmt = conn.prepareStatement("SELECT * from customer WHERE emailAddress = ? AND password = ?");	
		   stmt.setString(1, refUserID);
		   stmt.setString(2, refPassword);
		   
		   ResultSet rs = stmt.executeQuery();
		   
		   if (rs.next())
			{
				do 
				{
					if (rs.getString(1).compareTo(refUserID) == 0)
				    {
						if(rs.getString(2).compareTo(refPassword) == 0)
						{
							return true;
						} //end if
				    } //end if
					
				} while(rs.next());
				
			} //end if 
		   
		}
		catch (SQLException e)
		{
			System.out.println("This email address" + refUserID + " and password " + refPassword + "do not exist.");
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
				System.out.println("Caught Exception");
			} //end catch
	    } //end finally
		
		return false;
		
	} //end isUserExists method

	//Retrieve the user that is logged in
	@Override
	public UserDetails getUser(String emailAddress)
	{
		getConnection();
		UserDetails user = new UserDetails();
		
		try
		{
			stmt = conn.prepareStatement("SELECT * FROM customer WHERE emailAddress = ?");
			stmt.setString(1, emailAddress);
			
			ResultSet rs = stmt.executeQuery();
			
			do 
			{
				if (rs.getString(1).compareTo(emailAddress) == 0)
				{
					user.getRefUser().setEmailAddress(rs.getString(1));
					user.getRefUser().setPassword(rs.getString(2));
					user.getRefUser().setSecurityKey(rs.getString(3));
					
			    } //end if
				
				
			} while(rs.next());
		}
		catch (SQLException e)
		{
			System.out.println("Employee is not in the list.");
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
				System.out.println("Caught Exception");
			} //end catch
	    } //end finally
		
		return user;
	}
		
	@Override
	public boolean checkUserDetails(String refEmail, String refSecurityKey)
	{
		getConnection();
		
		try
		{
		   stmt = conn.prepareStatement("SELECT * from customer WHERE emailAddress = ? AND securityKey = ?");	
		   stmt.setString(1, refEmail);
		   stmt.setString(2, refSecurityKey);
		   
		   ResultSet rs = stmt.executeQuery();
		   
		   if (rs.next())
			{
				do 
				{
					if (rs.getString(1).compareTo(refEmail) == 0)
				    {
						if(rs.getString(3).compareTo(refSecurityKey) == 0)
						{
							return true;
						} //end if
				    } //end if
					
				} while(rs.next());
				
			} //end if 
		   
		}
		catch (SQLException e)
		{
			System.out.println("This email address " + refEmail + " and security key " + refSecurityKey + " do not exist.");
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
				System.out.println("Caught Exception");
			} //end catch
	    } //end finally
		
		return false;
  } //end ATMDAOImpl
	
	@Override
	public void updateUserDetails(UserDetails refUser)
	{
		try
		{
			getConnection();
			
			String sql = "UPDATE customer SET password = ?, securityKey = ? WHERE emailAddress = ?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(3, refUser.getRefUser().getEmailAddress());
		    stmt.setString(1, refUser.getRefUser().getPassword());
		    stmt.setString(2, refUser.getRefUser().getSecurityKey());
		    
		    stmt.executeUpdate();
		}
		catch (SQLException e)
		{
			System.out.println("Cannot update record.");
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
	} //end updateUserDetails
	
}