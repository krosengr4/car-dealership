package UI;

import Models.Contract;
import Models.Vehicle;
import Utilities.Utils;

import java.util.ArrayList;
import java.util.List;

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
				1 - View All Sales Contracts				4 - View All Lease Contracts
				2 - Search Sales Contract by Contract ID	5 - Search Lease Contracts by Contract ID
				3 - Search Sales Contract by Vehicle ID		6 - Search Lease Contracts by Vehicle ID
												0 - Go Back
				""");

		return Utils.getUserInputIntMinMax("Enter your option: ", 0, 6);
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

	public List<String> displaySellLeaseScreen(int sellOrLease) {
		List<String> contractAttributes = new ArrayList<>();

		String customerName = Utils.getUserInput("Please enter customers first and last name: ");
		String customerEmail = Utils.getUserInput("Pleas enter customers email: ");
		int vehicleId = Utils.getUserInputInt("Enter the Vehicle ID that you are leasing: ");

		contractAttributes.add(customerName);
		contractAttributes.add(customerEmail);
		contractAttributes.add(String.valueOf(vehicleId));
		if(sellOrLease == 1) {
			int isFinanced = Utils.getUserInputIntMinMax("Is the vehicle financed?\n1 - yes\n2 - no\nEnter: ", 1, 2);
			contractAttributes.add(String.valueOf(isFinanced));
		}

		return contractAttributes;
	}

	public int displayUpdateVehicle() {
		System.out.println("What would you like to update?");
		System.out.println("""
						---OPTIONS---
				1 - Update Vehicle VIN
				2 - Update Vehicle Year
				3 - Update Vehicle Make
				4 - Update Vehicle Model
				5 - Update Vehicle Color
				6 - Update Vehicle Type
				7 - Update Vehicle Odometer
				8 - Update Vehicle Price
				9 - Update if Vehicle Was Sold
				0 - Go back
				""");

		return Utils.getUserInputIntMinMax("Enter here: ", 0, 9);
	}

	public int displayUpdateSaleContract() {
		System.out.println("\nWhat would you like to update?");
		System.out.println("""
						---OPTIONS---
				1 - Update Customer Name
				2 - Update Customer Email
				3 - Update Vehicle Sold
				4 - Update if the sale was financed
				0 - Go back
				""");

		return Utils.getUserInputIntMinMax("Enter here: ", 0, 4);
	}

	public int displayUpdateLeaseContract() {
		System.out.println("\nWhat would you like to update?");
		System.out.println("""
						---OPTIONS---
				1 - Update Customer Name
				2 - Update Customer Email
				3 - Update Vehicle Sold
				0 - Go back
				""");

		return Utils.getUserInputIntMinMax("Enter here: ", 0, 3);
	}

}
