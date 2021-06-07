package com.darksideoftherainbow.bootstrap;

import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.model.Genre;
import com.darksideoftherainbow.repository.AlbumRepository;
import com.google.common.collect.Sets;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final AlbumRepository albumRepository;

    public DatabaseLoader(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        this.albumRepository.addAlbum(new Album(1, "Dawn", "Aimer", LocalDate.parse("2015-07-29"), Sets.newHashSet(Genre.JAPANESE_POP, Genre.ROCK), 13, 9.99));
        this.albumRepository.addAlbum(new Album(2, "My Heart Will Go On", "Celine Dion", LocalDate.parse("1997-12-08"), Sets.newHashSet(Genre.POP), 2, 4.99));
        this.albumRepository.addAlbum(new Album(3, "R∃/MEMBER", "Hiroyuki Sawano", LocalDate.parse("2019-03-06"), Sets.newHashSet(Genre.JAPANESE_POP, Genre.POP_ROCK), 11, 7.99));
        this.albumRepository.addAlbum(new Album(4, "Naraku No Hana (奈落の花)", "Eiko Shimamiya", LocalDate.parse("2007-08-22"), Sets.newHashSet(Genre.JAPANESE_POP, Genre.ELECTRONICA), 4, 3.99));
        this.albumRepository.addAlbum(new Album(5, "Dynasty", "MIIA", LocalDate.parse("2015-09-22"), Sets.newHashSet(Genre.POP), 12, 6.99));
        this.albumRepository.addAlbum(new Album(6, "Unter dem Eis", "Eisblume", LocalDate.parse("2009-03-06"), Sets.newHashSet(Genre.ROCK, Genre.DARK_ROCK, Genre.DARKWAVE), 14, 8.49));
    }
}