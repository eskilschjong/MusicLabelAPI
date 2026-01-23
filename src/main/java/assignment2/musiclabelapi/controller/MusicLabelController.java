package assignment2.musiclabelapi.controller;

import assignment2.musiclabelapi.model.MusicLabel;
import org.springframework.web.bind.annotation.*;
import assignment2.musiclabelapi.service.MusicLabelService;

@RestController
@RequestMapping("/musiclabel")
public class MusicLabelController {

    private final MusicLabelService musicLabelService;

    public MusicLabelController(MusicLabelService musicLabelService) {
        this.musicLabelService = musicLabelService;
    }

    @GetMapping("/all")
    public Iterable<MusicLabel> getAllMusicLabels() {
        return musicLabelService.getAllMusicLabels();
    }

    @GetMapping("/{id}")
    public MusicLabel getMusicLabelById(@PathVariable(required = false) Long id) {
        return musicLabelService.getMusicLabelById(id);
    }

    @PostMapping
    public MusicLabel createMusicLabel(@RequestBody MusicLabel musicLabel) {
        return musicLabelService.createMusicLabel(musicLabel);
    }

    @PutMapping("/{id}")
    public MusicLabel updateMusicLabel(@PathVariable Long id, @RequestBody MusicLabel musicLabel) {
        return musicLabelService.updateMusicLabel(id, musicLabel);
    }

    @DeleteMapping("/{id}")
    public Boolean deleteMusicLabelById(@PathVariable Long id) {
        return musicLabelService.deleteMusicLabelById(id);
    }
}