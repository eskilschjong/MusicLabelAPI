package assignment2.musiclabelapi.controller;

import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/album")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album created = albumService.createAlbum(album);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/album/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        Album updated = albumService.updateAlbum(id, album);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/album/{id}")
    public ResponseEntity<Boolean> deleteAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(albumService.deleteAlbumById(id));
    }
}
