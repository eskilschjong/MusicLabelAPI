package assignment2.musiclabelapi.DTO;

import java.util.List;

public class AlbumReadDTO {

    private Long id;
    private String title;
    private String genre;
    private Integer releaseYear;
    private String coverImage;
    private Long MusicLabelId;
    private List<Long> ArtistIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public Long getMusicLabelId() {
        return MusicLabelId;
    }

    public void setMusicLabelId(Long musicLabelId) {
        MusicLabelId = musicLabelId;
    }

    public List<Long> getArtistIds() {
        return ArtistIds;
    }

    public void setArtistIds(List<Long> artistIds) {
        ArtistIds = artistIds;
    }
}
