package com.darksideoftherainbow.repository.impl;

import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.model.Genre;
import com.darksideoftherainbow.repository.AlbumRepository;
import com.google.common.collect.MoreCollectors;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AlbumRepositoryImpl implements AlbumRepository {
    private List<Album> albumList;

    public AlbumRepositoryImpl() {
        albumList = new ArrayList<>();
    }

    @Override
    public List<Album> getAlbums() {
        return albumList;
    }

    @Override
    public Album addAlbum(Album album) {

        albumList.add(album);
        return album;
    }

    @Override
    public int getNextAlbumId() {

        Album maxAlbum = albumList.stream().max(Comparator.comparing(Album::getAlbumId)).get();
        return maxAlbum.getAlbumId() + 1;
    }

    @Override
    public Album findAlbumById(Integer albumId) {

        return albumList.stream().filter(g -> g.getAlbumId().equals(albumId)).collect(MoreCollectors.onlyElement());
    }

    @Override
    public Album editAlbum(Integer albumId, String title, String artist, LocalDate dateReleased, Set<Genre> genre, Integer numberOfTracks, Double price) {

        Album current = findAlbumById(albumId);
        if (current == null) {
            throw new IllegalStateException("album with ID not found");
        }
        current.setTitle(title);
        current.setArtist(artist);
        current.setDateReleased(dateReleased);
        current.setGenre(genre);
        current.setNumberOfTracks(numberOfTracks);
        current.setPrice(price);
        current.setImageLink();
        current.setVideoLink();
        return current;
    }

    @Override
    public boolean deleteAlbumById(Integer albumId) {

        albumList = albumList.stream().filter(g -> !g.getAlbumId().equals(albumId)).collect(Collectors.toList());
        for(int i=albumId-1; i<albumList.size(); i++){
            albumList.get(i).setAlbumId(i+1);
        }
        return true;
    }
}
