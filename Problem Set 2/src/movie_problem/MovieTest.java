package movie_problem;

public class MovieTest {
    public static void main(String[] args) {
        System.out.println("Default Genre Movie");
        Movie m1 = new Movie(MPAARRating.G, 1, "Clannad");
        System.out.println("Late fees for 10 days of " + m1.getTitle() + ": $" + m1.calculateLateFees(10));
        System.out.println("Action Movie");
        Action m2 = new Action(MPAARRating.R, 2, "Kara no Kyoukai");
        System.out.println("Late fees for 10 days of " + m2.getTitle() + ": $" + m2.calculateLateFees(10));
        System.out.println("Comedy Movie");
        Comedy m3 = new Comedy(MPAARRating.PG, 3, "Kono Suba");
        System.out.println("Late fees for 10 days of " + m3.getTitle() + ": $" + m3.calculateLateFees(10));
        System.out.println("Drama Movie");
        Drama m4 = new Drama(MPAARRating.G, 1, "Clannad After Story");
        System.out.println("Late fees for 10 days of " + m4.getTitle() + ": $" + m4.calculateLateFees(10));
        System.out.println();
        System.out.printf("Is \'%s\' equal to \'%s\'? %s\n", m1.getTitle(), m2.getTitle(), m1.equals(m2));
        System.out.printf("Is \'%s\' equal to \'%s\'? %s\n", m1.getTitle(), m4.getTitle(), m1.equals(m4));
    }
}
