public class DoctorTest {
    public static void main(String[] args) {
        //Testing the three constructors in class Doctor
        Doctor defaultDoctor = new Doctor();
        System.out.println("Doctor object created using default constructor:");
        System.out.println(defaultDoctor); //Invoking the toString() method in class Doctor
        System.out.println();

        System.out.println("Doctor object created using parameterized constructor:");
        Doctor father = new Doctor("Dr. Sanjay Somane",
                new Date("January", 6, 1991), 30000.00,
                "Radiologist", 39.00);
        System.out.println(father);
        System.out.println();

        System.out.println("Creating a Doctor object using the copy constructor:");
        Doctor impostor = new Doctor(father);
        System.out.println(impostor);
        System.out.println();
        System.out.println("Do impostor and father have the same memory location? " + (impostor == father));
        System.out.println("Do impostor and father share the same employee details? " + impostor.equals(father));
        System.out.println("Do defaultDoctor and father share the same employee details? " + defaultDoctor.equals(father));
        System.out.println();

        //Test the accessor and mutator methods
        impostor.setName("Dr. Kenzo Tenma");
        impostor.setDoctorSpeciality("Neurosurgeon");
        impostor.setDoctorVisitFee(150.0);
        System.out.println("Updated impostor object:");
        System.out.println(impostor);
        System.out.println();
        System.out.println("Speciality of " + impostor.getName() + ": " + impostor.getDoctorSpeciality());
        System.out.println("Each office visit costs $" + impostor.getDoctorVisitFee());
        System.out.println();
        System.out.println("Attempting to set negative visit fee for impostor");
        impostor.setDoctorVisitFee(-13.13);
    }
}

