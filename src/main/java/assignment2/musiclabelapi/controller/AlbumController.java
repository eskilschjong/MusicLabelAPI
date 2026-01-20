package assignment2.musiclabelapi.controller;

import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/album/all")
    public ResponseEntity<List<Album>> getAllAlbums() {
        var albums = albumService.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/album/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        var album = albumService.getAlbumById(id);
        return ResponseEntity.ok(album);
    }
}
