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
    Song (String title){
        this.title = title;
    }
}
