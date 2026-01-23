package assignment2.musiclabelapi.DTOConverter;
import assignment2.musiclabelapi.DTO.AlbumWriteDTO;
import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.model.Artist;
import assignment2.musiclabelapi.model.MusicLabel;
import assignment2.musiclabelapi.service.ArtistService;
import assignment2.musiclabelapi.service.MusicLabelService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AlbumEntityConverter {

    private final MusicLabelService musicLabelService;
    private final ArtistService artistService;

    public AlbumEntityConverter(MusicLabelService musicLabelService, ArtistService artistService) {
        this.musicLabelService = musicLabelService;
        this.artistService = artistService;
    }

    public Album convertToEntity(AlbumWriteDTO dto) {
        Album album = new Album();
        album.setTitle(dto.getTitle());
        album.setGenre(dto.getGenre());
        album.setReleaseYear(dto.getReleaseYear());
        album.setCoverImage(dto.getCoverImage());

        if (dto.getMusicLabelId() != null) {
            MusicLabel label = musicLabelService.getMusicLabelById(dto.getMusicLabelId());
            album.setMusicLabel(label);
        }

        if (dto.getArtistIds() != null) {
            List<Artist> artists = dto.getArtistIds().stream()
                    .map(artistService::getArtistById)
                    .toList();
            album.setArtists(artists);
        }

        return album;
    }

}
