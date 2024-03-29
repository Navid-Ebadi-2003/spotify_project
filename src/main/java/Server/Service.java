package Server;

import Server.Database.Query;
import Shared.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.Socket;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.UUID;

public class Service implements Runnable {
    private final Socket serverSocket;
    private final Scanner in;
    private final Response responseObject;

    private UUID userId;

    //Constructor
    public Service(Socket serverSocket) {
        this.serverSocket = serverSocket;
        responseObject = new Response(serverSocket);
        this.userId = null;
        try {
            this.in = new Scanner(serverSocket.getInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            try {
                while (true) {
                    //Give service to client
                    doService();
                }
            } finally {
                //Close the Scanner after finishing work
                in.close();
            }
        } finally {
            //Write in LOG FILE that client has been disconnected
            //TODO
            System.out.println("CLIENT WITH IP: " + serverSocket.getRemoteSocketAddress() + " HAS BEEN DISCONNECTED");
        }
    }

    public void log(String requestType, UUID userId) {
        try {
            FileWriter writer = new FileWriter("log.txt");
            LocalTime time = LocalTime.now();

            if (!userId.equals(null)) {

                writer.write(time + "\n" + "IP address: " + serverSocket.getLocalAddress() + "\n" + "port number: " + serverSocket.getPort() + "\n" + "userID: " + userId + "\n" + "request type: " + requestType + "\n" + "\n" + "\n");
            } else {
                writer.write(time + "\n" + "IP address: " + serverSocket.getLocalAddress() + "\n" + "port number: " + serverSocket.getPort() + "\n" + "request type: " + requestType + "\n" + "\n" + "\n");
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doService() {
        //Listening until client sends request
        String request = in.nextLine();
        //Converting request to json
        JsonObject jsonRequest = new Gson().fromJson(request, JsonObject.class);

        if (jsonRequest.get("requestType").getAsString().equals("EXIT")) {
            //Terminate the program
        } else {
            //Execute desired request
            executeRequest(jsonRequest);
        }
    }

    public void executeRequest(JsonObject jsonRequest) {
        //Switch on requestTypes
        String requestType = jsonRequest.get("requestType").getAsString();

        switch (requestType) {
            case "signup request" -> {
                JsonObject requestBody = jsonRequest.getAsJsonObject("requestBody");
                UUID userId = UUID.randomUUID();
                String email = requestBody.get("email").getAsString();
                String username = requestBody.get("username").getAsString();
                String password = requestBody.get("password").getAsString();

                if (!Query.doesEmailExist(email) && !Query.doesUsernameExist(username)) {
                    Query.signUpUser(userId, username, email, password);
                    // Consider removing userId from that response because we have to login again
                    responseObject.signupRes(true);
                } else {
                    responseObject.signupRes(false);
                }

                log(requestType, this.userId);
            }
            case "login request" -> {
                JsonObject requestBody = jsonRequest.getAsJsonObject("requestBody");
                String username = requestBody.get("username").getAsString();
                String password = requestBody.get("password").getAsString();
                JsonObject jsonResults = Query.logIn(username, password);

                this.userId = UUID.fromString(jsonResults.get("userId").getAsString());

                if (userId != null) {
                    responseObject.loginRes(true, jsonResults);
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
                    try {
                        uploadUserProfilePic(jsonResults);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    responseObject.loginRes(false, null);
                }

                log(requestType, this.userId);
            }
            case "go home page request" -> {
                JsonObject requestBody = jsonRequest.getAsJsonObject("requestBody");
                UUID userId = UUID.fromString(requestBody.get("userId").getAsString());
                JsonObject jsonResults = Query.getHomePage(userId);
                responseObject.goHomePageRes(jsonResults);
                try {
                    uploadHomePage(jsonResults);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                log(requestType, this.userId);
            }
            case "search request" -> {
                JsonObject requestBody = jsonRequest.getAsJsonObject("requestBody");
                String pattern = requestBody.get("searchedText").getAsString();
                JsonObject jsonResults = Query.search(pattern);

                responseObject.searchRes(jsonResults);
                try {
                    uploadSearchPage(jsonResults);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "watch user page request" -> {
                //TODO
            }
            case "watch artist page request" -> {
                //TODO
            }
            case "watch music page request" -> {
                JsonObject requestBody = jsonRequest.getAsJsonObject("requestBody");
                UUID trackId = UUID.fromString(requestBody.get("trackId").getAsString());
                JsonObject jsonResults = Query.getMusic(trackId);
                responseObject.watchMusicPageRes(jsonResults);
                try {
                    uploadWatchMusicPage(jsonResults);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "watch album page request" -> {
                JsonObject requestBody = jsonRequest.getAsJsonObject("requestBody");
                UUID albumId = UUID.fromString(requestBody.get("albumId").getAsString());
                JsonObject jsonResults = Query.getAlbum(albumId);
                responseObject.watchAlbumRes(jsonResults);
                try {
                    uploadWatchAlbum(jsonResults);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            case "watch playlist page request" -> {
                //TODO
            }
            case "follow request" -> {
                //TODO
            }
            case "like music request" -> {
                //TODO
            }
            case "download track request" -> {
                //TODO
            }
            case "add to playlist request" -> {
                //TODO
            }
            case "edit personal info request" -> {
                //TODO
            }
            case "create playlist request" -> {
                //TODO
            }
            case "download playlist request" -> {
                //TODO
            }
            case "watch liked tracks request" -> {
                //TODO
            }
        }
    }

    // Upload method to send "goHomePage" Results from the server to the client
    public void uploadFile(String filePath) throws IOException {
        filePath = "src/main/java/Server/" + filePath;
        OutputStream outputStream = serverSocket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        File file = new File(filePath);
        FileInputStream fileInputStream = new FileInputStream(file);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Send the file size to client
        long fileSize = file.length();
        dataOutputStream.writeLong(fileSize);

        // Send the file data
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        dataOutputStream.flush();
        outputStream.flush();
        fileInputStream.close();
    }

    public void uploadFiles(JsonArray jsonArray, String filePathKey) throws IOException {

        for (int i = 0; i < jsonArray.size(); i++) {
            // Extract profilePath
            JsonObject arrayItem = jsonArray.get(i).getAsJsonObject();
            String profilePath = arrayItem.get(filePathKey).getAsString();
            uploadFile(profilePath);
        }
    }

    public void uploadUserProfilePic(JsonObject jsonResults) throws IOException {
        String profilePath = jsonResults.get("profilePath").getAsString();
        uploadFile(profilePath);
    }

    public void uploadHomePage(JsonObject jsonResults) throws IOException {
        //  Template of jsonResults:
        //  {
        //  "createdPlaylistsResult": [{"playlistId" : %s, "title" : %s, "description" : %s, "creator" : {"userId" : %s, "username" : %s}}, "popularity" : %d, "profilePath" : %s, "fileName" : %s, "isPrivate" : %d, "tracks" : []}, ...]
        //  "likedPlaylistsResult":   [{"playlistId" : %s, "title" : %s, "description" : %s, "creator" : {"userId" : %s, "username" : %s}, "popularity" : %d, "profilePath" : %s, "fileName" : %s, "isPrivate" : %d, "tracks" : []}, ...],
        //  "likedMusicsResult":      [{"trackId" : %s, "title" : %s, "artists" : [{"artistId" : %s, "artistName" : %s}, ...], "albumId" : %s, "genreId" : %s, "duration" : %d, "releaseDate" : %s, "popularity" : %d, "profilePath" : %s, "trackPath" : %s, "fileName" : %s}, ...],
        //  "randomMusicsResult":     [{"trackId" : %s, "title" : %s, "artists" : [{"artistId" : %s, "artistName" : %s}, ...], "albumId" : %s, "genreId" : %s, "duration" : %d, "releaseDate" : %s, "popularity" : %d, "profilePath" : %s, "trackPath" : %s, "fileName" : %s}, ...]
        //   }

        // Parsing Results
        JsonArray createdPlaylistsJson = jsonResults.getAsJsonArray("createdPlaylistsResult");
        JsonArray likedPlaylistsJson = jsonResults.getAsJsonArray("likedPlaylistsResult");
        JsonArray randomMusicsJson = jsonResults.getAsJsonArray("randomMusicsResult");
        JsonArray likedMusicsJson = jsonResults.getAsJsonArray("likedMusicsResult");

        // Uploading profile pictures
        uploadFiles(createdPlaylistsJson, "profilePath");
        uploadFiles(likedPlaylistsJson, "profilePath");
        uploadFiles(randomMusicsJson, "profilePath");
        uploadFiles(likedMusicsJson, "profilePath");
    }

    public void uploadSearchPage(JsonObject jsonResults) throws IOException {
        //  Template of jsonResults:
        //  {
        //  "albumsResult":              [{"albumId" : %s, "title" : %s, "artists" : [{"artistName" : %s, "artistId" : %s}], "genreId" : %s, "releaseDate" : %s, "popularity" : %d, "profilePath" : %s, "fileName" : %s}, ...],
        //  "artistsResult":             [{"artistId" : %s, "name" : %s, "genreId" : %s, "biography" : %s, "profilePath" : %s, "fileName" : %s, "socialMediaLinks" : [], "albums" : []}, ...],
        //  "musicsResult":              [{"trackId" : %s, "title" : %s, "artists" : [{"artistId" : %s, "artistName" : %s}, ...], "albumId" : %s, "genreId" : %s, "duration" : %d, "releaseDate" : %s, "popularity" : %d, "profilePath" : %s, "trackPath" : %s, "fileName" : %s}, ...],
        //  "playlistsResult":           [{"playlistId" : %s, "title" : %s, "description" : %s, "creator" : {"userId" : %s, "username" : %s}, "popularity" : %d, "profilePath" : %s, "fileName" : %s, "isPrivate" : %d, "tracks" : []}, ...],
        //  "usersResult":               [{"userId" : %s, "username" : %s, "profilePath" : %s, "fileName" : %s}, ...]
        //   }

        // Parsing Results
        JsonArray albumsJson = jsonResults.getAsJsonArray("albumsResult");
        JsonArray artistsJson = jsonResults.getAsJsonArray("artistsResult");
        JsonArray musicsJson = jsonResults.getAsJsonArray("musicsResult");
        JsonArray playlistsJson = jsonResults.getAsJsonArray("playlistsResult");
        JsonArray usersJson = jsonResults.getAsJsonArray("usersResult");

        // Uploading profile pictures
        uploadFiles(albumsJson, "profilePath");
        uploadFiles(artistsJson, "profilePath");
        uploadFiles(musicsJson, "profilePath");
        uploadFiles(playlistsJson, "profilePath");
        uploadFiles(usersJson, "profilePath");
    }

    public void uploadWatchUserPage(JsonObject jsonResults) throws IOException {
        // Template of jsonResults:
        // {
        // "userId" : %s,
        // "username" : %s,
        // "profilePath" : %s,
        // "fileName" : %s
        //  }

        // Parsing Results
        String profilePath = jsonResults.get("profilePath").getAsString();
        JsonArray createdPlaylistsJson = jsonResults.getAsJsonArray("createdPlaylists");
        JsonArray likedPlaylistsJson = jsonResults.getAsJsonArray("likedPlaylists");

        // Uploading profile pictures
        uploadFile(profilePath);
        uploadFiles(createdPlaylistsJson, "profilePath");
        uploadFiles(likedPlaylistsJson, "profilePath");
    }

    public void uploadWatchArtistPage(JsonObject jsonResults) throws IOException {
        // Template of jsonResults:
        // {
        // "artistId" : %s,
        // "name" : %s,
        // "genreId" : %s,
        // "biography" : %s,
        // "profilePath" : %s,
        // "fileName" : %s,
        // "socialMediaLinks" : [],
        // "albums" : []
        //  }

        // Parsing Results
        String profilePath = jsonResults.get("profilePath").getAsString();
        JsonArray albumsJson = jsonResults.getAsJsonArray("albums");

        // Uploading profile pictures
        uploadFile(profilePath);
        uploadFiles(albumsJson, "profilePath");
    }

    public void uploadWatchMusicPage(JsonObject jsonResults) throws IOException {
        // Template of jsonResults:
        // {
        // "trackId" : %s,
        // "title" : %s,
        // "artists" : [{"artistId" : %s, "artistName" : %s}, ...],
        // "albumId" : %s,
        // "genreId" : %s,
        // "duration" : %d,
        // "releaseDate" : %s,
        // "popularity" : %d,
        // "profilePath" : %s,
        // "trackPath" : %s,
        // "fileName" : %s
        //  }

        // Parsing Results
        String profilePath = jsonResults.get("profilePath").getAsString();
        String trackPath = jsonResults.get("trackPath").getAsString();

        // Uploading profile pictures
//        uploadFile(profilePath);
        uploadFile(trackPath);
    }

    public void uploadWatchAlbum(JsonObject jsonResults) throws IOException {
        // Template of jsonResult:
        // {
        // "albumId" : %s,
        // "title" : %s,
        // "artists" : [{"artistName" : %s, "artistId" : %s}],
        // "genreId" : %s,
        // "releaseDate" : %s,
        // "popularity" : %d,
        // "tracks" : [],
        // "profilePath" : %s,
        // "fileName" : %s
        //  }

        // Parsing Results
        String profilePath = jsonResults.get("profilePath").getAsString();
        JsonArray tracksJson = jsonResults.getAsJsonArray("tracks");

        // Uploading profile pictures
        uploadFile(profilePath);
        uploadFiles(tracksJson, "profilePath");
    }

    public void uploadWatchPlaylist(JsonObject jsonResults) throws IOException {
        // Template of jsonResult:
        // {
        // "playlistId" : %s,
        // "title" : %s,
        // "description" : %s,
        // "creator" : {"userId" : %s, "username" : %s},
        // "popularity" : %d,
        // "profilePath" : %s,
        // "fileName" : %s,
        // "isPrivate" : %d,
        // "tracks" : []
        //  }

        // Parsing Results
        String profilePath = jsonResults.get("profilePath").getAsString();
        JsonArray tracksJson = jsonResults.getAsJsonArray("tracks");

        // Uploading profile pictures
        uploadFile(profilePath);
        uploadFiles(tracksJson, "profilePath");
        uploadFiles(tracksJson, "trackPath");
    }

    public void uploadWatchLikedTracks(JsonArray jsonResults) throws IOException {
        // Template of jsonResults :
        // [
        // {
        // "trackId" : %s,
        // "title" : %s,
        // "artists" : [{"artistId" : %s, "artistName" : %s}, ...],
        // "albumId" : %s,
        // "genreId" : %s,
        // "duration" : %d,
        // "releaseDate" : %s,
        // "popularity" : %d,
        // "profilePath" : %s,
        // "trackPath" : %s,
        // "fileName" : %s
        //  }, ...
        //  ]
        // Uploading profile pictures
        uploadFiles(jsonResults, "profilePath");
        uploadFiles(jsonResults, "trackPath");
    }
}
