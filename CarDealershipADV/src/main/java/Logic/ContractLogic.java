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
				case 2 -> processShowAllLease();
				case 0 -> ifContinue = false;
			}
		}
	}

	private static void processShowAllSales() {
		System.out.println("All sales contracts");
	}

	private static void processShowAllLease() {
		System.out.println("All lease contracts");
	}

}
