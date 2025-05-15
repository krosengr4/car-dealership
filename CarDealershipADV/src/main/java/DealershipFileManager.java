import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DealershipFileManager {

    public static String filePath = "CarDealershipADV/src/main/resources/car-inventory.csv";
    public static ArrayList<Vehicle> inventory;

    public Dealership getDealership() {
        Dealership dealership = new Dealership();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));

            String input;
            while ((input = bufferedReader.readLine()) != null) {
                String[] lineParts = input.split("\\|");

                if (lineParts.length > 3 || input.isEmpty()) {
                    continue;
                }

                dealership.setName(lineParts[0]);
                dealership.setAddress(lineParts[1]);
                dealership.setPhoneNumber(lineParts[2]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return dealership;
    }

    public static ArrayList<Vehicle> getInventory() {
        inventory = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(DealershipFileManager.filePath));
            String input;

            while ((input = bufferedReader.readLine()) != null) {
                String[] lineParts = input.split("\\|");

                if (lineParts.length < 5 || input.isEmpty()) {
                    continue;
                }
                int vin = Integer.parseInt(lineParts[0]);
                int year = Integer.parseInt(lineParts[1]);
                String make = lineParts[2];
                String model = lineParts[3];
                String vehicleType = lineParts[4];
                String color = lineParts[5];
                int odometer = Integer.parseInt(lineParts[6]);
                double price = Double.parseDouble(lineParts[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                inventory.add(vehicle);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return inventory;
    }

    public void saveDealership(Dealership dealership) {
        System.out.println("Save Dealership");
    }

    public static void writeToFile(ArrayList<Vehicle> inventory) {

        try {
            FileWriter writer = new FileWriter(filePath);

            writer.write("Codeo Cars|2834 Old Codeo Cv|512-685-2489\n");

            for (Vehicle v : inventory) {
                writer.write(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" + v.getVehicleType()
                        + "|" + v.getColor() + "|" + v.getOdometer() + "|" + v.getPrice() + "\n");
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
