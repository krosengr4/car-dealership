public class LeaseContract extends Contract{

    double expectedEndingValue;
    double leaseFee;
    boolean isFinance;

    public LeaseContract(){}

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.isFinance = isFinance;
    }

    //region getters and setters
    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
    //endregion

    @Override
    public double calculateMonthlyPayment() {
        return 0;
    }

    @Override
    public double calculateTotalPrice() {
        return 0;
    }
}
