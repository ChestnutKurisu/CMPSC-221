package com.darksideoftherainbow.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.persistence.*;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "album_id_seq")
    @SequenceGenerator(name = "album_id_seq", sequenceName = "album_id_seq", allocationSize = 100)
    private Integer albumId;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;
    private LocalDate dateReleased;
    private Genre genre;
    private Integer numberOfTracks;
    private Double price;
    private String imageLink;
    private String videoLink;


    public Album(String title, Artist artist, LocalDate dateReleased, Genre genre, Integer numberOfTracks, Double price) {
        this.title = title;
        this.artist = artist;
        this.dateReleased = dateReleased;
        this.genre = genre;
        this.numberOfTracks = numberOfTracks;
        this.price = price;
        this.imageLink = generateImageLink();
        this.videoLink = generateVideoLink();
    }

    public Album() {
        albumId = 0;
        title = "";
        artist = null;
        dateReleased = LocalDate.parse("2000-01-01");
        genre = Genre.NO_GENRE;
        numberOfTracks = 0;
        price = 0.0;
    }

    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public LocalDate getDateReleased() {
        return dateReleased;
    }

    public String getDateReleasedString() {
        DateTimeFormatter form = DateTimeFormatter.ofPattern("d MMM yyyy");
        return dateReleased.format(form);
    }

    public void setImageLink() {
        imageLink = generateImageLink();
    }

    public String getImageLink() {
        return imageLink;
    }

    //Learned about JSON objects & image searching from https://stackoverflow.com/questions/40162503/java-jsoup-google-image-search-result-parsing
    public String generateImageLink() {
        String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
        String query = (title + " " + artist.getArtist());
        query = query.replace(' ', '+');
        String url = "https://www.google.com/search?site=imghp&tbm=isch&source=hp&q=" + query + "&gws_rd=cr";
        String link = null;
        int count = 10;
        while ((link == null || link.isEmpty()) && count != 0) {
            count--;
            try {
                Document doc = Jsoup.connect(url).userAgent(userAgent).referrer("https://www.google.com/").get();

                Elements elements = doc.select("div.rg_meta");

                JSONObject jsonObject;
                for (Element element : elements) {
                    if (element.childNodeSize() > 0) {
                        jsonObject = (JSONObject) (new JSONParser()).parse(element.childNode(0).toString());
                        link = (String) jsonObject.get("ou");
                        break;
                    }
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        return link;
    }

    public void setVideoLink() {
        videoLink = generateVideoLink();
    }

    public String getVideoLink() {
        return videoLink;
    }

    //Learned about video searching from https://stackoverflow.com/questions/49866246/how-can-i-get-the-first-url-result-on-google-video-search-tag-selector
    public String generateVideoLink() {
        String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
        String query = (title + " " + artist.getArtist());
        query = query.replace(' ', '+');
        String url = "https://www.google.com/search?tbm=vid&hl=en-TR&source=hp&biw=&bih=&q=";
        String link = "";
        try {
            Document doc = Jsoup.connect(url + URLEncoder.encode(query, "UTF-8")).userAgent(userAgent).get();

            Elements elements = doc.select(".g .r a");
            for (Element element : elements) {
                link = element.absUrl("href");
                link = link.replace("watch?v=", "embed/");
                if (link.contains("youtube.com"))
                    return link;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public void setDateReleased(LocalDate dateReleased) {
        this.dateReleased = dateReleased;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(Integer numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
