package Models;

import java.sql.Date;
import java.time.LocalDate;

public abstract class Contract {

    Date dateOfContract;
    String customerName;
    String customerEmail;
    Vehicle vehicleSold;
    double totalPrice;
    double monthlyPayment;
    Vehicle vehicle;

    public Contract(){}

    public Contract(Date dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold) {
        this.dateOfContract = dateOfContract;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    //region getters and setters
    public Date getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(Date dateOfContract) {
        this.dateOfContract = dateOfContract;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }
    //endregion

    //Abstract methods that will need to be overridden
    abstract double calculateTotalPrice();
    abstract double calculateMonthlyPayment();
	public abstract void print();

}
