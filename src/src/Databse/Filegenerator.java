package Databse;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Filegenerator implements Runnable {
    private static final String filepath = "songs\\songs.txt";

    Filegenerator(){

    }
    public static void saveInfo(String[] info){

        String song = info[0];
        String artist = info[1];
        String album = info[2];
        System.out.printf("Låt: %s\n" +
                "Artist: %s\n" +
                "Album: %s\n",song, artist, album);
        try {
            FileWriter fileWriter = new FileWriter(filepath, true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.printf("\nSong: %s, Artist: %s, Album: %s",song, artist,album);
            printWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }
}
