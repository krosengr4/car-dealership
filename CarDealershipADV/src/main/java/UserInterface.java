import com.pluralsight.utils.*;

import java.util.ArrayList;

public class UserInterface {

    Dealership dealership;

    //Method to process a users choice from the main menu, and call the corresponding method
    public void display() {
        boolean ifContinue = true;

        while (ifContinue) {
            String userMenuChoice = menuChoice();
            init();

            switch (userMenuChoice) {
                case "1" -> processGetByPriceRequest();
                case "2" -> processGetByMakeModel();
                case "3" -> processGetByYear();
                case "4" -> processGetByColor();
                case "5" -> processGetByOdometer();
                case "6" -> processGetByType();
                case "7" -> processAllVehiclesRequest();
                case "8" -> processAddAVehicle();
                case "9" -> processRemoveAVehicle();
                case "10" -> displaySellLease();
                case "00" -> displayAdminScreen();
                case "99" -> ifContinue = false;
                default -> System.err.println("ERROR! Please enter a number between 1 - 9, or 99 to exit!");
            }
        }
    }

    //Method to display the main menu and prompt user for a choice
    private String menuChoice() {
        System.out.println("\n\t\t\t\t\t\tMAIN MENU");
        Design.designLine(70, false);
        System.out.println("""
                1 - Find Car by Price               6 - Find Cars by Vehicle Type
                2 - Find Car by Make and Model      7 - Show All Vehicles
                3 - Find Car by Year                8 - Add a Vehicle
                4 - Find Car by Color               9 - Remove a Vehicle
                5 - Find Car by Mileage             10 - Sell or Lease a Vehicle
                                        99 - Exit
                                        00 - Admin
                """);

        return UserIO.promptGetUserInput("What would you like to do?: ").trim();
    }

