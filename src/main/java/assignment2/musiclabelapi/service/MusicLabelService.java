package assignment2.musiclabelapi.service;

import assignment2.musiclabelapi.model.MusicLabel;
import assignment2.musiclabelapi.repository.MusicLabelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicLabelService {

    private final MusicLabelRepository musicLabelRepository;

    public MusicLabelService(MusicLabelRepository musicLabelRepository) {
        this.musicLabelRepository = musicLabelRepository;
    }

    public MusicLabel createMusicLabel(MusicLabel musicLabel) {
        return musicLabelRepository.save(musicLabel);
    }

    public MusicLabel getMusicLabelById(long id) {
        return musicLabelRepository.findById(id).orElse(null);
    }

    public List<MusicLabel> getAllMusicLabels() {
        return musicLabelRepository.findAll();
    }

    public MusicLabel updateMusicLabel(long id, MusicLabel musicLabel) {
        musicLabel.setId(id);
        return musicLabelRepository.save(musicLabel);
    }

    public boolean deleteMusicLabelById(long id) {
        if (musicLabelRepository.existsById(id)) {
            musicLabelRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
