package assignment2.musiclabelapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "labelid")
    private Long labelId;

    private String title;
    private String genre;

    @Column(name = "releaseyear")
    private Integer releaseYear;

    @Column(name = "coverimage")
    private String coverImage;

    // Default constructor for Hibernate
    public Album() {}

    public Album(Long id, Long labelId, String title, String genre, Integer releaseYear, String coverImage) {
        this.id = id;
        this.labelId = labelId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.coverImage = coverImage;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getLabelId() { return labelId; }
    public void setLabelId(Long labelId) { this.labelId = labelId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
}
