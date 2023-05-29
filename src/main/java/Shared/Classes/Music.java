package Shared.Classes;

import java.time.LocalDate;
import java.util.UUID;

public class Music {
    private UUID trackId;
    private String title;
    private UUID artistId;
    private UUID albumId;
    private UUID genreId;
    private int duration;
    private LocalDate releaseDate;
    private int popularity;

    //Constructor

    public Music(UUID trackId, String title, UUID artistId, UUID albumId, UUID genreId, int duration, LocalDate releaseDate, int popularity) {
        this.trackId = trackId;
        this.title = title;
        this.artistId = artistId;
        this.albumId = albumId;
        this.genreId = genreId;
        this.duration = duration;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
    }

    //Getters

    public UUID getTrackId() {
        return trackId;
    }

    public String getTitle() {
        return title;
    }

    public UUID getArtistId() {
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
}
