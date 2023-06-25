package Shared.Classes;

import java.util.UUID;

public class Genre {
    private final UUID genreId;
    private final String name;
    private final String description;

    //Constructor

    public Genre(UUID genreId, String name, String description) {
        this.genreId = genreId;
        this.name = name;
        this.description = description;
    }

    //Getters

    public UUID getGenreId() {
        return genreId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
