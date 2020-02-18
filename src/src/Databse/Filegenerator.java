package Databse;

public class Filegenerator {
    Filegenerator(){

    }
    public static void newSong(String[] info){
        String song = info[0];
        String artist = info[1];
        String album = info[2];
        System.out.printf("Låt: %s\n" +
                "Artist: %s\n" +
                "Album: %s\n",song, artist, album);

    }
}
