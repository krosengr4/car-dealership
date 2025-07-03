package Models;

import java.sql.Date;
import java.time.LocalDate;

public class SalesContract extends Contract implements Printable{

    double salesTax;
    double recordingFee;
    double processingFee;
    boolean isFinance;

    public SalesContract() {}

    public SalesContract(Date dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinance) {

        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.isFinance = isFinance;
    }

    //region getters and setters
    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return 100.00;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return isFinance;
    }

    public void setFinance(boolean finance) {
        isFinance = finance;
    }
    //endregion

    public double calculateSalesTax() {
        return vehicleSold.getPrice() * .05;
    }

    public double calculateProcessingFee() {
        if (this.vehicleSold.getPrice() < 10000.00) {
            return 295.00;
        } else {
            return  495.00;
        }
    }

    @Override
    public double calculateMonthlyPayment() {
        //M = P [ i(1 + i)^n ] / [ (1 + i)^n-1
        //P = loan amount
        //i = monthly interest rate
        //n = number of months
        double loanAmount = this.calculateTotalPrice();

        if (this.isFinance && this.vehicleSold.getPrice() >= 10000) {
            double monthlyInterest = .0425 / 12;
            double numberOfMonths = 48;

            return loanAmount * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfMonths) / (Math.pow(1 + monthlyInterest, numberOfMonths) - 1));
        } else if (this.isFinance && this.vehicleSold.getPrice() < 10000) {
            double monthlyInterest = .0525 / 12;
            double numberOfMonths = 24;

            return loanAmount * (monthlyInterest * Math.pow(1 + monthlyInterest, numberOfMonths) / (Math.pow(1 + monthlyInterest, numberOfMonths) - 1));
        } else {
            return 0.00;
        }
    }

    @Override
    public double calculateTotalPrice() {

        return this.getVehicleSold().getPrice() + this.calculateSalesTax() + this.getRecordingFee() + this.calculateProcessingFee();

    }

	@Override
	public void print() {
		System.out.println("-----SALES CONTRACT-----");
		System.out.println("Date of Contract: " + this.dateOfContract);
		System.out.println("Customer Name: " + this.customerName);
		System.out.println("Vehicle Sold: " + this.vehicleSold.year + ", " + this.vehicleSold.make + " " + this.vehicleSold.model);
		System.out.printf("Sales Tax: $%.2f\n", this.calculateSalesTax());
		if(this.isFinance)
			System.out.printf("Monthly Payment: $%.2f\n", this.calculateMonthlyPayment());
		else
			System.out.println("This Vehicle was not Financed.");
		System.out.printf("Total Price: $%.2f\n", this.calculateTotalPrice());
	}
}
