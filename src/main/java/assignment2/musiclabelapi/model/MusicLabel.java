package assignment2.musiclabelapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musiclabel")
public class MusicLabel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String text;

    // Default constructor for Hibernate
    public MusicLabel() {
    }

    public MusicLabel(Long id, String name, String text) {
        this.id = id;
        this.name = name;
        this.text = text;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}