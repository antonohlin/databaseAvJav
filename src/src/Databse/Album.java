package Databse;

import java.util.List;

public class Album {
    String title;
    List<Song> songs;
    List<Artist> artists;
    int albumID;
    Album(){
        String title = "untitled";
    }
    Album(String title){
        this.title = title;
    }
}
