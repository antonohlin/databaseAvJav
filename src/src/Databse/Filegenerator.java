package Databse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Filegenerator {
    static File songs = new File("songs/songs.txt");
    static FileWriter writer;

    static {
        try {
            writer = new FileWriter(songs, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    Filegenerator(){

    }
    public static void newSong(String[] info) {
        String song = info[0];
        String artist = info[1];
        String album = info[2];
        System.out.printf("Låt: %s\n" +
                "Artist: %s\n" +
                "Album: %s\n",song, artist, album);
        try {
            writer.write(song+"\n");
            writer.close();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
