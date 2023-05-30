package Shared.Classes;

import java.util.ArrayList;
import java.util.UUID;

public class Playlist {
    private UUID playlistId;
    private String title;
    private String description;
    private UUID userId;
    private int popularity;
    private ArrayList<UUID> tracks;

    //added profilePath because each playlist has a profile picture
    private String profilePath;

    //Constructor

    public Playlist(UUID playlistId, String title, String description, UUID userId, int popularity, ArrayList<UUID> tracks, String profile_path) {
        this.playlistId = playlistId;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.popularity = popularity;
        this.tracks = tracks;
        this.profilePath = profile_path;
    }


    //Getters

    public UUID getPlaylistId() {
        return playlistId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public UUID getUserId() {
        return userId;
    }

    public int getPopularity() {
        return popularity;
    }

    public ArrayList<UUID> getTracks() {
        return tracks;
    }

    public String getProfile_path() {
        return profilePath;
    }
}
