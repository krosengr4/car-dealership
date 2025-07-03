package UI;

import Utilities.Utils;

public class UserInterface {

	public int displayMainMenu() {
		Utils.designLine(55, false, "=");
		System.out.println("\t\t\t\t\tMAIN MENU");
		Utils.designLine(55, false, "=");
		System.out.println("""
									---OPTIONS---
				1 - Go to Vehicle Menu			3 - Sell or Lease Vehicle
				2 - Go to Contract Menu			4 - Admin
									0 - Exit
				""");

		return Utils.getUserInputIntMinMax("Enter your option: ", 0, 4);
	}

	public int displayVehicleMenu() {
		Utils.designLine(60, false, "=");
		System.out.println("\t\t\t\t\t  VEHICLE MENU");
		Utils.designLine(60, false, "=");
		System.out.println("""
										---OPTIONS---
				1 - Show All Vehicles				5 - Find Car By Year
				2 - Find Car By Price				6 - Find Car By Color
				3 - Find Car By Make				7 - Find Car By Mileage
				4 - Find Car By Model				8 - Find Cars by Vehicle Type
										0 - Go back
				""");

		return Utils.getUserInputIntMinMax("Enter your option: ", 0, 8);
	}

	public int displayContractMenu() {
		Utils.designLine(55, false, "=");
		System.out.println("\t\t\t\t\tCONTRACT MENU");
		Utils.designLine(55, false, "=");
		System.out.println("""
									---OPTIONS---
				1 - View All Sales Contracts
				2 - View All Lease Contracts
				0 - Go Back
				""");

		return Utils.getUserInputIntMinMax("Enter your option: ", 0, 2);
	}

	public int displayAdminMenu() {
		Utils.designLine(60, false, "=");
		System.out.println("\t\t\t\t\tADMIN MENU");
		Utils.designLine(60, false, "=");
		System.out.println("""
									---OPTIONS---
				1 - Add A Vehicle				5 - Delete Sales Contract
				2 - Update A Vehicle			6 - Update Lease Contract
				3 - Delete A Vehicle			7 - Delete Lease Contract
				4 - Update Sales Contract
				
									0 - Go Back
				""");

		return Utils.getUserInputIntMinMax("Enter your option: ", 0, 7);
	}

}
