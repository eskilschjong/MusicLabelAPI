package assignment2.musiclabelapi.controller;

import assignment2.musiclabelapi.DTO.MusicLabelReadDTO;
import assignment2.musiclabelapi.DTO.MusicLabelWriteDTO;
import assignment2.musiclabelapi.DTOConverter.MusicLabelDTOConverter;
import assignment2.musiclabelapi.DTOConverter.MusicLabelEntityConverter;
import assignment2.musiclabelapi.model.MusicLabel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import assignment2.musiclabelapi.service.MusicLabelService;

import java.util.List;

@RestController
@RequestMapping("/musiclabel")
public class MusicLabelController {

    private final MusicLabelService musicLabelService;
    private final MusicLabelDTOConverter converter;
    private final MusicLabelEntityConverter entityConverter;

    public MusicLabelController(MusicLabelService musicLabelService, MusicLabelDTOConverter converter, MusicLabelEntityConverter entityConverter) {
        this.musicLabelService = musicLabelService;
        this.converter = converter;
        this.entityConverter = entityConverter;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MusicLabelReadDTO>> getAllMusicLabels() {
        var musicLabels = musicLabelService.getAllMusicLabels();
        var dtoMusicLabels = musicLabels.stream()
                .map(converter::convertToDTO)
                .toList();
        return ResponseEntity.ok(dtoMusicLabels);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicLabelReadDTO> getMusicLabelById(@PathVariable(required = false) Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        var musicLabel = musicLabelService.getMusicLabelById(id);
        var dtoMusicLabel = converter.convertToDTO(musicLabel);
        return ResponseEntity.ok(dtoMusicLabel);
    }

    @PostMapping
    public ResponseEntity<MusicLabelReadDTO> createMusicLabel(@RequestBody MusicLabelWriteDTO musicLabelDTO) {
        MusicLabel musicLabel = entityConverter.convertToEntity(musicLabelDTO);
        MusicLabel created = musicLabelService.createMusicLabel(musicLabel);
        MusicLabelReadDTO dtoMusicLabel = converter.convertToDTO(created);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoMusicLabel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MusicLabelReadDTO> updateMusicLabel(@PathVariable Long id, @RequestBody MusicLabelWriteDTO musicLabelDTO) {
        MusicLabel musicLabel = entityConverter.convertToEntity(musicLabelDTO);
        MusicLabel updated = musicLabelService.updateMusicLabel(id, musicLabel);
        MusicLabelReadDTO dtoMusicLabel = converter.convertToDTO(updated);
        return ResponseEntity.ok(dtoMusicLabel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMusicLabelById(@PathVariable Long id) {
        return ResponseEntity.ok(musicLabelService.deleteMusicLabelById(id));
    }
}