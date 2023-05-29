package Classes;

import java.util.UUID;

public class Genre {
    private UUID genre_id;
    private String name;
    private String description;

    //Constructor

    public Genre(UUID genre_id, String name, String description) {
        this.genre_id = genre_id;
        this.name = name;
        this.description = description;
    }

    //Getters

    public UUID getGenre_id() {
        return genre_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
