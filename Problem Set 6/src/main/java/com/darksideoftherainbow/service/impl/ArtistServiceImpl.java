package com.darksideoftherainbow.service.impl;

import com.darksideoftherainbow.model.Artist;
import com.darksideoftherainbow.repository.ArtistRepository;
import com.darksideoftherainbow.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getArtists() {
        return artistRepository.getArtists();
    }

    @Override
    public Artist addArtist(String artist) {

        if (artist == null || artist.isEmpty()) {
            throw new IllegalArgumentException("Artist cannot be null.");
        }

        //int nextId = albumRepository.getNextAlbumId();
        Artist artistNew = new Artist(artist);
        return artistRepository.addArtist(artistNew);
    }

}
