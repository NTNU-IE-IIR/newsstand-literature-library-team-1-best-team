import java.sql.SQLOutput;

/**
 * This class of my newspaper register with UI!
 *
 * This class handles all the UI text which will
 * be displayed to the user.
 *
 * @author Emil Elton Nilsen
 * @version 27.02.2019
 */

public class RegisterUI {

    private Parser parser;
    private Register newspaperRegister;

    public RegisterUI() {
        newspaperRegister = new Register();
        parser = new Parser();
    }

    public void start() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for using our store. Have a good day!");
    }

    private void printWelcome() {
        System.out.println("Welcome!");
        System.out.println("This is my new super awesome newspaper store!");
        System.out.println("if you need help type 'help'");
        System.out.println();
    }

    private boolean processCommand (Command command) {
        boolean wantToExit = false;

        if (command.isUnknown()) {
            System.out.println("I dont know what you mean...");
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("exit")) {
            wantToExit = exit(command);
        }
        else if (commandWord.equals("newspapers")) {
            printNewspaperList();
        }
        else if (commandWord.equals("search")) {
            printNewspaperByTitle(command);
        }
        return wantToExit;
    }

    private boolean exit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Exit what?");
            return false;
        }
        else {
            return true;
        }
    }

    private void printHelp() {
        System.out.println("Im sorry that you are having trouble using the store,");
        System.out.println("Maby some of these command words can help you? ");
        parser.showCommands();
    }
    /**
     * Prints out a full list of all the newspapers in our list of Newspapers with
     * all the information about our newspapers.
     */
    public void printNewspaperList() {

        System.out.println(newspaperRegister.getNewspaperListAsString());

    }

    public void printNewspaperByTitle(Command command) {
        if (!command.hasSecondWord()) {
            //if there is no second word, we dont know what to search for
            System.out.println("After 'search' type in the newspaper you want to search for");
        }


        String searchWord = command.getSecondWord();
        if (newspaperRegister.findNewspaperByTitle(searchWord) == null) {
            System.out.println("The newspaper you searched for is not in our store...");
        }
        else {
            System.out.println(newspaperRegister.findNewspaperByTitle(searchWord));
        }
    }

    /**
     * Where we run our store from
     */
    public static void main(String[] args) {
        RegisterUI registerUI = new RegisterUI();
        registerUI.start();

    }

}
