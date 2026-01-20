package assignment2.musiclabelapi.service;

import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.repository.AlbumRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
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

    public Album updateAlbum(Album album) {
        // JPA uses .save() for both creating and updating
        return albumRepository.save(album);
    }

    public boolean deleteAlbumById(long id) {
        if (albumRepository.existsById(id)) {
            albumRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
