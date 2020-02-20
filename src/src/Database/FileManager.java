package Database;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileManager implements Runnable {
    private static final String filepath = "songs\\songs.txt";

    FileManager(){

    }
    public static List<String> collectInfo(String fileName){
        List<String> info = new ArrayList<>();
        System.out.println("Ange låttitel: ");
        String songTitle = Utility.getLine();
        info.add(songTitle);
        System.out.println("Ange artist: ");
        String artistName = Utility.getLine();
        info.add(artistName);
        System.out.println("Ange album: ");
        String albumTitle = Utility.getLine();
        info.add(albumTitle);
        return info;
    }

    public static void saveInfo(List<String> info){
        String song = info.get(0);
        String artist = info.get(1);
        String album = info.get(2);
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
