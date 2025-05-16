import com.pluralsight.utils.UserPrompt;

import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public static String filePath = "CarDealershipADV/src/main/resources/contracts.csv";

    public static void writeToContractsFile(Contract contract) {

        Vehicle vehicle = contract.getVehicleSold();
        String contractAttributes = null;

        if (contract instanceof SalesContract) {
            contractAttributes =
                    "SALE" + "|" + contract.getDateOfContract() + "|" + contract.getCustomerName() + "|" + contract.getCustomerEmail()
                            + "|" + "|" + vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel() + "|"
                            + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice() + "|"
                            + ((SalesContract) contract).calculateSalesTax() + "|" + ((SalesContract) contract).getRecordingFee() + "|"
                            + ((SalesContract) contract).calculateProcessingFee() + "|" + contract.calculateTotalPrice() + "|" + ((SalesContract) contract).isFinance
                            + "|" + contract.calculateMonthlyPayment();

        } else if (contract instanceof LeaseContract) {
            contractAttributes =
                    "LEASE" + "|" + contract.getDateOfContract() + "|" + contract.getCustomerName() + "|" + contract.getCustomerEmail()
                            + "|" + vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() + "|" + vehicle.getModel() + "|"
                            + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() + "|" + vehicle.getPrice()
                            + "|" + ((LeaseContract) contract).getExpectedEndingValue() + "|" + ((LeaseContract) contract).getLeaseFee()
                            + "|" + contract.calculateTotalPrice() + "|" + contract.calculateMonthlyPayment();
        }

        try {
            FileWriter writer = new FileWriter(filePath, true);

            if (contractAttributes != null) {
                writer.write("\n" + contractAttributes);
                writer.close();
                System.out.printf("%s %s %s, VIN of %d. \nThe contract was recorded in the contracts.csv file.\n",
                        vehicle.getColor(), vehicle.getMake(),vehicle.getModel(), vehicle.getVin());
            } else {
                System.err.println("ERROR! This contract is null!!!");
            }
            UserPrompt.pauseApp();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
