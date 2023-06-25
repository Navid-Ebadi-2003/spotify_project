package Client;

import Client.Controllers.Boxes.MusicThirdBox.MusicThirdBoxController;
import Client.Controllers.InjectableController;
import javafx.scene.image.Image;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class DownloadFile implements Runnable {
    //    String fileName, InjectableController controller, String filePathKey, Socket clientSocket
    private final String fileName;
    private final InjectableController controller;
    private final String filePathKey;
    private final Socket clientSocket;

    // Constructor

    public DownloadFile(String fileName, InjectableController controller, String filePathKey, Socket clientSocket) {
        this.fileName = fileName;
        this.controller = controller;
        this.filePathKey = filePathKey;
        this.clientSocket = clientSocket;
    }

    /*
        DownloadFIle related methods
     */
    public static void downloadFile(String fileName, InjectableController controller, String filePathKey, Socket clientSocket) throws IOException {
        FileOutputStream fileOutputStream = null;
        if (filePathKey.equals("profilePath")) {
            // If filePathKey equals profilePath we have to store the file in profilePictures
            fileOutputStream = new FileOutputStream("src\\main\\java\\Client\\Downloads\\files\\profile_pic\\" + fileName + ".jpg");
        } else {
            // Else we have to store in Musics
            fileOutputStream = new FileOutputStream("src\\main\\java\\Client\\Downloads\\files\\track_file\\" + fileName + ".mp3");
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
        if (filePathKey.equals("profilePath")) {
            loadFileIntoController(controller, "src\\main\\java\\Client\\Downloads\\files\\profile_pic\\" + fileName + ".jpg");
        } else {
            loadFileIntoController(controller, "src\\main\\java\\Client\\Downloads\\files\\track_file\\" + fileName + ".mp3");
        }
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    public static void loadFileIntoController(InjectableController controller, String filePath) {
        System.out.println(filePath);
        Image image = new Image("file:" + filePath);
        controller.setControllerProfilePic(image);
    }

    public static void downloadTrackOnly(String fileName, MusicThirdBoxController musicThirdBoxController, Socket clientSocket) throws IOException {
        FileOutputStream fileOutputStream = null;
        fileOutputStream = new FileOutputStream("src\\main\\java\\Client\\Downloads\\files\\track_file\\" + fileName + ".mp3");
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
        musicThirdBoxController.changeDownToPlayIcon();
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    @Override
    public void run() {
        try {
            downloadFile(fileName, controller, filePathKey, clientSocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
