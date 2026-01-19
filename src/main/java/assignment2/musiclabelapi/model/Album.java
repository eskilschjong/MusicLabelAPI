package assignment2.musiclabelapi.model;

public record Album(
        int id,
        String title,
        String genre,
        int releaseYear,
        String coverImage
    )
{}
