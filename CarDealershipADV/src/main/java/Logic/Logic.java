package Logic;

import Data.LeaseContractDao;
import Data.SalesContractDao;
import Data.mysql.MySqlLeaseDao;
import Data.mysql.MySqlSalesDao;
import UI.UserInterface;
import Utilities.Utils;
import configurations.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;

public class Logic {

	static SalesContractDao salesDao = new MySqlSalesDao(DatabaseConfig.setDataSource());
	static LeaseContractDao leaseDao = new MySqlLeaseDao(DatabaseConfig.setDataSource());
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
		System.out.println("Would you like to sell or lease?");
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
