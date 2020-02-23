package Database;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Utility implements Runnable {
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    Utility(){

    }

    public static void menu(){
        System.out.println("Hej vad vill du göra?");
        System.out.println("[1] Lägga till en fil");
        System.out.println("[2] Hämta information om en fil");
        System.out.println("[3] Skriv ut en lista över mina filer");
        System.out.println("[4] Redigera en fil");
        System.out.println("[5] Tillbaka till startmeny");
        int menuChoice;
        do {
            menuChoice = Utility.getInt();
            switch (menuChoice) {
                case 1:
                    Utility.addFile();
                    break;
                case 2:
                    Utility.getFileInfo();
                    break;
                case 3:
                    Utility.printAllInfo();
                    break;
                case 4:
                    System.out.println("edit file");
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen!");
                    break;
            }
        } while (menuChoice < 1 || menuChoice > 5 );
    }

    private static void printAllInfo() {

        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(Database.getFilepath()));
            String filesLine = null;
            while ((filesLine = lineReader.readLine()) != null) {
                System.out.println(filesLine);
            }
            lineReader.close();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }

    public static void addFile(){
        System.out.println("Ange ett filnamn: ");
        String fileName = Utility.getLine().toLowerCase();
        List<String> fileInformation = FileManager.collectInfo(fileName);
        FileManager.saveInfo(fileInformation);
        Serializer.serialize(fileInformation, fileName);
    }

    private static void getFileInfo() {
        System.out.print("Ange filnamn: ");
        String fileName = Utility.getLine().toLowerCase();
        Object deSer = Serializer.deserialize(fileName);
        System.out.println(deSer.toString());
    }

    public static int getInt(){
        return Integer.parseInt(getLine());
    }

    public static String getLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void run() {

    }
}
