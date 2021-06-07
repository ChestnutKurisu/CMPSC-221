public class Doctor extends SalariedEmployee {
    private String doctorSpeciality;
    private double doctorVisitFee;

    public Doctor() {
        super();
        doctorSpeciality = "No Speciality";
        doctorVisitFee = 0;
    }

    public Doctor(String theName, Date theDate, double theSalary, String doctorSpeciality, double doctorVisitFee) {
        super(theName, theDate, theSalary);
        this.doctorSpeciality = doctorSpeciality;
        this.doctorVisitFee = doctorVisitFee;
    }

    public Doctor(Doctor originalObject) {
        super(originalObject);
        doctorSpeciality = originalObject.doctorSpeciality;
        doctorVisitFee = originalObject.doctorVisitFee;
    }

    public void setDoctorSpeciality(String speciality) {
        doctorSpeciality = speciality;
    }

    public void setDoctorVisitFee(double visitFee) {
        if (visitFee >= 0)
            doctorVisitFee = visitFee;
        else {
            System.out.println("Fatal Error: Negative visit fee.");
            System.exit(0);
        }
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public double getDoctorVisitFee() {
        return doctorVisitFee;
    }

    public String toString() {
        return ("Name of Doctor: " + getName() + "\nHired on: " + getHireDate().toString()
                + "\nAnnual Salary: $" + getSalary() + " per year\nSpeciality: " + doctorSpeciality + "\nFee per visit: $" + doctorVisitFee);
    }

    public boolean equals(Doctor other) {
        return (getName().equals(other.getName())
                && getHireDate().equals(other.getHireDate())
                && getSalary() == other.getSalary()
                && doctorSpeciality.equalsIgnoreCase(other.doctorSpeciality)
                && doctorVisitFee == other.doctorVisitFee);
    }
}
