package assignment2.musiclabelapi.controller;


import assignment2.musiclabelapi.DTOConverter.ArtistDTOConverter;
import assignment2.musiclabelapi.DTOConverter.ArtistEntityConverter;
import assignment2.musiclabelapi.DTO.ArtistReadDTO;
import assignment2.musiclabelapi.DTO.ArtistWriteDTO;
import assignment2.musiclabelapi.model.Artist;
import assignment2.musiclabelapi.service.AlbumService;
import assignment2.musiclabelapi.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistService artistService;
    private final ArtistDTOConverter converter;
    private final ArtistEntityConverter entityConverter;

    public ArtistController(ArtistService artistService, ArtistDTOConverter converter, ArtistEntityConverter entityConverter) {
        this.artistService = artistService;
        this.converter = converter;
        this.entityConverter = entityConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArtistReadDTO>> getAllArtists() {
        var artists = artistService.getAllArtists();
        var artistDTOs = artists.stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(artistDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistReadDTO> getArtistById(@PathVariable(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        var artist = artistService.getArtistById(id);
        var artistDTO = converter.convertToDTO(artist);
        return ResponseEntity.ok(artistDTO);
    }

    @PostMapping
    public ResponseEntity<ArtistReadDTO> createArtist(@RequestBody ArtistWriteDTO artistWriteDTO) {
        Artist artist = entityConverter.convertToEntity(artistWriteDTO);
        Artist created = artistService.createArtist(artist);
        ArtistReadDTO artistReadDTO = converter.convertToDTO(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(artistReadDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtistReadDTO> updateArtist(@PathVariable Long id, @RequestBody ArtistWriteDTO artistWriteDTO) {
        Artist artist = entityConverter.convertToEntity(artistWriteDTO);
        Artist updated = artistService.updateArtist(id, artist);
        ArtistReadDTO artistReadDTO = converter.convertToDTO(updated);
        return ResponseEntity.ok(artistReadDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.deleteArtistById(id));
    }
}