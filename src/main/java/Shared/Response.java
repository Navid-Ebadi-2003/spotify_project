package Shared;

import Shared.Classes.User;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.UUID;

public class Response {
    private Socket serverSocket;
    private PrintWriter out;

    //Constructor

    public Response(Socket serverSocket) {
        this.serverSocket = serverSocket;
        try {
            this.out = new PrintWriter(serverSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Methods to get called by service provider to send different kinds of responses
    //An Explanation on format of requests; Request could be an object that gets initialized automatically withing a socket connection from server.
    //Basically it takes some inputs in service and build up a json with this template {"responseType" : smth, "responseBody" : smth} and send the response to the client through socket
    public void signupRes(Boolean result, UUID userId) {
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        //Building jsons
        responseBody.addProperty("result", result);
        responseBody.addProperty("userId", userId.toString());
        jsonTemplate.addProperty("responseType", "signup response");
        //putting responseBody into template
        jsonTemplate.add("responseBody", responseBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void loginRes(Boolean result, User user) {
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        //Building jsons
        if (result) {
            responseBody.addProperty("result", result);
            responseBody.addProperty("userId", user.getUserId().toString());
            responseBody.addProperty("username", user.getUsername());
            responseBody.addProperty("email", user.getEmail());
            responseBody.addProperty("address", user.getAddress());
            responseBody.addProperty("password", user.getPassword());
            responseBody.addProperty("profilePath", user.getProfilePath());
            responseBody.add("createdPlaylists", Request.hashmapToJson(user.getCreatedPlaylists()));
            responseBody.add("likedPlaylists", Request.hashmapToJson(user.getLikedPlaylists()));
            responseBody.add("followers", Request.arraylistToJsonArray(user.getFollowers()));
            responseBody.add("followings", Request.arraylistToJsonArray(user.getFollowings()));
            jsonTemplate.addProperty("responseType", "login response");
        } else {
            //responseBody is gonna be a empty json
        }
        //putting responseBody into template
        jsonTemplate.add("responseBody", responseBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void goHomePageRes(JsonObject jsonResults) {
        //template of jsonResult:
        // {
        // "createdPlaylistsResult":  {"downloadCount":%d, "playlists":   {%s},
        // "likedPlaylistsResult":    {"downloadCount":%d, "playlists":   {%s},
        // "likedMusicsResult":       {"downloadCount":%d, "musics":      {%s},
        // "randomMusicsResult":      {"downloadCount":%d, "musics":      {%s}
        // }

        //template of responseBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        //Building jsons
        responseBody.add("results", jsonResults);
        jsonTemplate.addProperty("responseType", "go home page response");
        //putting responseBody into template
        jsonTemplate.add("responseBody", responseBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void searchRes(JsonObject jsonResults) {
        //template of jsonResult:
        // {
        // "albumsResult":          {"downloadCount":%d, "albums":          {%s},
        // "artistsResult":         {"downloadCount":%d, "artists":         {%s},
        // "musicsResult":          {"downloadCount":%d, "musics":          {%s},
        // "playlistsResult":       {"downloadCount":%d, "playlists":       {%s},
        // "usersResult":           {"downloadCount":%d, "users":           {%s}
        // }

        //template of responseBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        //Building jsons
        responseBody.add("results", jsonResults);
        jsonTemplate.addProperty("responseType", "search response");
        //putting responseBody into template
        jsonTemplate.add("responseBody", responseBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchUserPageRes(JsonObject jsonResult) {
        //template of jsonResult:
        //{"userId:            %s,
        //"username:           %s,
        //"email:              %s,
        //"address:            %s,
        //"password:           %s,
        //"profilePath:        %s,
        //"createdPlaylists:  {%s},
        //"likedPlaylists:    {%s},
        //"followers:         {%s},
        //"followings:        {%s}
        // }

        //template of responseBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        //Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch user page response");
        //putting responseBody into template
        jsonTemplate.add("responseBody", responseBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchArtistPageRes(JsonObject jsonResult) {
        //template of jsonResult:
        //{
        //"artistId":             %s,
        //"name":                 %s,
        //"genreId":              %s,
        //"biography":            %s,
        //"socialMediaLinks":   {%s},
        //"albums":             {%s},
        //"profilePath":          %s
        // }

        //template of responseBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        //Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch artist page response");
        //putting responseBody into template
        jsonTemplate.add("responseBody", responseBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchMusicPageRes(JsonObject jsonResult) {
        //template of jsonResult:
        //{
        //"trackId":        %s,
        //"title":          %s,
        //"artistId":     {%s},
        //"albumId":        %s,
        //"genreId":        %s,
        //"duration":       %d,
        //"releaseDate":    %s,
        //"popularity":     %d,
        //"ProfilePath":    %s,
        //}

        //template of responseBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        //Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch music page response");
        //putting responseBody into template
        jsonTemplate.add("responseBody", responseBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchAlbumRes(JsonObject jsonResult) {
        //template of jsonResult:
        //{
        //"albumId": %s,
        //"title": %s,
        //"artistId": {%s},
        //"genreId": %s,
        //"releaseDate": %s,
        //"popularity": %d,
        //"tracks": {%s},
        //"profilePath": %s,
        //}

        //template of responseBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        //Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch album page response");
        //putting responseBody into template
        jsonTemplate.add("responseBody", responseBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchPlaylistRes(JsonObject jsonResult) {
        //template of jsonResult:
        //{
        //"playlistId":     %s,
        //"title":          %s,
        //"description":    %s,
        //"userId":         %s,
        //"popularity":     %s,
        //"tracks":       {%s},
        //"profilePath":    %s,
        // }

        //template of responseBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        //Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch playlist page response");
        //putting responseBody into template
        jsonTemplate.add("responseBody", responseBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchLikedTracksRes(JsonObject jsonResult) {
        //template of jsonResult:
        //{
        //"musicsResult": {%s}
        // }

        //template of responseBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        //Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch liked tracks response");
        //putting responseBody into template
        jsonTemplate.add("responseBody", responseBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }
}
