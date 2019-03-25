import java.util.ArrayList;

/**
 * Register keeps a list of all the newspapers that are in stock, which
 * is printed into the terminal.
 * From this register you can:
 *  Add a literature
 *  Remove a literature
 *  Get a list of all the newspapers
 *  Search literature by title
 *
 * @author Emil Elton Nilsen.
 * @version 1.0 (30.01.2019)
 */
public class Register {

    private ArrayList<Literature> literatureList;

    /**
     * Create a new ArrayList that stores all of our added newspapers in a list
     */
    public Register () {
        this.literatureList = new ArrayList<Literature>();
        addLiteratureToList();
    }

    /**
     * Add a created literature for the literature class to our
     * list of literature.
     *
     * @param literature A literature object created from the literature class
     */
    public void addLiterature(Literature literature) {
        this.literatureList.add(literature);
    }

    public String getLiteratureListAsString() {

        String literatureListed = "";
        for (Literature literature : literatureList) {
            literatureListed += literature.getTitle() + ", " + literature.getPublisher() + ", " + literature.getPublishedDate() + ", " +
                               literature.getPrice() + literature.getCurrency() + "\n";
        }
        return literatureListed;
    }

    /**
     * Search the literature list for a literature with the name given.
     *
     * Return the literature if found. If not found null is returned
     */

    public Literature findLiteratureByTitle(String title) {
        Literature foundLiterature = null;
        for (Literature literature : literatureList) {
            if (literature.getTitle().equals(title)) {
                foundLiterature = literature;
            }
        }
        return foundLiterature;
    }

    public void addLiteratureToList() {
        Literature VG = new Newspaper("VG", "Emil Nilsen", "21.03.18", 40, "kr");
        Literature VG2 = new Newspaper("VG", "Emil Nilsen", "12.04.18", 40, "kr");
        Literature Aftenposten = new Newspaper("Aftenposten", "Rune Berg", "18.03.17", 60, "kr");
        Literature NRK = new Newspaper("NRK", "Trygve Woldseth", "04.04.98", 100, "kr");

        Literature Vi_menn = new Magazine("Vi menn", "Egmont publishing", "25.03.19", 200, "kr");
        Literature KK = new Magazine("KK", "Kvinner", "25.03.19", 2000, "kr");
        Literature Donald_duck = new Magazine("Donald duck", "Disney", "25.03.19", 100, "kr");
        Literature Hjemmet = new Magazine("Hjemmet", "Hjemmfolket", "25.03.19", 300, "kr");

        Literature Døden_Søt = new Book("Døden søt", "egnes", "25.03.19", 400, "$");
        Literature Blomsteng = new Book("Blomstereng", "egnes", "25.03.19", 400, "$");

        addLiterature(VG);
        addLiterature(VG2);
        addLiterature(Aftenposten);
        addLiterature(NRK);

        addLiterature(Vi_menn);
        addLiterature(KK);
        addLiterature(Donald_duck);
        addLiterature(Hjemmet);

        addLiterature(Døden_Søt);
        addLiterature(Blomsteng);
    }


}

