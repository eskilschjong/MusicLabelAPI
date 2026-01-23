package assignment2.musiclabelapi.controller;

import assignment2.musiclabelapi.DTO.AlbumReadDTO;
import assignment2.musiclabelapi.DTO.AlbumWriteDTO;
import assignment2.musiclabelapi.DTOConverter.AlbumDTOConverter;
import assignment2.musiclabelapi.DTOConverter.AlbumEntityConverter;
import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService service;
    private final AlbumDTOConverter converter;
    private final AlbumEntityConverter entityConverter;

    public AlbumController(AlbumService service, AlbumDTOConverter converter, AlbumEntityConverter entityConverter) {
        this.service = service;
        this.converter = converter;
        this.entityConverter = entityConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AlbumReadDTO>> getAllAlbums() {
        var albums = service.getAllAlbums();
        var dtoAlbums = albums.stream()
                .map(converter::convertToDTO)
                .toList();
        return ResponseEntity.ok(dtoAlbums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumReadDTO> getAlbumById(@PathVariable(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        var album = service.getAlbumById(id);
        var dtoAlbum = converter.convertToDTO(album);
        return ResponseEntity.ok(dtoAlbum);
    }

    @PostMapping
    public ResponseEntity<AlbumReadDTO> createAlbum(@RequestBody AlbumWriteDTO albumDTO) {
        Album album = entityConverter.convertToEntity(albumDTO);
        Album created = service.createAlbum(album);
        AlbumReadDTO dtoAlbum = converter.convertToDTO(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoAlbum);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumReadDTO> updateAlbum(@PathVariable Long id, @RequestBody AlbumWriteDTO albumDTO) {
        Album album = entityConverter.convertToEntity(albumDTO);
        Album updated = service.updateAlbum(id, album);
        AlbumReadDTO dtoAlbum = converter.convertToDTO(updated);
        return ResponseEntity.ok(dtoAlbum);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteAlbumById(id));
    }
}
