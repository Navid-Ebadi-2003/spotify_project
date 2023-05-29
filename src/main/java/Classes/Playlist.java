package Classes;

import java.util.ArrayList;
import java.util.UUID;

public class Playlist {
    private UUID playlist_id;
    private String title;
    private String description;
    private UUID user_id;
    private int popularity;
    private ArrayList<Music> tracks;

    //Constructor

    public Playlist(UUID playlist_id, String title, String description, UUID user_id, int popularity, ArrayList<Music> tracks) {
        this.playlist_id = playlist_id;
        this.title = title;
        this.description = description;
        this.user_id = user_id;
        this.popularity = popularity;
        this.tracks = tracks;
    }

    //Getters

    public UUID getPlaylist_id() {
        return playlist_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public int getPopularity() {
        return popularity;
    }

    public ArrayList<Music> getTracks() {
        return tracks;
    }
}
