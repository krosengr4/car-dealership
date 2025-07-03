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
import Utilities.Utils;
import configurations.DatabaseConfig;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
		List<String> contractAttributes = ui.displaySellLeaseScreen(1);
		String customerName = contractAttributes.get(0);
		String customerEmail = contractAttributes.get(1);
		int vehicleId = Integer.parseInt(contractAttributes.get(2));
		boolean isFinanced = Integer.parseInt(contractAttributes.get(3)) == 1;

		Vehicle vehicle = vehicleDao.getById(vehicleId);

		if(!vehicle.isSold()) {
			SalesContract contract = salesDao.add(new SalesContract(Date.valueOf(LocalDate.now()), customerName, customerEmail, vehicle, isFinanced));
			if(contract != null) {
				vehicle.setIsSold(true);
				vehicleDao.update(vehicle, vehicleId);

				Utils.designLine(50, true, "_");
				contract.print();
			}
		} else {
			System.err.println("ERROR! This vehicle was sold already!!!");
		}
		Utils.pauseApp();
	}

	private static void leaseVehicle() {
		List<String> contractAttributes = ui.displaySellLeaseScreen(2);
		String customerName = contractAttributes.get(0);
		String customerEmail = contractAttributes.get(1);
		int vehicleId = Integer.parseInt(contractAttributes.get(2));
		Vehicle vehicle = vehicleDao.getById(vehicleId);

		if(!vehicle.isSold()) {
			LeaseContract contract = leaseDao.add(new LeaseContract(Date.valueOf(LocalDate.now()), customerName, customerEmail, vehicle));
			if(contract != null) {
				vehicle.setIsSold(true);
				vehicleDao.update(vehicle, vehicleId);

				Utils.designLine(50, true, "_");
				contract.print();
			}
		} else {
			System.err.println("ERROR! This vehicle was sold already!!!");
		}
		Utils.pauseApp();
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
