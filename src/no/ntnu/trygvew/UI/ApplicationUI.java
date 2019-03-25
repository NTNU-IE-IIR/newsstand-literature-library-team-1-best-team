package no.ntnu.trygvew.UI;

import no.ntnu.trygvew.BookFilter;
import no.ntnu.trygvew.InputtValidator;
import no.ntnu.trygvew.LiteratureStockRegister;
import no.ntnu.trygvew.litratureTypes.*;
import no.ntnu.trygvew.messingAround.Transaction;
import no.ntnu.trygvew.messingAround.User;
import no.ntnu.trygvew.messingAround.UserLoggin;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Makes up the user interface (text based) of the application.
 * Responsible for all user interaction, like displaying the menu
 * and receiving input from the user.
 * 
 * @author Trygve Woldseth
 * @version 1.0
 *
 *
 * Todo: Se om du skal ta og fjern balance fra brukeran siden det bare blir en litt sån halves imp
 *
 */


public class ApplicationUI
{
    private LiteratureStockRegister booRegister;
    private HashMap<String, Transaction> transactions;
    private UserLoggin userLoggin;
    private User currentUser = null;


    // The menu tha will be displayed. Please edit/alter the menu
    // to fit your application (i.e. replace "prodct" with "litterature"
    // etc.
    private final String[] baseMenuItems = {
            "view products",
            "Add new product",
            "Find a product by name",
    };

    private final String[] notLoggedInnMenuItems = {
            "Log inn",
            "Sign up",
    };

    private final String[] loggedInnMenuItems = {
            "Purhchase item",
            "logg out",
    };

    private final String[] searchMenuItems = {
            "Show Books",
            "Show Book Series",
            "Show Papers",
            "Show Magazines",
            "Back"
    };



    /**
     * Creates an instance of the ApplicationUI User interface.
     */
    public ApplicationUI() 
    {

    }

    /**
     * Starts the application by showing the menu and retrieving input from the
     * user.
     */
    public void start()
    {
        this.init();


        boolean quit = false;
        String UiState = "";

        while (!quit) 
        {
            try 
            {
                // rely not a good way of doing thid
                String menuSelection = this.showMenu(UiState);
                UiState = "";
                switch (menuSelection)
                {
                    case "view products":
                        this.displayLitrature(this.booRegister.getStock());
                        UiState = "Search";
                        break;

                    case "Add new product":
                        this.addNewProduct();
                        break;

                    case "Find a product by name":
                        this.filterLiteratureStock();
                        break;

                    case "Log inn":
                        this.tryUserLogin();
                        break;

                    case "Sign up":
                        this.addNewUser();
                        break;

                    case "Purhchase item":
                        System.out.println("not implemented");
                        break;

                    case "logg out":
                        this.logOut();
                        break;

                    case "Show Books":
                        this.displayBooks(this.booRegister.getStock());
                        break;

                    case "Show Book Series":
                        System.out.println("not imp");
                        break;

                    case "Show Papers":
                        this.displayPaper(this.booRegister.getStock());
                        break;

                    case "Show Magazines":
                        this.displayMagazine(this.booRegister.getStock());
                        break;

                    case "Back":
                        break;

                    case "Exit":
                        System.out.println("\nThank you for using Application v0.1. Bye!\n");
                        quit = true;
                        break;

                    default:
                }
            } 
            catch (InputMismatchException ime) 
            {
                System.out.println("\nERROR: not a valid number ..\n");
            }
        }        
        
    }

