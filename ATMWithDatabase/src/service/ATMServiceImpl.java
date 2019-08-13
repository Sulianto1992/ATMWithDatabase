package service; //Name of the package

//Import methods from classes and library
import java.util.InputMismatchException;
import java.util.Scanner;
import dao.ATMDAO;
import dao.ATMDAOImpl;
import model.UserDetails;

public class ATMServiceImpl implements ATMService //Name of the class
{
	//Variable Declaration
	String password, secondPassword, securityKey;
	String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	String charOnly = "^[a-zA-Z]*$";
	String emailAddress, reply;
	Scanner sc = new Scanner(System.in);
	ATMDAO refATMDAO;		
	int choice, amount;
	
	
	//Default Constructor
	public ATMServiceImpl() 
	{
		refATMDAO = new ATMDAOImpl();
	} //end constructor
	
	@Override
	public void registerUser()
	{
		//Prompt for user details
		System.out.print("Enter email address : ");
		emailAddress = sc.nextLine();
		
		//Prompt the user for email address if the value is incorrect
		while (!(emailAddress.matches(regex)))
		{
			//Prompt for user details
			System.out.print("Incorrect email address. Please enter the correct value.\n");
			System.out.print("Enter email address : ");
			emailAddress = sc.nextLine();
		} //end while
		
		//Determine whether the email address exists
		while (refATMDAO.checkEmailAddress(emailAddress) == true)
		{
			System.out.println("\nEmail already exists!");
			System.out.println("Please enter another valid address\n");
			System.out.print("Enter email address : ");
			emailAddress = sc.nextLine();
		} //end while
		
		//Prompt user for his or her password
		System.out.print("\nEnter Password: ");
		password = sc.nextLine();
		
		//Prompt user to enter his or her password again
		System.out.print("\nRe-type Password: ");
		secondPassword = sc.nextLine();		
			
		//Execute this statement until both passwords matches
		while (!(password.equals(secondPassword)))
		{
			System.out.println("Password doesn't match!!\n");
			System.out.println("Please enter the password again");
			
			//Prompt user for his or her password
			System.out.print("\nEnter Password: ");
			password = sc.nextLine();
			
			//Prompt user to enter his or her password again
			System.out.print("\nRe-type Password: ");
			secondPassword = sc.nextLine();
			System.out.println();
			
		} //end while
			
		//Prompt user for his or her favourite colour
		System.out.print("\nWhat is your favourite colour ? ");
		securityKey = sc.nextLine();
		
		//Prompt the user for email address if the value is incorrect
		while (!(securityKey.matches(charOnly)))
		{
			//Prompt for user details
			System.out.print("Invalid value. Please enter the correct value.\n");
			System.out.print("What is your favourite colour ?");
			securityKey = sc.nextLine();
		} //end while
			
		System.out.println(securityKey + " is your security key, incase if you forgot your password.\n");
		
		//Set value to setter method of User class
		UserDetails refUser = new UserDetails();
	
		refUser.getRefUser().setEmailAddress(emailAddress);
		refUser.getRefUser().setPassword(password);
		refUser.getRefUser().setSecurityKey(securityKey);
		
	    refATMDAO.addUser(refUser);
		
		//Display message
		System.out.println("Registration Successful!!!\n");
	}   
	
