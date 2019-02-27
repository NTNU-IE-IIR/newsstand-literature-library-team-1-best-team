import java.util.ArrayList;

/**
 * Register keeps a list of all the newspapers that are in stock, which
 * is printed into the terminal.
 * From this register you can:
 *  Add a newspaper
 *  Remove a newspaper
 *  Get a list of all the newspapers
 *  Search newspapers by title
 *
 * @author Emil Elton Nilsen.
 * @version 1.0 (30.01.2019)
 */
public class Register {

    private ArrayList<Newspaper> newspaperList;

    /**
     * Create a new ArrayList that stores all of our added newspapers in a list
     */
    public Register () {
        this.newspaperList = new ArrayList<Newspaper>();
    }

    /**
     * Add a created newspaper for the Newspaper class to our
     * list of newspapers.
     *
     * @param newspaper A newspaper object created from the Newspaper class
     */
    public void addNewspaper(Newspaper newspaper) {
        this.newspaperList.add(newspaper);
    }

    /**
     * Prints out a full list of all the newspapers in our list of Newspapers with
     * all the information about our newspapers.
     */
    public void printNewspaperList() {
        for (Newspaper newspaper : newspaperList) {
            System.out.println(newspaper.getTitle() + ", " + newspaper.getPublisher() + ", " + newspaper.getPublishedDate() + ", "
                    + newspaper.getPrice() + newspaper.getCurrency());
        }
    }

    /**
     * Search the newspaper list for a newspaper with the name given.
     *
     * Return the newspaper if found. If not found null is returned
     */
    public Newspaper findNewspaperByTitle(String title) {
        Newspaper newspaperTitle = null;
        for (Newspaper newspaper : newspaperList) {
            if (newspaper.getTitle().equals(title)) {
                newspaperTitle = newspaper;
            }
        }
        return newspaperTitle;
    }

}

