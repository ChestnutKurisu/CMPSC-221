package com.darksideoftherainbow.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_id_seq")
    @SequenceGenerator(name = "artist_id_seq", sequenceName = "artist_id_seq", allocationSize = 100)
    private Integer artistId;
    private String artist;
    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private Set<Album> albums;
    private String albumList;

    public Artist(String artist) {
        this.artist = artist;
        albums  = new HashSet<>();
    }

    public Artist(){
    }

    public Integer getArtistId() {
        return artistId;
    }

    public void setArtistId(Integer artistId) {
        this.artistId = artistId;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void addAlbum(Album album) {
        albums.add(album);
        computeAlbumList();
    }

    private void computeAlbumList() {
        albumList="";
        for(Album album: albums){
            albumList = albumList + String.format("{%s}", album.getTitle());
        }
    }

    public boolean removeAlbum(Album album) {
        albums.remove(album);
        computeAlbumList();
        if(albums.isEmpty())
            return true;
        return false;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
        computeAlbumList();
    }
}

