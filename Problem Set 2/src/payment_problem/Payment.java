package payment_problem;

public class Payment {
    private double paymentAmount;

    public Payment() {
        paymentAmount = 0.0;
    }

    public Payment(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public void paymentDetails() {
        System.out.println("The payment is $" + paymentAmount);
    }
}
