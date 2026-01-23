package assignment2.musiclabelapi.DTOConverter;

import assignment2.musiclabelapi.DTO.MusicLabelWriteDTO;
import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.model.MusicLabel;
import assignment2.musiclabelapi.service.AlbumService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MusicLabelEntityConverter {

    private final AlbumService albumService;

    public MusicLabelEntityConverter(AlbumService albumService) {
        this.albumService = albumService;
    }

    public MusicLabel convertToEntity(MusicLabelWriteDTO dto) {
        MusicLabel musicLabel = new MusicLabel();
        musicLabel.setName(dto.getName());
        musicLabel.setDescription(dto.getDescription());

        if (dto.getAlbumIds() != null) {
            List<Album> albums = dto.getAlbumIds().stream()
                    .map(albumService::getAlbumById)
                    .toList();
            musicLabel.setAlbums(albums);
        }

        return musicLabel;
    }

}
