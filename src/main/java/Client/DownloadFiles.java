package Client;

import Client.Controllers.InjectableController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class DownloadFiles implements Runnable {
    // JsonArray jsonArray, ArrayList<InjectableController> controllers, String filePathKey, Socket clientSocket
    private final JsonArray jsonArrays;
    private final List<InjectableController> controllers;
    private final String filePathKey;
    private final Socket clientSocket;

    // Constructor

    public DownloadFiles(JsonArray jsonArrays, List<InjectableController> controllers, String filePathKey, Socket clientSocket) {
        this.jsonArrays = jsonArrays;
        this.controllers = controllers;
        this.filePathKey = filePathKey;
        this.clientSocket = clientSocket;
    }

    /*
        methods related to downloadFiles
     */
    public static void
    downloadFiles(JsonArray jsonArray, List<InjectableController> controllers, String filePathKey, Socket clientSocket) throws IOException {
        for (int i = 0; i < jsonArray.size(); i++) {
            InjectableController controller = controllers.get(i);
            JsonObject arrayItem = jsonArray.get(i).getAsJsonObject();
            String fileName = arrayItem.get("fileName").getAsString();
            DownloadFile.downloadFile(fileName, controller, filePathKey, clientSocket);
        }
    }

    @Override
    public void run() {
        try {
            downloadFiles(jsonArrays, controllers, filePathKey, clientSocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
