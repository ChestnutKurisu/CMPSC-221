package com.darksideoftherainbow.repository.impl;

import com.darksideoftherainbow.jpa.JpaArtistRepository;
import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.model.Artist;
import com.darksideoftherainbow.repository.ArtistRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ArtistRepositoryImpl implements ArtistRepository {

    private final JpaArtistRepository jpaArtistRepository;

    public ArtistRepositoryImpl(JpaArtistRepository jpaArtistRepository) {
        this.jpaArtistRepository = jpaArtistRepository;
    }

    @Override
    public List<Artist> getArtists() {
        return (List<Artist>) jpaArtistRepository.findAll();
    }
    @Override
    public Artist addArtist(Artist artist) {
        return jpaArtistRepository.save(artist);
    }

    public Artist findArtist(String artistName) {
        List<Artist> artists = getArtists();
        for (Artist artist : artists) {
            if (artist.getArtist().equalsIgnoreCase(artistName)) {
                return artist;
            }
        }
        return null;
    }
}
