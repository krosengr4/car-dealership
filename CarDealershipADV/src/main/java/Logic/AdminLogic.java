package Logic;

import Data.LeaseContractDao;
import Data.SalesContractDao;
import Data.VehicleDao;
import Data.mysql.MySqlLeaseDao;
import Data.mysql.MySqlSalesDao;
import Data.mysql.MySqlVehicleDao;
import Models.Vehicle;
import UI.UserInterface;
import configurations.DatabaseConfig;
import Utilities.Utils;

public class AdminLogic {

	static UserInterface ui = new UserInterface();
	static VehicleDao vehicleDao = new MySqlVehicleDao(DatabaseConfig.setDataSource());
	static SalesContractDao salesDao = new MySqlSalesDao(DatabaseConfig.setDataSource());
	static LeaseContractDao leaseDao = new MySqlLeaseDao(DatabaseConfig.setDataSource());

	public static void processAdminMenu(){
		boolean ifContinue = true;

		while(ifContinue) {
			int userChoice = ui.displayAdminMenu();

			switch(userChoice) {
				case 1 -> processAddVehicle();
				case 2 -> processUpdateVehicle();
				case 3 -> processDeleteVehicle();
				case 4 -> processUpdateSalesContract();
				case 5 -> processDeleteSalesContract();
				case 6 -> processUpdateLeaseContract();
				case 7 -> processDeleteLeaseContract();
				case 0 -> ifContinue = false;
			}
		}
	}

	private static void processAddVehicle() {
		String vin = Utils.getUserInput("Enter the vehicle VIN: ");
		int year = Utils.getUserInputInt("Enter the vehicle year: ");
		String make = Utils.getUserInput("Enter the vehicle make: ");
		String model = Utils.getUserInput("Enter the vehicle model: ");
		String color = Utils.getUserInput("Enter the vehicle color: ");
		String type = Utils.getUserInput("Enter the vehicle type: ");
		int odometer = Utils.getUserInputInt("Enter the mileage on the vehicle: ");
		double price = Utils.getUserInputDouble("Enter the vehicle price: ");

		Vehicle vehicle = vehicleDao.add(new Vehicle(0, vin, year, make, model, color, type, odometer, price, false));
		if(vehicle != null) {
			Utils.designLine(50, true, "_");
			vehicle.print();
		}

		Utils.pauseApp();
//		('1F3JK34K53O4532JOL3', 2017, 'Mitsubishi', 'Outlander', 'SUV', 'Gray', 3'
	}

	private static void processUpdateVehicle() {
		System.out.println("Process Update vehicle");
	}

	private static void processDeleteVehicle() {
		int vehicleId = Utils.getUserInputInt("Enter the Vehicle ID of the vehicle you wish to delete: ");
		vehicleDao.delete(vehicleId);
	}

	private static void processUpdateSalesContract() {
		System.out.println("Update sales contract");
	}

	private static void processDeleteSalesContract() {
		System.out.println("Delete sales contract");
	}

	private static void processUpdateLeaseContract() {
		System.out.println("Update Lease Contract");
	}

	private static void processDeleteLeaseContract() {
		System.out.println("Delete lease contract");
	}

}
