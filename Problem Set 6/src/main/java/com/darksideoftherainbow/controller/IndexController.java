package com.darksideoftherainbow.controller;

import com.darksideoftherainbow.model.Album;
import com.darksideoftherainbow.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    private final AlbumService albumService;

    public IndexController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Album> albums = albumService.getAlbums();
        model.addAttribute("albums", albums);
        return "index";
    }

    @GetMapping("/admin/albums/view")
    public String view(Model model) {
        List<Album> albums = albumService.getAlbums();
        model.addAttribute("albums", albums);
        return "index";
    }

    @GetMapping("/search")
    public String indexFiltered(Model model, @RequestParam("term") String term, @RequestParam("by") String by) {
        List<Album> albums = albumService.findAllFilteredAlbums(term, by);
        model.addAttribute("albums", albums);
        return "index";
    }

    @GetMapping("/success")
    public String indexWithSuccess(Model model) {
        List<Album> albums = albumService.getAlbums();
        model.addAttribute("albums", albums);
        model.addAttribute("success", "Your changes were saved successfully.");
        return "index";
    }

    @GetMapping("/successemail")
    public String contactWithSuccess(Model model) {
        List<Album> albums = albumService.getAlbums();
        model.addAttribute("albums", albums);
        model.addAttribute("success", "Your email has been sent.");
        return "index";
    }
}
