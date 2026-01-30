package assignment2.musiclabelapi.controller;

import assignment2.musiclabelapi.DTO.MusicLabelReadDTO;
import assignment2.musiclabelapi.DTO.MusicLabelWriteDTO;
import assignment2.musiclabelapi.DTOConverter.MusicLabelDTOConverter;
import assignment2.musiclabelapi.DTOConverter.MusicLabelEntityConverter;
import assignment2.musiclabelapi.model.MusicLabel;
import assignment2.musiclabelapi.service.MusicLabelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/musiclabel")
@Tag(name = "Music Label", description = "Endpoints for managing music labels")
public class MusicLabelController {

    private final MusicLabelService musicLabelService;
    private final MusicLabelDTOConverter converter;
    private final MusicLabelEntityConverter entityConverter;

    public MusicLabelController(MusicLabelService musicLabelService, MusicLabelDTOConverter converter, MusicLabelEntityConverter entityConverter) {
        this.musicLabelService = musicLabelService;
        this.converter = converter;
        this.entityConverter = entityConverter;
    }

    /**
     * Get all music labels
     * Retrieves a list of all music labels
     * @return List of MusicLabelReadDTO
     */
    @GetMapping("/all")
    @Operation(summary = "Get all music labels", description = "Retrieves a list of all music labels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of music labels")
    })
    public ResponseEntity<List<MusicLabelReadDTO>> getAllMusicLabels() {
        var musicLabels = musicLabelService.getAllMusicLabels();
        var dtoMusicLabels = musicLabels.stream()
                .map(converter::convertToDTO)
                .toList();
        return ResponseEntity.ok(dtoMusicLabels);
    }

    /**
     * Get music label by ID
     * Retrieves a specific music label by its ID
     * @param id Unique identifier of the music label
     * @return MusicLabelReadDTO
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get music label by ID", description = "Retrieves a specific music label by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the music label"),
            @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
            @ApiResponse(responseCode = "404", description = "Music label not found")
    })
    public ResponseEntity<MusicLabelReadDTO> getMusicLabelById(@PathVariable(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        var musicLabel = musicLabelService.getMusicLabelById(id);
        var dtoMusicLabel = converter.convertToDTO(musicLabel);
        return ResponseEntity.ok(dtoMusicLabel);
    }

    /**
     * Create a music label
     * Creates a new music label
     * @param musicLabelDTO Data Transfer Object containing music label details
     * @return Created MusicLabelReadDTO
     */
    @PostMapping
    @Operation(summary = "Create a music label", description = "Creates a new music label")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created the music label"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<MusicLabelReadDTO> createMusicLabel(@RequestBody MusicLabelWriteDTO musicLabelDTO) {
        MusicLabel musicLabel = entityConverter.convertToEntity(musicLabelDTO);
        MusicLabel created = musicLabelService.createMusicLabel(musicLabel);
        MusicLabelReadDTO dtoMusicLabel = converter.convertToDTO(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMusicLabel);
    }

    /**
     * Update a music label
     * Updates an existing music label
     * @param id Unique identifier of the music label to update
     * @param musicLabelDTO Updated music label details
     * @return Updated MusicLabelReadDTO
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a music label", description = "Updates an existing music label")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated the music label"),
            @ApiResponse(responseCode = "400", description = "Invalid ID or data supplied"),
            @ApiResponse(responseCode = "404", description = "Music label not found")
    })
    public ResponseEntity<MusicLabelReadDTO> updateMusicLabel(@PathVariable Long id, @RequestBody MusicLabelWriteDTO musicLabelDTO) {
        MusicLabel musicLabel = entityConverter.convertToEntity(musicLabelDTO);
        MusicLabel updated = musicLabelService.updateMusicLabel(id, musicLabel);
        MusicLabelReadDTO dtoMusicLabel = converter.convertToDTO(updated);
        return ResponseEntity.ok(dtoMusicLabel);
    }

    /**
     * Update music label albums
     * Updates the list of albums associated with a music label
     * @param id Unique identifier of the music label
     * @param albumIds List of album identifiers
     * @return Updated MusicLabelReadDTO
     */
    @PutMapping("/{id}/albums")
    @Operation(summary = "Update music label albums", description = "Updates the list of albums associated with a music label")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated music label albums"),
            @ApiResponse(responseCode = "400", description = "Invalid ID or album IDs supplied"),
            @ApiResponse(responseCode = "404", description = "Music label not found")
    })
    public ResponseEntity<MusicLabelReadDTO> updateMusicLabelAlbums(@PathVariable Long id, @RequestBody List<Long> albumIds) {
        MusicLabel updated = musicLabelService.updateMusicLabelAlbums(id, albumIds);
        MusicLabelReadDTO dtoMusicLabel = converter.convertToDTO(updated);
        return ResponseEntity.ok(dtoMusicLabel);
    }

    /**
     * Delete a music label
     * Deletes a music label by ID
     * @param id Unique identifier of the music label to delete
     * @return Boolean indicating success
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a music label", description = "Deletes a music label by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted the music label"),
            @ApiResponse(responseCode = "404", description = "Music label not found")
    })
    public ResponseEntity<Boolean> deleteMusicLabelById(@PathVariable Long id) {
        return ResponseEntity.ok(musicLabelService.deleteMusicLabelById(id));
    }
}