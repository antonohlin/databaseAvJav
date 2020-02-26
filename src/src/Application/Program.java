package Application;
import Database.Database;
import Database.Utility;
import jdk.jshell.execution.Util;


public class Program {
    Program(){


        new Database();

        Song a = new Song("Låten");
        Song b = new Song("Den andra låten");

        Artist c = new Artist("Artisten");

        Album d = new Album("Albumet");

        d.songs.add(a);
        d.songs.add(b);
        c.albums.add(d);

        Utility.add(a);
        Utility.add(c);
        Utility.add(d);

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
