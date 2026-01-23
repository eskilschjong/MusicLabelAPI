package assignment2.musiclabelapi.DTO;

import java.util.List;

public class ArtistWriteDTO {

    private Long id;
    private String name;
    private String stageName;
    private String coverImage;
    private String biography;
    private List<Long> AlbumIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public List<Long> getAlbumIds() {
        return AlbumIds;
    }

    public void setAlbumIds(List<Long> albumIds) {
        AlbumIds = albumIds;
    }
}
