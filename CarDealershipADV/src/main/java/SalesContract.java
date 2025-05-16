public class SalesContract extends Contract {

    double salesTax;
    double recordingFee;
    double processingFee;
    boolean isFinance;

    public SalesContract() {}

    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinance) {

        super(dateOfContract, customerName, customerEmail, vehicleSold);
//        this.salesTax = .05;
        this.isFinance = isFinance;
//        this.salesTax = vehicleSold.getPrice() * .05;
    }

    //region getters and setters
//    public void setSalesTax(double salesTax) {
//        this.salesTax = vehicleSold.getPrice() * .05;
//    }

    public double getRecordingFee() {
        return 100.00;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

//    public double getProcessingFee() {
//        return processingFee;
//    }

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
        //M = P [ i(1 + i)^n ] / [ (1 + i)^n
        //P = loan amount
        //i = monthly interest rate
        //n = number of months
        if (this.isFinance && this.vehicleSold.getPrice() >= 10000) {
            return calculateTotalPrice() * (.0425 * Math.pow((1 + .0425), 48)) / (Math.pow(1 + .0425, 48));
        } else if (this.isFinance && this.vehicleSold.getPrice() < 10000) {
            return calculateTotalPrice() * (.0525 * Math.pow((1 + .0525), 24)) / (Math.pow(1 + .0525, 24));
        } else {
            return 0.00;
        }
    }

    @Override
    public double calculateTotalPrice() {

        return this.calculateSalesTax() + this.getRecordingFee() + this.calculateProcessingFee();

    }
}
