package com.darksideoftherainbow.bootstrap;

import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.model.ApplicationUser;
import com.darksideoftherainbow.model.Artist;
import com.darksideoftherainbow.model.Genre;
import com.darksideoftherainbow.repository.AlbumRepository;
import com.darksideoftherainbow.repository.ApplicationUserRepository;
import com.darksideoftherainbow.repository.ArtistRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatabaseLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final AlbumRepository albumRepository;
    private final ArtistRepository artistRepository;
    private final ApplicationUserRepository applicationUserRepository;

    public DatabaseLoader(AlbumRepository albumRepository, ArtistRepository artistRepository, ApplicationUserRepository applicationUserRepository) {
        this.albumRepository = albumRepository;
        this.artistRepository = artistRepository;
        this.applicationUserRepository = applicationUserRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //PS5
        /*
        this.albumRepository.addAlbum(new Album(1, "Dawn", "Aimer", LocalDate.parse("2015-07-29"), Sets.newHashSet(Genre.JAPANESE_POP, Genre.ROCK), 13, 9.99));
        this.albumRepository.addAlbum(new Album(2, "My Heart Will Go On", "Celine Dion", LocalDate.parse("1997-12-08"), Sets.newHashSet(Genre.POP), 2, 4.99));
        this.albumRepository.addAlbum(new Album(3, "R∃/MEMBER", "Hiroyuki Sawano", LocalDate.parse("2019-03-06"), Sets.newHashSet(Genre.JAPANESE_POP, Genre.POP_ROCK), 11, 7.99));
        this.albumRepository.addAlbum(new Album(4, "Naraku No Hana (奈落の花)", "Eiko Shimamiya", LocalDate.parse("2007-08-22"), Sets.newHashSet(Genre.JAPANESE_POP, Genre.ELECTRONICA), 4, 3.99));
        this.albumRepository.addAlbum(new Album(5, "Dynasty", "MIIA", LocalDate.parse("2015-09-22"), Sets.newHashSet(Genre.POP), 12, 6.99));
        this.albumRepository.addAlbum(new Album(6, "Unter dem Eis", "Eisblume", LocalDate.parse("2009-03-06"), Sets.newHashSet(Genre.ROCK, Genre.DARK_ROCK, Genre.DARKWAVE), 14, 8.49));
        */

        // PS6 (ADD ALBUMS)
        Artist a1 = new Artist("Aimer");
        Artist a2 = new Artist("Celine Dion");
        Artist a3 = new Artist("Hiroyuki Sawano");
        Artist a4 = new Artist("Eiko Shimamiya");
        Artist a5 = new Artist("MIIA");
        Artist a6 = new Artist("Eisblume");
        Album b1 = new Album("Dawn", a1, LocalDate.parse("2015-07-29"), Genre.JAPANESE_POP, 13, 9.99);
        Album b2 = new Album("My Heart Will Go On", a2, LocalDate.parse("1997-12-08"), Genre.POP, 2, 4.99);
        Album b3 = new Album("R∃/MEMBER", a3, LocalDate.parse("2019-03-06"), Genre.JAPANESE_POP, 11, 7.99);
        Album b4 = new Album("Naraku No Hana (奈落の花)", a4, LocalDate.parse("2007-08-22"), Genre.JAPANESE_POP, 4, 3.99);
        Album b5 = new Album("Dynasty", a5, LocalDate.parse("2015-09-22"), Genre.POP, 12, 6.99);
        Album b6 = new Album("Unter dem Eis", a6, LocalDate.parse("2009-03-06"), Genre.ROCK, 14, 8.49);
        a1.addAlbum(b1);
        a2.addAlbum(b2);
        a3.addAlbum(b3);
        a4.addAlbum(b4);
        a5.addAlbum(b5);
        a6.addAlbum(b6);
        this.artistRepository.addArtist(a1);
        this.artistRepository.addArtist(a2);
        this.artistRepository.addArtist(a3);
        this.artistRepository.addArtist(a4);
        this.artistRepository.addArtist(a5);
        this.artistRepository.addArtist(a6);

        // (ADD USERS)
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        ApplicationUser user1 = new ApplicationUser("admin", encoder.encode("secretpassword"), true);
        ApplicationUser user2 = new ApplicationUser("fred", encoder.encode("123456"), false);
        this.applicationUserRepository.addUser(user1);
        this.applicationUserRepository.addUser(user2);
    }
}