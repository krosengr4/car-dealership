package Logic;

import UI.UserInterface;

public class AdminLogic {

	static UserInterface ui = new UserInterface();

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
		System.out.println("Add vehicle");
	}

	private static void processUpdateVehicle() {
		System.out.println("Process Update vehicle");
	}

	private static void processDeleteVehicle() {
		System.out.println("Delete a vehicle");
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
