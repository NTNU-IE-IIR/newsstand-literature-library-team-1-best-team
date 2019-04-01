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

    private ArrayList<Literature> literatureList;


    /**
     * Create a new ArrayList that stores all of our added Literature in a list
     */
    public Register () {
        this.literatureList = new ArrayList<Literature>();
        addLiteratureToList();
    }

    /**
     * Add a created object for the subclasses class to our
     * list of Literature's.
     *
     * @param Literature A Literature object created from the subclasses
     */
    public void addLiterature(Literature Literature) {
        this.literatureList.add(Literature);
    }

    public String getLiteratureListAsString() {

        String literatureListed = "";
        for (Literature literature : literatureList) {
            literatureListed += literature.getTitle() + ", " + literature.getPublisher() + ", " + literature.getPublishedDate() + ", " +
                               literature.getPrice() + literature.getCurrency() + "\n";
        }
        return literatureListed;
    }
    public ArrayList<Literature> getLiterature(){
        return this.literatureList;
    }


    /**
     * Search the newspaper list for a newspaper with the name given.
     *
     * Return the newspaper if found. If not found null is returned
     */
    public Literature findLiteratureByTitle(String title) {
        Literature literatureTitle = null;
        for (Literature literature : literatureList) {
            if (literature.getTitle().equals(title)) {
                literatureTitle = literature;
            }
        }
        return literatureTitle;
    }

    public void addLiteratureToList() {
        Literature VG = new Newspaper("VG", "Emil Nilsen", "21.03.18", 40, "kr");
        Literature VG2 = new Newspaper("VG", "Emil Nilsen", "12.04.18", 40, "kr");
        Literature Aftenposten = new Newspaper("Aftenposten", "Rune Berg", "18.03.17", 60, "kr");
        Literature NRK = new Newspaper("NRK", "Trygve Woldseth", "04.04.98", 100, "kr");

        addLiterature(VG);
        addLiterature(VG2);
        addLiterature(Aftenposten);
        addLiterature(NRK);

        Literature Snømannen = new Book("Snømannen", "Dag Olsen", "27.03.2019", 200, "kr");
        addLiterature(Snømannen);

        Literature DonaldD = new Magazine("Donald Duck", "Quack Quackesen", "27.03.2019", 60, "kr");
        addLiterature(DonaldD);
    }


}

