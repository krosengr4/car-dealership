import com.pluralsight.utils.UserPrompt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ContractFileManager {

    public static String filePath = "CarDealershipADV/src/main/resources/contracts.csv";
    public static ArrayList<SalesContract> saleContractsList;
    public static ArrayList<LeaseContract> leaseContractsList;

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
                            + "|" + ((LeaseContract) contract).calculateExpectedEnding() + "|" + ((LeaseContract) contract).calculateLeaseFee()
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

    public static ArrayList<SalesContract> readSaleContracts() {
        saleContractsList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String input;

            while((input = bufferedReader.readLine()) != null) {
                String[] lineParts = input.split("\\|");

                 if(input.isEmpty() || lineParts[0].startsWith("LEASE") ||lineParts[1].startsWith("DATE")) {
                     continue;
                 }

                 String dateOfContract = lineParts[1];
                 String customerName = lineParts[2];
                 String customerEmail = lineParts[3];
                 int vehicleVIN = Integer.parseInt(lineParts[4]);
                 int vehicleYear = Integer.parseInt(lineParts[5]);
                 String vehicleMake = lineParts[6];
                 String vehicleModel = lineParts[7];
                 String vehicleType = lineParts[8];
                 String vehicleColor = lineParts[9];
                 int vehicleOdometer = Integer.parseInt(lineParts[10]);
                 double vehiclePrice = Integer.parseInt(lineParts[11]);
                 boolean isFinanced = Boolean.parseBoolean(lineParts[17]);

                 Vehicle vehicle = new Vehicle(vehicleVIN, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleOdometer, vehiclePrice);

                 SalesContract salesContract = new SalesContract(dateOfContract, customerName, customerEmail, vehicle, isFinanced);

                 saleContractsList.add(salesContract);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return saleContractsList;
    }

    public static ArrayList<LeaseContract> readLeaseContracts() {
        leaseContractsList = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String input;

            while((input = bufferedReader.readLine()) != null) {
                String[] lineParts = input.split("\\|");

                if (input.isEmpty() || lineParts[0].startsWith("SALE") || lineParts[1].startsWith("DATE")) {
                    continue;
                }

                String dateOfContract = lineParts[1];
                String customerName = lineParts[2];
                String customerEmail = lineParts[3];
                int vehicleVIN = Integer.parseInt(lineParts[4]);
                int vehicleYear = Integer.parseInt(lineParts[5]);
                String vehicleMake = lineParts[6];
                String vehicleModel = lineParts[7];
                String vehicleType = lineParts[8];
                String vehicleColor = lineParts[9];
                int vehicleOdometer = Integer.parseInt(lineParts[10]);
                double vehiclePrice = Integer.parseInt(lineParts[11]);

                Vehicle vehicle = new Vehicle(vehicleVIN, vehicleYear, vehicleMake, vehicleModel, vehicleType, vehicleColor, vehicleOdometer, vehiclePrice);
                LeaseContract leaseContract = new LeaseContract(dateOfContract, customerName, customerEmail, vehicle);

                leaseContractsList.add(leaseContract);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return leaseContractsList;
    }
}
