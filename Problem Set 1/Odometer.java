public class Odometer {
    //member variables to store the odometer reading and the fuel efficiency respectively
    private double milesDriven = 0, fuelEfficiency = 0;

    public static void main(String[] args) {
        //create a new instance of the class Odometer
        Odometer od1 = new Odometer();
        //first test: fuel efficiency = 12.5 miles/gallon and Odometer reading = 14 miles
        od1.setFuelEfficiency(12.5);
        od1.addToOdometer(14);
        System.out.println("Odometer reading after 1st trip = " + od1.getOdometerReading() + " miles.");
        System.out.println("Fuel efficiency = " + od1.getFuelEfficiency() + " miles/gallon.");
        System.out.println("Total amount of gasoline consumed for the trip is " + (od1.gasolineConsumed()) + " gallons.");
        //reset odometer before embarking on the second trip
        od1.resetOdometer();
        System.out.println("After resetting the odometer the reading is " + od1.getOdometerReading() + " miles.");
        //the second trip consists of two trips. total distance driven = 25 + 37.5 = 62.5 miles
        od1.addToOdometer(25);
        System.out.println("Odometer reading after 2nd trip = " + od1.getOdometerReading() + " miles");
        od1.addToOdometer(37.5);
        System.out.println("Odometer reading after 3rd trip = " + od1.getOdometerReading() + " miles");
        System.out.println("Total amount of gasoline consumed for the last two trips is " + (od1.gasolineConsumed()) + " gallons.");
    }

    public void resetOdometer() {
        milesDriven = 0;
    }

    public void setFuelEfficiency(double fe) {
        fuelEfficiency = fe;
    }

    public void addToOdometer(double miles) {
        milesDriven += miles;
    }

    public double gasolineConsumed() {
        return (milesDriven / fuelEfficiency);
    }

    public double getFuelEfficiency() {
        return fuelEfficiency;
    }

    public double getOdometerReading() {
        return milesDriven;
    }
}
