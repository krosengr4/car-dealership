package UI;

import Utilities.Utils;

public class UserInterface {

	public static int displayMainScreen() {
		System.out.println("\n\t\t\t_____MAIN MENU_____");
		Utils.designLine(70, false);
		System.out.println("""
										---OPTIONS---
				1 - Show All Vehicles				5 - Find Car By Color
				2 - Find Car By Price				6 - Find Car By Mileage
				3 - Find Car By Make and Model		7 - Find Cars by Vehicle Type
				4 - Find Car By Year				8 - Sell Or Lease a Vehicle
										9 - Admin
										00 - Exit
				""");

		return Utils.getUserInputIntMinMax("Enter your option: ", 0, 9);
	}

}
