package assignment2.musiclabelapi.DTOConverter;

import assignment2.musiclabelapi.DTO.MusicLabelReadDTO;
import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.model.MusicLabel;
import org.springframework.stereotype.Component;

@Component
public class MusicLabelDTOConverter {

    public MusicLabelReadDTO convertToDTO(MusicLabel musicLabel) {

        MusicLabelReadDTO musicLabelReadDTO = new MusicLabelReadDTO();

        musicLabelReadDTO.setId(musicLabel.getId());
        musicLabelReadDTO.setName(musicLabel.getName());
        musicLabelReadDTO.setDescription(musicLabel.getDescription());
        musicLabelReadDTO.setAlbumIds(musicLabel.getAlbums().stream().map(Album::getId).toList());

        return musicLabelReadDTO;
    }
}
