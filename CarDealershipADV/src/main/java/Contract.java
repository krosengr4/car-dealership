public abstract class Contract {

    String dateOfContract;
    String customerName;
    String customerEmail;
    String vehicleSold;
    double totalPrice;
    double monthlyPayment;

    public Contract(){}

    public Contract(String dateOfContract, String customerName, String customerEmail, String vehicleSold) {
        this.dateOfContract = dateOfContract;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleSold = vehicleSold;
    }

    //region getters and setters
    public String getDateOfContract() {
        return dateOfContract;
    }

    public void setDateOfContract(String dateOfContract) {
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

    public String getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(String vehicleSold) {
        this.vehicleSold = vehicleSold;
    }
    //endregion

    //Abstract methods that will need to be overridden
    abstract double calculateTotalPrice();
    abstract double calculateMonthlyPayment();

}
