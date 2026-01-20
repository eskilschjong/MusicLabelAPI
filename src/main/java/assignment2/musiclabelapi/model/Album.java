package assignment2.musiclabelapi.model;

public record Album(
        long id,
        long labelId,
        String title,
        String genre,
        int releaseYear,
        String coverImage
    )
{}