    /**
     * Displays the menu to the user, and waits for the users input. The user is
     * expected to input an integer between 1 and the max number of menu items. 
     * If the user inputs anything else, an InputMismatchException is thrown. 
     * The method returns the valid input from the user.
     *
     * @return the menu number (between 1 and max menu item number) provided by the user.
     * @throws InputMismatchException if user enters an invalid number/menu choice
     */
    private String showMenu(String UiState) throws InputMismatchException
    {
        System.out.println("\n\n\n\n**** Application v0.1 ****\n");
        boolean isUserLoggedInn = !(currentUser == null);
        if (!isUserLoggedInn){
            System.out.println("STATUS: " + "\u001B[31m"+ "Not Logged Inn" +"\u001B[0m");
        } else{
            System.out.println("STATUS: " + "\u001B[32m"+ "Logged Inn" +"\u001B[0m"+ " As " + this.currentUser.getUsername());
        }

        ArrayList<String> menuItems = new ArrayList<>();

        switch (UiState){
            case "Search":
                menuItems.addAll(Arrays.asList(this.searchMenuItems));
                break;
            default:
                // adds the base menu items
                menuItems.addAll(Arrays.asList(this.baseMenuItems));

                if (!isUserLoggedInn){
                    menuItems.addAll(Arrays.asList(this.notLoggedInnMenuItems));
                } else {
                    menuItems.addAll(Arrays.asList(this.loggedInnMenuItems));
                }
        }

        menuItems.add("Exit");

        // Display the menu
        for ( String item : menuItems )
        {
            System.out.println((menuItems.indexOf(item) + 1) + ". " + item);
        }

        int maxMenuItemNumber = menuItems.size();
        // Add the "Exit"-choice to the menu
        System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");


        // Read input from user
        Scanner reader = new Scanner(System.in);
        int menuSelection = reader.nextInt();
        if ((menuSelection < 1) || (menuSelection > maxMenuItemNumber)) 
        {
            throw new InputMismatchException();
        }
        return menuItems.get(menuSelection - 1);
    }
    
    // ------ The methods below this line are "helper"-methods, used from the menu ----
    // ------ All these methods are made privat, since they are only used by the menu ---
    
    /**
     * Initializes the application.
     * Typically you would create the LiteratureRegistrer-instance here
     */
    private void init()
    {
        this.booRegister = new LiteratureStockRegister("Data/inventory.json");
        this.userLoggin = new UserLoggin();
    }


    /**
     * Allows the user to try to logg inn with username and password and returns the user if valid loggin
     * @return a User object if sucsessful loggin, none if invallid login
     */
    private void tryUserLogin(){
        boolean isValidLogin = false;

        String inpUserName = null;
        String inpPassword = null;

        int tries = 0;
        try {
            Console console = System.console();
            if (console != null) {
                // TODO: decide if the user shold get a cople of tries befor beeing booted back to the menu
                /*
                while (!isValidLogin || tries > 3){
                    inpPassword = new String(console.readPassword("Password: "));

                    if (this.userLoggin.isValidLoggin(userName, inpPassword)){
                        isValidLogin = true;
                    } else {
                        System.out.println("Wrong username or password try again");
                    }

                    if (tries > 2){
                        System.out.println("inva");
                    }
                }
                */
                inpUserName = console.readLine("User name: ");
                inpPassword = new String(console.readPassword("Password: "));
                if (this.userLoggin.isValidLoggin(inpUserName, inpPassword)){
                    isValidLogin = true;
                    System.out.println("Welcom " + inpUserName);
                } else {
                    System.out.println("Wrong username or password");
                }

            }
        } catch (Exception e){e.printStackTrace();}

        if (isValidLogin){
            this.currentUser = this.userLoggin.loadUser(inpUserName, inpPassword);
        }
    }

    /**
     * Saves the current user data and loggs out the current user
     */
    private void logOut(){
        this.userLoggin.saveUser(this.currentUser);
        this.currentUser = null;
    }

