package Database;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utility implements Runnable {
    private static final BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

    Utility(){

    }

    public static void menu(){
        System.out.println("Hej vad vill du göra?");
        System.out.println("[1] Ta bort fil");
        System.out.println("[2] Hämta information om en fil");
        System.out.println("[3] Skriv ut en lista över mina filer");
        System.out.println("[4] Redigera en fil");
        System.out.println("[5] Tillbaka till startmeny");
        int menuChoice;
        do {
            menuChoice = Utility.getInt();
            switch (menuChoice) {
                case 1:
                    Utility.delete();
                    break;
                case 2:
                    Utility.getFileInfo();
                    break;
                case 3:
                    Utility.printAllInfo();
                    break;
                case 4:
                   Utility.editFile();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Ogiltigt val, försök igen!");
                    break;
            }
        } while (menuChoice < 1 || menuChoice > 5 );
    }

    private static void editFile() {
        System.out.println("Sök efter fil att redigera: ");
        String search = Utility.getLine();
        int searchIndex = 1;
        if (Utility.search(search).size() > 0){
            for (String s : Utility.search(search)) {
                System.out.println("["+ searchIndex++ + "] "+ s);
            }
            System.out.println("Hittade "+ Utility.search(search).size()+ " sökresultat");
            System.out.print("Ange siffran för den fil du vill redigera, välj 0 för att avbryta: ");
            int chosenFile = Utility.getInt()-1;
            String fileName = Utility.search(search).get(chosenFile);
            List<String> list = Arrays.asList(fileName.split(":"));
            String editedClass = list.get(0);
            editedClass = editedClass.substring(0, 1).toUpperCase() + editedClass.substring(1);
            System.out.println("Ange nytt namn: ");
            String newName = Utility.getLine();
            System.out.println("Ange nytt ID: ");
            String newID = Utility.getLine();
            String newLine = (editedClass+":"+newName+newID).replaceAll("\\s","");
            FileManager.saveEdit(newLine);
            Utility.removeLine(fileName);
        } else {
            System.out.println("Inga resultat.");
        }
    }

    private static void delete() {
        System.out.println("Sök efter fil att ta bort: ");
        String search = Utility.getLine();
        int searchIndex = 1;
        if (Utility.search(search).size() > 0){
            for (String s : Utility.search(search)) {
                System.out.println("["+searchIndex++ + "] "+ s);
            }
            System.out.println("Hittade "+ Utility.search(search).size()+ " sökresultat");
            System.out.print("Ange siffran för den fil du vill ta bort, välj 0 för att avbryta: ");
            int chosenFile = Utility.getInt()-1;
            String fileName = Utility.search(search).get(chosenFile);
            Utility.removeLine(fileName);
        } else {
            System.out.println("Inga resultat.");
        }

    }

    private static void removeLine(String fileName) {
        try {
            File inputFile = new File(Database.getFilepath());
            String tempPath = "src\\src\\Database\\Files\\filesText\\temp.txt";
            File tempFile = new File(tempPath);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.toLowerCase().contains(fileName)) {
                    continue;
                }
                writer.write(currentLine + "\n");
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
            Files.write(Path.of(tempPath), "".getBytes());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void printAllInfo() {
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(Database.getFilepath()));
            String filesLine;
            while ((filesLine = lineReader.readLine()) != null) {
                System.out.println(filesLine);
            }
            lineReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static List<String> search(String search){
        List<String> searchResults = new ArrayList<>();
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(Database.getFilepath()));
            String filesLine;
            while ((filesLine = lineReader.readLine()) != null) {
                filesLine = filesLine.toLowerCase();
                assert search != null;
                if (filesLine.contains(search)){
                    searchResults.add(filesLine);
                }
            }
            lineReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return searchResults;
    }

    public static void add(Entity o) {
        Class objectClass = o.getClass();
        try {
        for (Field ignored : objectClass.getFields()) {
            Field name = objectClass.getField("name");
            Field id = objectClass.getField("id");
        }
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        }
        String filename = o.toString() + o.getID();
        Utility.save(o, filename);
    }

    private static void save(Entity o, String fileName){
        FileManager.saveInfo(o);
        Serializer.serialize(o, fileName);
    }

    private static void getFileInfo() {
        System.out.print("Sök efter fil: ");
        String search = Utility.getLine();
        int searchIndex = 1;
        if (Utility.search(search).size() > 0){
            for (String s : Utility.search(search)) {
                System.out.println("["+searchIndex++ + "] "+ s);
            }
            System.out.println("Hittade "+ Utility.search(search).size()+ " sökresultat");
            System.out.print("Ange siffran för den fil du vill få information om, välj 0 för att avbryta: ");
            int chosenFile = Utility.getInt()-1;
            String fileName = Utility.search(search).get(chosenFile);
            /*List<String> list = Arrays.asList(fileName.split(":"));
            String nameToDeSer = list.get(1);*/
            System.out.println(fileName);
        } else {
            System.out.println("Inga resultat.");
        }
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
