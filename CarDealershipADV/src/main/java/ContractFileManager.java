import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public static String filePath = "CarDealershipADV/src/main/resources/contracts.csv";


    public static void writeToContractsFile(Contract contract) {

        Vehicle vehicle = contract.getVehicleSold();

        String typeOfContract = null;
        if (contract instanceof SalesContract) {
            typeOfContract = "SALE";
        } else if (contract instanceof LeaseContract) {
            typeOfContract = "LEASE";
        }

        try {
            FileWriter writer = new FileWriter(filePath, true);

            writer.write(typeOfContract + "|" + contract.getDateOfContract() + "|" + contract.getCustomerName() + "|"
            + contract.getCustomerEmail() + "|" + vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() +
                    "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() +
                    "|" + vehicle.getPrice() + "|" + contract.calculateTotalPrice() + "|" + contract.calculateMonthlyPayment() + "\n");


            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