    /**
     * Adds a new user
     */
    private void addNewUser(){
        Console console = System.console();
        Scanner sc = new Scanner(System.in);

        boolean validInput;
        String firstName = null;
        String lastName = null;

        String userName = null;
        String userPassword = null;

        float userFunds = 0;
        System.out.println("#######  NEW USER  #######");





        validInput = false;
        while (!validInput){
            System.out.print("input first name: ");
            firstName = sc.nextLine();
            validInput = InputtValidator.isValidStingInp(firstName);
        }


        validInput = false;
        while (!validInput){
            System.out.print("input last name: ");
            lastName = sc.nextLine();
            validInput = InputtValidator.isValidStingInp(lastName);
        }

        validInput = false;
        while (!validInput){
            System.out.print("input username: ");
            userName = sc.nextLine();
            if (this.userLoggin.isValidUsername(userName) && InputtValidator.isValidStingInp(userName)){
                validInput = true;
            } else if (InputtValidator.isValidStingInp(userName)){
                System.out.println("Username alredy in use");
            }

        }

        /*

        fjern hvis den over virke
        validInput = false;
        while (!validInput){
            System.out.print("input username: ");
            String inpUn = sc.nextLine();
            if(inpUn.length() > 0) {
                if (this.userLoggin.isValidUsername(inpUn)){
                    validInput = true;
                    userName = inpUn;
                } else {
                    System.out.println("Username alredy in use");
                }
            } else {
                System.out.println("No inputt provided");
            }
        }
        */

        validInput = false;
        while (!validInput) {
            System.out.print("input user funds: ");
            String inpFunds = sc.nextLine();
            validInput = InputtValidator.isValidFloatInp(inpFunds);
            if (validInput){
                userFunds = Float.valueOf(inpFunds);
            }
        }


        validInput = false;
        while (!validInput){
            try {
                if (console != null) {
                    String pw1 = new String(console.readPassword("Password: "));
                    String pw2 = new String(console.readPassword("repeat Password: "));

                    if (pw1.equals(pw2)) {
                        userPassword = pw1;
                        validInput = true;
                    } else{
                        System.out.println("passwords do not match");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.currentUser = this.userLoggin.createUser(userName,userPassword, firstName, lastName, userFunds);
    }

    private void  displayLitrature(ArrayList<Literature> displayList){
        DisplayLitrature.displayLiterature(displayList);

    }

    private void  displayBooks(ArrayList<Literature> displayList){
        DisplayLitrature.displayBook(displayList);
    }

    private void  displayPaper(ArrayList<Literature> displayList){
        DisplayLitrature.displayPaper(displayList);
    }

    private void  displayMagazine(ArrayList<Literature> displayList){
        DisplayLitrature.displayMagazine(displayList);
    }




    
    /**
     * Add a new product/literature to the register.
     * In this method you have to add code to ask the
     * user for the necessary information you need to 
     * create an instance of the product, which you
     * then send as a parameter to the addNewspaper()-
     * method of the register.
     * Remember to also handle invalid input from the
     * user!!
     */
    private void addNewProduct()
    {
        Scanner sc = new Scanner(System.in);

        boolean validInput;
        String typeToAdd = null;

        String title = null;
        String publisher = null;
        float price = 0;
        int stock = 0;



        System.out.println("To add Book - b");
        System.out.println("To add Book series - bs");
        System.out.println("To add Magazine - m");
        System.out.println("To add Paper - p");


        try {
            typeToAdd = sc.nextLine();
            assert typeToAdd.length() > 0;
            validInput = typeToAdd.equals("b") || typeToAdd.equals("bs")|| typeToAdd.equals("m")|| typeToAdd.equals("p");
        } catch (Exception e){
            validInput = false;
        }

        if (validInput) {

            validInput = false;
            while (!validInput) {
                System.out.print("input Title: ");
                title = sc.nextLine();
                validInput = InputtValidator.isValidStingInp(title);
            }

            validInput = false;
            while (!validInput) {
                System.out.print("input Publisher: ");
                publisher = sc.nextLine();
                validInput = InputtValidator.isValidStingInp(publisher);
            }

            validInput = false;
            while (!validInput) {
                System.out.print("input Price: ");
                String inpPrice = sc.nextLine();
                validInput = InputtValidator.isValidFloatInp(inpPrice);
                if (validInput) {
                    price = Float.valueOf(inpPrice);
                }
            }


            validInput = false;
            while (!validInput) {
                System.out.print("input Stock: ");
                String inpStock = sc.nextLine();
                validInput = InputtValidator.isValidIntInp(inpStock);
                if (validInput) {
                    stock = Integer.valueOf(inpStock);
                }
            }

            String inp_type = typeToAdd;
            if (typeToAdd.equals("m") || typeToAdd.equals("p")){
                typeToAdd = "sr";
            }



            switch (typeToAdd) {
                case "b": // book
                    // standalone
                    int edition = 0;
                    String author = null;
                    String publicationDate = null;


                    validInput = false;
                    while (!validInput) {
                        System.out.print("input Edition: ");
                        String inpEdition = sc.nextLine();
                        validInput = InputtValidator.isValidIntInp(inpEdition);
                        if (validInput) {
                            edition = Integer.valueOf(inpEdition);
                        }
                    }

                    validInput = false;
                    while (!validInput) {
                        System.out.print("input Author: ");
                        author = sc.nextLine();
                        validInput = InputtValidator.isValidStingInp(author);
                    }


                    validInput = false;
                    while (!validInput) {
                        System.out.print("input PublicationDate YYYY-MM-DD: ");
                        String inpPublicationDate = sc.nextLine();
                        // removes the - if the user inputs them.
                        inpPublicationDate = inpPublicationDate.replaceAll("-", "");

                        if (InputtValidator.isValidDate(inpPublicationDate)) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                            try {
                                if (dateFormat.parse(inpPublicationDate).before(new Date())) {
                                    validInput = true;
                                    publicationDate = inpPublicationDate;
                                } else {
                                    System.out.println("Date has not passsed");
                                }
                            } catch (Exception e) {
                            }
                        } else {
                            System.out.println("not a valid date");
                        }
                    }

                    Literature standalone = new Book(
                            title, publisher, stock, price, edition,  author, publicationDate);
                    this.booRegister.addLiterature(standalone);
                    break;
                case "bs": // book series
                    // standalone


                    validInput = false;
                    while (!validInput) {
                        System.out.print("input Edition: ");
                        String inpEdition = sc.nextLine();
                        validInput = InputtValidator.isValidIntInp(inpEdition);
                        if (validInput) {
                            edition = Integer.valueOf(inpEdition);
                        }
                    }

                    validInput = false;
                    while (!validInput) {
                        System.out.print("input Author: ");
                        author = sc.nextLine();
                        validInput = InputtValidator.isValidStingInp(author);
                    }


                    validInput = false;
                    while (!validInput) {
                        System.out.print("input PublicationDate YYYY-MM-DD: ");
                        String inpPublicationDate = sc.nextLine();
                        // removes the - if the user inputs them.
                        inpPublicationDate = inpPublicationDate.replaceAll("-", "");

                        if (InputtValidator.isValidDate(inpPublicationDate)) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                            try {
                                if (dateFormat.parse(inpPublicationDate).before(new Date())) {
                                    validInput = true;
                                    publicationDate = inpPublicationDate;
                                } else {
                                    System.out.println("Date has not passsed");
                                }
                            } catch (Exception e) {
                            }
                        } else {
                            System.out.println("not a valid date");
                        }
                    }
                    /**
                    Literature bookSeries = new BookSeries(
                            title, publisher, "Standalone", stock, price,);
                    this.booRegister.addLiterature(bookSeries);
                     **/
                    break;
                case "sr": // serialized
                    int yearlyDist = 0;
                    String serializedType = null;
                    String genere = null;


                    validInput = false;
                    while (!validInput) {
                        System.out.print("num Yearly distrebutions: ");
                        String inpNumY = sc.nextLine();
                        validInput = InputtValidator.isValidIntInp(inpNumY);
                        if (validInput) {
                            yearlyDist = Integer.valueOf(inpNumY);
                        }
                    }


                    validInput = false;
                    while (!validInput) {
                        System.out.print("input genere: ");
                        genere = sc.nextLine();
                        validInput = InputtValidator.isValidStingInp(genere);
                    }


                    Literature serialized = null;
                    if (inp_type.equals("m")){
                        serialized = new Magazine(
                                title, publisher,  stock, price, yearlyDist, genere);
                    } else if (inp_type.equals("p")){
                        serialized = new Paper(
                                title, publisher, stock, price, yearlyDist, genere);
                    }

                    this.booRegister.addLiterature(serialized);
                    break;

            }
        } else{
            System.out.println("invallid Inputt");
        }


    }

    /**
     * Find and display a product based om name (title).
     * As with the addNewProduct()-method, you have to
     * ask the user for the string (name/title/publisher)
     * to search for, and then use this string as input-
     * parameter to the method in the register-object.
     * Then, upon return from the register, you need
     * to print the details of the found item.
     */
    private void filterLiteratureStock()
    {
        ArrayList<Literature> bookStock =  this.booRegister.getStock();
        Scanner sc = new Scanner(System.in);


        System.out.println("For title - t");
        //System.out.println("For author - a");
        System.out.println("For publisher - p");
        System.out.print("chose search type: ");


        String inpTitle = sc.nextLine();

        if (inpTitle.contains("t")){
            System.out.print("search for title: ");
            String filter = sc.nextLine();
            ArrayList<Literature> ar = BookFilter.filterLiteratureByTitle(filter, bookStock);
            this.displayLitrature(ar);
        } /*else if (inpTitle.contains("a")){
            System.out.print("search for author: ");
            String filter = sc.nextLine();
            ArrayList<StandaloneLiterature> ar = BookFilter.filterBookByAuthor(filter, bookStock);
            this.displayItems(ar);
        }*/ else if (inpTitle.contains("p")){
            System.out.print("search for publisher: ");
            String filter = sc.nextLine();
            ArrayList<Literature> ar = BookFilter.filterLiteratureByPublisher(filter, bookStock);
            this.displayLitrature(ar);
        }
    }
    
}


