package Logic;

import Data.LeaseContractDao;
import Data.SalesContractDao;
import Data.VehicleDao;
import Data.mysql.MySqlLeaseDao;
import Data.mysql.MySqlSalesDao;
import Data.mysql.MySqlVehicleDao;
import Models.LeaseContract;
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

	public static void processAdminMenu() {
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
		Vehicle updateVehicle = vehicleDao.getById(vehicleId);
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
			case 9 ->
					updateVehicle.setIsSold(Utils.getUserInputBoolean("Enter true or false for is the vehicle sold: "));
			case 0 -> {
				return;
			}
		}

		vehicleDao.update(updateVehicle, vehicleId);
	}

	private static void processDeleteVehicle() {
		int vehicleId = Utils.getUserInputInt("Enter the Vehicle ID of the vehicle you wish to delete: ");
		Vehicle vehicleToDelete = vehicleDao.getById(vehicleId);

		if(vehicleToDelete != null) {
			Utils.designLine(50, true, "-");
			vehicleToDelete.print();

			int userConfirmation = Utils.getUserInputIntMinMax("\nIs this the vehicle you'd like to delete?\n1 - Yes, be gone with it!\n2 - No! Please no!\nEnter here: ", 1, 2);
			if(userConfirmation == 1)
				vehicleDao.delete(vehicleId);
			else
				System.out.println("\nVehicle NOT deleted. It is safe... for now");

		} else {
			System.out.println("\nCould not find vehicle with that ID...");
		}
		Utils.pauseApp();
	}

	private static void processUpdateSalesContract() {
		int contractId = Utils.getUserInputInt("Enter the ID of the sales contract to update: ");
		SalesContract updateContract = salesDao.getByContractId(contractId);

		if(updateContract == null) {
			System.out.println("\nThere were no sales contracts found with that ID...");
		} else {

			int userChoice = ui.displayUpdateSaleContract();

			switch(userChoice) {
				case 1 -> updateContract.setCustomerName(Utils.getUserInput("Enter the new customer name: "));
				case 2 -> updateContract.setCustomerEmail(Utils.getUserInput("Enter the new customer email: "));
				case 3 -> {
					Vehicle newVehicle = updateVehicleOnContract(updateContract.getVehicleSold());

					if(newVehicle == null)
						System.err.println("ERROR! This Vehicle has already been sold!!!");
					else
						updateContract.setVehicleSold(newVehicle);

				}
				case 4 ->
						updateContract.setFinance(Utils.getUserInputBoolean("Enter true or false for if the vehicle was financed: "));
				case 0 -> {
					return;
				}
			}

			salesDao.update(updateContract, contractId);
		}
		Utils.pauseApp();
	}

	private static void processDeleteSalesContract() {
		int contractId = Utils.getUserInputInt("Enter the ID of the sales contract to delete: ");
		SalesContract contractToDelete = salesDao.getByContractId(contractId);

		if(contractToDelete != null) {
			Utils.designLine(50, true, "-");
			contractToDelete.print();

			int userConfirmation = Utils.getUserInputIntMinMax("\nIs this the contract you'd like to delete?\n1 - Yes, delete it\n2 - No! Hold your horses!\nEnter here: ", 1, 2);
			if(userConfirmation == 1) {
				salesDao.delete(contractId);

				Vehicle vehicle = contractToDelete.getVehicleSold();
				vehicle.setIsSold(false);
				vehicleDao.update(vehicle, vehicle.getVehicleId());
			} else {
				System.out.println("\nThen the contract shall remain! It was not deleted!");
			}

		} else {
			System.out.println("No contract was found with that ID...");
		}

		Utils.pauseApp();
	}

	private static void processUpdateLeaseContract() {
		int contractId = Utils.getUserInputInt("Enter the ID of the lease contract to update: ");
		LeaseContract updateContract = leaseDao.getByContractId(contractId);
		if(updateContract == null) {
			System.out.println("\nNo lease contract found with that ID...");

		} else {
			int userChoice = ui.displayUpdateLeaseContract();

			switch(userChoice) {
				case 1 -> updateContract.setCustomerName(Utils.getUserInput("Enter the new customer name: "));
				case 2 -> updateContract.setCustomerEmail(Utils.getUserInput("Enter the new customer email: "));
				case 3 -> {
					Vehicle newVehicle = updateVehicleOnContract(updateContract.getVehicleSold());

					if(newVehicle == null)
						System.err.println("ERROR! This vehicle has already been sold!!!");
					else
						updateContract.setVehicleSold(newVehicle);

				}
				case 0 -> {
					return;
				}
			}

			leaseDao.update(updateContract, contractId);
		}
		Utils.pauseApp();
	}

	private static void processDeleteLeaseContract() {
		int contractId = Utils.getUserInputInt("Enter the ID of the lease contract to delete: ");
		LeaseContract contractToDelete = leaseDao.getByContractId(contractId);

		if(contractToDelete != null) {
			Utils.designLine(50, true, "-");
			contractToDelete.print();

			int userConfirmation = Utils.getUserInputIntMinMax("\nIs this the contract you'd like to delete?\n1 - Yes, delete it\n2 - No! Hold your horses!\nEnter here: ", 1, 2);
			if(userConfirmation == 1) {
				leaseDao.delete(contractId);

				Vehicle vehicle = contractToDelete.getVehicleSold();
				vehicle.setIsSold(false);
				vehicleDao.update(vehicle, vehicle.getVehicleId());
			} else {
				System.out.println("\nContract NOT deleted... No need to worry." + Utils.redHeart);
			}

		} else {
			System.out.println("There were no lease contracts found with that ID...");
		}

		Utils.pauseApp();
	}

	private static Vehicle updateVehicleOnContract(Vehicle oldVehicle) {
		//Get the vehicle to add onto the contract
		int newVehicleId = Utils.getUserInputInt("Enter the ID for the new vehicle: ");
		Vehicle newVehicle = vehicleDao.getById(newVehicleId);

		//Make sure that the vehicles exists in the db
		if(newVehicle == null) {
			System.out.println("\nThere were no vehicles found with that ID...");
		} else {
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
		}
		return null;
	}

}
