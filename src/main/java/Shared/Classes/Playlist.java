package Shared.Classes;

import java.util.ArrayList;
import java.util.UUID;

public class Playlist {
    private final UUID playlistId;
    private final String title;
    private final String description;
    private final UUID userId;
    private final int popularity;
    private final ArrayList<UUID> tracks;

    //added profilePath because each playlist has a profile picture
    private final String profilePath;

    //Constructor

    public Playlist(UUID playlistId, String title, String description, UUID userId, int popularity, ArrayList<UUID> tracks, String profilePath) {
        this.playlistId = playlistId;
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.popularity = popularity;
        this.tracks = tracks;
        this.profilePath = profilePath;
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

    public String getProfilePath() {
        return profilePath;
    }
}
