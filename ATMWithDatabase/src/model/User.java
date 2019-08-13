package model; //Name of the package

public class User //Name of the class 
{
	//Variable declaration
	private String emailAddress;
	private String password;
	private String securityKey;
	
	//Default Constructor
	public User()
	{
		emailAddress = "";
		password = "";
		securityKey = "";
	}//end User method
	
	//Setters 
	public void setEmailAddress(String email) 
	{
		this.emailAddress = email;
	}
	
	public void setPassword(String pwd) 
	{
		this.password = pwd;
	}
	
	public void setSecurityKey(String colour) 
	{
		this.securityKey = colour;
	}
	
	//Getters
	public String getEmailAddress()
	{
		return emailAddress;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getSecurityKey()
	{
		return securityKey;
	}
	
} //end class
