package Server.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
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
                INSERT INTO User values(?, ?, ?, ?, ?);
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
    public static synchronized UUID logIn(String username, String password) {
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
                UUID userId = UUID.fromString(rs.getString("user_id"));
                // TODO: hash the password
                if(storedPass.equals(storedPass)) {
                    return userId;
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

    // Editing a user's info. Users can only edit their username, email or profile pictures
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
     * USER_CREATE_PLAYLIST: User creating a playlist
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

                JsonArray artists = getSubjectsFromHistory(trackId, "ARTIST_ADD_TRACK");
                musicJson.add("artists", artists);

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

                String profilePath = rs.getString("profile_path");
                musicJson.addProperty("profilePath", profilePath);
    
                String trackPath = rs.getString("track_path");
                musicJson.addProperty("trackPath", trackPath);

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

                String profilePath = rs.getString("profile_path");
                albumJson.addProperty("profilePath", profilePath);

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
                JsonObject playlistJson = new JsonObject();
                playlistJson.addProperty("playlistId", playlistId.toString());

                String title = rs.getString("title");
                playlistJson.addProperty("title", title);

                String description = rs.getString("description");
                playlistJson.addProperty("description", description);

                String userId = rs.getString("user_id");
                playlistJson.addProperty("userId", userId);

                int popularity = rs.getInt("popularity");
                playlistJson.addProperty("popularity", popularity);

                int isPrivate = rs.getInt("is_private");
                playlistJson.addProperty("isPrivate", isPrivate);
        
                String profilePath = rs.getString("profile_path");
                playlistJson.addProperty("profilePath", profilePath);

                JsonArray tracks = getObjectsFromHistory(playlistId, "PLAYLIST_ADD_TRACK");
                playlistJson.add("tracks", tracks);

                return playlistJson;
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
            INSERT INTO Playlist values(?, ?, ?, ?, ?, ?, ?);
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

            addToHistory(userId, playlistId, "USER_CREATE_PLAYLIST");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // get all existing tracks' id
    public static synchronized JsonArray getAllTracksId() {
        final String query = """
            SELECT track_id FROM Music;
            """;


        try {
            JsonArray tracks = new JsonArray();
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                tracks.add(rs.getString("track_id"));
            }
            return tracks;
            
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // All methods related to homepage

    // get some random number of tracks
    public static synchronized JsonArray getRandomTracks(int amount) {
        // get all tracks
        JsonArray allTracksId = getAllTracksId();
        // select some random tracks
        JsonArray randomTracksId = getRandomElements(allTracksId, amount);

        JsonArray randomTracks = new JsonArray();

        for(JsonElement randomTrackId: randomTracksId) {
            UUID trackId = UUID.fromString(randomTrackId.getAsString());
            // get music's meta-data
            randomTracks.add(getMusic(trackId));
        }
        return randomTracks;

    }

    // get all tracks a given user has liked
    public static synchronized JsonArray getLikedTracks(UUID userId) {
        // get all user's liked tracks
        JsonArray likedTracksId = getObjectsFromHistory(userId, "USER_LIKE_TRACK");
        JsonArray likedTracks = new JsonArray();
        for(JsonElement trackIdString: likedTracksId) {
            UUID trackId = UUID.fromString(trackIdString.getAsString());
            // get music's meta-data
            likedTracks.add(getMusic(trackId));
        }
        return likedTracks;

    }

    // get all playlists a given user has created
    public static synchronized JsonArray getCreatedPlaylists(UUID userId) {
        // get all user's created playlists
        JsonArray createdPlaylistId = getObjectsFromHistory(userId, "USER_CREATE_PLAYLIST");
        JsonArray createdPlaylist = new JsonArray();
        for(JsonElement playlistIdString: createdPlaylistId) {
            UUID playlistId = UUID.fromString(playlistIdString.getAsString());
            // get playlist's meta-data
            createdPlaylist.add(getMusic(playlistId));
        }
        return createdPlaylist;
    }

    // get all playlists a given user has liked
    public static synchronized JsonArray getLikedPlaylists(UUID userId) {
        JsonArray likedPlaylistId = getObjectsFromHistory(userId, "USER_LIKE_PLAYLIST");
        JsonArray likedPlaylist = new JsonArray();
        for(JsonElement playlistIdString: likedPlaylistId) {
            UUID playlistId = UUID.fromString(playlistIdString.getAsString());
            // get playlist's meta-data
            likedPlaylist.add(getPlaylist(playlistId));
        }
        return likedPlaylist;
    }

    // get all albums a given user has liked
    public static synchronized JsonArray getLikedAlbums(UUID userId) {
        JsonArray likedAlbumIds = getObjectsFromHistory(userId, "USER_LIKE_likedALBUM");
        JsonArray likedAlbums = new JsonArray();
        for(JsonElement likedAlbumIdString: likedAlbumIds) {
            UUID likedAlbumId = UUID.fromString(likedAlbumIdString.getAsString());
            // get likedalbum's meta-data
            likedAlbums.add(getAlbum(likedAlbumId));
        }
        return likedAlbums;
    }

    // get all artists a given user has followed
    public static synchronized JsonArray getFollowedArtists(UUID userId) {
        JsonArray artistsId = getObjectsFromHistory(userId, "USER_FOLLOW_ARTIST");
        JsonArray artists = new JsonArray();
        for(JsonElement artistIdString: artistsId) {
            UUID artistId = UUID.fromString(artistIdString.getAsString());
            // get artist's meta-data
            artists.add(getArtist(artistId));
        }
        return artists;
    }

    // get all users a given user has followed
    public static synchronized JsonArray getFollowedUsers(UUID userId) {
        JsonArray followedUsersId = getObjectsFromHistory(userId, "USER_FOLLOW_USER");
        JsonArray followedUserdId = new JsonArray();
        for(JsonElement followedUserIdString: followedUsersId) {
            UUID followedUserId = UUID.fromString(followedUserIdString.getAsString());
            // get user's meta-data
            followedUserdId.add(getUser(followedUserId));
        }
        return followedUserdId;
    }

    // get all followed users' playlists of a given user (if they're public)
    public static synchronized JsonArray getFollowedUsersPlaylists(UUID userId) {
        JsonArray followedUsers = getFollowedUsers(userId);
        JsonArray usersPlaylists = new JsonArray();

        // loop over users and get the their playlists
        for(JsonElement followedUserElement: followedUsers) {
            UUID followedUserId = UUID.fromString(followedUserElement.getAsString());
            JsonArray playlists = getCreatedPlaylists(followedUserId);
            for(JsonElement playlist: playlists) {
                UUID playlistId = UUID.fromString(playlist.getAsString());
                JsonObject playlistJson = getPlaylist(playlistId);
                // checking whether a playlists is private or not
                if(playlistJson.get("isPrivate").getAsInt() == 0) {
                    usersPlaylists.add(playlistJson);
                }
            }
        }
        return usersPlaylists;
    }

    // get all meta data a user needs for their homepage
    public static synchronized JsonObject getHomePage(UUID userId) {
        JsonObject homePageJson = new JsonObject();
        homePageJson.add("likedMusicsResult", getLikedTracks(userId));
        homePageJson.add("createdPlaylistsResult", getCreatedPlaylists(userId));
        homePageJson.add("likedPlaylistsResult", getLikedPlaylists(userId));
        homePageJson.add("randomMusicsResult", getRandomTracks(8));

        return homePageJson;
    }



    // Miscellaneous methods

    // select some random elements from a json array
    public static JsonArray getRandomElements(JsonArray jsonArray, int totalItems) {
        Random rand = new Random();
 
        JsonArray newArray = new JsonArray();
        for (int i = 0; i < totalItems; i++) {
 
            // take a random index between 0 to size of given jsonArray
            int randomIndex = rand.nextInt(jsonArray.size());
 
            // add element in temporary jsonArray
            newArray.add(jsonArray.get(randomIndex));
        }
        return newArray;
    }
}
