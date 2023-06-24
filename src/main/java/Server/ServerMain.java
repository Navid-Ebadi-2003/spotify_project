package Server;

import Server.Database.DBConnection;
import Server.Database.Query;
import Server.Miscellaneous;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {
    public static void main(String[] args) {
        try {
            System.out.println(Miscellaneous.hashText("123"));
            //Establishing server connection
            ServerSocket serverSocket = new ServerSocket(8888);
            System.out.println("SERVER HAS STARTED LISTENING ON PORT '8888'");

            DBConnection dbConnection = new DBConnection();
            Query query = new Query(dbConnection.getConnection());

            while (true){
                //Waiting for clients to connect
                Socket socket = serverSocket.accept();
                //WRITE IN LOG FILE client has been connected
                System.out.println("CLIENT WITH IP: " + socket.getRemoteSocketAddress() + " HAS BEEN CONNECTED");
                //Giving service to each client
                Service service = new Service(socket);
                Thread thread = new Thread(service);
                thread.start();
            }
        } catch (IOException io){
            io.printStackTrace();
        }
    }
}
