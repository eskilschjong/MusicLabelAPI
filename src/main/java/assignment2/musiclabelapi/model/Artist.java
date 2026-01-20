package assignment2.musiclabelapi.model;

public record Artist(
        long id,
        String name,
        String stageName,
        String coverImage,
        String biography
) {
}
