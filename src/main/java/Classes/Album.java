package Classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

public class Album {
    private UUID album_id;
    private String title;
    private UUID artist_id;
    private UUID genre_id;
    private LocalDate release_date;
    private int popularity;
    private ArrayList<Music> tracks;

    //Constructor

    public Album(UUID album_id, String title, UUID artist_id, UUID genre_id, LocalDate release_date, int popularity, ArrayList<Music> tracks) {
        this.album_id = album_id;
        this.title = title;
        this.artist_id = artist_id;
        this.genre_id = genre_id;
        this.release_date = release_date;
        this.popularity = popularity;
        this.tracks = tracks;
    }


    //Getters

    public UUID getAlbum_id() {
        return album_id;
    }

    public String getTitle() {
        return title;
    }

    public UUID getArtist_id() {
        return artist_id;
    }

    public UUID getGenre_id() {
        return genre_id;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public int getPopularity() {
        return popularity;
    }

    public ArrayList<Music> getTracks() {
        return tracks;
    }
}
