import java.util.InputMismatchException;

/**
 * This class of my literature register with UI!
 *
 * This class handles all the UI text which will
 * be displayed to the user.
 *
 * In this class you can:
 *  create a text based UI for a simple newspaper register
 *  search for newspapers by a specific title
 *  list all the newspaper in our newspaper register
 *  start our RegisterUI
 *  stop our RegisterUI
 *
 * @author Emil Elton Nilsen
 * @version 27.02.2019
 */

public class RegisterUI {

    private Parser parser;
    private Register literatureRegister;

    public RegisterUI() {
        literatureRegister = new Register();
        parser = new Parser();
    }

    /**
     * The method that handles how we start our Register UI,
     * and how we stop our RegisterUI.
     *
     * To start the application this method must be called
     * in main.
     */
    public void start() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for using our store. Have a good day!");
    }

    /**
     *
     */
    private void printWelcome() {
        System.out.println("Welcome!");
        System.out.println("This is my new super awesome kiosk!");
        System.out.println("if you need help type 'help'");
        System.out.println();
    }

    /**
     * This method processes the user input commands.
     *
     * @param command the command the a user types into the UI
     * @return wantToExit, that checks if 'exit' has been typed in the UI
     */
    private boolean processCommand (Command command) {
        boolean wantToExit = false;

        String commandWord = command.getCommandWord();
        if (command.isUnknown()) {
            System.out.println("I dont know what you mean...");
        }
        else if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("exit")) {
            wantToExit = exit(command);
        }
        else if (commandWord.equals("literature")) {
            printLiteratureList();
        }
        else if (commandWord.equals("titlesearch")) {
            printLiteratureByTitle(command);
        }
        else if (commandWord.equals("publishersearch")) {
            printLiteratureByPublisher(command);
        }
        else if (commandWord.equals("type")) {
            printLiteratureByType(command);
        }
        else if (commandWord.equals("publisher")) {
            printLiteratureByPublisherAsList(command);
        }
        else if (commandWord.equals("add")) {
            addNewLiterature(command);
        }
        else if (commandWord.equals("series")) {
            addLiteratureToSeries(command);
        }
        return wantToExit;
    }

    /**
     * This method checks if our user has entered the 'exit' command
     */
    private boolean exit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Exit what?");
            return false;
        }
        else {
            return true;
        }
    }

    /**
     * A method tha prints out a help message, which contains all available commands
     */
    private void printHelp() {
        System.out.println("Im sorry that you are having trouble using the store,");
        System.out.println("Maby some of these command words can help you? ");
        parser.showCommands();
    }

    /**
     * Prints out a full list of all the newspapers in our list of Newspapers with
     * all the information about our newspapers.
     */
    public void printLiteratureList() {

        System.out.println(literatureRegister.getLiteratureListAsString());

    }

    /**
     * Lets the user search for a newspaper just by entering the title.
     * If there is a newspaper connected to the title, the UI will list all
     * necessary information about the newspaper.
     *
     * If there is no newspaper connected to the title, a error message
     * will be printed in the UI
     *
     * @param command 'search' + the title of the desired newspaper
     */
    public void printLiteratureByTitle(Command command) {
        if (!command.hasSecondWord()) {
            //if there is no second word, we dont know what to search for
            System.out.println("After 'search' write in the literature by title that you want to search for");
        }

        String searchWord = command.getSecondWord();
        if (literatureRegister.findLiteratureByTitle(searchWord) == null) {
            System.out.println("The literature you searched for is not in our store...");
        }
        else {
            Literature literature = literatureRegister.findLiteratureByTitle(searchWord);
            System.out.println(literature.getDescriptionOfLiteratureAsString());
        }
    }

    public void printLiteratureByPublisher(Command command) {
        if (!command.hasSecondWord()) {
            //if there is no second word, we dont know what to search for
            System.out.println("After 'search' write in the literature by publisher that you want to search for");
        }

        String searchWord = command.getSecondWord();
        if (literatureRegister.findLiteratureByPublisher(searchWord) == null) {
            System.out.println("The literature that you searched for is not in our store...");
        }
        else {
            Literature literature = literatureRegister.findLiteratureByPublisher(searchWord);
            System.out.println(literature.getDescriptionOfLiteratureAsString());
        }
    }

    public void printLiteratureByType(Command command) {
        if (!command.hasSecondWord()) {
            //if there is no second word, we dont know what to search for
            System.out.println("After 'type' write in the literature you want to search for");
        }

        String searchWord = command.getSecondWord();
        if (literatureRegister.getLiteratureByTypeAsString(searchWord) == null) {
            System.out.println("The type of literature you searched for is not in our store...");
        }
        else {
            System.out.println(literatureRegister.getLiteratureByTypeAsString(searchWord));
            System.out.println("Do you want to make any of these into a series?");
            System.out.println("give the command 'series + the title of the specified literature'");
            literatureRegister.removeLiteratureFromLiteratureByTypeList();
        }
    }

    public void addLiteratureToSeries(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("after 'series' type in the title of the literature that you want to add to a series");
        }

        String searchWord = command.getSecondWord();
        if (literatureRegister.findLiteratureByTitle(searchWord) == null) {
            System.out.println("The type of literature you want to add to a series is not in our store");
        }
        else {
            literatureRegister.addLiteratureToSeries(literatureRegister.findLiteratureByTitle(searchWord));
            literatureRegister.removeLiterature(literatureRegister.findLiteratureByTitle(searchWord));
            System.out.println("The literature was added to the series!");
        }
    }

    public void printLiteratureByPublisherAsList(Command command) {
        if (!command.hasSecondWord()) {
            //if there is no second word, we dont know what to search for
            System.out.println("After 'publisher' write in the publisher that you want to search for");
        }

        String searchWord = command.getSecondWord();
        if (literatureRegister.getLiteratureByPublisherAsString(searchWord) == null) {
            System.out.println("We dont have anything from that publisher in our store");
        }
        else {
            System.out.println(literatureRegister.getLiteratureByPublisherAsString(searchWord));
            literatureRegister.removeLiteratureFromLiteratureByPublisherList();
        }
    }

    public void addNewLiterature(Command command) {
        try {
            String title = command.getSecondWord();
            String publisher = command.getThirdWord();
            String publishedDate = command.getFourthWord();
            String price = command.getFifthWord();
            String currency = command.getSixthWord();
            String typeOfLiterature = command.getSeventhWord();
            if (!command.hasSecondWord() || !command.hasThirdWord() || !command.hasFourthWord() || !command.hasFifthWord() || !command.hasSixthWord() || !command.hasSeventhWord()) {
                System.out.println("Please write the command on this form: add Javafordummies javaexperts 12.03.17 400 kr book");
            } else {
                /*Here the type of literature is made lowercase as this makes searching by type
                more user friendly*/
                literatureRegister.addLiteratureToRegister(title, publisher, publishedDate, price, currency, typeOfLiterature.toLowerCase());
                System.out.println("The literature was added to the register");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println("Add literature was cancelled, please restart the register");
        }
    }

    /**
     * Where we run our store from
     */
    public static void main(String[] args) {
        try {
            RegisterUI registerUI = new RegisterUI();
            registerUI.start();
        }
        catch (Exception e) {
            System.out.println("Something went wrong, please restart the application or visit: 'Dumb-it-service' to get no help");
        }

    }

}
