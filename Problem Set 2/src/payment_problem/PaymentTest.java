package payment_problem;

public class PaymentTest {
    public static void main(String[] args){
        System.out.println("Default Cash Payment");
        CashPayment CPdefault = new CashPayment();
        CPdefault.paymentDetails();
        System.out.println();
        System.out.println("Cash Payment 1");
        CashPayment CP1 = new CashPayment(45.54);
        CP1.paymentDetails();
        CP1.setPaymentAmount(13.13);
        CP1.paymentDetails();
        System.out.println();
        System.out.println("Default Credit Card");
        CreditCardPayment CCPdefault = new CreditCardPayment();
        CCPdefault.paymentDetails();
        System.out.println();
        System.out.println("Credit Card Payment 1");
        CreditCardPayment CCP1 = new CreditCardPayment(99.99, "Kazuto Kirigaya", "1/3/2020", "4912 1892 2191 0230");
        CCP1.paymentDetails();
        System.out.println();
        System.out.println("Credit Card Payment 2");
        CreditCardPayment CCP2 = new CreditCardPayment("Asuna Yuuki", "1/3/2024", "6712 1892 2191 0230");
        CCP2.paymentDetails();
        CCP2.setExpirationDate("4/5/2020");
        CCP2.setPaymentAmount(150);
        System.out.println("After modifying member variables");
        CCP2.paymentDetails();
    }
}
