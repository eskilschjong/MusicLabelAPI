package assignment2.musiclabelapi.DTO;

import assignment2.musiclabelapi.model.Album;

import java.util.List;

public class MusicLabelReadDTO {

    private Long id;
    private String name;
    private String description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Long> getAlbumIds() {
        return AlbumIds;
    }

    public void setAlbumIds(List<Long> albumIds) {
        AlbumIds = albumIds;
    }
}
