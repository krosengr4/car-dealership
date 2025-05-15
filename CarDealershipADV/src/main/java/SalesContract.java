public class SalesContract extends Contract {

    double salesTax;
    double recordingFee;
    double processingFee;
    boolean isFinance;

    public SalesContract() {}

    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicleSold, boolean isFinance) {

        super(dateOfContract, customerName, customerEmail, vehicleSold);
        this.salesTax = .05;
        this.recordingFee = 100.00;
        this.isFinance = isFinance;
    }

    //region getters and setters
    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
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

    @Override
    public double calculateMonthlyPayment() {
        return 0;
    }

    @Override public double calculateTotalPrice() {
        return 0;
    }
}
