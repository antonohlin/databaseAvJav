package Application;
import Database.Database;
import Database.Utility;


public class Program {
    Program(){
        new Database();

        Album album1 = new Album("Allebumme");
        Song c = new Song("lååååd");
        album1.songs.add(c);
        Utility.add(c);
        Utility.add(album1);
        int menuChoice;
        do {
            Menus.printWelcomeMenu();
            menuChoice = Utility.getInt();
        switch (menuChoice) {
            case 1:
                System.out.println("Appfunktion 1");
                break;
            case 2:
                System.out.println("Appfunktion 2");
                break;
            case 3:
                System.out.println("Appfunktion 3");
                break;
            case 4: Utility.menu();
                break;
            case 5:
                System.out.println("Hej då");
                break;
            default:
                System.out.println("Ogiltigt val, försök igen!");
                break;
        }
        }  while (menuChoice != 5);
    }
}
