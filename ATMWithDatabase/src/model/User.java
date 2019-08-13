package pojo; //Name of the package

public class User //Name of the class 
{
	//Variable declaration
	private String emailAddress;
	private String password;
	private String favouriteColour;
	
	//Default Constructor
	public User()
	{
		emailAddress = "";
		password = "";
		favouriteColour = "";
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
	
	public void setFavouriteColour(String colour) 
	{
		this.favouriteColour = colour;
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
	
	public String getFavouriteColour()
	{
		return favouriteColour;
	}
	
} //end class
