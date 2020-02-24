package Database;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
                System.out.println("["+searchIndex++ + "] "+ s);
            }
            System.out.println("Hittade "+ Utility.search(search).size()+ " sökresultat");
            System.out.print("Ange siffran för den fil du vill redigera, välj 0 för att avbryta: ");
        }
        int chosenFile = Utility.getInt()-1;
        String fileName = Utility.search(search).get(chosenFile);
        List<String> fileInformation = FileManager.collectInfo(fileName);
        Utility.save(fileInformation, fileName);
    }


    private static void printAllInfo() {
        try {
            BufferedReader lineReader = new BufferedReader(new FileReader(Database.getFilepath()));
            String filesLine;
            while ((filesLine = lineReader.readLine()) != null) {
                Stream.of(filesLine)
                        .map(s -> s.replace(", "," Value:"))
                        .map(s -> s.replace("[", ""))
                        .map(s -> s.replace("]", ""))
                        .map("Filename:"::concat)
                        .peek(System.out::println).collect(Collectors.toList());
                //System.out.println(filesLine);
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
                filesLine = filesLine.split(" ")[0]
                        .replace("[","")
                        .replace(",","");
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

    public static void addFile(){
        boolean available = false;
        System.out.println("Ange ett filnamn: ");
        String fileName = Utility.getLine().toLowerCase();
        do {
            if (Utility.search(fileName).size() > 0) {
                for (String s : Utility.search(fileName)) {
                    if (fileName.equals(s)) {
                        available = false;
                        System.out.println("Filnamnet: " + s + " finns redan. Ange ett nytt filnamn: ");
                        fileName = Utility.getLine().toLowerCase(); break;
                    } else {
                        available = true;
                    }
                }
            } else {
                available = true;
            }
        }while (!available);
        List<String> fileInformation = FileManager.collectInfo(fileName);
        Utility.save(fileInformation, fileName);
    }
    private static void save(List<String> fileInformation, String fileName){
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
