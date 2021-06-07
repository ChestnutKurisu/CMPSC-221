package payment_problem;

public class CreditCardPayment extends Payment {
    private String cardholderName, expirationDate, creditCardNumber;

    public CreditCardPayment() {
        super();
        cardholderName = "John Smith";
        expirationDate = "12/31/2099";
        creditCardNumber = "0000 0000 0000 0000";
    }

    public CreditCardPayment(String cardholderName, String expirationDate, String creditCardNumber) {
        super();
        this.cardholderName = cardholderName;
        this.expirationDate = expirationDate;
        this.creditCardNumber = creditCardNumber;
    }

    public CreditCardPayment(double paymentAmount, String cardholderName, String expirationDate, String creditCardNumber) {
        super(paymentAmount);
        this.cardholderName = cardholderName;
        this.expirationDate = expirationDate;
        this.creditCardNumber = creditCardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public void paymentDetails() {
        System.out.printf("Cardholder's Name: %s \nExpiration Date: %s \nCredit Card Number: %s \n", cardholderName, expirationDate, creditCardNumber);
        System.out.println("Payment Made: $" + getPaymentAmount());
    }
}
