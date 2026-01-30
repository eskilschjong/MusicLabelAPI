package assignment2.musiclabelapi.controller;


import assignment2.musiclabelapi.DTOConverter.ArtistDTOConverter;
import assignment2.musiclabelapi.DTOConverter.ArtistEntityConverter;
import assignment2.musiclabelapi.DTO.ArtistReadDTO;
import assignment2.musiclabelapi.DTO.ArtistWriteDTO;
import assignment2.musiclabelapi.model.Artist;
import assignment2.musiclabelapi.service.ArtistService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/artist")
@Tag(name = "Artist", description = "Endpoints for managing artists")
public class ArtistController {

    private final ArtistService service;
    private final ArtistDTOConverter converter;
    private final ArtistEntityConverter entityConverter;

    public ArtistController(ArtistService artistService, ArtistDTOConverter converter, ArtistEntityConverter entityConverter) {
        this.service = artistService;
        this.converter = converter;
        this.entityConverter = entityConverter;
    }

    /**
     * Get all artists
     * Retrieves a list of all artists in the system
     * @return List of ArtistReadDTO
     */
    @GetMapping("/all")
    @Operation(summary = "Get all artists", description = "Retrieves a list of all artists in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of artists")
    })
    public ResponseEntity<List<ArtistReadDTO>> getAllArtists() {
        var artists = service.getAllArtists();
        var artistDTOs = artists.stream()
                .map(converter::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(artistDTOs);
    }

    /**
     * Get artist by ID
     * Retrieves a specific artist by their unique identifier
     * @param id The unique identifier of the artist
     * @return ArtistReadDTO
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get artist by ID", description = "Retrieves a specific artist by their unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the artist"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Artist not found")
    })
    public ResponseEntity<ArtistReadDTO> getArtistById(@PathVariable(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        var artist = service.getArtistById(id);
        var artistDTO = converter.convertToDTO(artist);
        return ResponseEntity.ok(artistDTO);
    }

    /**
     * Create a new artist
     * Creates a new artist with the provided details
     * @param artistWriteDTO Data Transfer Object containing artist details
     * @return Created ArtistReadDTO
     */
    @PostMapping
    @Operation(summary = "Create a new artist", description = "Creates a new artist with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the artist"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ArtistReadDTO> createArtist(@RequestBody ArtistWriteDTO artistWriteDTO) {
        Artist artist = entityConverter.convertToEntity(artistWriteDTO);
        Artist created = service.createArtist(artist);
        ArtistReadDTO artistReadDTO = converter.convertToDTO(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(artistReadDTO);
    }

    /**
     * Update an artist
     * Updates an existing artist identified by ID
     * @param id The unique identifier of the artist to update
     * @param artistWriteDTO Updated artist details
     * @return Updated ArtistReadDTO
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an artist", description = "Updates an existing artist identified by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the artist"),
            @ApiResponse(responseCode = "400", description = "Invalid ID or data supplied"),
            @ApiResponse(responseCode = "404", description = "Artist not found")
    })
    public ResponseEntity<ArtistReadDTO> updateArtist(@PathVariable Long id, @RequestBody ArtistWriteDTO artistWriteDTO) {
        Artist artist = entityConverter.convertToEntity(artistWriteDTO);
        Artist updated = service.updateArtist(id, artist);
        ArtistReadDTO artistReadDTO = converter.convertToDTO(updated);
        return ResponseEntity.ok(artistReadDTO);
    }

    /**
     * Delete an artist
     * Deletes an artist identified by ID
     * @param id The unique identifier of the artist to delete
     * @return Boolean indicating success
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an artist", description = "Deletes an artist identified by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the artist"),
            @ApiResponse(responseCode = "404", description = "Artist not found")
    })
    public ResponseEntity<Boolean> deleteArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteArtistById(id));
    }

    /**
     * Get artists by album
     * Retrieves a list of artists associated with a specific album
     * @param albumId The unique identifier of the album
     * @return List of ArtistReadDTO
     */
    @GetMapping("/album/{albumId}")
    @Operation(summary = "Get artists by album", description = "Retrieves a list of artists associated with a specific album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of artists"),
            @ApiResponse(responseCode = "404", description = "Album not found")
    })
    public ResponseEntity<List<ArtistReadDTO>> getAlbumArtists(@PathVariable Long albumId) {
        var artists = service.getAlbumArtists(albumId);
        var dtoArtists = artists.stream().map(converter::convertToDTO).toList();
        return ResponseEntity.ok(dtoArtists);
    }

    /**
     * Get artists by music label
     * Retrieves a list of artists associated with a specific music label
     * @param labelId The unique identifier of the music label
     * @return List of ArtistReadDTO
     */
    @GetMapping("/musiclabel/{labelId}")
    @Operation(summary = "Get music label artists", description = "Retrieves a list of artists associated with a specific music label")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of artists"),
            @ApiResponse(responseCode = "404", description = "Music label not found")
    })
    public ResponseEntity<List<ArtistReadDTO>> getMusicLabelArtists(@PathVariable Long labelId) {
        var artists = service.getMusicLabelArtists(labelId);
        var dtoArtists = artists.stream().map(converter::convertToDTO).toList();
        return ResponseEntity.ok(dtoArtists);
    }
}