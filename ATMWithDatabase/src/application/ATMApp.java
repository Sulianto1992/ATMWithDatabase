package application; //Name of the package

import controller.MainController;

public class ATMApp //Name of the class
{
	public static void main(String[] args) //Start of the program
	{
		//Create new object of MainController class
		MainController refMainController = new MainController();
		
		//Invoke method
		refMainController.userMainController();
	} //end main

} //end ATMApp
