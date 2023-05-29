package Shared.Classes;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private UUID userId;
    private String username;
    private String email;
    private String address;
    private String password;
    private String profilePath;
    private ArrayList<Playlist> createdPlaylists;
    private ArrayList<Playlist> likedPlaylists;

    //Constructor

    public User(UUID userId, String username, String email, String address, String password, String profilePath, ArrayList<Playlist> createdPlaylists, ArrayList<Playlist> likedPlaylists) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.address = address;
        this.password = password;
        this.profilePath = profilePath;
        this.createdPlaylists = createdPlaylists;
        this.likedPlaylists = likedPlaylists;
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

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public ArrayList<Playlist> getCreatedPlaylists() {
        return createdPlaylists;
    }

    public ArrayList<Playlist> getLikedPlaylists() {
        return likedPlaylists;
    }
}
