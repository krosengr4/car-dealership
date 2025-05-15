import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public static String filePath = "CarDealershipADV/src/main/resources/contracts.csv";


    public static void writeToContractsFile(Contract contract) {

        Vehicle vehicle = contract.getVehicleSold();

        String typeOfContract = null;
        String contractAttributes = null;

        if (contract instanceof SalesContract) {
            typeOfContract = "SALE";

            contractAttributes = ((SalesContract) contract).getSalesTax() + "|" + ((SalesContract) contract).getRecordingFee()
                    + "|" + ((SalesContract) contract).getProcessingFee() + "|" + contract.calculateTotalPrice() + "|"
                    + ((SalesContract) contract).isFinance();

        } else if (contract instanceof LeaseContract) {
            typeOfContract = "LEASE";
            contractAttributes = ((LeaseContract) contract).getExpectedEndingValue() + "|" + ((LeaseContract) contract).getLeaseFee()
                    + "|" + contract.calculateTotalPrice();
        }

        try {
            FileWriter writer = new FileWriter(filePath, true);

            writer.write(typeOfContract + "|" + contract.getDateOfContract() + "|" + contract.getCustomerName() + "|"
            + contract.getCustomerEmail() + "|" + vehicle.getVin() + "|" + vehicle.getYear() + "|" + vehicle.getMake() +
                    "|" + vehicle.getModel() + "|" + vehicle.getVehicleType() + "|" + vehicle.getColor() + "|" + vehicle.getOdometer() +
                    "|" + vehicle.getPrice() + "|" + contractAttributes + "|" + "|" + contract.calculateMonthlyPayment() + "\n");


            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
