package assignment2.musiclabelapi.service;

import assignment2.musiclabelapi.model.Artist;
import assignment2.musiclabelapi.repository.ArtistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public Artist getArtistById(long id) {
        return artistRepository.findById(id).orElse(null);
    }

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist updateArtist(long id, Artist artist) {
        artist.setId(id);
        return artistRepository.save(artist);
    }

    public boolean deleteArtistById(long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
