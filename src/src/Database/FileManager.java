package Database;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager implements Runnable {

    FileManager(){

    }

    static void saveInfo(Entity info){
        try {
            FileWriter fileWriter = new FileWriter(Database.getFilepath(), true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(info.getClass().getSimpleName() + ":" + info.toString() + info.getID());
            printWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    static void saveEdit(String info){
        try {
            FileWriter fileWriter = new FileWriter(Database.getFilepath(), true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(info);
            printWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {

    }
}
