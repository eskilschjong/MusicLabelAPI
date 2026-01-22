package assignment2.musiclabelapi.controller;

import assignment2.musiclabelapi.DTO.AlbumReadDTO;
import assignment2.musiclabelapi.DTOConverter.AlbumDTOConverter;
import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private AlbumService service;
    private AlbumDTOConverter converter;

    public AlbumController(AlbumService service, AlbumDTOConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Album>> getAllAlbums() {
        var albums = service.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> getAlbumById(@PathVariable(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        var album = service.getAlbumById(id);
        return ResponseEntity.ok(album);
    }

    @GetMapping("/DTO/{id}")
    public ResponseEntity<AlbumReadDTO> getAlbumByIdDTO(@PathVariable Long id) {

        var album = service.getAlbumById(id);

        var dtoAlbum = converter.convertToDTO(album);

        return ResponseEntity.ok(dtoAlbum);
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        Album created = service.createAlbum(album);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Album> updateAlbum(@PathVariable Long id, @RequestBody Album album) {
        Album updated = service.updateAlbum(id, album);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteAlbumById(id));
    }
}
