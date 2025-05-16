import com.pluralsight.utils.*;

public class Main {

    public static void main(String[] args) {

        Vehicle vehicle = new Vehicle(213453, 2022, "Honda", "Accord", "Sport", "Red", 12345, 10000);
        SalesContract contract = new SalesContract();
        contract.setVehicleSold(vehicle);
        System.out.printf("Recording: %.2f Sales Tax: %.2f Processing: %.2f", contract.getRecordingFee(), contract.calculateSalesTax(), contract.calculateProcessingFee());

        System.out.println("\n\t\t___________" + Symbols.cowboy + "WELCOME_TO_CODEO_CARS" + Symbols.cowboy + "___________");
        Design.designLine(70, true);

        UserInterface userInterface = new UserInterface();
        userInterface.display();

        System.out.println("\n\nThank you for using Codeo Cars!\nSee you next time!" + Symbols.smileyFace);

    }

}
