package no.ntnu.trygvew;

import java.text.ParseException;
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
 *
 * Todo: mere presis switchcase
 *
 */


public class ApplicationUI
{
    BookStokRegister booRegister;


    // The menu tha will be displayed. Please edit/alter the menu
    // to fit your application (i.e. replace "prodct" with "litterature"
    // etc.
    private String[] menuItems = {
        "1. List all products",
        "2. Add new product",
        "3. Find a product by name",
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

        while (!quit) 
        {
            try 
            {
                int menuSelection = this.showMenu();
                switch (menuSelection) 
                {
                    case 1:
                        this.listAllProducts();
                        break;

                    case 2:
                        this.addNewProduct();
                        break;

                    case 3:
                        this.findProductByName();
                        break;

                    case 4:
                        System.out.println("\nThank you for using Application v0.1. Bye!\n");
                        quit = true;
                        break;

                    default:
                }
            } 
            catch (InputMismatchException ime) 
            {
                System.out.println("\nERROR: Please provide a number between 1 and " + this.menuItems.length + "..\n");
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
    private int showMenu() throws InputMismatchException 
    {
        System.out.println("\n\n\n\n**** Application v0.1 ****\n");
        // Display the menu
        for ( String menuItem : menuItems )
        {
            System.out.println(menuItem);
        }
        int maxMenuItemNumber = menuItems.length + 1;
        // Add the "Exit"-choice to the menu
        System.out.println(maxMenuItemNumber + ". Exit\n");
        System.out.println("Please choose menu item (1-" + maxMenuItemNumber + "): ");
        // Read input from user
        Scanner reader = new Scanner(System.in);
        int menuSelection = reader.nextInt();
        if ((menuSelection < 1) || (menuSelection > maxMenuItemNumber)) 
        {
            throw new InputMismatchException();
        }
        return menuSelection;
    }
    
    // ------ The methods below this line are "helper"-methods, used from the menu ----
    // ------ All these methods are made privat, since they are only used by the menu ---
    
    /**
     * Initializes the application.
     * Typically you would create the LiteratureRegistrer-instance here
     */
    private void init()
    {
        this.booRegister = new BookStokRegister("Data/inventory.json");
    }

    /**
     * Displays the items
     * @param displayItr
     */
    private void displayItems(Iterator<Book> displayItr){

        ArrayList<Book> cash = new ArrayList<>();

        displayItr.forEachRemaining(b -> cash.add(b));

        if (cash.size() > 0){
            System.out.println("\n\nDisplaying :" + cash.size() + " items\n\n");
            String[] bookInfoHeaders = {"Title", "Edition", "Author", "PublicationDate", "Publisher", "Price", "Stock"};
            for (String hed: bookInfoHeaders){System.out.print(String.format("|  %-17s", hed));}

            cash.forEach(b -> {
                System.out.println();

                System.out.print(String.format("|  %-17s", b.getTitle()));
                System.out.print(String.format("|  %-17s", b.getEdition()));
                System.out.print(String.format("|  %-17s", b.getAutor()));
                System.out.print(String.format("|  %-17s", b.getPublicationDate()));
                System.out.print(String.format("|  %-17s", b.getPublisher()));
                System.out.print(String.format("|  %-17s", b.getPrice()));
                System.out.print(String.format("|  %-17s", b.getNumberInStok()));
            });

        } else {
            System.out.println("\n\n No items found \n\n");
        }


    }

    /**
     * Lists all the products/literature in the register
     */
    private void listAllProducts()
    {
        Iterator<Book> bookIterator =  this.booRegister.getBookIterator();
        this.displayItems(bookIterator);
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


        String title = null;
        int edition = 0;
        String author = null;
        String publicationDate = null;
        String publisher = null;
        float price = 0;
        int stok = 0;

        boolean validInput;



        validInput = false;
        while (!validInput){
            System.out.print("input Title: ");
            String inpTitle = sc.nextLine();
            if (inpTitle.length() > 0){
                validInput = true;
                title = inpTitle;
            } else{
                System.out.println("No Inputt Provided");
            }
        }


        validInput = false;
        while (!validInput){
            System.out.print("input Edition: ");
            String inpEdition = sc.nextLine();
            try {
                edition = Integer.parseInt(inpEdition);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("input is not a number (int)");
            }
        }


        validInput = false;
        while (!validInput){
            System.out.print("input Author: ");
            String inpAuthor = sc.nextLine();
            if(inpAuthor.length() > 0) {
                validInput = true;
                author = inpAuthor;
            } else {
                System.out.println("No inputt provided");
            }
        }

        validInput = false;
        while (!validInput){
            System.out.print("input PublicationDate YYYY-MM-DD: ");
            String inpPublicationDate = sc.nextLine();
            // removes the - if the user inputs them.
            if (inpPublicationDate.contains("-")){
                inpPublicationDate =inpPublicationDate.replaceAll("-", "");
            }
            if (InputtValidator.isValidDate(inpPublicationDate)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                try {
                    if (dateFormat.parse(inpPublicationDate).before(new Date())){
                        validInput = true;
                        publicationDate = inpPublicationDate;
                    } else {
                        System.out.println("Date has not passsed");
                    }
                } catch (ParseException e) {/* dup di dup di dup vil aldri skje */}
            } else {
                System.out.println("not a valid date");
            }
        }


        validInput = false;
        while (!validInput){
            System.out.print("input Publisher: ");
            String inpPublisher = sc.nextLine();
            if (inpPublisher.length() > 0) {
                validInput = true;
                publisher = inpPublisher;
            } else {
                System.out.println("no inputt detected");
            }
        }

        validInput = false;
        while (!validInput) {
            System.out.print("input Price: ");
            String inpPrice = sc.nextLine();
            try {
                price = Float.parseFloat(inpPrice);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("input is not a number (float)");
            }
        }

        System.out.print("input Stock: ");
        validInput = false;
        while (!validInput) {
            System.out.print("input Stock: ");
            String inpStock = sc.nextLine();
            try {
                stok = Integer.parseInt(inpStock);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("input is not a number (int)");
            }
        }


        Book newBook = new Book(title, publisher, edition,  author, publicationDate, stok, price);
        this.booRegister.addBook(newBook);
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
    private void findProductByName()
    {
        Iterator<Book> bookIterator =  this.booRegister.getBookIterator();
        Scanner sc = new Scanner(System.in);


        System.out.println("For title - t");
        System.out.println("For author - a");
        System.out.println("For publisher - p");
        System.out.print("chose search type: ");


        String inpTitle = sc.nextLine();

        if (inpTitle.contains("t")){
            System.out.print("search for title: ");
            String filter = sc.nextLine();
            Iterator<Book> it = BookFilter.filterBookByTitle(filter, bookIterator);
            this.displayItems(it);
        } else if (inpTitle.contains("a")){
            System.out.print("search for author: ");
            String filter = sc.nextLine();
            Iterator<Book> it = BookFilter.filterBookByAuthor(filter, bookIterator);
            this.displayItems(it);
        } else if (inpTitle.contains("p")){
            System.out.print("search for publisher: ");
            String filter = sc.nextLine();
            Iterator<Book> it = BookFilter.filterBookByPublisher(filter, bookIterator);
            this.displayItems(it);
        }
    }
    
}


