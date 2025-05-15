import com.pluralsight.utils.UserPrompt;

import java.util.ArrayList;

public class Dealership {

    String name;
    String address;
    String phoneNumber;
    ArrayList<Vehicle> inventory;

    //Default constructor
    public Dealership() {
    }

    //Overloaded constructor
    public Dealership(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        inventory = new ArrayList<>();
    }

    //region getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    //endregion

    //Method to search vehicles by a price range, and return array list of all vehicles within that price range
    public ArrayList<Vehicle> getVehiclesByPrice(int min, int max) {

        inventory = DealershipFileManager.getInventory();
        ArrayList<Vehicle> vehiclesByPrice = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                vehiclesByPrice.add(vehicle);
            }
        }

        return vehiclesByPrice;
    }

    //Method to search vehicles by Make and model, and return array list of all vehicles that match
    public ArrayList<Vehicle> getVehicleByMakeModel(String make, String model) {

        inventory = DealershipFileManager.getInventory();
        ArrayList<Vehicle> vehiclesByMakeModel = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                vehiclesByMakeModel.add(vehicle);
            }
        }

        return vehiclesByMakeModel;
    }

    //Method to search vehicles by year range, and return array list of all vehicles in that range
    public ArrayList<Vehicle> getVehiclesByYear(int min, int max) {
        inventory = DealershipFileManager.getInventory();
        ArrayList<Vehicle> vehiclesByYear = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle.getYear() >= min && vehicle.getYear() <= max) {
                vehiclesByYear.add(vehicle);
            }
        }
        return vehiclesByYear;
    }

    //Method to search vehicles by color, and return array list of all vehicles that match
    public ArrayList<Vehicle> getVehiclesByColor(String color) {
        inventory = DealershipFileManager.getInventory();
        ArrayList<Vehicle> vehiclesByColor = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (color.equalsIgnoreCase(v.getColor())) {
                vehiclesByColor.add(v);
            }
        }
        return vehiclesByColor;
    }

    //Method to search vehicles by mileage range, and return array list of all vehicles in that range
    public ArrayList<Vehicle> getVehiclesByMileage(int min, int max) {
        inventory = DealershipFileManager.getInventory();
        ArrayList<Vehicle> vehiclesByMileage = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                vehiclesByMileage.add(v);
            }
        }
        return vehiclesByMileage;
    }

    //Method to search vehicles by Vehicle type, and return array list of all vehicles that match
    public ArrayList<Vehicle> getVehiclesByType(String type) {
        inventory = DealershipFileManager.getInventory();
        ArrayList<Vehicle> vehiclesByType = new ArrayList<>();

        for (Vehicle v : inventory) {
            if (v.getVehicleType().equalsIgnoreCase(type)) {
                vehiclesByType.add(v);
            }
        }
        return vehiclesByType;
    }

    //Method that returns all vehicles in the inventory
    public ArrayList<Vehicle> getAllVehicles() {
        inventory = DealershipFileManager.getInventory();
        return inventory;
    }

    //Method to add a vehicle to the inventory, and write it to the car-inventory file
    public void addVehicle(Vehicle vehicle) {
        inventory = DealershipFileManager.getInventory();

        inventory.add(vehicle);
        DealershipFileManager.writeToInventory(inventory);
    }

    //Method that removes a vehicles in the inventory, and removes it from the car-inventory file
    public void removeVehicle(int VIN) {

        inventory = DealershipFileManager.getInventory();
        boolean isCarFound = false;

        for (Vehicle v : inventory) {
            if (v.getVin() == VIN) {
                inventory.remove(v);
                isCarFound = true;
                break;
            }
        }

        if (isCarFound) {
            System.out.println("Success! Car was removed");
            DealershipFileManager.writeToInventory(inventory);
        } else {
            System.err.println("Could not find car with that VIN...");
        }

        UserPrompt.pauseApp();
    }

    //Method to create a lease or sales contract for a vehicle, remove that vehicle from the inventory, and write the contract to the contracts file
    public void leaseSellVehicle(String leaseOrSell, String date, String customerName, String customerEmail, boolean isFinance, int VIN) {

        inventory = DealershipFileManager.getInventory();
        LeaseContract leaseContract = new LeaseContract();
        SalesContract salesContract = new SalesContract();

        boolean isCarFound = false;
        Vehicle vehicle = new Vehicle();

        for (Vehicle v : inventory) {
            if (VIN == v.getVin()) {
                if (leaseOrSell.equalsIgnoreCase("sell")) {
                    salesContract = new SalesContract(date, customerName, customerEmail, v, isFinance);
                } else if (leaseOrSell.equalsIgnoreCase("lease")) {
                    leaseContract = new LeaseContract(date, customerName, customerEmail, v, isFinance);
                }
                vehicle.setColor(v.getColor());
                vehicle.setMake(v.getMake());
                vehicle.setModel(v.getModel());
                vehicle.setVin(v.getVin());

                inventory.remove(v);
                isCarFound = true;
                break;
            }
        }

        if (!isCarFound) {
            System.err.println("ERROR! We could not find a car with that VIN!");
        } else {
            DealershipFileManager.writeToInventory(inventory);

            if (leaseOrSell.equalsIgnoreCase("sell")) {
                ContractFileManager.writeToContractsFile(salesContract);
                System.out.printf("Success! The %s %s %s VIN of %d was sold!",
                        vehicle.getColor(), vehicle.getMake(), vehicle.getModel(), vehicle.getVin());
            } else {
                ContractFileManager.writeToContractsFile(leaseContract);
                System.out.printf("Success! The %s %s %s, VIN of %d, was leased!",
                        vehicle.getColor(), vehicle.getMake(), vehicle.getModel(), vehicle.getVin());
            }
        }
    }
}
