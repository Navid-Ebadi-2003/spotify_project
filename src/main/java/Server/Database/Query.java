package Server.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class Query {
    private static Connection connection;

    public Query(Connection connection) {
        Query.connection = connection;
    }

    // A query to check whether a particular username exists or not
    public static synchronized boolean doesUsernameExist(String username) {
        final String query = """
                SELECT username FROM User
                WHERE username=?;
                """;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            // Setting the username value into the query
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            // If a match was found, then that means such a username exists, if not
            // then that means there's no such username.
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }  
        
    }

    // A query to check whether a particular email exists or not
    public static synchronized boolean doesEmailExist(String email) {
        final String query = """
                SELECT email FROM User
                WHERE email=?;
                """;
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            // Setting the email value into the query
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            // If a match was found, then that means such a email exists, if not
            // then that means there's no such email.
            if(rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }  

    }

    public static synchronized void signUpUser(UUID userId, String username, String email, String password) {
        final String query = """
                INSERT INTO User values(?, ?, ?, ?, ?, ?, ?, ?, ?);
                """; 

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, userId.toString());
            pstmt.setString(2, username);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            // Setting the profile_path value to none. That's because the user
            // hasn't set their profile picture yet
            pstmt.setString(5, null);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  

    }

    public static synchronized JsonObject getUser(UUID userId) {
        final String query = """
                SELECT * FROM User
                WHERE user_id=?;
                """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            // Setting the userId value into the query
            pstmt.setString(1, userId.toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                JsonObject userJson = new JsonObject();
                userJson.addProperty("userId", userId.toString());
                
                String username = rs.getString("username");
                userJson.addProperty("username", username);
                String email = rs.getString("email");
                userJson.addProperty("email", email);
                return userJson;
            } else {
                // Means that the user doesn't exist
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // If null is returned(and the username exists) then that means the password is incorrect
    public static synchronized JsonObject logIn(String username, String password) {
        final String query = """
                SELECT user_id,username,passowrd FROM User
                WHERE username=?;
                """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            // Setting the username value into the query
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                String storedPass = rs.getString("password");
                UUID userid = UUID.fromString(rs.getString("user_id"));
                // TODO: hash the password
                if(storedPass.equals(storedPass)) {
                    return getUser(userid);
                } else {
                    // If null then it means that the password is incorrect
                    return null;
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Editing a user's info. Users can only edit their username,
    // email or profile pictures
    public static synchronized void editUser(UUID userId, JsonObject newUserInfo) {
        final String query = """
                UPDATE User
                SET username=?, email=?, profile_path=?
                where user_id=?;
                """;


        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, newUserInfo.get("username").getAsString());
            pstmt.setString(2, newUserInfo.get("email").getAsString());
            pstmt.setString(3, newUserInfo.get("profile_path").getAsString());
            pstmt.setString(4, userId.toString());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } 
    }

    /*
     * History in general stores what users, artists and etc have done in Spotify
     * There are three columns:
     * 1: Subject: the initiator, someone or something that does the action
     * 2: Object: Someone or something being acted upon
     * 3: Action: The act that's being one
     * Type of actions:
     * ADD_ARTIST_SOCIALMEDIA: Artist adding a social media to their page
     * ADD_ARTIST_ALBUM: Artist adding an album to their page
     * ALBUM_ADD_TRACK: Adding a track to an album
     * PLAYLIST_ADD_TRACK: Adding a track to a playlist
     * USER_FOLLOW_USER: User following a user
     * USER_FOLLOW_ARTIST: User following an artist
     * USER_CREAT_PLAYLIST: User creating a playlist
     * USER_LIKE_TRACK: User liking a track
     * USER_LIKE_ALBUM: User liking a album
     * USER_LIKE_PLAYLIST: User liking a playlist
     */


    // Get all of objects corresponding to a specific subject and action
    public static synchronized JsonArray getObjectsFromHistory(UUID subject, String action) {
        final String query = """
                SELECT * FROM History
                WHERE subject=?
                AND action=?;
                """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, subject.toString());
            pstmt.setString(2, action);
            ResultSet rs = pstmt.executeQuery();
            JsonArray objects = new JsonArray();

            while(rs.next()) {
                objects.add(rs.getString("object"));;
            }

            return objects;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get all of subjects corresponding to a specific object and action
    public static synchronized JsonArray getSubjectsFromHistory(UUID object, String action) {
        final String query = """
                SELECT * FROM History
                WHERE object=?
                AND action=?;
                """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, object.toString());
            pstmt.setString(2, action);
            ResultSet rs = pstmt.executeQuery();
            JsonArray subjects = new JsonArray();

            while(rs.next()) {
                subjects.add(rs.getString("subject"));;
            }

            return subjects;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static synchronized void addToHistory(UUID subject, UUID object, String action) {
        final String query = """
                INSERT INTO History values(?, ?, ?);
                """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, subject.toString());
            pstmt.setString(2, object.toString());
            pstmt.setString(3, action);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized void deleteFromHistory(UUID subject, UUID object, String action) {
        final String query = """
            DELETE FROM History
            WHERE subject=?
            AND object=?
            AND action=?;
            """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, subject.toString());
            pstmt.setString(2, object.toString());
            pstmt.setString(3, action);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Get an artist's info from id
    public static synchronized JsonObject getArtist(UUID artistId) {
        final String query = """
                SELECT * FROM Artist
                WHERE artist_id=?;
                """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, artistId.toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                JsonObject artistJson = new JsonObject();
                artistJson.addProperty("artistId", artistId.toString());
                String name = rs.getString("name");
                artistJson.addProperty("name", name);
                String genreId = rs.getString("genre_id");
                artistJson.addProperty("genreId", genreId);
                String biography = rs.getString("biography");
                artistJson.addProperty("biography", biography);

                
                JsonArray socialMediaLinks = getObjectsFromHistory(artistId, "ADD_ARTIST_SOCIALMEDIA");
                artistJson.add("socialMediaLinks", socialMediaLinks);

                JsonArray albums = getObjectsFromHistory(artistId, "ADD_ARTIST_ALBUM");
                artistJson.add("albums", albums);

                return artistJson;
            } else {
                // If null, then that means the artist doesn't exist
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get a track's info from id
    public static synchronized JsonObject getMusic(UUID trackId) {
        final String query = """
                SELECT * FROM Music
                WHERE track_id=?;
                """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, trackId.toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                JsonObject musicJson = new JsonObject();
                musicJson.addProperty("trackId", trackId.toString());

                String title = rs.getString("title");
                musicJson.addProperty("title", title);

                String artistId = rs.getString("artist_id");
                musicJson.addProperty("artistId", artistId);

                String albumId = rs.getString("album_id");
                musicJson.addProperty("albumId", albumId);

                String genreId = rs.getString("genre_id");
                musicJson.addProperty("genreId", genreId);

                int duration = rs.getInt("duration");
                musicJson.addProperty("duration", duration);

                String releaseDate = rs.getString("release_date");
                musicJson.addProperty("releaseDate", releaseDate);

                int popularity = rs.getInt("popularity");
                musicJson.addProperty("popularity", popularity);

                return musicJson;
            } else {
                // If null, then that means the track doesn't exist
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get an album's info from id
    public static synchronized JsonObject getAlbum(UUID albumId) {
        final String query = """
                SELECT * FROM Album
                WHERE album_id=?;
                """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, albumId.toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                JsonObject albumJson = new JsonObject();
                albumJson.addProperty("albumId", albumId.toString());

                String title = rs.getString("title");
                albumJson.addProperty("title", title);

                String artistId = rs.getString("artist_id");
                albumJson.addProperty("artistId", artistId);

                String genreId = rs.getString("genre_id");
                albumJson.addProperty("genreId", genreId);

                String releaseDate = rs.getString("release_date");
                albumJson.addProperty("releaseDate", releaseDate);

                int popularity = rs.getInt("popularity");
                albumJson.addProperty("popularity", popularity);

                JsonArray tracks = getObjectsFromHistory(albumId, "ALBUM_ADD_TRACK");
                albumJson.add("tracks", tracks);

                return albumJson;
            } else {
                // If null, then that means the album doesn't exist
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Get a playlist's info from id
    public static synchronized JsonObject getPlaylist(UUID playlistId) {
        final String query = """
                SELECT * FROM Playlist
                WHERE playlist_id=?;
                """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, playlistId.toString());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                JsonObject albumJson = new JsonObject();
                albumJson.addProperty("playlistId", playlistId.toString());

                String title = rs.getString("title");
                albumJson.addProperty("title", title);

                String description = rs.getString("description");
                albumJson.addProperty("description", description);

                String userId = rs.getString("user_id");
                albumJson.addProperty("userId", userId);

                int popularity = rs.getInt("popularity");
                albumJson.addProperty("popularity", popularity);

                int isPrivate = rs.getInt("is_private");
                albumJson.addProperty("isPrivate", isPrivate);

                JsonArray tracks = getObjectsFromHistory(playlistId, "PLAYLIST_ADD_TRACK");
                albumJson.add("tracks", tracks);

                return albumJson;
            } else {
                // If null, then that means the album doesn't exist
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
}

    // Follow/unfollow a user
    public static synchronized void toggleFollowUser(UUID selfId, UUID userId) {
        final String query = """
            SELECT * FROM History
            WHERE subject=?
            AND object=?
            AND action=?;
            """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, selfId.toString());
            pstmt.setString(2, userId.toString());
            pstmt.setString(3, "USER_FOLLOW_USER");
            ResultSet rs = pstmt.executeQuery();
            // If the user has followed the user before, then they're going to unfollow.
            // else they're going to follow
            if(rs.next()) {
                deleteFromHistory(selfId, userId, "USER_FOLLOW_USER");
            } else {
                addToHistory(selfId, userId, "USER_FOLLOW_USER");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    // Follow/unfollow an artist
    public static synchronized void toggleFollowArtist(UUID userId, UUID artistId) {
        final String query = """
            SELECT * FROM History
            WHERE subject=?
            AND object=?
            AND action=?;
            """;

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, userId.toString());
                pstmt.setString(2, artistId.toString());
                pstmt.setString(3, "USER_FOLLOW_ARTIST");
                ResultSet rs = pstmt.executeQuery();
                // If the user has followed the artist before, then they're going to unfollow.
                // else they're going to follow
                if(rs.next()) {
                    deleteFromHistory(userId, artistId, "USER_FOLLOW_ARTIST");
                } else {
                    addToHistory(userId, artistId, "USER_FOLLOW_ARTIST");
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
    }

    // Like/unlike a track
    public static synchronized void toggleLikeTrack(UUID userId, UUID trackId) {
        final String query = """
            SELECT * FROM History
            WHERE subject=?
            AND object=?
            AND action=?;
            """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, userId.toString());
            pstmt.setString(2, trackId.toString());
            pstmt.setString(3, "USER_LIKE_TRACK");
            ResultSet rs = pstmt.executeQuery();
            // If the user has liked the track before, then they're going to unlike.
            // else they're going to like
            if(rs.next()) {
                deleteFromHistory(userId, trackId, "USER_LIKE_TRACK");
            } else {
                addToHistory(userId, trackId, "USER_LIKE_TRACK");
            }
            JsonArray trackLikes = getSubjectsFromHistory(trackId, "USER_LIKE_TRACK");
            updateMusicPopularity(trackId, trackLikes.size());     
            
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    // Like/unlike an album
    public static synchronized void toggleLikeAlbum(UUID userId, UUID albumId) {
        final String query = """
            SELECT * FROM History
            WHERE subject=?
            AND object=?
            AND action=?;
            """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, userId.toString());
            pstmt.setString(2, albumId.toString());
            pstmt.setString(3, "USER_LIKE_ALBUM");
            ResultSet rs = pstmt.executeQuery();
            // If the user has liked the album before, then they're going to unlike.
            // else they're going to like
            if(rs.next()) {
                deleteFromHistory(userId, albumId, "USER_LIKE_ALBUM");
            } else {
                addToHistory(userId, albumId, "USER_LIKE_ALBUM");
            }
            JsonArray albumLikes = getSubjectsFromHistory(albumId, "USER_LIKE_ALBUM");
            updateAlbumPopularity(albumId, albumLikes.size());            

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    // Like/unlike a plalist
    public static synchronized void toggleLikePlaylist(UUID userId, UUID playlistId) {
        final String query = """
            SELECT * FROM History
            WHERE subject=?
            AND object=?
            AND action=?;
            """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, userId.toString());
            pstmt.setString(2, playlistId.toString());
            pstmt.setString(3, "USER_LIKE_PLAYLIST");
            ResultSet rs = pstmt.executeQuery();
            // If the user has liked the playlist before, then they're going to unlike.
            // else they're going to like
            if(rs.next()) {
                deleteFromHistory(userId, playlistId, "USER_LIKE_PLAYLIST");
            } else {
                addToHistory(userId, playlistId, "USER_LIKE_PLAYLIST");
            }
            JsonArray playlistLikes = getSubjectsFromHistory(playlistId, "USER_LIKE_PLAYLIST");
            updatePlaylistPopularity(playlistId, playlistLikes.size());            

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    public static synchronized void updateMusicPopularity(UUID trackId, int popularity) {
        final String query = """
                UPDATE Music
                SET popularity=?
                WHERE track_id=?;
                """;

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, popularity);
                pstmt.setString(2, trackId.toString());
                pstmt.executeUpdate();
                
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
    }

    public static synchronized void updatePlaylistPopularity(UUID playlistId, int popularity) {
        final String query = """
                UPDATE Playlist
                SET popularity=?
                WHERE playlist_id=?;
                """;

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, popularity);
                pstmt.setString(2, playlistId.toString());
                pstmt.executeUpdate();
                
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
    }

    public static synchronized void updateAlbumPopularity(UUID albumId, int popularity) {
        final String query = """
                UPDATE Album
                SET popularity=?
                WHERE album_id=?;
                """;

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1, popularity);
                pstmt.setString(2, albumId.toString());
                pstmt.executeUpdate();
                
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
    }


    public static synchronized String getMusicPath(UUID trackId){
        final String query = """
            SELECT * FROM Music
            WHERE track_id=?;
            """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, trackId.toString());
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return rs.getString("profile_path");
            } else {
                return null;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized String getUserPicPath(UUID userId){
        final String query = """
            SELECT * FROM User
            WHERE user_id=?;
            """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, userId.toString());
            ResultSet rs = pstmt.executeQuery();

            if(rs.next()) {
                return rs.getString("profile_path");
            } else {
                return null;
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized void createPlaylist(UUID userId, JsonObject playListJson) {
        final String query = """
            INSERT INTO Playlist values(?, ?, ?, ?, ?, ?, ?, ?);
            """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, playListJson.get("playlistId").getAsString());
            pstmt.setString(2, playListJson.get("title").getAsString());
            pstmt.setString(3, playListJson.get("description").getAsString());
            pstmt.setString(4, playListJson.get("userId").getAsString());
            pstmt.setInt(5, playListJson.get("popularity").getAsInt());
            pstmt.setString(6, playListJson.get("profilePath").getAsString());
            pstmt.setInt(7, playListJson.get("isPrivate").getAsInt());

            pstmt.executeUpdate();

            UUID playlistId = UUID.fromString(playListJson.get("playlistId").getAsString());

            addToHistory(userId, playlistId, "USER_CREAT_PLAYLIST");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static synchronized JsonArray getUsersLikedTracks(UUID userId) {
        return getObjectsFromHistory(userId, "USER_LIKE_TRACK");
    }

    public static synchronized JsonArray getUsersPlaylists(UUID userId) {
        return getObjectsFromHistory(userId, "USER_CREAT_PLAYLIST");
    }
}
