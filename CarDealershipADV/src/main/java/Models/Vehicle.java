package Models;

public class Vehicle implements Printable{

	int vehicleId;
    String vin;
    int year;
    String make;
    String model;
    String color;
    String vehicleType;
    int odometer;
    double price;
	boolean isSold;

    public Vehicle(){}

    public Vehicle(int vehicleId, String vin, int year, String make, String model, String vehicleType, String color, int odometer, double price, boolean isSold) {
		this.vehicleId = vehicleId;
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
		this.isSold = isSold;
    }

    //region Getters and Setters
	public int getVehicleId() { return vehicleId; }

	public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getOdometer() {
        return odometer;
    }

    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

	public boolean isSold() { return isSold; }

	public void setIsSold(boolean isSold) {
		this.isSold = isSold;
	}
    //endregion

	@Override
	public void print() {
		System.out.println("-----VEHICLE-----");
		System.out.println("Vehicle ID: " + this.vehicleId);
		System.out.println("VIN: " + this.vin);
		System.out.println("Year: " + this.year);
		System.out.println("Make: " + this.make);
		System.out.println("Model: " + this.model);
		System.out.println("Type: " + this.vehicleType);
		System.out.println("Color: " + this.color);
		System.out.println("Odometer: " + this.odometer);
		System.out.println("Price: $" + this.price);
		if(this.isSold) {
			System.out.println("This vehicle has been sold!");
		}
	}

}
