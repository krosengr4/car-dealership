package Logic;

import Data.LeaseContractDao;
import Data.SalesContractDao;
import Data.VehicleDao;
import Data.mysql.MySqlLeaseDao;
import Data.mysql.MySqlSalesDao;
import Data.mysql.MySqlVehicleDao;
import Models.SalesContract;
import Models.Vehicle;
import UI.UserInterface;
import Utilities.Utils;
import configurations.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Date;
import java.time.LocalDate;

public class Logic {

	static SalesContractDao salesDao = new MySqlSalesDao(DatabaseConfig.setDataSource());
	static LeaseContractDao leaseDao = new MySqlLeaseDao(DatabaseConfig.setDataSource());
	static VehicleDao vehicleDao = new MySqlVehicleDao(DatabaseConfig.setDataSource());
	static UserInterface ui = new UserInterface();

	public static void processMainMenu() {
		boolean ifContinue = true;

		while(ifContinue) {
			int userChoice = ui.displayMainMenu();

			switch(userChoice) {
				case 1 -> VehicleLogic.processVehicleMenu();
				case 2 -> ContractLogic.processContractMenu();
				case 3 -> sellOrLeaseVehicle();
				case 4 -> processGoToAdmin();
				case 0 -> ifContinue = false;
			}
		}
	}

	private static void sellOrLeaseVehicle() {
		System.out.println("\n1 - Sell Vehicle\n2 - Lease Vehicle\n0 - Go Back");
		int sellOrLease = Utils.getUserInputIntMinMax("Would you like to sell or lease?: ", 0, 2);

		if(sellOrLease == 1)
			sellVehicle();
		else if(sellOrLease == 2)
			leaseVehicle();
	}

	private static void sellVehicle() {
		Date date = Date.valueOf(LocalDate.now());
		String customerName = Utils.getUserInput("Please enter customers first and last name: ");
		String customerEmail = Utils.getUserInput("Pleas enter customers email: ");

		int vehicleId = Utils.getUserInputInt("Enter the Vehicle ID that you are selling: ");
		Vehicle vehicle = vehicleDao.searchById(vehicleId);

		boolean isFinanced = false;
		String stringIsFinanced = Utils.getUserInput("Is the vehicle financed? (Y or N): ").trim();

		if(stringIsFinanced.equalsIgnoreCase("y"))
			isFinanced = true;
		else if (!stringIsFinanced.equalsIgnoreCase("n"))
			System.err.println("ERROR! Enter either y or n!\nProcess cancelled.");

		if(!vehicle.isSold()) {
			SalesContract contract = salesDao.add(new SalesContract(date, customerName, customerEmail, vehicle, isFinanced));
			if(contract != null) {
				vehicle.setIsSold(true);
				vehicleDao.update(vehicle, vehicleId);

				contract.print();
			}
		} else {
			System.err.println("ERROR! This vehicle was sold already!!!");
		}
	}

	private static void leaseVehicle() {

	}

	private static void processGoToAdmin() {
		String userPassword = Utils.getUserInput("Enter the password: ");
		boolean passwordCheck = Utils.passwordCheck(userPassword);

		if(passwordCheck) {
			AdminLogic.processAdminMenu();
		} else {
			System.out.println("The password is incorrect!!!");
		}
	}



}
