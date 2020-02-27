package Application;

import Database.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Artist implements Serializable, Entity {
    private String name;
    List<Song> songs = new ArrayList<>();
    List<Album> albums = new ArrayList<>();
    private long id;
    Artist(){
        name = "unknown";
    }
    Artist(String name){
        this.name = name;
        this.id = System.currentTimeMillis();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }

    public long getID() {
        return id;
    }
}
