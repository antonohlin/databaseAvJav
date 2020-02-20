package Application;

import java.io.Serializable;

public class Song implements Serializable {
    String title;
    String artist;
    String album;
    int songID;
    Song(){
        title = "untitled";
    }
    Song (String title){
        this.title = title;
    }
    Song(String[] info){
        this.title = info[0];
        this.artist = info[1];
        this.album = info[2];
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                '}';
    }
}
