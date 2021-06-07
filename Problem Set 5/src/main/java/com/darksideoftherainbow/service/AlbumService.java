package com.darksideoftherainbow.service;

import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.model.Genre;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface AlbumService {
    List<Album> getAlbums();

    Album addAlbum(String title, String artist, LocalDate dateReleased, Set<Genre> genre, Integer numberOfTracks, Double price);

    List<Album> findAllFilteredAlbums(String filter, String category);

    Album findAlbumById(Integer albumId);

    Album editAlbum(Integer albumId, String title, String artist, LocalDate dateReleased, Set<Genre> genre, Integer numberOfTracks, Double price);

    boolean deleteAlbumById(Integer albumId);
}
