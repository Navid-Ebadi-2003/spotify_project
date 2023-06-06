package Server.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import Shared.Classes.Artist;
import Shared.Classes.User;
import java.util.Map;
import java.util.Set;


public class Query {
    private Connection connection;

    public Query(Connection connection) {
        this.connection = connection;
    }

    // A query to check whether a particular username exists or not
    public boolean doesUsernameExist(String username) {
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
    public boolean doesEmailExist(String email) {
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

    public void signUpUser(UUID userId, String username, String email, String password) {
        final String query = """
                INSERT INTO User values(?, ?, ?, ?, ?, ?, ?, ?, ?);
                """; 

        final JsonObject emptyJson = new JsonObject();

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1, userId.toString());
            pstmt.setString(2, username);
            pstmt.setString(3, email);
            pstmt.setString(4, password);
            // Setting the profile_path value to none. That's because the user
            // hasn't set their profile picture yet
            pstmt.setString(5, null);
            // Setting created_playlist ,like_playlist, followers and following as
            // empty jsons. That's because the user hasn't done anything yet.
            pstmt.setString(6, emptyJson.toString());
            pstmt.setString(7, emptyJson.toString());
            pstmt.setString(8, emptyJson.toString());
            pstmt.setString(9, emptyJson.toString());
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }  

    }

    public User getUser(UUID userId) {
        final String query = """
                SELECT * FROM User
                WHERE user_id=?;
                """;

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                // Setting the email value into the query
                pstmt.setString(1, userId.toString());
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    String username = rs.getString("username");
                    String email = rs.getString("email");

                    JsonObject createdPlaylistsJson = new Gson().fromJson(rs.getString("created_playlists"), JsonObject.class);
                    HashMap<UUID, Boolean> createdPlaylists = jsonToHashmap(createdPlaylistsJson);

                    JsonObject likedPlaylistsJson = new Gson().fromJson(rs.getString("liked_playlists"), JsonObject.class);
                    HashMap<UUID, Boolean> likedPlaylists = jsonToHashmap(likedPlaylistsJson);

                    JsonArray followersJson = new Gson().fromJson(rs.getString("followers"), JsonArray.class);
                    ArrayList<UUID> followers = jsonArrayToArrayList(followersJson);

                    JsonArray followingJson = new Gson().fromJson(rs.getString("following"), JsonArray.class);
                    ArrayList<UUID> following = jsonArrayToArrayList(followingJson);

                    User user = new User(userId, username, email, "", "", createdPlaylists, likedPlaylists, followers, following);
                    return user;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    public JsonObject getArtist(UUID artistId) {
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

                    
                    JsonObject socialMediaLinks = new Gson().fromJson(rs.getString("social_media_links"), JsonObject.class);
                    artistJson.add("socialMediaLinks", socialMediaLinks);

                    JsonObject albums = new Gson().fromJson(rs.getString("albums"), JsonObject.class);
                    artistJson.add("albums", albums);

                    return artistJson;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    public JsonObject getMusic(UUID trackId) {
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
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    public JsonObject getAlbum(UUID albumId) {
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

                    JsonObject tracks = new Gson().fromJson(rs.getString("tracks"), JsonObject.class);
                    albumJson.add("tracks", tracks);

                    return albumJson;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    public JsonObject getPlaylist(UUID playlistId) {
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

                    JsonObject tracks = new Gson().fromJson(rs.getString("tracks"), JsonObject.class);
                    albumJson.add("tracks", tracks);

                    return albumJson;
                } else {
                    return null;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
    }

    public void toggleFollowUser(UUID selfId, UUID userId) {
        final String query = """
            SELECT user_id, followers FROM User
            WHERE user_id=?;
            """;

            try {
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setString(1, userId.toString());
                ResultSet rs = pstmt.executeQuery();
                rs.next();
                JsonArray followersJson = new Gson().fromJson(rs.getString("followers"), JsonArray.class);
                boolean isFollower = followersJson.contains(new JsonPrimitive(selfId.toString()));
                if(isFollower) {
                    followersJson.remove(new JsonPrimitive(selfId.toString()));
                } else {
                    followersJson.add(selfId.toString());
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
    }

    public void toggleLikeMusic(UUID userId, UUID trackId) {
        // TODO
    }

    

    // If null is returned(and the username exists) then that means the password is incorrect
    public User logIn(String username, String password) {
        final String query = """
                SELECT user_id,username,passowrd FROM User
                WHERE username=?;
                """;

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            // Setting the email value into the query
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



    //Requirement methods

    public static HashMap<UUID, Boolean> jsonToHashmap(JsonObject jsonObject) {
        HashMap<UUID, Boolean> hashMap = new HashMap<UUID, Boolean>();
        Set<String> keys = jsonObject.keySet();
        for (String key: keys){
            Boolean isPublic = jsonObject.get(key).getAsBoolean();
            hashMap.put(UUID.fromString(key), isPublic);
        }
        return hashMap;
    }

    public static ArrayList<UUID> jsonArrayToArrayList(JsonArray jsonArray) {
        ArrayList<UUID> arrayList = new ArrayList<UUID>();
        for (int i = 0; i < jsonArray.size(); i++){
            jsonArray.add(arrayList.get(i).toString());
            UUID uuid = UUID.fromString(jsonArray.get(i).getAsString());
            arrayList.add(uuid);
        }
        return arrayList;
    }
    
}