	@Override
	public void loginService()
	{
		//Prompt user for email address
		System.out.print("Enter User ID: ");
		emailAddress = sc.nextLine();
		
		//Prompt user for password
		System.out.print("Password: ");
		password = sc.nextLine();
		
		//Prompt the user for email address if the value is incorrect
		while (!(emailAddress.matches(regex)))
		{
			//Prompt for user details
			System.out.print("Incorrect email address. Please enter the correct value.\n");
			
			//Prompt user for email address
			System.out.println("\nEnter User ID: ");
			emailAddress = sc.nextLine();
			
			//Prompt user for password
			System.out.println("Password: ");
			password = sc.nextLine();
		} //end while
		
		
		//Prompt user for his or her password if the password is incorrect
		while (refATMDAO.isUserDataValid(emailAddress, password) != true)
		{
			System.out.println("\nInvalid login credentials");
			System.out.println("\nPlease enter the correct values");
			
			//Prompt user for email address
			System.out.println("\nEnter User ID: ");
			emailAddress = sc.nextLine();
			
			//Prompt user for password
			System.out.println("Password: ");
			password = sc.nextLine();
		} //end while
		
		//Display valid message
		System.out.println("\nLogin Successful!!\n");
	    
		
		
		do
		{
			//Display bank menu
			displayBankMenu();
			
			try 
			{
				//Prompt user to select one of the bank options
				System.out.print("Enter Your Choice : ");
				choice = sc.nextInt();
	
				//Prompt for user input should the number is out of the range
				while ((choice < 1) | (choice > 4))
				{
					//Prompt for user input
					System.out.println("Choice not available!!");
					System.out.print("Enter Your Choice : ");
					sc = new Scanner(System.in);
					choice = sc.nextInt();
					System.out.println(); 
				} //end while
			} 
			catch (InputMismatchException e) //Detect for any other input characters
			{
				//Prompt for user input
				System.out.println("Choice not available!!");
				System.out.print("Enter Your Choice : ");
				Scanner sc = new Scanner(System.in);
				choice = sc.nextInt();
				System.out.println(); 
				
			} //end try catch
			
			//Retrieve user that is currently logged in
			UserDetails currentUser = refATMDAO.getUser(emailAddress);
			
			//Execute the statement based on the user choice
			switch(choice)
			{
				case 1:  checkBalance(currentUser);
					     break;
				case 2:  depositAmount(currentUser);
			    		 break;
				case 3:  withdrawAmount(currentUser);
				         break;
				default: System.out.println("Thanks for Banking with Us!!!\n");
						 break;
			} //end switch
			
		}while((choice != 4));
		
	} //end loginService method

	@Override
	public void logout() 
	{
		System.out.println("Logout Successfully!!!");
	} //end logout method
	
	//Display main menu
	public void displayMainMenu()
	{
		//Main page menu
		System.out.println("User Home Page :");
		System.out.println("1. Register");
		System.out.println("2. Login");
		System.out.println("3. Forget Password");
		System.out.println("4. Logout (exit)\n");
	} //end main menu
	
	//Display bank options
	public void displayBankMenu()
	{
		//Bank menu
		System.out.println("Type 1: Check Available Bank Balance");
		System.out.println("Type 2: Deposit Amount");
		System.out.println("Type 3: Withdraw Amount");
	} //end displayBankMenu

	//Invoke method to check user's current balance
	public void checkBalance(UserDetails currentUser)
	{
		//Display current balance
		System.out.println("\nAvailable balance: $" + currentUser.getRefAccountInfo().getBalance());
		System.out.println();
	} //end checkBalance
	
	//Invoke method to deposit amount into the bank balance
	public void depositAmount(UserDetails currentUser)
	{
		//Prompt user for the amount to be deposited
		System.out.print("Enter Amount: ");
		amount = sc.nextInt();
		
		try
		{
			//Prompt user if the deposit amount is less than 0
			while (amount < 0)
			{
				System.out.println("Amount can't be negative!!");
				System.out.println("Please enter a valid amount");
				
				//Prompt user for the amount to be deposited
				System.out.print("Enter Amount: ");
				amount = sc.nextInt();
			} //end while
		}
		catch (InputMismatchException e)
		{
			System.out.println("Amount must be in number!!");
			System.out.println("Please enter a valid amount");
			
			//Prompt user for the amount to be deposited
			System.out.print("Enter Amount: ");
			amount = sc.nextInt();
		} //end try catch
		
		//Obtain and display new bank balance
		currentUser.getRefAccountInfo().depositAmount(amount);
		System.out.println();
		System.out.println(amount + " dollar deposited successfully!!\n");
		
	} //end checkBalance

