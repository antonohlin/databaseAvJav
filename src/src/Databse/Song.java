package Databse;

import java.io.Serializable;

public class Song implements Serializable {
    String title;
    int lengthInSeconds;
    int year;
    int songID;
    Song(){
        title = "untitled";
        lengthInSeconds = 0;
        year = 2020;
    }
    Song (String title){
        this.title = title;
    }
    Song(String[] info){
        this.title = info[0];
    }

    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                '}';
    }
}
