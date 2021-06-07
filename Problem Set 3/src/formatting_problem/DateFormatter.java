package formatting_problem;

import java.util.Scanner;

public class DateFormatter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter date in the format (mm/dd/yyyy): ");
        String intDate = in.next();
        String[] dateComponent = intDate.split("/", 3);
        int monthN = Integer.parseInt(dateComponent[0]);
        int day = Integer.parseInt(dateComponent[1]);
        int year = Integer.parseInt(dateComponent[2]);
        //Testing for validity of entered month
        while (true) {
            try {
                if (monthN < 1 || monthN > 12)
                    throw new MonthException();
                else
                    break;
            } catch (MonthException me) {
                System.out.println(me.getMessage());
                System.out.print("Please enter a month between 1 and 12: ");
                monthN = in.nextInt();
            }
        }
        Month month = null;
        switch (monthN) {
            case 1:
                month = Month.JANUARY;
                break;
            case 2:
                month = Month.FEBRUARY;
                break;
            case 3:
                month = Month.MARCH;
                break;
            case 4:
                month = Month.APRIL;
                break;
            case 5:
                month = Month.MAY;
                break;
            case 6:
                month = Month.JUNE;
                break;
            case 7:
                month = Month.JULY;
                break;
            case 8:
                month = Month.AUGUST;
                break;
            case 9:
                month = Month.SEPTEMBER;
                break;
            case 10:
                month = Month.OCTOBER;
                break;
            case 11:
                month = Month.NOVEMBER;
                break;
            case 12:
                month = Month.DECEMBER;
                break;
            default:
        }
        //Testing for validity of entered year
        while (true) {
            try {
                if (year < 1000 || year > 3000)
                    throw new YearException();
                else
                    break;
            } catch (YearException ye) {
                System.out.println(ye.getMessage());
                System.out.println("Please enter a year between 1000 and 3000: ");
                year = in.nextInt();
            }
        }
        if (isLeapYear(year) && monthN == 2)
            month.setDays(29);
        //Testing for validity of entered day
        while (true) {
            try {
                if (day < 1 || day > month.getDays())
                    throw new DayException();
                else
                    break;
            } catch (DayException de) {
                System.out.println(de.getMessage());
                System.out.println("Please enter a day between 1 and " + month.getDays() + ": ");
                day = in.nextInt();
            }
        }
        String newDate = String.format("%s %d, %d", month.getName(), day, year);
        System.out.println("Date in new format: " + newDate);
    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }
}
