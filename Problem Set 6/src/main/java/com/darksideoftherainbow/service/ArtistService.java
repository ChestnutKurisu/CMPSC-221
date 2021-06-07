package com.darksideoftherainbow.service;

import com.darksideoftherainbow.model.Artist;
import com.darksideoftherainbow.model.Genre;

import java.time.LocalDate;
import java.util.List;

public interface ArtistService {
    List<Artist> getArtists();

    Artist addArtist(String artist);
}
