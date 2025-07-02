package Logic;

import Data.VehicleDao;
import UI.UserInterface;

public class VehicleLogic {

	static UserInterface ui = new UserInterface();

	public static void processVehicleMenu() {
		boolean ifContinue = true;

		while(ifContinue) {
			int userChoice = ui.displayVehicleMenu();

			switch(userChoice) {
				case 1 -> processShowAll();
				case 2 -> processShowByPrice();
				case 3 -> processShowByMake();
				case 4 -> processShowByModel();
				case 5 -> processShowByYear();
				case 6 -> processShowByColor();
				case 7 -> processShowByMileage();
				case 8 -> processShowByType();
				case 0 -> ifContinue = false;
			}
		}
	}

	private static void processShowAll() {
		System.out.println("Show all vehicles");
	}

	private static void processShowByPrice() {
		System.out.println("Show by price");
	}

	private static void processShowByMake() {
		System.out.println("Show by Make");
	}

	private static void processShowByModel() {
		System.out.println("Show by model");
	}

	private static void processShowByYear() {
		System.out.println("Show by year");
	}

	private static void processShowByColor() {
		System.out.println("Show by color");
	}

	private static void processShowByMileage() {
		System.out.println("Show by mileage");
	}

	private static void processShowByType() {
		System.out.println("Process Show By Type");
	}
}
