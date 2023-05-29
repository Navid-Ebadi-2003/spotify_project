package Shared.Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Album {
    private UUID albumId;
    private String title;
    private UUID artistId;
    private UUID genreId;
    private LocalDate releaseDate;
    private int popularity;
    private ArrayList<Music> tracks;

    //Constructor

    public Album(UUID albumId, String title, UUID artistId, UUID genreId, LocalDate releaseDate, int popularity, ArrayList<Music> tracks) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
        this.genreId = genreId;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.tracks = tracks;
    }


    //Getters

    public UUID getAlbumId() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }

    public UUID getArtistId() {
        return artistId;
    }

    public UUID getGenreId() {
        return genreId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getPopularity() {
        return popularity;
    }

    public ArrayList<Music> getTracks() {
        return tracks;
    }
}
