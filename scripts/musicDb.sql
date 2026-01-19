-- Create the database
CREATE DATABASE musicDb;

-- Connect to the database
\c musicDb;

CREATE TABLE MusicLabel (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(50) NOT NULL,
                            description TEXT
);

CREATE TABLE Artist (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        stageName VARCHAR(50),
                        coverImage TEXT,
                        biography TEXT
);

CREATE TABLE Album (
                       id SERIAL PRIMARY KEY,
                       labelId BIGINT REFERENCES MusicLabel(id),
                       title VARCHAR(50) NOT NULL,
                       genre VARCHAR(100),
                       releaseYear SMALLINT,
                       coverImage TEXT
);

CREATE TABLE AlbumArtist (
                             albumId BIGINT REFERENCES Album(id),
                             artistId BIGINT REFERENCES Artist(id),
                             PRIMARY KEY (albumId, artistId)
);