    //Invoke method to withdraw amount from the bank balance
	public void withdrawAmount(UserDetails currentUser) 
    {
		//Prompt user for the amount to be withdrawn
		System.out.print("Enter Amount: ");
		amount = sc.nextInt();
		
		try
		{
			//Prompt user if the deposit amount is less than 0
			while (amount < 0)
			{
				System.out.println("Amount can't be negative!!");
				System.out.println("Please enter a valid amount");
				
				//Prompt user for the amount to be deposited
				System.out.print("Enter Amount: ");
				amount = sc.nextInt();
			} //end while
		}
		catch (InputMismatchException e)
		{
			System.out.println("Amount must be in number!!");
			System.out.println("Please enter a valid amount");
			
			//Prompt user for the amount to be deposited
			System.out.print("Enter Amount: ");
			amount = sc.nextInt();
		} //end try catch
		
		// Determine whether the users has enough amount to withdraw from the bank balance
		if (currentUser.getRefAccountInfo().isEnoughWithdraw(amount) == false) 
		{
			//Display invalid message
			System.out.println("Sorry!! insufficient balance.");
		}
		else
		{
			//Display valid message
			currentUser.getRefAccountInfo().withdrawAmount(amount);
			System.out.println("\nTransaction Successful!!");
		} //end if
		
	} //end withdrawAmount
	
	//Invoke method to reset the user's password
	public void forgetPassword() 
	{
		//Prompt user for user ID and security key
		System.out.print("Enter Your ID : ");
		emailAddress = sc.nextLine();
		
		System.out.print("\nEnter security key: ");
		securityKey = sc.nextLine();
		
		//Prompt the user for email address if the value is incorrect
		while (!(emailAddress.matches(regex)))
		{
			//Prompt for user details
			System.out.print("Incorrect email address. Please enter the correct value.\n");
			System.out.print("Enter email address : ");
			emailAddress = sc.nextLine();
		} //end while
		
		//Prompt the user for email address if the value is incorrect
		while (!(securityKey.matches(charOnly)))
		{
			//Prompt for user details
			System.out.print("Invalid value. Please enter the correct value.\n");
			System.out.print("What is your favourite colour ?");
			securityKey = sc.nextLine();
		} //end while
		
		//Determine whether his or her userID and security key are valid
		if (refATMDAO.checkUserDetails(emailAddress, securityKey) == false)
		{
			//Display invalid message
			System.out.println("Invalid user credentials.");
		} 
		else
		{
			//Prompt the user for new password
			System.out.print("\nEnter new password: ");
			password = sc.nextLine();
			System.out.print("Retype password : ");
			secondPassword = sc.nextLine();
			
			//Execute this statement until both passwords matches
			while (!(password.equals(secondPassword)))
			{
				System.out.println("Password doesn't match!!\n");
				System.out.println("Please enter the password again");
				
				//Prompt user for his or her password
				System.out.print("Enter Password: ");
				password = sc.nextLine();
				
				//Prompt user to enter his or her password again
				System.out.print("\nRe-type Password: ");
				secondPassword = sc.nextLine();
				System.out.println();
				
			} //end while
			
			//Prompt user for his or her favourite colour
			System.out.print("\nWhat is your favourite colour ? ");
			securityKey = sc.nextLine();
			
			//Prompt the user for email address if the value is incorrect
			while (!(securityKey.matches(charOnly)))
			{
				//Prompt for user details
				System.out.print("Invalid value. Please enter the correct value.\n");
				System.out.print("What is your favourite colour ?");
				securityKey = sc.nextLine();
			} //end while
				
			//Set the new security key
			refATMDAO.getUser(emailAddress).getRefUser().setSecurityKey(securityKey);
			
			//Display answer for the security question
			System.out.println(securityKey + " is your security key, incase if you forgot your password.\n");
			
			//Set the new password
			refATMDAO.getUser(emailAddress).getRefUser().setPassword(password);
			
			//Display valid message
			System.out.println("Your password has been reset successfully.\n");
			
		} //end if

	} //end forgetPassword method
	
	
} //end ATMServiceImpl
