package Application;
import Database.Database;
import Database.Utility;


public class Program {
    Program(){


        new Database();
        Song c = new Song("blablabla");
        Utility.add(c);

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
