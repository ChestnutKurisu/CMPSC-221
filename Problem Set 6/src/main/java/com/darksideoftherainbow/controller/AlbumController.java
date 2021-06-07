package com.darksideoftherainbow.controller;

import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.model.Genre;
import com.darksideoftherainbow.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Controller
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    // Add album

    @GetMapping("/admin/albums/add")
    public String addAlbumForm(Model model) {
        return "addalbum";
    }

    @PostMapping("/admin/albums/add")
    public String addAlbumSubmit(Model model, @RequestParam("title") String title, @RequestParam("artist") String artist, @RequestParam("dateReleased") String dateReleased,
                                 @RequestParam("genre") String genre, @RequestParam("numberOfTracks") Integer numberOfTracks, @RequestParam("price") String price) {

        //  Add the album to the database
        try {
            this.albumService.addAlbum(title, artist, LocalDate.parse(dateReleased), Genre.getGenreById(Integer.parseInt(genre)), numberOfTracks, Double.parseDouble(price));
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "addalbum";
        }

        model.addAttribute("success", "Your album was successfully added");
        return "redirect:/success";
    }


    // Edit album

    @GetMapping("/admin/albums/edit/{albumId}")
    public String editAlbumPage(@PathVariable Integer albumId, Model model) {

        Album album = albumService.findAlbumById(albumId);

        model.addAttribute("albumId", album.getAlbumId());
        model.addAttribute("title", album.getTitle());
        model.addAttribute("artist", album.getArtist());
        model.addAttribute("dateReleased", album.getDateReleased());
        Genre genres = album.getGenre();
        for(Genre genre: Genre.values()){
            if(genres.getName().equalsIgnoreCase(genre.getName()))
                model.addAttribute("is"+genre.toString(), true);
            else
                model.addAttribute("is"+genre.toString(), false);
        }
        model.addAttribute("numberOfTracks", album.getNumberOfTracks());
        model.addAttribute("price", album.getPrice());
        return "editalbum";
    }

    @PostMapping("/admin/albums/edit")
    public String editAlbumSubmit(Model model, @RequestParam("albumId") Integer albumId, @RequestParam("title") String title, @RequestParam("artist") String artist,
                                  @RequestParam("dateReleased") String dateReleased, @RequestParam("genre") String genre, @RequestParam("numberOfTracks") Integer numberOfTracks, @RequestParam("price") String price) {
        Genre genres = Genre.getGenreById(Integer.parseInt(genre));
        try {
            this.albumService.editAlbum(albumId, title, artist, LocalDate.parse(dateReleased), genres, numberOfTracks, Double.parseDouble(price));
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "redirect:/success";
    }

    @GetMapping("/admin/albums/delete/{albumId}")
    public String deleteAlbum(@PathVariable Integer albumId) {
        albumService.deleteAlbumById(albumId);
        return "redirect:/";
    }
}
