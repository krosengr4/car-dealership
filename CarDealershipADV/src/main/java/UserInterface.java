import com.pluralsight.utils.*;

import java.util.ArrayList;

public class UserInterface {

    Dealership dealership;

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
                case "99" -> ifContinue = false;
                default -> System.err.println("ERROR! Please enter a number between 1 - 9, or 99 to exit!");
            }
        }
    }

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
                """);

        return UserPrompt.promptGetUserInput("What would you like to do?: ").trim();
    }

    private void init() {
        DealershipFileManager dfm = new DealershipFileManager();
        this.dealership = dfm.getDealership();
    }

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
        UserPrompt.pauseApp();
    }

    private void processAllVehiclesRequest() {
        ArrayList<Vehicle> allVehicles = dealership.getAllVehicles();
        displayVehicles(allVehicles);
    }

    private void processGetByPriceRequest() {
        int userMin = Integer.parseInt(UserPrompt.promptGetUserInput("Enter the minimum price: ").trim());
        int userMax = Integer.parseInt(UserPrompt.promptGetUserInput("Enter the maximum price: ").trim());

        ArrayList<Vehicle> priceRangeVehicles = dealership.getVehiclesByPrice(userMin, userMax);

        displayVehicles(priceRangeVehicles);
    }

    private void processGetByMakeModel() {
        String userMake = UserPrompt.promptGetUserInput("Enter the make of the vehicle: ").trim();
        String userModel = UserPrompt.promptGetUserInput("Enter the model of the vehicle: ").trim();

        ArrayList<Vehicle> vehiclesByMakeModel = dealership.getVehicleByMakeModel(userMake, userModel);

        displayVehicles(vehiclesByMakeModel);
    }

    private void processGetByYear() {
        int yearMin = Integer.parseInt(UserPrompt.promptGetUserInput("Enter the minimum year: ").trim());
        int yearMax = Integer.parseInt(UserPrompt.promptGetUserInput("Enter the maximum year: ").trim());

        ArrayList<Vehicle> vehiclesByYear = dealership.getVehiclesByYear(yearMin, yearMax);

        displayVehicles(vehiclesByYear);
    }

    private void processGetByColor() {
        String userColor = UserPrompt.promptGetUserInput("Enter a color: ").trim();

        ArrayList<Vehicle> vehiclesByColor = dealership.getVehiclesByColor(userColor);
        displayVehicles(vehiclesByColor);
    }

    private void processGetByOdometer() {
        int odometerMin = Integer.parseInt(UserPrompt.promptGetUserInput("Enter the minimum amount of miles: ").trim());
        int odometerMax = Integer.parseInt(UserPrompt.promptGetUserInput("Enter the maximum amount of miles: ").trim());

        ArrayList<Vehicle> vehiclesByOdometer = dealership.getVehiclesByMileage(odometerMin, odometerMax);
        displayVehicles(vehiclesByOdometer);
    }

    private void processGetByType() {
        String userType = UserPrompt.promptGetUserInput("Enter the type of vehicle (SUV, Truck, etc...): ");

        ArrayList<Vehicle> vehiclesByType = dealership.getVehiclesByType(userType);
        displayVehicles(vehiclesByType);
    }

    private void processAddAVehicle() {
        int newVIN = Integer.parseInt(UserPrompt.promptGetUserInput("Enter the VIN of the new vehicle: ").trim());
        int newYear = Integer.parseInt(UserPrompt.promptGetUserInput("Enter the year of the new vehicle: ").trim());
        String newMake = UserPrompt.promptGetUserInput("Enter the make of the new vehicle: ").trim();
        String newModel = UserPrompt.promptGetUserInput("Enter the model of the new vehicle: ").trim();
        String newType = UserPrompt.promptGetUserInput("Enter the type of the new vehicle: ").trim();
        String newColor = UserPrompt.promptGetUserInput("Enter the color of the new vehicle: ").trim();
        int newOdometer = Integer.parseInt(UserPrompt.promptGetUserInput("Enter the odometer reading of the new vehicle: ").trim());
        double newPrice = Double.parseDouble(UserPrompt.promptGetUserInput("Enter the price of the new vehicle: ").trim());

        Vehicle newVehicle = new Vehicle(newVIN, newYear, newMake, newModel, newType, newColor, newOdometer, newPrice);

        dealership.addVehicle(newVehicle);
    }

    private void processRemoveAVehicle() {
        int userRemove = Integer.parseInt(UserPrompt.promptGetUserInput("Please enter the VIN of the Vehicle you'd like to remove: ").trim());

        dealership.removeVehicle(userRemove);
    }

    private void displaySellLease() {
        System.out.println("Sell or Lease?\n1 - Sell\n2 - Lease");
        String userChoice = UserPrompt.promptGetUserInput("Enter a number: ").trim();

        switch (userChoice) {
            case "1", "2" -> processSellLeaseVehicle(userChoice);
            default -> System.err.println("ERROR! Please enter 1 or 2!");
        }
    }

    private void processSellLeaseVehicle(String userChoice) {
        String date = DateTime.getLocalDate();
        String customerName = UserPrompt.promptGetUserInput("Enter customers full name: ").trim();
        String customerEmail = UserPrompt.promptGetUserInput("Enter customers email: ").trim();

        String customerFinance = UserPrompt.promptGetUserInput("Does the customer want to finance (Y or N)?: ").trim();
        boolean isFinance = false;
        boolean isContinue = true;

        while (isContinue) {
            if (customerFinance.equalsIgnoreCase("y")) {
                isFinance = true;
                isContinue = false;
            } else if (customerFinance.equalsIgnoreCase("n")) {
                isContinue = false;
            } else {
                System.err.println("ERROR! Please enter y or n!");
            }
        }

        int customerVIN = Integer.parseInt(UserPrompt.promptGetUserInput("Enter the VIN of the vehicle you want to sell: ").trim());
        String leaseOrSell = "";

        switch (userChoice) {
            case "1":
                leaseOrSell = "sell";
                dealership.leaseSellVehicle(leaseOrSell, date, customerName, customerEmail, isFinance, customerVIN);
                break;
            case "2":
                leaseOrSell = "lease";
                dealership.leaseSellVehicle(leaseOrSell, date, customerName, customerEmail, isFinance, customerVIN);
                break;
        }
    }
}
