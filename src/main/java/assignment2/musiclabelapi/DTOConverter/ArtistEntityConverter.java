package assignment2.musiclabelapi.DTOConverter;

import assignment2.musiclabelapi.DTO.ArtistWriteDTO;
import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.model.Artist;
import assignment2.musiclabelapi.service.AlbumService;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class ArtistEntityConverter {

    private final AlbumService albumService;

    public ArtistEntityConverter(AlbumService albumService) {
        this.albumService = albumService;
    }

    public Artist convertToEntity(ArtistWriteDTO dto) {
        Artist artist = new Artist();
        artist.setName(dto.getName());
        artist.setStageName(dto.getStageName());
        artist.setCoverImage(dto.getCoverImage());
        artist.setBiography(dto.getBiography());

        if (dto.getAlbumIds() != null) {
            List<Album> albums = dto.getAlbumIds().stream()
                    .map(albumService::getAlbumById)
                    .toList();
            artist.setAlbums(albums);
        }
        return artist;

    }

}
