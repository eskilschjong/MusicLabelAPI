package assignment2.musiclabelapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "stagename")
    private String stageName;

    @Column(name = "coverimage")
    private String coverImage;

    private String biography;

    // Default constructor for Hibernate
    public Artist() {
    }

    public Artist(Long id, String name, String stageName, String coverImage, String biography) {
        this.id = id;
        this.name = name;
        this.stageName = stageName;
        this.coverImage = coverImage;
        this.biography = biography;
    }

    // Getters and Setters
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
}