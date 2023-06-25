package Shared.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class User {
    private final UUID userId;
    private final String username;
    private final String email;
    private final String password;
    private final String profilePath;

    //Hashmap because it has to save either if the playlist is public or private, if true:public, false:private
    private final HashMap<UUID, Boolean> createdPlaylists;
    private final HashMap<UUID, Boolean> likedPlaylists;

    //added followers and following because users can follow artists and users and could be followed by other users
    private final ArrayList<UUID> followers;
    private final ArrayList<UUID> followings;
    private final ArrayList<UUID> likedTracks;

    //Constructor

    public User(UUID userId, String username, String email, String password, String profilePath, HashMap<UUID, Boolean> createdPlaylists, HashMap<UUID, Boolean> likedPlaylists, ArrayList<UUID> followers, ArrayList<UUID> followings, ArrayList<UUID> likedTracks) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.profilePath = profilePath;
        this.createdPlaylists = createdPlaylists;
        this.likedPlaylists = likedPlaylists;
        this.followers = followers;
        this.followings = followings;
        this.likedTracks = likedTracks;
    }


    //Getters

    public UUID getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public HashMap<UUID, Boolean> getCreatedPlaylists() {
        return createdPlaylists;
    }

    public HashMap<UUID, Boolean> getLikedPlaylists() {
        return likedPlaylists;
    }

    public ArrayList<UUID> getFollowers() {
        return followers;
    }

    public ArrayList<UUID> getFollowings() {
        return followings;
    }

    public ArrayList<UUID> getLikedTracks() {
        return likedTracks;
    }
}
