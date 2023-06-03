package Shared;

import Shared.Classes.Playlist;
import Shared.Classes.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Request {
    private Socket clientSocket;
    private PrintWriter out;

    //Constructor

    public Request(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            this.out = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //An Explanation on format of requests; Request could be an object that gets initialized automatically withing a socket connection from client side.
    //It has some methods to send request for each of the clients to the server
    //Basically it takes some inputs in clientHandler and build up a json with this template {"requestType" : smth, "jsonRequest" : smth} and send the request to the server through socket

    public void signupReq(String username, String email, String password){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("username", username);
        jsonRequest.addProperty("email", email);
        jsonRequest.addProperty("password", password);
        jsonTemplate.addProperty("requestType", "signup request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void loginReq(String username, String password){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("username", username);
        jsonRequest.addProperty("password", password);
        jsonTemplate.addProperty("requestType", "login request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void goHomePageReq(UUID userId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("userId", userId.toString());
        jsonTemplate.addProperty("requestType", "go home page request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void searchReq(String searchedText){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("searchedText", searchedText);
        jsonTemplate.addProperty("requestType", "search request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchUserPageReq(UUID userId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("userId", userId.toString());
        jsonTemplate.addProperty("requestType", "watch user page request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchArtistPageReq(UUID artistId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("artistId", artistId.toString());
        jsonTemplate.addProperty("requestType", "watch artist page request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchMusicPageReq(UUID trackId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("trackId", trackId.toString());
        jsonTemplate.addProperty("requestType", "watch music page request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchAlbumPageReq(UUID albumId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("albumId", albumId.toString());
        jsonTemplate.addProperty("requestType", "watch album page request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchPlaylistPageReq(UUID playlistId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("playlistId", playlistId.toString());
        jsonTemplate.addProperty("requestType", "watch playlist page request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    //User-Page requests (Below, I will bring those method that have not been defined before)
    //*This is a one-way operation*
    public void toggleFollowReq(UUID selfId, UUID userId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("selfId", selfId.toString());
        jsonRequest.addProperty("userId", userId.toString());
        jsonTemplate.addProperty("requestType", "follow request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    //Artist-Page requests (Below, I will bring those method that have not been defined before)
    //Nothing has been found to define

    //Music-Page requests (Below, I will bring those method that have not been defined before)
    //*This is a one-way operation*
    public void toggleLikeMusicReq(UUID userId, UUID trackId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("userId", userId.toString());
        jsonRequest.addProperty("trackId", trackId.toString());
        jsonTemplate.addProperty("requestType", "like music request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    //*This is a one-way operation*
    public void downloadTrackReq(UUID userId, UUID trackId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("userId", userId.toString());
        jsonRequest.addProperty("trackId", trackId.toString());
        jsonTemplate.addProperty("requestType", "download track request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    //*This is a one-way operation*
    public void toggleAddToPlaylistReq(UUID userId, UUID playlistId, UUID trackId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("userId", userId.toString());
        jsonRequest.addProperty("playlistId", playlistId.toString());
        jsonRequest.addProperty("trackId", trackId.toString());
        jsonTemplate.addProperty("requestType", "add to playlist request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    //Album-Page requests (Below, I will bring those method that have not been defined before)
    //Nothing has been found to define

    //Account-Page requests (Below, I will bring those method that have not been defined before)
    //*This is a one-way operation*
    public void editPersonalInfoReq(User user){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("userId", user.getUserId().toString());
        jsonRequest.addProperty("username", user.getUsername());
        jsonRequest.addProperty("email", user.getEmail());
        jsonRequest.addProperty("address", user.getAddress());
        jsonRequest.addProperty("password", user.getPassword());
        jsonRequest.addProperty("profilePath", user.getProfilePath());
        jsonRequest.add("createdPlaylists", hashmapToJson(user.getCreatedPlaylists()));
        jsonRequest.add("likedPlaylists", hashmapToJson(user.getLikedPlaylists()));
        jsonRequest.add("followers", arraylistToJsonArray(user.getFollowers()));
        jsonRequest.add("followings", arraylistToJsonArray(user.getFollowings()));
        jsonTemplate.addProperty("requestType", "edit personal info request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    //Playlist requests (Below, I will bring those method that have not been defined before)
    //*This is a one-way operation*
    public void createPlaylistReq(UUID userId, Playlist playlist){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("playlistId", playlist.getPlaylistId().toString());
        jsonRequest.addProperty("title", playlist.getTitle());
        jsonRequest.addProperty("description", playlist.getDescription());
        jsonRequest.addProperty("userId", userId.toString());
        jsonRequest.addProperty("popularity", playlist.getPopularity());
        jsonRequest.add("tracks", arraylistToJsonArray(playlist.getTracks()));
        jsonRequest.addProperty("profilePath", playlist.getProfilePath());
        jsonTemplate.addProperty("requestType", "create playlist request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    //*This is a one-way operation*
    public void downloadPlaylistReq(UUID playlistId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("playlistId", playlistId.toString());
        jsonTemplate.addProperty("requestType", "download playlist request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }


    public void watchLikedTracksReq(UUID userId){
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonRequest = new JsonObject();
        //Building jsons
        jsonRequest.addProperty("userId", userId.toString());
        jsonTemplate.addProperty("requestType", "watch liked tracks request");
        //putting jsonRequest into template
        jsonTemplate.add("jsonRequest", jsonRequest);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    //Requirement methods

    public static JsonObject hashmapToJson(HashMap<UUID, Boolean> hashMap){
        JsonObject jsonObject = new JsonObject();
        for (Map.Entry<UUID, Boolean> entry : hashMap.entrySet()){
            String key = entry.getKey().toString();
            Boolean value = entry.getValue();
            jsonObject.addProperty(key, value);
        }
        return jsonObject;
    }

    public static JsonArray arraylistToJsonArray(ArrayList<UUID> arrayList){
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < arrayList.size(); i++){
            jsonArray.add(arrayList.get(i).toString());
        }
        return jsonArray;
    }
}
