package movie_problem;

import java.util.Objects;

public class Movie {
    private MPAARRating rating;
    private int ID;
    private String title;

    public Movie() {
        rating = MPAARRating.G;
        ID = 0;
        title = "";
    }

    public Movie(MPAARRating rating, int ID, String title) {
        this.rating = rating;
        this.ID = ID;
        this.title = title;
    }

    public MPAARRating getRating() {
        return rating;
    }

    public void setRating(MPAARRating rating) {
        this.rating = rating;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean equals(Movie m) {
        return (this.ID == m.ID);
    }

    public double calculateLateFees(int daysLate) {
        return (2 * daysLate);
    }
}
