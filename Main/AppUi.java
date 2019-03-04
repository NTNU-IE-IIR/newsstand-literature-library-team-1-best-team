
import java.util.Scanner;
/**
 * Makes up the user interface (text based) of the application.
 * Responsible for all user interaction, like displaying the menu
 * and receiving input from the user.
 *
 * @author Rune S.B
 * @version 1.0
 */

public class AppUi {
    private Parser parser;
    //
    //


    public void start() {
        PrintWelcome();


            boolean finished = false;
            while (!finished) {
                Command command = parser.getCommand();
                finished = processCommand(command);

            }
            System.out.println("Thank you come again.");

    }
        private void PrintWelcome()
        {
            System.out.println("Welcome!");
            System.out.println("This is the search window for my store");
            System.out.println("For the commands type: commands ");
            System.out.println();
        }
        private boolean processCommand (Command command)
        {
            boolean wantToQuit = false;

            if (command.isUnknown()) {
                System.out.println("I don't know what you mean...");
                return false;
            }
            String commandWord = command.getCommandWord();
            if (commandWord.equals("commands")) {
                printhelp();
            } else if (commandWord.equals("list")) {
                printNewspaperList(command);
            } else if (commandWord.equals("quit")) {
                wantToQuit = quit(command);
            }
            // else command not recognised.
            return wantToQuit;
        }
         private void printhelp() {
             System.out.println("This is the list of your commands");
             parser.showCommands();

         }
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true;  // signal that we want to quit
        }
    }

        public void printNewspaperList() {

            System.out.println(newspaperRegister.getNewspaperListAsString());
        }
        public void printNewspaperByTitle (Command command)
            {
            if (!command.hasSecondWord()) {
                //if there is no second word, we dont know what to search for
                System.out.println("After 'search' type in the newspaper you want to search for");
            }
        }

}