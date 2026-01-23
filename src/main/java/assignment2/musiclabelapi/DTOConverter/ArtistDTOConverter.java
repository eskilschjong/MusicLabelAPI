package assignment2.musiclabelapi.DTOConverter;

import assignment2.musiclabelapi.DTO.ArtistReadDTO;
import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.model.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistDTOConverter {

    public ArtistReadDTO convertToDTO(Artist artist) {

        ArtistReadDTO artistReadDTO = new ArtistReadDTO();

        artistReadDTO.setId(artist.getId());
        artistReadDTO.setName(artist.getName());
        artistReadDTO.setStageName(artist.getStageName());
        artistReadDTO.setCoverImage(artist.getCoverImage());
        artistReadDTO.setBiography(artist.getBiography());
        artistReadDTO.setAlbumIds(artist.getAlbums().stream().map(Album::getId).toList());


        return artistReadDTO;
    }
}
