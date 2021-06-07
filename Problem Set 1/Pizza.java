public class Pizza {
    private int cheeseTop, pepperoniTop, hamTop;

    enum Size {
        NOSIZE, SMALL, MEDIUM, LARGE
    }

    private Size size;

    public Pizza() {
        cheeseTop = 0;
        pepperoniTop = 0;
        hamTop = 0;
        size = Size.NOSIZE; //Default size of a pizza.
    }

    public Pizza(int cheeseTop, int pepperoniTop, int hamTop, Size size) {
        //Calling member methods here will verify that the object
        //is being formed with valid arguments for the instance variables (Checking the precondition).
        this.setCheeseTop(cheeseTop);
        this.setPepperoniTop(pepperoniTop);
        this.setHamTop(hamTop);
        this.setSize(size);
    }

    public Pizza(Pizza p) {
        this.cheeseTop = p.cheeseTop;
        this.pepperoniTop = p.pepperoniTop;
        this.hamTop = p.hamTop;
        this.size = p.size;
    }

    public int getCheeseTop() {
        return cheeseTop;
    }

    public int getPepperoniTop() {
        return pepperoniTop;
    }

    public int getHamTop() {
        return hamTop;
    }

    public Size getSize() {
        return size;
    }

    //Mutator methods. Number of toppings must not be negative.
    public void setCheeseTop(int cheeseTop) {
        if (cheeseTop < 0) {
            System.err.println("Number of cheese toppings must not be negative.");
            return;
        }
        this.cheeseTop = cheeseTop;
    }

    public void setPepperoniTop(int pepperoniTop) {
        if (pepperoniTop < 0) {
            System.err.println("Number of pepperoni toppings must not be negative.");
            return;
        }
        this.pepperoniTop = pepperoniTop;
    }

    public void setHamTop(int hamTop) {
        if (hamTop < 0) {
            System.err.println("Number of ham toppings must not be negative.");
            return;
        }
        this.hamTop = hamTop;
    }

    public void setSize(Size size) {
        if (size != Size.SMALL && size != Size.MEDIUM && size != Size.LARGE) { //Invalid size cannot be set
            System.err.println("Size of the pizza is invalid.");
            return;
        }
        this.size = size;
    }

    public void setSize(String s) {
        switch (s.toUpperCase()) {
            case "SMALL":
                size = Size.SMALL;
                break;
            case "MEDIUM":
                size = Size.MEDIUM;
                break;
            case "LARGE":
                size = Size.LARGE;
                break;
            default:
                System.err.println("Size of the pizza is invalid.");
                break;
        }
    }

    public double calcCost() {
        int totalTop = cheeseTop + pepperoniTop + hamTop;
        double cost = 0;
        switch (size) {
            case SMALL:
                cost = 10 + 2 * totalTop;
                break;
            case MEDIUM:
                cost = 12 + 2 * totalTop;
                break;
            case LARGE:
                cost = 14 + 2 * totalTop;
                break;
            default:
                System.err.println("Invalid Pizza Size");
        }
        return cost;
    }

    public String getDescription() {
        return ("Pizza Size: " + size + "\nNumber of Cheese Toppings: "
                + cheeseTop + "\nNumber of Pepperoni Toppings: " + pepperoniTop + "\nNumber of Ham Toppings: " + hamTop
                + "\nTotal Cost: $" + calcCost() + "\n");
    }

    public static void main(String[] args) {
        Pizza p1 = new Pizza();
        Pizza p2 = new Pizza(3, 3, 3, Size.SMALL);
        Pizza p3 = new Pizza(1, 1, 2, Size.LARGE);
        Pizza p4 = new Pizza(p3);

        System.out.println("Pizza 1");
        System.out.println(p1.getDescription());
        p1.setCheeseTop(5);
        p1.setPepperoniTop(3);
        p1.setHamTop(2);
        p1.setSize("MEDIUM");
        System.out.println("Updated Pizza 1");
        System.out.println(p1.getDescription());
        System.out.println("Pizza 2");
        System.out.println(p2.getDescription());
        System.out.println("Pizza 3/4 (Through Copy Constructor)");
        System.out.println(p4.getDescription());
        System.out.println("Attempting to set invalid values for the instance variables:");
        p3.setCheeseTop(-3);
        p3.setPepperoniTop(-6);
        p3.setHamTop(-9);
        p3.setSize("INTERMEDIATE");
    }
}
