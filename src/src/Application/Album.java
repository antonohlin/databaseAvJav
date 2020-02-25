package Application;

import Database.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Album implements Serializable, Entity {
    private String name;
    List<Song> songs = new ArrayList<>();
    private long id;
    Album(){
        String title = "untitled";
    }
    Album(String name){
        this.name = name;
        this.id = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public long getID() {
        return id;
    }
}
