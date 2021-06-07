package payment_problem;

public class CashPayment extends Payment {

    public CashPayment() {
        super();
    }

    public CashPayment(double paymentAmount) {
        super(paymentAmount);
    }

    @Override
    public void paymentDetails() {
        System.out.println("The payment made in cash is $" + getPaymentAmount());
    }
}
