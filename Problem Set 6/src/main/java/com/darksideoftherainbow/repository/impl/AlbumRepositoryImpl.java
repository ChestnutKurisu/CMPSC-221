package com.darksideoftherainbow.repository.impl;

import com.darksideoftherainbow.jpa.JpaAlbumRepository;
import com.darksideoftherainbow.jpa.JpaArtistRepository;
import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.model.Artist;
import com.darksideoftherainbow.model.Genre;
import com.darksideoftherainbow.repository.AlbumRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class AlbumRepositoryImpl implements AlbumRepository {
    /*private List<Album> albumList;

    public AlbumRepositoryImpl() {
        albumList = new ArrayList<>();
    }
    */

    private final JpaAlbumRepository jpaAlbumRepository;
    private final JpaArtistRepository jpaArtistRepository;

    public AlbumRepositoryImpl(JpaAlbumRepository jpaAlbumRepository, JpaArtistRepository jpaArtistRepository) {
        this.jpaAlbumRepository = jpaAlbumRepository;
        this.jpaArtistRepository = jpaArtistRepository;
    }

    @Override
    public List<Album> getAlbums() {
        return (List<Album>) jpaAlbumRepository.findAll();
    }

    @Override
    public Album addAlbum(String title, String artist, LocalDate dateReleased, Genre genre, Integer numberOfTracks, Double price) {
        Artist thisArtist = (new ArtistRepositoryImpl(jpaArtistRepository)).findArtist(artist);
        if (thisArtist == null)
            thisArtist = new Artist(artist);
        Album album = new Album(title, thisArtist, dateReleased, genre, numberOfTracks, price);
        jpaArtistRepository.save(thisArtist);
        thisArtist.addAlbum(album);
        return jpaAlbumRepository.save(album);
    }

    /*@Override
    public int getNextAlbumId() {

        Album maxAlbum = albumList.stream().max(Comparator.comparing(Album::getAlbumId)).get();
        return maxAlbum.getAlbumId() + 1;
    }
    */

    @Override
    public Album findAlbumById(Integer albumId) {
        //return albumList.stream().filter(g -> g.getAlbumId().equals(albumId)).collect(MoreCollectors.onlyElement());
        Optional<Album> album = jpaAlbumRepository.findById(albumId);
        if (album.isPresent()) {
            return album.get();
        }
        throw new IllegalStateException("Album with ID " + albumId + " was not found!");
    }

    @Override
    public Album editAlbum(Integer albumId, String title, String artist, LocalDate dateReleased, Genre genre, Integer numberOfTracks, Double price) {

        Album current = findAlbumById(albumId);
        if (current == null) {
            throw new IllegalStateException("album with ID not found");
        }
        Artist thisArtist = (new ArtistRepositoryImpl(jpaArtistRepository)).findArtist(artist);
        if (thisArtist == null)
            thisArtist = new Artist(artist);
        if (current.getArtist() != thisArtist) {
            current.getArtist().removeAlbum(current);
        }
        current.setArtist(thisArtist);
        current.setTitle(title);
        current.setDateReleased(dateReleased);
        current.setGenre(genre);
        current.setNumberOfTracks(numberOfTracks);
        current.setPrice(price);
        current.setImageLink();
        current.setVideoLink();
        thisArtist.addAlbum(current);
        jpaArtistRepository.save(thisArtist);
        jpaAlbumRepository.save(current);
        return current;
    }

    @Override
    public boolean deleteAlbumById(Integer albumId) {
        Album album = findAlbumById(albumId);
        album.getArtist().removeAlbum(album);
        jpaAlbumRepository.deleteById(albumId);
        return true;
    }
}
