package assignment2.musiclabelapi.controller;

import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.service.AlbumService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/albums")
    public List<Album> getAllAlbums() {
        var albums = albumService.getAllAlbums();
        return albums;
    }
}
