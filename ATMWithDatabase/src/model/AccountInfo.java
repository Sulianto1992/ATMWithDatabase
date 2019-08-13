package pojo; //Name of the package

public class AccountInfo //Name of the class
{
	//Member declaration
	private int balance;
	
	//Display zero dollars for new account users
	public AccountInfo()
	{
		balance = 0;
	} //end AccountInfo method
	
	//Return the current balance
	public int getBalance()
	{
		return balance;
	}
	
	//Setter method for deposit amount
	public void depositAmount(int amount)
	{
		balance = balance + amount;
	} //end setDepositAmount method
	
	
	//Getter method for checking the account balance
	public int getAccountBalance()
	{
		return balance;
	} //end getAccountBalance method
	
	//Update the current balance
	public boolean withdrawAmount(int amount)
	{
		if (isEnoughWithdraw(amount))
		{
			balance = balance - amount;
			return true;
		}
		else
		{
			return false;
		} //end if
	} //end withdrawAmount

	//Determine whether it is enough amount to withdraw
	public boolean isEnoughWithdraw(int amount) 
	{
		if (balance > amount)
		{
			return true;
		}
		else
		{
			return false;
		} //end if
		
	} //end isEnoughWithdraw
	
} //end AccountInfo
