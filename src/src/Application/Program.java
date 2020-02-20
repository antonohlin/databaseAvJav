package Application;

import Databse.Database;
import Databse.Utility;

public class Program {
    Program(){
        new Database();
        Menus.printWelcomeMenu();
        Utility.menuChoiceSwitch();
    }
}
