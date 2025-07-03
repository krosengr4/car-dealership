package Logic;

import Data.LeaseContractDao;
import Data.SalesContractDao;
import Data.VehicleDao;
import Data.mysql.MySqlLeaseDao;
import Data.mysql.MySqlSalesDao;
import Data.mysql.MySqlVehicleDao;
import Models.Contract;
import Models.SalesContract;
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
		int vehicleId = Utils.getUserInputInt("Enter the Vehicle ID of the vehicle to update: ");
		Vehicle updateVehicle  = vehicleDao.searchById(vehicleId);
		int userChoice = ui.displayUpdateVehicle();

		switch(userChoice) {
			case 1 -> updateVehicle.setVin(Utils.getUserInput("Enter the new VIN: "));
			case 2 -> updateVehicle.setYear(Utils.getUserInputInt("Enter the new year: "));
			case 3 -> updateVehicle.setMake(Utils.getUserInput("Enter the new make: "));
			case 4 -> updateVehicle.setModel(Utils.getUserInput("Enter the new model: "));
			case 5 -> updateVehicle.setColor(Utils.getUserInput("Enter the new color: "));
			case 6 -> updateVehicle.setVehicleType(Utils.getUserInput("Enter the new type: "));
			case 7 -> updateVehicle.setOdometer(Utils.getUserInputInt("Enter the new odometer: "));
			case 8 -> updateVehicle.setPrice(Utils.getUserInputDouble("Enter the new price: "));
			case 9 -> updateVehicle.setIsSold(Utils.getUserInputBoolean("Enter true or false for is the vehicle sold: "));
			case 0 -> {
				return;
			}
		}

		vehicleDao.update(updateVehicle, vehicleId);
	}

	private static void processDeleteVehicle() {
		int vehicleId = Utils.getUserInputInt("Enter the Vehicle ID of the vehicle you wish to delete: ");
		vehicleDao.delete(vehicleId);
	}

	private static void processUpdateSalesContract() {
		int contractId = Utils.getUserInputInt("Enter the ID of the sales contract to update: ");
		SalesContract updateContract = salesDao.getByContractId(contractId);
		int userChoice = ui.displayUpdateSaleContract();

		switch(userChoice) {
			case 1 -> updateContract.setCustomerName(Utils.getUserInput("Enter the new customer name: "));
			case 2 -> updateContract.setCustomerEmail(Utils.getUserInput("Enter the new customer email: "));
			case 3 -> {
				Vehicle newVehicle = processUpdatingVehicleOnContract(updateContract.getVehicleSold());

				if(newVehicle == null)
					System.err.println("ERROR! This Vehicle has already been sold!!!");
				else
					updateContract.setVehicleSold(vehicleDao.searchById(newVehicle.getVehicleId()));
			}

			case 4 -> updateContract.setFinance(Utils.getUserInputBoolean("Enter true or false for if the vehicle was financed: "));
			case 0 -> {
				return;
			}
		}

		salesDao.update(updateContract, contractId);
	}

	private static Vehicle processUpdatingVehicleOnContract(Vehicle oldVehicle) {
		//Get the vehicle to add onto the contract
		int newVehicleId = Utils.getUserInputInt("Enter the ID for the new vehicle: ");
		Vehicle newVehicle = vehicleDao.searchById(newVehicleId);

		//Make sure that the new vehicle user is trying to put on the contract isn't already sold
		if(!newVehicle.isSold()) {
			//Set sold to true and update vehicle in the db
			newVehicle.setIsSold(true);
			vehicleDao.update(newVehicle, newVehicle.getVehicleId());

			//Set isSold to false for the vehicle that was on the contract. Update vehicle in the db
			oldVehicle.setIsSold(false);
			vehicleDao.update(oldVehicle, oldVehicle.getVehicleId());

			//return the new vehicle
			return newVehicle;
		}
		return null;
	}

	private static void processDeleteSalesContract() {
		int contractId = Utils.getUserInputInt("Enter the ID of the sales contract to delete: ");
		SalesContract contract = salesDao.getByContractId(contractId);

		if(contract != null) {
			Utils.designLine(50, true, "-");
			contract.print();

			int userConfirmation = Utils.getUserInputIntMinMax("\nIs this the contract you'd like to delete?\n1 - Yes, delete it\n2 - No! Hold your horses!\nEnter here: ", 1, 2);
			if(userConfirmation == 1) {
				salesDao.delete(contractId);

				Vehicle vehicle = contract.getVehicleSold();
				vehicle.setIsSold(false);
				vehicleDao.update(vehicle, vehicle.getVehicleId());
			}
			else
				System.out.println("\nThen the contract shall remain! It was not deleted!");
		} else {
			System.out.println("No contract was found with that ID...");
		}
		Utils.pauseApp();
	}

	private static void processUpdateLeaseContract() {
		System.out.println("Update Lease Contract");
	}

	private static void processDeleteLeaseContract() {
		System.out.println("Delete lease contract");
	}

}
