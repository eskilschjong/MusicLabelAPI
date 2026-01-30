package assignment2.musiclabelapi.controller;

import assignment2.musiclabelapi.DTO.AlbumReadDTO;
import assignment2.musiclabelapi.DTO.AlbumWriteDTO;
import assignment2.musiclabelapi.DTOConverter.AlbumDTOConverter;
import assignment2.musiclabelapi.DTOConverter.AlbumEntityConverter;
import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.service.AlbumService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/album")
@Tag(name = "Album", description = "Endpoints for managing albums")
public class AlbumController {

    private final AlbumService service;
    private final AlbumDTOConverter converter;
    private final AlbumEntityConverter entityConverter;

    public AlbumController(AlbumService service, AlbumDTOConverter converter, AlbumEntityConverter entityConverter) {
        this.service = service;
        this.converter = converter;
        this.entityConverter = entityConverter;
    }

    /**
     * Get all albums
     * Retrieves a list of all albums
     * @return List of AlbumReadDTO
     */
    @GetMapping("/all")
    @Operation(summary = "Get all albums", description = "Retrieves a list of all albums")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of albums")
    })
    public ResponseEntity<List<AlbumReadDTO>> getAllAlbums() {
        var albums = service.getAllAlbums();
        var dtoAlbums = albums.stream()
                .map(converter::convertToDTO)
                .toList();
        return ResponseEntity.ok(dtoAlbums);
    }

    /**
     * Get album by ID
     * Retrieves a specific album by its ID
     * @param id Unique identifier of the album
     * @return AlbumReadDTO
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get album by ID", description = "Retrieves a specific album by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the album"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Album not found")
    })
    public ResponseEntity<AlbumReadDTO> getAlbumById(@PathVariable(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        var album = service.getAlbumById(id);
        var dtoAlbum = converter.convertToDTO(album);
        return ResponseEntity.ok(dtoAlbum);
    }

    /**
     * Create an album
     * Creates a new album
     * @param albumDTO Data Transfer Object containing album details
     * @return Created AlbumReadDTO
     */
    @PostMapping
    @Operation(summary = "Create an album", description = "Creates a new album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the album"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<AlbumReadDTO> createAlbum(@RequestBody AlbumWriteDTO albumDTO) {
        Album album = entityConverter.convertToEntity(albumDTO);
        Album created = service.createAlbum(album);
        AlbumReadDTO dtoAlbum = converter.convertToDTO(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoAlbum);
    }

    /**
     * Update an album
     * Updates an existing album
     * @param id Unique identifier of the album to update
     * @param albumDTO Updated album details
     * @return Updated AlbumReadDTO
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an album", description = "Updates an existing album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the album"),
            @ApiResponse(responseCode = "400", description = "Invalid ID or data supplied"),
            @ApiResponse(responseCode = "404", description = "Album not found")
    })
    public ResponseEntity<AlbumReadDTO> updateAlbum(@PathVariable Long id, @RequestBody AlbumWriteDTO albumDTO) {
        Album album = entityConverter.convertToEntity(albumDTO);
        Album updated = service.updateAlbum(id, album);
        AlbumReadDTO dtoAlbum = converter.convertToDTO(updated);
        return ResponseEntity.ok(dtoAlbum);
    }

    /**
     * Update album artists
     * Updates the list of artists associated with an album
     * @param id Unique identifier of the album
     * @param artistIds List of artist identifiers
     * @return Updated AlbumReadDTO
     */
    @PutMapping("/{id}/artists")
    @Operation(summary = "Update album artists", description = "Updates the list of artists associated with an album")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated album artists"),
            @ApiResponse(responseCode = "400", description = "Invalid ID or artist IDs supplied"),
            @ApiResponse(responseCode = "404", description = "Album not found")
    })
    public ResponseEntity<AlbumReadDTO> updateAlbumArtists(@PathVariable Long id, @RequestBody List<Long> artistIds) {
        Album updated = service.updateAlbumArtists(id, artistIds);
        AlbumReadDTO dtoAlbum = converter.convertToDTO(updated);
        return ResponseEntity.ok(dtoAlbum);
    }

    /**
     * Delete an album
     * Deletes an album by ID
     * @param id Unique identifier of the album to delete
     * @return Boolean indicating success
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an album", description = "Deletes an album by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the album"),
            @ApiResponse(responseCode = "404", description = "Album not found")
    })
    public ResponseEntity<Boolean> deleteAlbumById(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteAlbumById(id));
    }

    /**
     * Get albums by music label
     * Retrieves all albums associated with a specific music label
     * @param labelId Unique identifier of the music label
     * @return List of AlbumReadDTO
     */
    @GetMapping("/musiclabel/{labelId}")
    @Operation(summary = "Get albums by music label", description = "Retrieves all albums associated with a specific music label")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of albums"),
            @ApiResponse(responseCode = "404", description = "Music label not found")
    })
    public ResponseEntity<List<AlbumReadDTO>> getLabelAlbums(@PathVariable Long labelId){
        var albums = service.getLabelAlbums(labelId);
        var dtoAlbums = albums.stream().map(converter::convertToDTO).toList();
        return ResponseEntity.ok(dtoAlbums);
    }
}
