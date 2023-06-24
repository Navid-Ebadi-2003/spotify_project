package Client;

import java.util.ArrayList;

public class Download extends Thread {
    private ArrayList<DownloadFiles> downloadFilesArray;
    private ArrayList<DownloadFile> downloadFileArray;

    public Download(ArrayList<DownloadFile> downloadFileArray, ArrayList<DownloadFiles> downloadFilesArray) {
        this.downloadFileArray = downloadFileArray;
        this.downloadFilesArray = downloadFilesArray;
    }

    @Override
    public void run() {
        for(DownloadFile downloadFile: downloadFileArray) {
            Thread thread = new Thread(downloadFile);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(DownloadFiles downloadFiles: downloadFilesArray) {
            Thread thread = new Thread(downloadFiles);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
