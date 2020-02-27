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

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
