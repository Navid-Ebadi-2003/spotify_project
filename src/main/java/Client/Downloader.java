package Client;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class Downloader implements Runnable{

    public static void downloadFile(String fileName, String filePathKey, Socket clientSocket) throws IOException {
        FileOutputStream fileOutputStream = null;
        if (filePathKey.equals("profilePath")) {
            // If filePathKey equals profilePath we have to store the file in profilePictures
            fileOutputStream = new FileOutputStream("src\\main\\java\\Client\\Downloads\\ProfilePictures\\" + fileName + ".png");
        } else {
            // Else we have to store in Musics
            fileOutputStream = new FileOutputStream("src\\main\\java\\Client\\Downloads\\Musics" + fileName + ".mp3");
        }
        InputStream inputStream = clientSocket.getInputStream();

        // Receive the file size from the server
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        long fileSize = dataInputStream.readLong();

        // Receive the file data
        byte[] buffer = new byte[4096];
        int bytesRead;
        long totalBytesRead = 0;
        while (totalBytesRead < fileSize && (bytesRead = inputStream.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, bytesRead);
            totalBytesRead += bytesRead;
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public void downloadFiles(JsonArray jsonArray, String filePathKey, Socket clientSocket) throws IOException {

        for (int i = 0; i < jsonArray.size(); i++){
            // Extract fileName
            JsonObject arrayItem = jsonArray.get(i).getAsJsonObject();
            String fileName = arrayItem.get("fileName").getAsString();
            downloadFile(fileName, filePathKey, clientSocket);
        }
    }

    @Override
    public void run() {
        // TODO
    }
}
