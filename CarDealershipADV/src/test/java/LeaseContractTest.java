import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeaseContractTest {

    LeaseContract leaseContract = new LeaseContract();
    Vehicle vehicle = new Vehicle(37846, 2021, "Chevrolet", "Silverado", "truck", "Black", 2750, 31995.00);

    @Test
    void calculateExpectedEnding() {
        leaseContract.setVehicleSold(vehicle);

        double actual = leaseContract.calculateExpectedEnding();

        assertEquals(15997.50, actual);
    }

    @Test
    void calculateLeaseFee() {
        leaseContract.setVehicleSold(vehicle);

        double actual = leaseContract.calculateLeaseFee();

        assertEquals(2239.65, actual, 0.01);
    }

    @Test
    void calculateMonthlyPayment() {
        leaseContract.setVehicleSold(vehicle);

        double actual = leaseContract.calculateMonthlyPayment();

        assertEquals(541.39, actual, 0.01);
    }

    @Test
    void calculateTotalPrice() {
        leaseContract.setVehicleSold(vehicle);

        double actual = leaseContract.calculateTotalPrice();

        assertEquals(18337.15, actual, 0.01);
    }
}