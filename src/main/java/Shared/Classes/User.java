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
    private ArrayList<UUID> createdPlaylists;
    private ArrayList<UUID> likedPlaylists;

    //Constructor

    public User(UUID userId, String username, String email, String address, String password, String profilePath, ArrayList<UUID> createdPlaylists, ArrayList<UUID> likedPlaylists) {
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

    public ArrayList<UUID> getCreatedPlaylists() {
        return createdPlaylists;
    }

    public ArrayList<UUID> getLikedPlaylists() {
        return likedPlaylists;
    }
}
