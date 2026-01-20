package assignment2.musiclabelapi.repository;

import assignment2.musiclabelapi.model.Album;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;
import java.util.List;
import java.util.Map;

@Repository
public class AlbumRepositoryJDBC {
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public AlbumRepositoryJDBC(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Album createAlbum(Album album) {
        String sql = """
                    INSERT INTO Album (labelId, title, genre, releaseYear, coverImage)
                    VALUES (:labelId, :title, :genre, :releaseYear, :coverImage)
                """;

        var params = Map.of(
                "labelId", album.labelId(),
                "title", album.title(),
                "genre", album.genre(),
                "releaseYear", album.releaseYear(),
                "coverImage", album.coverImage()
        );

        jdbcTemplate.update(sql, params);
        return album;
    }

    public Album getAlbumById(long id) {
        String sql = """
                    SELECT * FROM Album WHERE id = :albumId
                """;
        var params = Map.of("albumId", id);
        var result = jdbcTemplate.query(sql, params,
                (rs, rowNum) -> new Album(
                        rs.getLong("id"),
                        rs.getLong("labelId"),
                        rs.getString("title"),
                        rs.getString("genre"),
                        rs.getInt("releaseYear"),
                        rs.getString("coverImage")
                ));
        return result.isEmpty() ? null : result.getFirst();
    }

    public List<Album> getAllAlbusms() {
        String sql = """
                    SELECT * FROM Album
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new Album(
                rs.getLong("id"),
                rs.getLong("labelId"),
                rs.getString("title"),
                rs.getString("genre"),
                rs.getInt("releaseYear"),
                rs.getString("coverImage")
        ));
    }

    public Album updateAlbum(Album album) {
        String sql = """
                    UPDATE Album SET title = :title, genre = :genre, releaseYear = :releaseYear, coverImage = :coverImage
                    WHERE id = :id
                """;

        var params = Map.of(
                "id", album.id(),
                "title", album.title(),
                "genre", album.genre(),
                "releaseYear", album.releaseYear(),
                "coverImage", album.coverImage()
        );

        jdbcTemplate.update(sql, params);
        return album;
    }

    public boolean deleteAlbumById(long id) {
        String sql = """
                    DELETE FROM Album WHERE id = :albumId
                """;
        var params = Map.of("albumId", id);
        return jdbcTemplate.update(sql, params) > 0;
    }
}
