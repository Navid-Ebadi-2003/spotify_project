package Classes;

import java.util.ArrayList;
import java.util.UUID;

public class User {
    private UUID user_id;
    private String username;
    private String email;
    private String address;
    private String password;
    private String profile_path;
    private ArrayList<Playlist> created_playlists;
    private ArrayList<Playlist> liked_playlists;

    //Constructor

    public User(UUID user_id, String username, String email, String address, String password, String profile_path, ArrayList<Playlist> created_playlists, ArrayList<Playlist> liked_playlists) {
        this.user_id = user_id;
        this.username = username;
        this.email = email;
        this.address = address;
        this.password = password;
        this.profile_path = profile_path;
        this.created_playlists = created_playlists;
        this.liked_playlists = liked_playlists;
    }

    //Getters

    public UUID getUser_id() {
        return user_id;
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

    public String getProfile_path() {
        return profile_path;
    }

    public ArrayList<Playlist> getCreated_playlists() {
        return created_playlists;
    }

    public ArrayList<Playlist> getLiked_playlists() {
        return liked_playlists;
    }
}
