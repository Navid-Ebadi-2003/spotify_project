package Shared.Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Album {
    private final UUID albumId;
    private final String title;

    // Changed datatype of artistId to ArrayList<UUID> because an album could belong to multiple artists
    private final ArrayList<UUID> artistId;
    private final UUID genreId;
    private final LocalDate releaseDate;
    private final int popularity;
    private final ArrayList<UUID> tracks;

    //added profilePath because each Album has a profile Picture
    private final String profilePath;

    //Constructor

    public Album(UUID albumId, String title, ArrayList<UUID> artistId, UUID genreId, LocalDate releaseDate, int popularity, ArrayList<UUID> tracks, String profilePath) {
        this.albumId = albumId;
        this.title = title;
        this.artistId = artistId;
        this.genreId = genreId;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.tracks = tracks;
        this.profilePath = profilePath;
    }


    //Getters

    public UUID getAlbumId() {
        return albumId;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<UUID> getArtistId() {
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

    public ArrayList<UUID> getTracks() {
        return tracks;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
