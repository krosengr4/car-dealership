package Logic;

import UI.UserInterface;

public class ContractLogic {

	static UserInterface ui = new UserInterface();

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
		System.out.println("All sales contracts");
	}

	private static void processSearchSalesByContractId() {
		System.out.println("Search by contract ID");
	}

	private static void processSearchSalesByVehicleId() {}

	private static void processShowAllLease() {
		System.out.println("All lease contracts");
	}

	private static void processSearchLeaseByContractId() {}

	private static void processSearchLeaseByVehicleId() {}

}
