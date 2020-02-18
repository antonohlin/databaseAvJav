package Databse;

import java.util.List;

public class Artist {
    String name;
    List<Song> songs;
    List<Album> albums;
    int artistID;
    Artist(){
        name = "unknown";
    }
    Artist(String name){
        this.name = name;
    }
}
