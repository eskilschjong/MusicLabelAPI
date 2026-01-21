package assignment2.musiclabelapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "album")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String genre;

    @Column(name = "releaseyear")
    private Integer releaseYear;

    @Column(name = "coverimage")
    private String coverImage;

    @ManyToOne
    @JoinColumn(name = "labelid")
    @JsonIgnoreProperties("albums")
    private MusicLabel musicLabel;

    @ManyToMany
    @JoinTable(
            name = "albumartist",
            joinColumns = @JoinColumn(name = "albumid"),
            inverseJoinColumns = @JoinColumn(name = "artistid")
    )
    @JsonIgnoreProperties("albums")
    private List<Artist> artists;

    // Default constructor for Hibernate
    public Album() {}

    public Album(Long id, MusicLabel musicLabel, String title, String genre, Integer releaseYear, String coverImage) {
        this.id = id;
        this.musicLabel = musicLabel;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.coverImage = coverImage;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public MusicLabel getMusicLabel() { return musicLabel; }
    public void setMusicLabel(MusicLabel musicLabel) { this.musicLabel = musicLabel; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public Integer getReleaseYear() { return releaseYear; }
    public void setReleaseYear(Integer releaseYear) { this.releaseYear = releaseYear; }
    public String getCoverImage() { return coverImage; }
    public void setCoverImage(String coverImage) { this.coverImage = coverImage; }
    public List<Artist> getArtists() { return artists; }
    public void setArtists(List<Artist> artists) { this.artists = artists; }
}