    //Method to instantiate a DealershipFileManager object and initialize the dealership
    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        this.dealership = dfm.getDealership();
    }

    //Method to display the list of vehicles based on user request
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("There are no vehicles...");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.printf("VIN: %d | Year: %d | Make: %s | Model: %s | Color: %s | Total Mileage: %d " +
                                "| Price: $%.2f\n", vehicle.getVin(), vehicle.getYear(), vehicle.getMake(), vehicle.getModel(),
                        vehicle.getColor(), vehicle.getOdometer(), vehicle.getPrice());
            }
        }
        UserIO.pauseApp();
    }

    //Method to process a users request to display all vehicles
    private void processAllVehiclesRequest() {
        ArrayList<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    //Method to allow user to search vehicles based on a price range
    private void processGetByPriceRequest() {
        int userMin = Integer.parseInt(UserIO.promptGetUserInput("Enter the minimum price: ").trim());
        int userMax = Integer.parseInt(UserIO.promptGetUserInput("Enter the maximum price: ").trim());

        ArrayList<Vehicle> priceRangeVehicles = dealership.getVehiclesByPrice(userMin, userMax);

        displayVehicles(priceRangeVehicles);
    }

    //Method to allow user to search vehicles based on make and model
    private void processGetByMakeModel() {
        String userMake = UserIO.promptGetUserInput("Enter the make of the vehicle: ").trim();
        String userModel = UserIO.promptGetUserInput("Enter the model of the vehicle: ").trim();

        ArrayList<Vehicle> vehiclesByMakeModel = dealership.getVehicleByMakeModel(userMake, userModel);

        displayVehicles(vehiclesByMakeModel);
    }

    //Method to allow user to search vehicles based on a year range
    private void processGetByYear() {
        int yearMin = Integer.parseInt(UserIO.promptGetUserInput("Enter the minimum year: ").trim());
        int yearMax = Integer.parseInt(UserIO.promptGetUserInput("Enter the maximum year: ").trim());

        ArrayList<Vehicle> vehiclesByYear = dealership.getVehiclesByYear(yearMin, yearMax);

        displayVehicles(vehiclesByYear);
    }

    //Method to allow user to search vehicles based on color
    private void processGetByColor() {
        String userColor = UserIO.promptGetUserInput("Enter a color: ").trim();

        ArrayList<Vehicle> vehiclesByColor = dealership.getVehiclesByColor(userColor);
        displayVehicles(vehiclesByColor);
    }

    //Method to allow user to search vehicles based on odometer range
    private void processGetByOdometer() {
        int odometerMin = Integer.parseInt(UserIO.promptGetUserInput("Enter the minimum amount of miles: ").trim());
        int odometerMax = Integer.parseInt(UserIO.promptGetUserInput("Enter the maximum amount of miles: ").trim());

        ArrayList<Vehicle> vehiclesByOdometer = dealership.getVehiclesByMileage(odometerMin, odometerMax);
        displayVehicles(vehiclesByOdometer);
    }

    //Method to allow user to search vehicles based on the vehicle type
    private void processGetByType() {
        String userType = UserIO.promptGetUserInput("Enter the type of vehicle (SUV, Truck, etc...): ");

        ArrayList<Vehicle> vehiclesByType = dealership.getVehiclesByType(userType);
        displayVehicles(vehiclesByType);
    }

    //Method to allow user to add a vehicle
    private void processAddAVehicle() {
        int newVIN = Integer.parseInt(UserIO.promptGetUserInput("Enter the VIN of the new vehicle: ").trim());
        int newYear = Integer.parseInt(UserIO.promptGetUserInput("Enter the year of the new vehicle: ").trim());
        String newMake = UserIO.promptGetUserInput("Enter the make of the new vehicle: ").trim();
        String newModel = UserIO.promptGetUserInput("Enter the model of the new vehicle: ").trim();
        String newType = UserIO.promptGetUserInput("Enter the type of the new vehicle: ").trim();
        String newColor = UserIO.promptGetUserInput("Enter the color of the new vehicle: ").trim();
        int newOdometer = Integer.parseInt(UserIO.promptGetUserInput("Enter the odometer reading of the new vehicle: ").trim());
        double newPrice = Double.parseDouble(UserIO.promptGetUserInput("Enter the price of the new vehicle: ").trim());

        Vehicle newVehicle = new Vehicle(newVIN, newYear, newMake, newModel, newType, newColor, newOdometer, newPrice);

        dealership.addVehicle(newVehicle);
    }

    //Method to allow user to remove a vehicle based on VIN
    private void processRemoveAVehicle() {
        int userRemove = Integer.parseInt(UserIO.promptGetUserInput("Please enter the VIN of the Vehicle you'd like to remove: ").trim());

        dealership.removeVehicle(userRemove);
    }

    //Method to options to sell a vehicle or lease a vehicle
    private void displaySellLease() {
        System.out.println("Sell or Lease?\n1 - Sell\n2 - Lease");
        String userChoice = UserIO.promptGetUserInput("Enter a number: ").trim();

        switch (userChoice) {
            case "1" -> processSellVehicle();
            case "2" -> processLeaseVehicle();
            default -> System.err.println("ERROR! Please enter 1 or 2!");
        }
    }

    //Method to allow user to sell a vehicle to a customer based on VIN
    private void processSellVehicle() {
        String date = DateTime.getLocalDate();
        String customerName = UserIO.promptGetUserInput("Enter customers full name: ").trim();
        String customerEmail = UserIO.promptGetUserInput("Enter customers email: ").trim();
        String customerFinance = UserIO.promptGetUserInput("Does the customer want to finance (Y or N)?: ").trim();
        int customerVIN = Integer.parseInt(UserIO.promptGetUserInput("Enter the VIN of the vehicle you want to sell: ").trim());

        boolean isFinance = customerFinance.equalsIgnoreCase("y");

        dealership.sellVehicle(date, customerName, customerEmail, isFinance, customerVIN);
    }

    //Method to allow user to lease a vehicle to a customer based on VIN
    private void processLeaseVehicle() {
        String date = DateTime.getLocalDate();
        String customerName = UserIO.promptGetUserInput("Enter customers full name: ").trim();
        String customerEmail = UserIO.promptGetUserInput("Enter customers email: ").trim();
        int customerVIN = Integer.parseInt(UserIO.promptGetUserInput("Enter the VIN of the vehicle you would like to lease: "));

        dealership.leaseVehicle(date, customerName, customerEmail, customerVIN);
    }

    //Method to display list of contracts passed in
    private void displayContracts(ArrayList<Contract> contracts) {
        for (Contract c : contracts) {
            if (c instanceof SalesContract) {
                System.out.printf(
                        """
                                SALE|DATE|NAME|EMAIL|VVIN|VYEAR|VMAKE|VMODEL|VTYPE|VCOLOR|VODOMETER|VPRICE|SALESTAX|RECORDINGFEE|PROCESSINGFEE|TOTALPRICE|ISFINANCED|MONTHLYPAYMENT
                                SALE | %s | %s | %s | %d | %d | %s | %s | %s | %s | %d | %.2f | %.2f | %.2f | %.2f | %b | %.2f
                                """, c.getDateOfContract(), c.getCustomerName(), c.getCustomerEmail(), c.getVehicleSold().getVin(), c.getVehicleSold().getYear(),
                        c.getVehicleSold().getMake(), c.getVehicleSold().getModel(), c.getVehicleSold().getVehicleType(), c.getVehicleSold().getColor(),
                        c.getVehicleSold().getOdometer(), c.getVehicleSold().getPrice(), ((SalesContract) c).calculateSalesTax(),
                        ((SalesContract) c).getRecordingFee(), c.calculateTotalPrice(), ((SalesContract) c).isFinance(), c.calculateMonthlyPayment());
            } else if (c instanceof LeaseContract) {
                System.out.printf(
                        """
                                LEASE|DATE|NAME|EMAIL|VVIN|VYEAR|VMAKE|VMODEL|VTYPE|VCOLOR|VODOMETER|VPRICE|EXPECTEDENDING|LEASEFEE|TOTALPRICE|MONTHLYPAYMENT
                                LEASE | %s | %s | %s | %d | %d | %s | %s | %s | %s | %d | %.2f | %.2f | %.2f | %.2f | %.2f
                                """, c.getDateOfContract(), c.getCustomerName(), c.getCustomerEmail(), c.getVehicleSold().getVin(), c.getVehicleSold().getYear(),
                        c.getVehicleSold().getMake(), c.getVehicleSold().getModel(), c.getVehicleSold().getVehicleType(), c.getVehicleSold().getColor(),
                        c.getVehicleSold().getOdometer(), c.getVehicleSold().getPrice(), ((LeaseContract) c).calculateExpectedEnding(),
                        ((LeaseContract) c).calculateLeaseFee(), c.calculateTotalPrice(), c.calculateMonthlyPayment());

            }
        }
        UserIO.pauseApp();
    }

    //Method to display The Admin Screen
    private void displayAdminScreen() {

        String getPassword = UserIO.promptGetUserInput("Please Enter Admin Password: ");

        if (getPassword.equals("Hotdogs")) {
            System.out.println("""
                    
                    \t\tOPTIONS:
                    1 - Display All Contracts
                    2 - Display Sales Contracts
                    3 - Display Lease Contracts
                    4 - Back to Main Menu""");
            String adminScreenChoice = UserIO.promptGetUserInput("Enter your choice: ").trim();

            processAdminScreenChoice(adminScreenChoice);

        } else {
            System.err.println("That password is incorrect!");
        }
    }

    //method to process the users choice from admin screen
    private void processAdminScreenChoice(String userAdminChoice) {
        switch (userAdminChoice) {
            case "1":
                ArrayList<Contract> allContracts = dealership.getAllContracts();
                displayContracts(allContracts);
                break;
            case "2":
                ArrayList<Contract> salesContracts = dealership.getSalesContracts();
                displayContracts(salesContracts);
                break;
            case "3":
                ArrayList<Contract> leaseContracts = dealership.getLeaseContracts();
                displayContracts(leaseContracts);
                break;
        }
    }
}
