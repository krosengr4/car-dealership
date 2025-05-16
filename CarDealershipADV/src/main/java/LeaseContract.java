public class LeaseContract extends Contract {

    double expectedEndingValue;
    double leaseFee;
    double recordingFee;
    boolean isFinance;

    public LeaseContract() {
    }

    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinance) {
        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.isFinance = isFinance;
    }

    //region getters and setters

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getRecordingFee() {
        return 100;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }
    //endregion

    public double calculateExpectedEnding() {
        return this.vehicleSold.getPrice() * .5;
    }

    public double calculateLeaseFee() {
        return this.vehicleSold.getPrice() * .07;
    }

    @Override
    public double calculateMonthlyPayment() {
        //payment=(r×p)/(1−(1+r)^−n)
        //p = principal
        //r = annualRate/12
        // n = numberOfMonths
        double principal = this.calculateTotalPrice();
        double monthlyRate = .04 / 12;
        double numberOfMonths = 36;

        return (monthlyRate * principal) / (1 - (Math.pow (1 + monthlyRate, -numberOfMonths)));
    }

    @Override
    public double calculateTotalPrice() {
        //totalPrice = (price - expectedEndingValue) + leaseFee + recordingFee
        return (this.vehicleSold.getPrice() - this.calculateExpectedEnding()) + this.calculateLeaseFee() + this.getRecordingFee();
    }
}
