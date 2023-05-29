package Classes;

import java.time.LocalDate;
import java.util.UUID;

public class Music {
    private UUID track_id;
    private String title;
    private UUID artist_id;
    private UUID album_id;
    private UUID genre_id;
    private int duration;
    private LocalDate release_date;
    private int popularity;

    //Constructor

    public Music(UUID track_id, String title, UUID artist_id, UUID album_id, UUID genre_id, int duration, LocalDate release_date, int popularity) {
        this.track_id = track_id;
        this.title = title;
        this.artist_id = artist_id;
        this.album_id = album_id;
        this.genre_id = genre_id;
        this.duration = duration;
        this.release_date = release_date;
        this.popularity = popularity;
    }

    //Getters

    public UUID getTrack_id() {
        return track_id;
    }

    public String getTitle() {
        return title;
    }

    public UUID getArtist_id() {
        return artist_id;
    }

    public UUID getAlbum_id() {
        return album_id;
    }

    public UUID getGenre_id() {
        return genre_id;
    }

    public int getDuration() {
        return duration;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public int getPopularity() {
        return popularity;
    }
}
