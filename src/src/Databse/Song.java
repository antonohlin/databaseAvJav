package Databse;

public class Song {
    String title;
    int lengthInSeconds;
    int year;
    int songID;
    Song(){
        title = "untitled";
        lengthInSeconds = 0;
        year = 2020;
    }
    Song (String title, int lengthInSeconds, int year){
        this.title = title;
        this.lengthInSeconds = lengthInSeconds;
        this.year = year;
    }
}
