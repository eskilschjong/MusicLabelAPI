
INSERT INTO MusicLabel (name, description) VALUES
   ('Sunset Records', 'Independent label focusing on indie and alternative music.'),
   ('NeonWave Music', 'Electronic and synthwave focused label.'),
   ('Golden Era Studios', 'Classic rock and soul music label.');

INSERT INTO Artist (name, stageName, coverImage, biography) VALUES
    ('Alice Johnson', 'AJay', NULL, 'Indie-pop singer and songwriter.'),
    ('Marcus Lee', 'M-Lee', NULL, 'Hip-hop and R&B artist.'),
    ('Sophie Turner', 'Sofi T', NULL, 'Electronic music producer.'),
    ('Daniel Rivera', 'D-Riv', NULL, 'Latin pop artist.'),
    ('Hannah Smith', 'H-Smith', NULL, 'Folk and acoustic performer.'),
    ('Liam Carter', 'LC', NULL, 'Rock guitarist and vocalist.'),
    ('Nora Berg', 'Nora B', NULL, 'Synthwave and electronic artist.'),
    ('Ethan Walker', 'E-Walk', NULL, 'Alternative rock singer.'),
    ('Maya Patel', 'Maya P', NULL, 'Soul and jazz vocalist.'),
    ('Jonas Eriksen', 'J-Erik', NULL, 'Experimental electronic artist.');

INSERT INTO Album (labelId, title, genre, releaseYear, coverImage) VALUES
   (1, 'Indigo Dreams', 'Indie Pop', 2021, NULL),
   (1, 'Acoustic Echoes', 'Folk', 2023, NULL);

INSERT INTO Album (labelId, title, genre, releaseYear, coverImage) VALUES
   (2, 'Neon Skies', 'Synthwave', 2022, NULL),
   (2, 'Electric Pulse', 'Electronic', 2024, NULL);

INSERT INTO Album (labelId, title, genre, releaseYear, coverImage) VALUES
    (3, 'Golden Horizon', 'Rock', 2020, NULL),
    (3, 'Soul Reflections', 'Soul', 2023, NULL);

INSERT INTO AlbumArtist (albumId, artistId)
VALUES (1, 1),
       (1, 5);

INSERT INTO AlbumArtist (albumId, artistId)
VALUES (2, 5),
       (2, 8);

INSERT INTO AlbumArtist (albumId, artistId)
VALUES (3, 3),
       (3, 7);

INSERT INTO AlbumArtist (albumId, artistId)
VALUES (4, 3),
       (4, 10);

INSERT INTO AlbumArtist (albumId, artistId)
VALUES (5, 6),
       (5, 8);

INSERT INTO AlbumArtist (albumId, artistId)
VALUES (6, 9),
       (6, 2);