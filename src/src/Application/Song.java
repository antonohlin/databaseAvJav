package Application;

import Database.Entity;

import java.io.Serializable;

public class Song implements Serializable, Entity {
    public String title;
    public long songID;

    public Song(){
        title = "untitled";
    }

    Song (String title){
        this.title = title;
        this.songID = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public long getID() {
        return songID;
    }
}
