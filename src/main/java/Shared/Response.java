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

    // Constructor

    public Response(Socket serverSocket) {
        this.serverSocket = serverSocket;
        try {
            this.out = new PrintWriter(serverSocket.getOutputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Methods to get called by service provider to send different kinds of responses
    // An Explanation on format of requests; Request could be an object that gets initialized automatically withing a socket connection from server.
    // Basically it takes some inputs in service and build up a json with this Template {"responseType" : smth, "responseBody" : smth} and send the response to the client through socket
    public void signupRes(Boolean result) {
        // Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        // Building jsons
        responseBody.addProperty("result", result);
        jsonTemplate.addProperty("responseType", "signup response");
        // Putting responseBody into Template
        jsonTemplate.add("responseBody", responseBody);
        // Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void loginRes(Boolean result, UUID userId) {
        // Template of responseBody :
        // {
        // "result" : %s,
        // "userId" : %s
        // }
        // Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        // Building jsons
        if (userId != null){
            responseBody.addProperty("userId", userId.toString());
        }
        responseBody.addProperty("result", result);
        jsonTemplate.addProperty("responseType", "login response");
        // Putting responseBody into Template
        jsonTemplate.add("responseBody", responseBody);
        // Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void goHomePageRes(JsonObject jsonResults) {
        //  Template of jsonResult:
        //  {
        //  "createdPlaylistsResult": [%s],
        //  "likedPlaylistsResult":   [%s],
        //  "likedMusicsResult":      [%s],
        //  "randomMusicsResult":     [%s]
        //  }

        // Template of responseBody {"results":jsonResult}

        // Template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        // Also here we assume that each of the objects has been sent before.

        // Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        // Building jsons
        responseBody.add("results", jsonResults);
        jsonTemplate.addProperty("responseType", "go home page response");
        // Putting responseBody into Template
        jsonTemplate.add("responseBody", responseBody);
        // Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void searchRes(JsonObject jsonResults) {
        // Template of jsonResult:
        // {
        // "albumsResult":          [%s],
        // "artistsResult":         [%s],
        // "musicsResult":          [%s],
        // "playlistsResult":       [%s],
        // "usersResult":           [%s]
        // }

        // Template of responseBody {"results":jsonResult}

        // Template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        // Also here we assume that each of the objects has been sent before.

        // Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        // Building jsons
        responseBody.add("results", jsonResults);
        jsonTemplate.addProperty("responseType", "search response");
        // Putting responseBody into Template
        jsonTemplate.add("responseBody", responseBody);
        // Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchUserPageRes(JsonObject jsonResult) {
        // Template of jsonResult:
        // {
        // "userId:             %s,
        // "username:           %s,
        // "email:              %s,
        // "address:            %s,
        // "password:           %s,
        // "profilePath:        %s,
        // "createdPlaylists:  [%s],
        // "likedPlaylists:    [%s],
        // "followers:         [%s],
        // "followings:        [%s]
        //  }

        // Template of responseBody {"results":jsonResult}

        // Template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        // Also here we assume that each of the objects has been sent before.

        // Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        // Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch user page response");
        // Putting responseBody into Template
        jsonTemplate.add("responseBody", responseBody);
        // Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchArtistPageRes(JsonObject jsonResult) {
        // Template of jsonResult:
        // {
        // "artistId":             %s,
        // "name":                 %s,
        // "genreId":              %s,
        // "biography":            %s,
        // "socialMediaLinks":   [%s],
        // "albums":             [%s],
        // "profilePath":          %s
        // }

        // Template of responseBody {"results":jsonResult}

        // Template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        // Also here we assume that each of the objects has been sent before.

        // Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        // Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch artist page response");
        // Putting responseBody into Template
        jsonTemplate.add("responseBody", responseBody);
        // Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchMusicPageRes(JsonObject jsonResult) {
        // Template of jsonResult:
        // {
        // "trackId":        %s,
        // "title":          %s,
        // "artistId":     [%s],
        // "albumId":        %s,
        // "genreId":        %s,
        // "duration":       %d,
        // "releaseDate":    %s,
        // "popularity":     %d,
        // "ProfilePath":    %s,
        // }

        // Template of responseBody {"results":jsonResult}

        // Template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        // Also here we assume that each of the objects has been sent before.

        // Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        // Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch music page response");
        // Putting responseBody into Template
        jsonTemplate.add("responseBody", responseBody);
        // Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchAlbumRes(JsonObject jsonResult) {
        // Template of jsonResult:
        // {
        // "albumId": %s,
        // "title": %s,
        // "artistId": [%s],
        // "genreId": %s,
        // "releaseDate": %s,
        // "popularity": %d,
        // "tracks": [%s],
        // "profilePath": %s,
        // }

        // Template of responseBody {"results":jsonResult}

        // Template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        // Also here we assume that each of the objects has been sent before.

        // Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        // Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch album page response");
        // Putting responseBody into Template
        jsonTemplate.add("responseBody", responseBody);
        // Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchPlaylistRes(JsonObject jsonResult) {
        // Template of jsonResult:
        // {
        // "playlistId":     %s,
        // "title":          %s,
        // "description":    %s,
        // "userId":         %s,
        // "popularity":     %s,
        // "tracks":       [%s],
        // "profilePath":    %s,
        //  }

        // Template of responseBody {"results":jsonResult}

        // Template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        // Also here we assume that each of the objects has been sent before.

        // Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        // Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch playlist page response");
        // Putting responseBody into Template
        jsonTemplate.add("responseBody", responseBody);
        // Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }

    public void watchLikedTracksRes(JsonObject jsonResult) {
        // Template of jsonResult:
        // {
        // "musicsResult": [%s]
        //  }

        // Template of responseBody {"results":jsonResult}

        // Template of jsonTemplate {"responseType":%s, "responseBody"{%s}}

        // Also here we assume that each of the objects has been sent before.

        // Creating jsons
        JsonObject jsonTemplate = new JsonObject();
        JsonObject responseBody = new JsonObject();
        // Building jsons
        responseBody.add("results", jsonResult);
        jsonTemplate.addProperty("responseType", "watch liked tracks response");
        // Putting responseBody into Template
        jsonTemplate.add("responseBody", responseBody);
        // Sending json over the socket
        out.println(jsonTemplate);
        out.flush();
    }
}
