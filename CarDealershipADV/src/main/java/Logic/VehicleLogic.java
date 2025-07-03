package Logic;

import Data.VehicleDao;
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
	static VehicleDao vehicleDao = new MySqlVehicleDao(dataSource);

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
		String color = Utils.getUserInput("Enter the Vehicle Color: ");
		List<Vehicle> vehiclesList = vehicleDao.searchByColor(color);

		printData(vehiclesList);
		Utils.pauseApp();
	}

	private static void processShowByMileage() {
		int minMiles = Utils.getUserInputInt("Enter the Minimum Mileage: ");
		int maxMiles = Utils.getUserInputInt("Enter the Maximum Mileage: ");
		List<Vehicle> vehicleList = vehicleDao.searchByMileage(minMiles, maxMiles);

		printData(vehicleList);
		Utils.pauseApp();
	}

	private static void processShowByType() {
		String vehicleType = Utils.getUserInput("Enter the Vehicle Type: ");
		List<Vehicle> vehicleList = vehicleDao.searchByType(vehicleType);

		printData(vehicleList);
		Utils.pauseApp();
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
