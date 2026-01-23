package assignment2.musiclabelapi.DTOConverter;

import assignment2.musiclabelapi.DTO.AlbumReadDTO;
import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.model.Artist;
import org.springframework.stereotype.Component;

@Component
public class AlbumDTOConverter {

    public AlbumReadDTO convertToDTO(Album album) {

        AlbumReadDTO albumReadDTO = new AlbumReadDTO();

        albumReadDTO.setId(album.getId());
        albumReadDTO.setTitle(album.getTitle());
        albumReadDTO.setGenre(album.getGenre());
        albumReadDTO.setReleaseYear(album.getReleaseYear());
        albumReadDTO.setCoverImage(album.getCoverImage());
        albumReadDTO.setMusicLabelId(album.getMusicLabel().getId());
        albumReadDTO.setArtistIds(album.getArtists().stream().map(Artist::getId).toList());

        return albumReadDTO;
    }
}
