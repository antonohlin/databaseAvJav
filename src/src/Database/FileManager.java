package Database;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileManager implements Runnable {
    private final static Scanner input = new Scanner(System.in);

    FileManager(){

    }
    static List<String> collectInfo(String fileName){
        List<String> info = new ArrayList<>();
        info.add(fileName);
        String cont;
        do {
            System.out.println("Ange variabel: ");
            String type = Utility.getLine();
            System.out.println("Ange värde: ");
            String value = Utility.getLine();
            info.add(type);
            info.add(value);
            System.out.println("Lägga till fler variabler? Y/N");
            cont = input.next().toUpperCase();
            while (!cont.equals("Y") && !cont.equals("N")) {
                System.out.println("Ogiltigt svar, försök igen!");
                cont = input.next().toUpperCase();
            }
        } while (!cont.equals("N"));
        return info;
    }

    static void saveInfo(List<String> info){
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
