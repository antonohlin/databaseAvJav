package Application;

import Database.Entity;
import java.io.Serializable;

public class Song implements Serializable, Entity {
    private String name;
    private long id;

    public Song(){
        name = "untitled";
    }

    Song (String title){
        this.name = title;
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
