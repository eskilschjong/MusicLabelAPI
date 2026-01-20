package assignment2.musiclabelapi.service;

import assignment2.musiclabelapi.model.Album;
import assignment2.musiclabelapi.repository.AlbumRepositoryJDBC;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumService {
    
    private final AlbumRepositoryJDBC albumRepositoryJDBC;

    public AlbumService(AlbumRepositoryJDBC albumRepositoryJDBC) {
        this.albumRepositoryJDBC = albumRepositoryJDBC;
    }

    public Album createAlbum(Album album) {
        return albumRepositoryJDBC.createAlbum(album);
    }

    public Album getAlbumById(long id) {
        return albumRepositoryJDBC.getAlbumById(id);
    }

    public List<Album> getAllAlbums() {
        return albumRepositoryJDBC.getAllAlbusms();
    }

    public Album updateAlbum(Album album) {
        return albumRepositoryJDBC.updateAlbum(album);
    }

    public boolean deleteAlbumById(long id) {
        return albumRepositoryJDBC.deleteAlbumById(id);
    }

}
