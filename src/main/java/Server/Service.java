package Server;

import Server.Database.Query;
import Shared.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;

public class Service implements Runnable {
    private Socket serverSocket;
    private Scanner in;
    private Response responseObject;

    //Constructor
    public Service(Socket serverSocket) {
        this.serverSocket = serverSocket;
        responseObject = new Response(serverSocket);
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

    public void doService() {
        //Listening until client sends request
        String request = in.nextLine();
        //Converting request to json
        JsonObject jsonRequest = new Gson().fromJson(request, JsonObject.class);

        if (jsonRequest.get("requestType").getAsString().equals("EXIT")) {
            //Terminate the program
            return;
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
                if (!Query.doesEmailExist(email) && !Query.doesUsernameExist(username)){
                    Query.signUpUser(userId, username, email, password);
                    // Consider removing userId from that response because we have to login again
                    responseObject.signupRes(true, userId);
                } else {
                    responseObject.signupRes(false, userId);
                }
            }
            case "login request" -> {
                JsonObject requestBody = jsonRequest.getAsJsonObject("requestBody");
                UUID userId = UUID.randomUUID();
                String username = requestBody.get("username").getAsString();
                String password = requestBody.get("password").getAsString();
                JsonObject userJson = Query.logIn(username, password);

                if (userJson != null){
                    responseObject.loginRes(true, userJson);
                } else {
                    responseObject.loginRes(false, userJson);
                }
            }
            case "go home page request" -> {
                //TODO
            }
            case "search request" -> {
                //TODO
            }
            case "watch user page request" -> {
                //TODO
            }
            case "watch artist page request" -> {
                //TODO
            }
            case "watch music page request" -> {
                //TODO
            }
            case "watch album page request" -> {
                //TODO
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
    public void uploadHomePage(JsonObject jsonResults) throws IOException {
        //  Template of jsonResult:
        //  {
        //  "createdPlaylistsResult": [{"playlistId" : %s, "title" : %s, "description" : %s, "userId" : %s, "popularity" : %d, "profilePath" : %s, "isPrivate" : %d}, ...]
        //  "likedPlaylistsResult":   [{"playlistId" : %s, "title" : %s, "description" : %s, "userId" : %s, "popularity" : %d, "profilePath" : %s, "isPrivate" : %d}, ...],
        //  "likedMusicsResult":      [{"trackId" : %s, "title" : %s, "artistId" : [], "albumId" : %s, "genreId" : %s, "duration" : %d, "releaseDate" : %s, "popularity" : %d, "profilePath" : %s, "trackPath" : %s}, ...],
        //  "randomMusicsResult":     [{"trackId" : %s, "title" : %s, "artistId" : [], "albumId" : %s, "genreId" : %s, "duration" : %d, "releaseDate" : %s, "popularity" : %d, "profilePath" : %s, "trackPath" : %s}, ...]
        //  }

        // Parsing Results
        JsonArray createdPlaylistsJson = jsonResults.getAsJsonArray("createdPlaylistsResult");
        JsonArray likedPlaylistsJson = jsonResults.getAsJsonArray("likedPlaylistsResult");
        JsonArray likedMusicsJson = jsonResults.getAsJsonArray("likedMusicsResult");
        JsonArray randomMusicsJson = jsonResults.getAsJsonArray("randomMusicsResult");

        OutputStream outputStream = serverSocket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        for (int i = 0; i < createdPlaylistsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonCreatedPlaylist = createdPlaylistsJson.get(i).getAsJsonObject();
            String profilePath = jsonCreatedPlaylist.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < likedPlaylistsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonLikedPlaylist = likedPlaylistsJson.get(i).getAsJsonObject();
            String profilePath = jsonLikedPlaylist.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < likedMusicsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonLikedMusic = likedMusicsJson.get(i).getAsJsonObject();
            String profilePath = jsonLikedMusic.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < randomMusicsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonRandomMusic = randomMusicsJson.get(i).getAsJsonObject();
            String profilePath = jsonRandomMusic.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
    }

    public void uploadSearchPage(JsonObject jsonResults) throws IOException {
        //  Template of jsonResult:
        //  {
        //  "albumsResult":              [{"albumId" : %s, "title" : %s, "artistId" : %s, "genreId" : %s, "releaseDate" : %s, "popularity" : %d, "profilePath" : %s}, ...]
        //  "artistsResult":             [{"artistId" : %s, "name" : %s, "genreId" : %s, "biography" : %s, "profileFile" : %s}, ...]
        //  "musicsResult":              [{"trackId" : %s, "title" : %s, "artistId" : [], "albumId" : %s, "genreId" : %s, "duration" : %d, "releaseDate" : %s, "popularity" : %d, "profilePath" : %s, "trackPath" : %s}, ...]
        //  "playlistsResult":           [{"playlistId" : %s, "title" : %s, "description" : %s, "userId" : %s, "popularity" : %d, "profilePath" : %s, "isPrivate" : %d}, ...]
        //  "usersResult":               [{"userId" : %s, "username" : %s, "email" : %s, "password" : %s, "profilePath" : %s}, ...]
        //  }

        // Parsing Results
        JsonArray albumsJson = jsonResults.getAsJsonArray("albumsResult");
        JsonArray artistsJson = jsonResults.getAsJsonArray("artistsResult");
        JsonArray musicsJson = jsonResults.getAsJsonArray("musicsResult");
        JsonArray playlistsJson = jsonResults.getAsJsonArray("playlistsResult");
        JsonArray usersJson = jsonResults.getAsJsonArray("usersResult");

        OutputStream outputStream = serverSocket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        for (int i = 0; i < albumsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonAlbum = albumsJson.get(i).getAsJsonObject();
            String profilePath = jsonAlbum.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < artistsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonArtist = artistsJson.get(i).getAsJsonObject();
            String profilePath = jsonArtist.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < musicsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonMusic = musicsJson.get(i).getAsJsonObject();
            String profilePath = jsonMusic.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < playlistsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonPlaylist = playlistsJson.get(i).getAsJsonObject();
            String profilePath = jsonPlaylist.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < usersJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonUser = usersJson.get(i).getAsJsonObject();
            String profilePath = jsonUser.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
    }
   public void uploadUserPage(JsonObject jsonResults) throws IOException {
        //  Template of jsonResult:
        //  {
        //   "userId":             %s,
        //   "username":           %s,
        //   "email":              %s,
        //   "address":            %s,
        //   "password":           %s,
        //   "profilePath":        %s,
        //   "createdPlaylistsResult":  [{"playlistId" : %s, "title" : %s, "description" : %s, "userId" : %s, "popularity" : %d, "profilePath" : %s, "isPrivate" : %d}, ...],
        //   "likedPlaylistsResult":    [{"playlistId" : %s, "title" : %s, "description" : %s, "userId" : %s, "popularity" : %d, "profilePath" : %s, "isPrivate" : %d}, ...],
        //   "likedTracksResult":       [{"trackId" : %s, "title" : %s, "artistId" : [], "albumId" : %s, "genreId" : %s, "duration" : %d, "releaseDate" : %s, "popularity" : %d, "profilePath" : %s, "trackPath" : %s}, ...]
        //   "followers":         [%s],
        //   "followings":        [%s]
        //  }

        // Parsing Results
       JsonArray createdPlaylistsJson = jsonResults.getAsJsonArray("createdPlaylistsResult");
       JsonArray likedPlaylistsJson = jsonResults.getAsJsonArray("likedPlaylistsResult");


        OutputStream outputStream = serverSocket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        for (int i = 0; i < albumsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonAlbum = albumsJson.get(i).getAsJsonObject();
            String profilePath = jsonAlbum.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < artistsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonArtist = artistsJson.get(i).getAsJsonObject();
            String profilePath = jsonArtist.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < musicsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonMusic = musicsJson.get(i).getAsJsonObject();
            String profilePath = jsonMusic.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < playlistsJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonPlaylist = playlistsJson.get(i).getAsJsonObject();
            String profilePath = jsonPlaylist.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
        for (int i = 0; i < usersJson.size(); i++) {
            // Extract profilePath
            JsonObject jsonUser = usersJson.get(i).getAsJsonObject();
            String profilePath = jsonUser.get("profilePath").getAsString();
            File file = new File(profilePath);
            FileInputStream fileInputStream = new FileInputStream(file);

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
    }


}
