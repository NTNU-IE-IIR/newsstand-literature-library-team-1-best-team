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
        addNewspapersToList();
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

    private void addNewspapersToList() {
        Newspaper VG = new Newspaper("VG", "Emil Nilsen", "21.03.18", 40, "kr");
        Newspaper VG2 = new Newspaper("VG", "Emil Nilsen", "12.04.18", 40, "kr");
        Newspaper Aftenposten = new Newspaper("Aftenposten", "Rune Berg", "18.03.17", 60, "kr");
        Newspaper NRK = new Newspaper("NRK", "Trygve Woldseth", "04.04.98", 100, "kr");
        addNewspaper(VG);
        addNewspaper(VG2);
        addNewspaper(Aftenposten);
        addNewspaper(NRK);
    }

}

