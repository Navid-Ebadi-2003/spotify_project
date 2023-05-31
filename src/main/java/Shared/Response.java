package Shared;

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
    //Basically it takes some inputs in service and build up a json with this template {"requestType" : smth, "jsonBody" : smth} and send the response to the client through socket
    public void signupRes(Boolean result, UUID userId) {
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonBody = new JsonObject();
        //Building jsons
        jsonBody.addProperty("result", result);
        jsonBody.addProperty("userId", userId.toString());
        jsonTemplate.addProperty("requestType", "signup response");
        //putting jsonBody into template
        jsonTemplate.add("jsonBody", jsonBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void loginRes(Boolean result, UUID userId) {
        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonBody = new JsonObject();
        //Building jsons
        jsonBody.addProperty("result", result);
        jsonBody.addProperty("userId", userId.toString());
        jsonTemplate.addProperty("requestType", "login response");
        //putting jsonBody into template
        jsonTemplate.add("jsonBody", jsonBody);
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

        //template of jsonBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "jsonBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonBody = new JsonObject();
        //Building jsons
        jsonBody.add("results", jsonResults);
        jsonTemplate.addProperty("responseType", "go home page response");
        //putting jsonBody into template
        jsonTemplate.add("jsonBody", jsonBody);
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

        //template of jsonBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "jsonBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonBody = new JsonObject();
        //Building jsons
        jsonBody.add("results", jsonResults);
        jsonTemplate.addProperty("responseType", "search response");
        //putting jsonBody into template
        jsonTemplate.add("jsonBody", jsonBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchUserPageRes(JsonObject jsonResult){
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

        //template of jsonBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "jsonBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonBody = new JsonObject();
        //Building jsons
        jsonBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch user page response");
        //putting jsonBody into template
        jsonTemplate.add("jsonBody", jsonBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchArtistPageRes(JsonObject jsonResult){
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

        //template of jsonBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "jsonBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonBody = new JsonObject();
        //Building jsons
        jsonBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch artist page response");
        //putting jsonBody into template
        jsonTemplate.add("jsonBody", jsonBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchMusicPageRes(JsonObject jsonResult){
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

        //template of jsonBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "jsonBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonBody = new JsonObject();
        //Building jsons
        jsonBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch music page response");
        //putting jsonBody into template
        jsonTemplate.add("jsonBody", jsonBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchAlbumRes(JsonObject jsonResult){
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

        //template of jsonBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "jsonBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonBody = new JsonObject();
        //Building jsons
        jsonBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch album page response");
        //putting jsonBody into template
        jsonTemplate.add("jsonBody", jsonBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchPlaylistRes(JsonObject jsonResult){
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

        //template of jsonBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "jsonBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonBody = new JsonObject();
        //Building jsons
        jsonBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch playlist page response");
        //putting jsonBody into template
        jsonTemplate.add("jsonBody", jsonBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchLikedTracksRes(JsonObject jsonResult){
        //template of jsonResult:
        //{
        //"musicsResult": {%s}
        // }

        //template of jsonBody {"results":{jsonResult}}

        //template of jsonTemplate {"responseType":%s, "jsonBody"{%s}}

        //Also here we assume that each of the objects has been sent before.

        //Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject jsonBody = new JsonObject();
        //Building jsons
        jsonBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch liked tracks response");
        //putting jsonBody into template
        jsonTemplate.add("jsonBody", jsonBody);
        //Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }
}
