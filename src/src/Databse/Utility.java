package Databse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utility {
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    Utility(){

    }

    public static String[] gatherSongInfo(){
        String[] info = new String[3];
        System.out.println("Ange låttitel: ");
        String songTitle = getLine();
        info[0] = songTitle;
        System.out.println("Ange artist: ");
        String artistName = getLine();
        info[1] = artistName;
        System.out.println("Ange album: ");
        String albumTitle = getLine();
        info[2] = albumTitle;
        return info;
    }

    public static void menuChoiceSwitch(){
        int menuChoice;
        do {
            menuChoice = Utility.getInt();
            switch (menuChoice) {
                case 1:
                    break;
                case 2:
                    String[] info = Utility.gatherSongInfo();
                    Filegenerator.newSong(info);
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen!");
                    break;
            }
        } while (menuChoice < 1 || menuChoice > 5 );
    }
    public static int getInt(){
        return Integer.parseInt(getLine());
    }
    public static String getLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
