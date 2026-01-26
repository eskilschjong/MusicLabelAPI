package assignment2.musiclabelapi.service;

import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.model.Artist;
import assignment2.musiclabelapi.repository.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AlbumService {
    
    private final AlbumRepository albumRepository;
    private final ArtistService artistService;

    public AlbumService(AlbumRepository albumRepository, ArtistService artistService) {
        this.albumRepository = albumRepository;
        this.artistService = artistService;
    }

    public Album createAlbum(Album album) {
        return albumRepository.save(album);
    }

    public Album getAlbumById(long id) {
        return albumRepository.findById(id).orElse(null);
    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    public Album updateAlbum(long id, Album album) {
        Album existingAlbum = albumRepository.findById(id).orElse(null);
        if (existingAlbum != null) {
            album.setId(id);
            return albumRepository.save(album);
        }
        return null;
    }

    public boolean deleteAlbumById(long id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Album updateAlbumArtists(long id, List<Long> artistIds) {
        Album album = albumRepository.findById(id).orElse(null);
        if (album == null) {
            return null;
        }

        List<Artist> artists = artistIds.stream()
                .map(artistService::getArtistById)
                .filter(Objects::nonNull)
                .toList();

        if (album.getArtists() == null) {
            album.setArtists(new ArrayList<>());
        } else {
            album.getArtists().clear();
        }
        album.getArtists().addAll(artists);

        return album;
    }
}
