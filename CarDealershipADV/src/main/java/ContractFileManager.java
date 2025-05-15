import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public static String filePath = "CarDealershipADV/src/main/resources/contracts.csv";


    public static void writeSalesToContracts(SalesContract contract) {

        Vehicle vehicle = contract.getVehicleSold();

        try {
            FileWriter writer = new FileWriter(filePath, true);

            writer.write("SALE" + "|" + contract.getDateOfContract() + "|" + contract.getCustomerName() + "|"
            + contract.getCustomerEmail() + "|" + vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() +
                    "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() +
                    "|" + vehicle.getPrice() + "|" + contract.calculateTotalPrice() + "|" + contract.calculateMonthlyPayment() + "\n");


            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
