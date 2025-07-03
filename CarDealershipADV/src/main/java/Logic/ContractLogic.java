package Logic;

import Data.LeaseContractDao;
import Data.SalesContractDao;
import Data.mysql.MySqlLeaseDao;
import Data.mysql.MySqlSalesDao;
import Models.Contract;
import Models.LeaseContract;
import Models.SalesContract;
import UI.UserInterface;
import Utilities.Utils;
import configurations.DatabaseConfig;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class ContractLogic {

	static UserInterface ui = new UserInterface();
	static BasicDataSource dataSource = DatabaseConfig.setDataSource();
	static SalesContractDao salesDao = new MySqlSalesDao(dataSource);
	static LeaseContractDao leaseDao = new MySqlLeaseDao(dataSource);

	public static void processContractMenu() {
		boolean ifContinue = true;

		while(ifContinue) {
			int userChoice = ui.displayContractMenu();

			switch(userChoice) {
				case 1 -> processShowAllSales();
				case 2 -> processSearchSalesByContractId();
				case 3 -> processSearchSalesByVehicleId();
				case 4 -> processShowAllLease();
				case 5 -> processSearchLeaseByContractId();
				case 6 -> processSearchLeaseByVehicleId();
				case 0 -> ifContinue = false;
			}
		}
	}

	private static void processShowAllSales() {
		List<Contract> contractList = salesDao.getAll();
		printData(contractList);

		Utils.pauseApp();
	}

	private static void processSearchSalesByContractId() {
		int contractId = Utils.getUserInputInt("Enter the Contract ID: ");
		SalesContract contract = salesDao.getByContractId(contractId);

		if(contract == null) {
			System.out.println("Could not find sales contract with that ID...");
		} else {
			Utils.designLine(50, true, "_");
			contract.print();
		}
		Utils.pauseApp();
	}

	private static void processSearchSalesByVehicleId() {
		int vehicleId = Utils.getUserInputInt("Enter the Vehicle ID: ");
		SalesContract contract = salesDao.getByVehicleId(vehicleId);

		if(contract == null) {
			System.out.println("Could not find sales contract for that Vehicle...");
		} else {
			Utils.designLine(50, true, "_");
			contract.print();
		}
		Utils.pauseApp();
	}

	private static void processShowAllLease() {
		List<Contract> contractList = leaseDao.getAll();
		printData(contractList);

		Utils.pauseApp();
	}

	private static void processSearchLeaseByContractId() {
		int contractId = Utils.getUserInputInt("Enter the Lease Contract ID: ");
		LeaseContract contract = leaseDao.getByContractId(contractId);

		if(contract == null) {
			System.out.println("Could not find lease contract with that ID...");
		} else {
			Utils.designLine(50, true, "_");
			contract.print();
		}
		Utils.pauseApp();
	}

	private static void processSearchLeaseByVehicleId() {}

	private static void printData(List<Contract> contractList) {
		Utils.designLine(50, true, "_");

		if(contractList.isEmpty()) {
			System.out.println("There are no contracts to display...");
		} else {
			for(Contract contract : contractList) {
				contract.print();
				Utils.designLine(35, false, "_");
			}

			System.out.println("\nTotal Contracts: " + contractList.size());
		}
	}

}
