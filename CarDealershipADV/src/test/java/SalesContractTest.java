import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SalesContractTest {

    SalesContract salesContract = new SalesContract();
    Vehicle vehicle = new Vehicle(10112, 1993, "Ford", "Explorer", "SUV", "Red", 525123, 995.00);


    @Test
    void calculateSalesTax() {
        salesContract.setVehicleSold(vehicle);
        salesContract.setFinance(false);

        double actual = salesContract.calculateSalesTax();

        assertEquals(49.75, actual, 0.1);
    }

    @Test
    void calculateProcessingFee() {
        salesContract.setVehicleSold(vehicle);
        salesContract.setFinance(false);

        double actual = salesContract.calculateProcessingFee();

        assertEquals(295, actual);
    }

    @Test
    void calculateMonthlyPayment() {
        salesContract.setVehicleSold(vehicle);
        salesContract.setFinance(false);

        double actual = salesContract.calculateMonthlyPayment();

        assertEquals(0.00, actual, .01);
    }

    @Test
    void calculateTotalPrice() {
        salesContract.setVehicleSold(vehicle);
        salesContract.setFinance(false);

        double actual = salesContract.calculateTotalPrice();

        assertEquals(1439.75, actual, 0.01);

    }
}