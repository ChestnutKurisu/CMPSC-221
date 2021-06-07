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
                                 @RequestParam(required = false, value = "isROCK") String isROCK, @RequestParam(required = false, value = "isCLASSICAL") String isCLASSICAL, @RequestParam(required = false, value = "isPOP") String isPOP,
                                 @RequestParam(required = false, value = "isJAZZ") String isJAZZ, @RequestParam(required = false, value = "isJAPANESE_POP") String isJAPANESE_POP, @RequestParam(required = false, value = "isHINDUSTANI_CLASSICAL") String isHINDUSTANI_CLASSICAL,
                                 @RequestParam(required = false, value = "isCELTIC") String isCELTIC, @RequestParam(required = false, value = "isELECTRONICA") String isELECTRONICA, @RequestParam(required = false, value = "isDARK_ROCK") String isDARK_ROCK,
                                 @RequestParam(required = false, value = "isPOP_ROCK") String isPOP_ROCK, @RequestParam(required = false, value = "isDARKWAVE") String isDARKWAVE, @RequestParam("numberOfTracks") Integer numberOfTracks, @RequestParam("price") String price) {

        //  Add the album to the database
        Set<Genre> genre= new HashSet<>();
        if(null != isROCK)
            genre.add(Genre.ROCK);
        if(null != isCLASSICAL)
            genre.add(Genre.CLASSICAL);
        if(null != isPOP)
            genre.add(Genre.POP);
        if(null != isJAZZ)
            genre.add(Genre.JAZZ);
        if(null != isJAPANESE_POP)
            genre.add(Genre.JAPANESE_POP);
        if(null != isHINDUSTANI_CLASSICAL)
            genre.add(Genre.HINDUSTANI_CLASSICAL);
        if(null != isCELTIC)
            genre.add(Genre.CELTIC);
        if(null != isELECTRONICA)
            genre.add(Genre.ELECTRONICA);
        if(null != isDARK_ROCK)
            genre.add(Genre.DARK_ROCK);
        if(null != isPOP_ROCK)
            genre.add(Genre.POP_ROCK);
        if(null != isDARKWAVE)
            genre.add(Genre.DARKWAVE);
        if(genre.isEmpty())
            genre.add(Genre.NO_GENRE);
        try {
            this.albumService.addAlbum(title, artist, LocalDate.parse(dateReleased), genre, numberOfTracks, Double.parseDouble(price));
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
        Set<Genre> genres = album.getGenre();
        for(Genre genre: Genre.values()){
            if(genres.contains(genre))
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
                                  @RequestParam("dateReleased") String dateReleased, @RequestParam(required = false, value = "isROCK") String isROCK, @RequestParam(required = false, value = "isCLASSICAL") String isCLASSICAL,
                                  @RequestParam(required = false, value = "isPOP") String isPOP, @RequestParam(required = false, value = "isJAZZ") String isJAZZ, @RequestParam(required = false, value = "isJAPANESE_POP") String isJAPANESE_POP,
                                  @RequestParam(required = false, value = "isHINDUSTANI_CLASSICAL") String isHINDUSTANI_CLASSICAL, @RequestParam(required = false, value = "isCELTIC") String isCELTIC,
                                  @RequestParam(required = false, value = "isELECTRONICA") String isELECTRONICA, @RequestParam(required = false, value = "isDARK_ROCK") String isDARK_ROCK, @RequestParam(required = false, value = "isPOP_ROCK") String isPOP_ROCK,
                                  @RequestParam(required = false, value = "isDARKWAVE") String isDARKWAVE, @RequestParam("numberOfTracks") Integer numberOfTracks, @RequestParam("price") String price) {
        Set<Genre> genre= new HashSet<>();
        if(null != isROCK)
            genre.add(Genre.ROCK);
        if(null != isCLASSICAL)
            genre.add(Genre.CLASSICAL);
        if(null != isPOP)
            genre.add(Genre.POP);
        if(null != isJAZZ)
            genre.add(Genre.JAZZ);
        if(null != isJAPANESE_POP)
            genre.add(Genre.JAPANESE_POP);
        if(null != isHINDUSTANI_CLASSICAL)
            genre.add(Genre.HINDUSTANI_CLASSICAL);
        if(null != isCELTIC)
            genre.add(Genre.CELTIC);
        if(null != isELECTRONICA)
            genre.add(Genre.ELECTRONICA);
        if(null != isDARK_ROCK)
            genre.add(Genre.DARK_ROCK);
        if(null != isPOP_ROCK)
            genre.add(Genre.POP_ROCK);
        if(null != isDARKWAVE)
            genre.add(Genre.DARKWAVE);
        if(genre.isEmpty())
            genre.add(Genre.NO_GENRE);
        try {
            this.albumService.editAlbum(albumId, title, artist, LocalDate.parse(dateReleased), genre, numberOfTracks, Double.parseDouble(price));
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
