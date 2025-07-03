package Logic;

import Data.mysql.MySqlVehicleDao;
import Models.Vehicle;
import UI.UserInterface;
import Utilities.Utils;
import configurations.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class VehicleLogic {

	static UserInterface ui = new UserInterface();
	static BasicDataSource dataSource = DatabaseConfig.setDataSource();
	static MySqlVehicleDao vehicleDao = new MySqlVehicleDao(dataSource);

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
		List<Vehicle> vehicleList = vehicleDao.getAll();
		printData(vehicleList);

		Utils.pauseApp();
	}

	private static void processShowByPrice() {
		double minPrice = Utils.getUserInputDouble("Enter the minimum price: ");
		double maxPrice = Utils.getUserInputDouble("Enter the maximum price: ");

		List<Vehicle> vehicleList = vehicleDao.searchByPrice(minPrice, maxPrice);
		printData(vehicleList);

		Utils.pauseApp();
	}

	private static void processShowByMake() {
		String vehicleMake = Utils.getUserInput("Enter the Vehicle Make: ");
		List<Vehicle> vehicleList = vehicleDao.searchByMake(vehicleMake);

		printData(vehicleList);
		Utils.pauseApp();
	}

	private static void processShowByModel() {
		String vehicleModel = Utils.getUserInput("Enter the Vehicle Model: ");
		List<Vehicle> vehicleList = vehicleDao.searchByModel(vehicleModel);

		printData(vehicleList);
		Utils.pauseApp();
	}

	private static void processShowByYear() {
		int minYear = Utils.getUserInputInt("Enter the Minimum Year: ");
		int maxYear = Utils.getUserInputInt("Enter the Maximum Year: ");
		List<Vehicle> vehicleList = vehicleDao.searchByYear(minYear, maxYear);

		printData(vehicleList);
		Utils.pauseApp();
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

	private static void printData(List<Vehicle> vehicleList) {
		Utils.designLine(50, true, "_");
		if(vehicleList.isEmpty()) {
			System.out.println("There are no vehicles to display...");
		} else {
			for(Vehicle vehicle : vehicleList) {
				vehicle.print();
				Utils.designLine(35, false, "_");
			}
			System.out.println("\nTotal Vehicles: " + vehicleList.size());
		}
	}

}
