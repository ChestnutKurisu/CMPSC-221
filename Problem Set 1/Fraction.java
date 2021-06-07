public class Fraction {
    //member variables for the numerator nd denominator
    private int num, den;
    //flag for the case when the fraction is illegal (denominator = 0)
    private boolean denIsZero=false;

    public static void main(String[] args) {
        Fraction f1=new Fraction();
        System.out.println("Case 1");
        f1.setFrac(3,4);
        System.out.println("Value of the fraction = "+f1.printFract()+" = "+f1.value());
        System.out.println();

        System.out.println("Case 2");
        f1.setFrac(48,216);
        System.out.println("Value of the fraction = "+f1.printFract()+" = "+f1.value());
        f1.fracToCanonicalForm();
        System.out.println();

        System.out.println("Case 3");
        f1.setFrac(182,117);
        System.out.println("Value of the fraction = "+f1.printFract()+" = "+f1.value());
        f1.fracToCanonicalForm();
        System.out.println();

        //error test case when denominator is zero
        System.out.println("Case 4");
        f1.setFrac(18,0);
        f1.value();
        f1.fracToCanonicalForm();
        System.out.println();
    }

    public String printFract(){
        return (num+"\\"+den);
    }

    public void setFrac(int numerator, int denominator) {
        num = numerator;
        den = denominator;
        if(den==0){
            System.out.println("Error: Denominator cannot be zero.");
            denIsZero=true;
        }
    }

    public double value() {
        if(denIsZero){
            System.out.println("Error: \\ by 0.");
            return 0;
        }
        return ((double) num / den);
    }

    //reduce the fraction to its canonical form, such that the numerator and denominator are co-prime
    public void fracToCanonicalForm() {
        if(denIsZero){
            System.out.println("Cannot reduce a fraction with denominator 0.");
            return;
        }
        int min = (num < den) ? num : den;
        int gcd = 1; //greatest common divisor
        for (int i = min; i >= 1; i--) {
            if (num % i == 0 && den % i == 0) {
                gcd = i;
                break;
            }
        }
        System.out.println("The fraction reduced to its lowest terms is " + (num / gcd) + "/" + (den / gcd));
    }
}
