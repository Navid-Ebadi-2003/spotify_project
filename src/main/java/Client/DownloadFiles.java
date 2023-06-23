package Client;

import Client.Controllers.InjectableController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import Client.*;

public class DownloadFiles implements Runnable{
    // JsonArray jsonArray, ArrayList<InjectableController> controllers, String filePathKey, Socket clientSocket
    private JsonArray jsonArray;
    private ArrayList<InjectableController> controllers;
    private String filePathKey;
    private Socket clientSocket;

    // Constructor

    public DownloadFiles(JsonArray jsonArray, ArrayList<InjectableController> controllers, String filePathKey, Socket clientSocket) {
        this.jsonArray = jsonArray;
        this.controllers = controllers;
        this.filePathKey = filePathKey;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            downloadFiles(jsonArray, controllers, filePathKey, clientSocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        methods related to downloadFiles
     */
    public static void downloadFiles(JsonArray jsonArray, ArrayList<InjectableController> controllers, String filePathKey, Socket clientSocket) throws IOException {

        for (int i = 0; i < jsonArray.size(); i++){
            // Extract fileName
            JsonObject arrayItem = jsonArray.get(i).getAsJsonObject();
            String fileName = arrayItem.get("fileName").getAsString();
            DownloadFile.downloadFile(fileName, controllers.get(i),filePathKey, clientSocket);
        }
    }
}
