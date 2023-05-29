package Shared.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Artist {
    private UUID artistId;
    private String name;
    private UUID genreId;
    private ArrayList<UUID> genres;
    private String biography;
    private HashMap<String, String> socialMediaLinks;
    private ArrayList<UUID> albums;

    //Constructor

    public Artist(UUID artistId, String name, UUID genreId, ArrayList<UUID> genres, String biography, HashMap<String, String> socialMediaLinks, ArrayList<UUID> albums) {
        this.artistId = artistId;
        this.name = name;
        this.genreId = genreId;
        this.genres = genres;
        this.biography = biography;
        this.socialMediaLinks = socialMediaLinks;
        this.albums = albums;
    }

    //Getters

    public UUID getArtistId() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public UUID getGenreId() {
        return genreId;
    }

    public ArrayList<UUID> getGenres() {
        return genres;
    }

    public String getBiography() {
        return biography;
    }

    public HashMap<String, String> getSocialMediaLinks() {
        return socialMediaLinks;
    }

    public ArrayList<UUID> getAlbums() {
        return albums;
    }
}
