package Application;

import java.io.Serializable;

public class Song implements Serializable {
    String title;
    String artist;
    String album;
    int songID;
    public Song(){
        title = "untitled";
    }
    Song (String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                '}';
    }
}
