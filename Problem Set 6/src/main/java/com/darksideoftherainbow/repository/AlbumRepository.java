package com.darksideoftherainbow.repository;

import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.model.Artist;
import com.darksideoftherainbow.model.Genre;

import java.time.LocalDate;
import java.util.List;

public interface AlbumRepository {
    List<Album> getAlbums();

    //int getNextAlbumId();

    Album findAlbumById(Integer albumId);

    Album editAlbum(Integer albumId, String title, String artist, LocalDate dateReleased, Genre genre, Integer numberOfTracks, Double price);

    boolean deleteAlbumById(Integer albumId);

    Album addAlbum(String title, String artist, LocalDate dateReleased, Genre genre, Integer numberOfTracks, Double price);
}
