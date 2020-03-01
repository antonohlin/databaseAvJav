package Database;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


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
        String search;
        String fileName;
        String newLine;
        String nameToSerialize;
        String editedClass;
        String editedName;
        String newName;
        String newID;
        System.out.println("Sök efter fil att redigera: ");
        search = Utility.getLine();
        int searchIndex = 1;
        if (Utility.search(search).size() > 0) {
            for (String s : Utility.search(search)) {
                System.out.println("[" + searchIndex++ + "] " + s);
            }
            System.out.println("Hittade " + Utility.search(search).size() + " sökresultat");
            System.out.print("Ange siffran för den fil du vill redigera, välj 0 för att avbryta: ");
            int chosenFile = Utility.getInt() - 1;
            if (chosenFile != -1) {
                fileName = Utility.search(search).get(chosenFile);
                List<String> list = Arrays.asList(fileName.split(":"));
                editedClass = list.get(0);
                editedName = list.get(1);
                editedClass = editedClass.substring(0, 1).toUpperCase() + editedClass.substring(1);
                var deSer = Serializer.deserialize(editedName);
                assert deSer != null;
                try {
                    Method setName = deSer.getClass().getMethod("setName", String.class);
                    Method getID = deSer.getClass().getMethod("getID");
                    System.out.println("Ange nytt namn: ");
                    newName = Utility.getLine();
                    setName.invoke(deSer, newName);
                    System.out.println("Ange nytt ID (Lämna blankt för att använda ordinarie): ");
                    newID = Utility.getLine();
                    if (newID.isBlank()) {
                        long oldID = (long) getID.invoke(deSer);
                        newLine = (editedClass + ":" + newName + oldID).replaceAll("\\s", "");
                        nameToSerialize = (newName + oldID).replaceAll("\\s", "");
                    } else {
                        newLine = (editedClass + ":" + newName + newID).replaceAll("\\s", "");
                        nameToSerialize = (newName + newID).replaceAll("\\s", "");
                    }
                    Serializer.serialize(deSer, nameToSerialize);
                    Utility.saveEdit(newLine);
                    Utility.removeLine(fileName);
                    Files.deleteIfExists(Paths.get(Database.getFilesFolder() + editedName));
                } catch (NoSuchMethodException | IllegalAccessException | IOException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            System.out.println("Inga resultat.");
        }
    }

    private static void delete() {
        String search;
        String fileName;
        String editedName;
        System.out.println("Sök efter fil att ta bort: ");
        search = Utility.getLine();
        int searchIndex = 1;
        if (Utility.search(search).size() > 0){
            for (String s : Utility.search(search)) {
                System.out.println("["+searchIndex++ + "] "+ s);
            }
            System.out.println("Hittade "+ Utility.search(search).size()+ " sökresultat");
            System.out.print("Ange siffran för den fil du vill ta bort, välj 0 för att avbryta: ");
            int chosenFile = Utility.getInt()-1;
            if (chosenFile != -1){
            fileName = Utility.search(search).get(chosenFile);
            List<String> list = Arrays.asList(fileName.split(":"));
            editedName = list.get(1);
            Utility.removeLine(fileName);
            try {
                Files.deleteIfExists(Paths.get(Database.getFilesFolder()+editedName));
            } catch (IOException e){
                e.printStackTrace();
            }
            }
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
            Field name = objectClass.getDeclaredField("name");
            Field id = objectClass.getDeclaredField("id");
            id.setAccessible(true);
            name.setAccessible(true);
            String valueOfName = name.get(o).toString();
            String valueOfID = id.get(o).toString();
            String filename = valueOfName + valueOfID;
            Utility.save(o, filename);
        } catch (NoSuchFieldException | IllegalAccessException e){
            e.printStackTrace();
        }
    }

    private static void save(Entity o, String fileName){
        Utility.saveInfo(o);
        Serializer.serialize(o, fileName);
    }

    private static void getFileInfo() {
        System.out.print("Sök efter fil: ");
        String search = Utility.getLine();
        int searchIndex = 1;
        if (Utility.search(search).size() > 0) {
            for (String s : Utility.search(search)) {
                System.out.println("[" + searchIndex++ + "] " + s);
            }
            System.out.println("Hittade " + Utility.search(search).size() + " sökresultat");
            System.out.print("Ange siffran för den fil du vill få information om, välj 0 för att avbryta: ");
            int chosenFile = Utility.getInt() - 1;
            if (chosenFile != -1) {
                String fileName = Utility.search(search).get(chosenFile);
                System.out.println(fileName);
            }
        } else {
            System.out.println("Inga resultat.");
        }

    }

    private static int getInt(){
        return Integer.parseInt(getLine());
    }

    private static String getLine() {
        try {
            return input.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void saveInfo(Entity info){
        try {
            FileWriter fileWriter = new FileWriter(Database.getFilepath(), true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(info.getClass().getSimpleName() + ":" + info.toString() + info.getID());
            printWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void saveEdit(String info){
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
