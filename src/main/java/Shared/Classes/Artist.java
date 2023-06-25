package Shared.Classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Artist {
    private final UUID artistId;
    private final String name;
    private final UUID genreId;
    // Removed genres because it's simpler for artists to have only a genre
    private final String biography;
    private final HashMap<String, String> socialMediaLinks;
    private final ArrayList<UUID> albums;

    //added profilePath because each Artist has a profile picture
    private final String profilePath;

    //Constructor

    public Artist(UUID artistId, String name, UUID genreId, String biography, HashMap<String, String> socialMediaLinks, ArrayList<UUID> albums, String profilePath) {
        this.artistId = artistId;
        this.name = name;
        this.genreId = genreId;
        this.biography = biography;
        this.socialMediaLinks = socialMediaLinks;
        this.albums = albums;
        this.profilePath = profilePath;
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

    public String getBiography() {
        return biography;
    }

    public HashMap<String, String> getSocialMediaLinks() {
        return socialMediaLinks;
    }

    public ArrayList<UUID> getAlbums() {
        return albums;
    }

    public String getProfilePath() {
        return profilePath;
    }
}
