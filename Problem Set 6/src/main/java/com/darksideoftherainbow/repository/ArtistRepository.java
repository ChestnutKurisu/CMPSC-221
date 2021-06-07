package com.darksideoftherainbow.repository;

import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.model.Artist;

import java.util.List;

public interface ArtistRepository {
    List<Artist> getArtists();

    Artist addArtist(Artist artist);

    Artist findArtist(String artist);
}
