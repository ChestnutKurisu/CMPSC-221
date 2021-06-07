package com.darksideoftherainbow.model;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Album {

    private Integer albumId;
    private String title;
    private String artist;
    private LocalDate dateReleased;
    private Set<Genre> genre;
    private Integer numberOfTracks;
    private Double price;
    private String imageLink;
    private String videoLink;

    public Album(Integer albumId, String title, String artist, LocalDate dateReleased, Set<Genre> genre, Integer numberOfTracks, Double price) {
        this.albumId = albumId;
        this.title = title;
        this.artist = artist;
        this.dateReleased = dateReleased;
        this.genre = new HashSet<>(genre);
        this.numberOfTracks = numberOfTracks;
        this.price = price;
        this.imageLink = generateImageLink();
        this.videoLink = generateVideoLink();
    }

    public Album() {
        albumId = 0;
        title = "";
        artist = "";
        dateReleased = LocalDate.parse("2000-01-01");
        genre = (new HashSet<>());
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

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
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
        String query = (title + " " + artist);
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
        String query = (title + " " + artist);
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

    public Set<Genre> getGenre() {
        return genre;
    }

    public void setGenre(Set<Genre> genre) {
        this.genre = new HashSet<>(genre);
    }

    public String getGenreString() {
        String genres = "";
        for (Genre gen : genre) {
            genres = genres + gen.getName() + "; ";
        }
        return genres.substring(0, genres.length() - 2);
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
