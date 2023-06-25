package Shared.Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Music {
    private final UUID trackId;
    private final String title;
    // Changed datatype of artistId to ArrayList<UUID> because a music could belong to multiple artists
    private final ArrayList<UUID> artistId;
    private final UUID albumId;
    private final UUID genreId;
    private final int duration;
    private final LocalDate releaseDate;
    private final int popularity;
    //added profilePath because each Music has a profile picture
    private final String trackPath;

    //Constructor

    public Music(UUID trackId, String title, ArrayList<UUID> artistId, UUID albumId, UUID genreId, int duration, LocalDate releaseDate, int popularity, String trackPath) {
        this.trackId = trackId;
        this.title = title;
        this.artistId = artistId;
        this.albumId = albumId;
        this.genreId = genreId;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.trackPath = trackPath;
    }


    //Getters

    public UUID getTrackId() {
        return trackId;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<UUID> getArtistId() {
        return artistId;
    }

    public UUID getAlbumId() {
        return albumId;
    }

    public UUID getGenreId() {
        return genreId;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getTrackPath() {
        return trackPath;
    }
}
