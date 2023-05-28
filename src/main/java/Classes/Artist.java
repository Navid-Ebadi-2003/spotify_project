package Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Artist {
    private UUID artist_id;
    private String name;
    private UUID genre_id;
    private ArrayList<Genre> genres;
    private String biography;
    private HashMap<String, String> social_media_links;
    private ArrayList<Album> albums;

    //Constructor

    public Artist(UUID artist_id, String name, UUID genre_id, ArrayList<Genre> genres, String biography, HashMap<String, String> social_media_links, ArrayList<Album> albums) {
        this.artist_id = artist_id;
        this.name = name;
        this.genre_id = genre_id;
        this.genres = genres;
        this.biography = biography;
        this.social_media_links = social_media_links;
        this.albums = albums;
    }

    //Getters

    public UUID getArtist_id() {
        return artist_id;
    }

    public String getName() {
        return name;
    }

    public UUID getGenre_id() {
        return genre_id;
    }

    public ArrayList<Genre> getGenres() {
        return genres;
    }

    public String getBiography() {
        return biography;
    }

    public HashMap<String, String> getSocial_media_links() {
        return social_media_links;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
