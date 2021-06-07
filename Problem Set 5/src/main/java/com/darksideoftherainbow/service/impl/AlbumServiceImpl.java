package com.darksideoftherainbow.service.impl;

import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.model.Genre;
import com.darksideoftherainbow.repository.AlbumRepository;
import com.darksideoftherainbow.service.AlbumService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<Album> getAlbums() {
        return albumRepository.getAlbums();
    }

    @Override
    public Album addAlbum(String title, String artist, LocalDate dateReleased, Set<Genre> genre, Integer numberOfTracks, Double price) {

        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Album title cannot be null.");
        }
        if (artist == null || artist.isEmpty()) {
            throw new IllegalArgumentException("Album artist cannot be null.");
        }
        if (numberOfTracks <= 0) {
            throw new IllegalArgumentException("Album must have at least one track.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Album must have a non-negative price.");
        }

        int nextId = albumRepository.getNextAlbumId();
        Album album = new Album(nextId, title, artist, dateReleased, genre, numberOfTracks, price);
        return albumRepository.addAlbum(album);
    }

    @Override
    public List<Album> findAllFilteredAlbums(String filter, String category) {
        filter = filter.toLowerCase();
        if (category.equalsIgnoreCase("title")) {
            List<Album> allAlbums = albumRepository.getAlbums();
            String finalFilter = filter;
            return allAlbums.stream()
                    .filter(g -> g.getTitle().toLowerCase().contains(finalFilter))
                    .collect(Collectors.toList());
        } else if (category.equalsIgnoreCase("artist")) {
            List<Album> allAlbums = albumRepository.getAlbums();
            String finalFilter1 = filter;
            return allAlbums.stream()
                    .filter(g -> g.getArtist().toLowerCase().contains(finalFilter1))
                    .collect(Collectors.toList());
        }
        return albumRepository.getAlbums();
    }

    @Override
    public Album findAlbumById(Integer albumId) {
        return albumRepository.findAlbumById(albumId);
    }

    @Override
    public Album editAlbum(Integer albumID, String title, String artist, LocalDate dateReleased, Set<Genre> genre, Integer numberOfTracks, Double price) {
        return albumRepository.editAlbum(albumID, title, artist, dateReleased, genre, numberOfTracks, price);
    }

    @Override
    public boolean deleteAlbumById(Integer albumId) {
        return albumRepository.deleteAlbumById(albumId);
    }
}
