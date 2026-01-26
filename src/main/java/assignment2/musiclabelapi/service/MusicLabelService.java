package assignment2.musiclabelapi.service;

import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.model.MusicLabel;
import assignment2.musiclabelapi.repository.MusicLabelRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MusicLabelService {

    private final MusicLabelRepository musicLabelRepository;
    private final AlbumService albumService;

    public MusicLabelService(MusicLabelRepository musicLabelRepository, AlbumService albumService) {
        this.musicLabelRepository = musicLabelRepository;
        this.albumService = albumService;
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

    @Transactional
    public MusicLabel updateMusicLabelAlbums(long id, List<Long> albumIds) {
        MusicLabel musicLabel = musicLabelRepository.findById(id).orElse(null);
        if (musicLabel == null) {
            return null;
        }

        List<Album> albums = albumIds.stream()
                .map(albumService::getAlbumById)
                .filter(Objects::nonNull)
                .toList();

        if (musicLabel.getAlbums() == null) {
            musicLabel.setAlbums(new ArrayList<>());
        } else {
            musicLabel.getAlbums().clear();
        }

        for (Album album : albums) {
            album.setMusicLabel(musicLabel);
        }

        musicLabel.getAlbums().addAll(albums);
    
        return musicLabel;
    }
}
