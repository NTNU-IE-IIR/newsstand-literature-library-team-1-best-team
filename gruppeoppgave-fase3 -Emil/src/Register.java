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
    private ArrayList<Literature> literatureByTypeList;

    /**
     * Create a new ArrayList that stores all of our added newspapers in a list
     */
    public Register () {
        this.literatureList = new ArrayList<Literature>();
        this.literatureByTypeList = new ArrayList<Literature>();
        addLiteratureToList();
    }

    /**
     * Add a created newspaper for the Newspaper class to our
     * list of newspapers.
     *
     * @param literature A newspaper object created from the Newspaper class
     */
    public boolean addLiterature(Literature literature) {
        boolean result = this.literatureList.add(literature);
        return result;
    }

    public String getLiteratureListAsString() {
        String literatureListed = "";
        for (Literature literature : literatureList) {
            literatureListed += literature.getDescriptionOfLiteratureAsString();
        }
        return literatureListed;
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

    /**
    *Checks if there is a literature in our list with a specific literature type,
     * then adds it to a list
    *
    * @param typeOfLiterature the type of literature you want to find
    */
    public void findLiterautreByType(String typeOfLiterature) {
        for (Literature literature : literatureList) {
            if (literature.getTypeOfLiterature().equals(typeOfLiterature)) {
                literatureByTypeList.add(literature);
            }
        }
    }

    /**
     *
     * @param typeOfLiterature
     * @return literatureType
     */
    public String getLiteratureByTypeAsString(String typeOfLiterature) {
        literatureByTypeList.removeAll(literatureByTypeList);
        String literatureType = "";
        findLiterautreByType(typeOfLiterature);
        for (Literature literature : literatureByTypeList) {
            if (literatureByTypeList.isEmpty()) {
                return null;
            }
            else {
                literatureType += literature.getDescriptionOfLiteratureAsString();
            }
        }
        return literatureType;
    }

    public void removeLiteratureFromLiteratureByTypeList() {
        literatureByTypeList.removeAll(literatureByTypeList);
    }

    public void addLiteratureToList() {

        //Static Literature object with a dynamic Newspaper object being created
        Literature VG = new Newspaper("VG", "Emil Nilsen", "21.03.18", 40, "kr", "Newspaper");
        Literature VG2 = new Newspaper("VG", "Emil Nilsen", "12.04.18", 40, "kr", "Newspaper");
        Literature Aftenposten = new Newspaper("Aftenposten", "Rune Berg", "18.03.17", 60, "kr", "Newspaper");
        Literature NRK = new Newspaper("NRK", "Trygve Woldseth", "04.04.98", 100, "kr", "Newspaper");

        //Static Literature object with a dynamic Magazine object being created
        Literature Vi_menn = new Magazine("Vi menn", "Egmont", "27.04.17", 90, "kr", "Magazine");
        Literature Donald_duck = new Magazine("Donald duck", "Disney", "27.04.28", 50, "kr", "Magazine");
        Literature KK = new Magazine("KK", "Kvinnene", "07.08.17", 2000, "kr", "Magazine");
        Literature Se_og_Hør = new Magazine("Se og hør", "Drama AS", "27.04.17", 200, "kr", "Magazine");

        //Static Literature object with a dynamic Book object being created
        Literature Krim_og_mord = new Book("Krim og mord", "krimmens gangs", "03.03.13", 300, "kr", "Book");
        Literature Tull_og_tøys = new Book("Tull og tøys", "vitsemakerne", "04.04.14", 200, "kr", "Book");

        //Static Literature object with a dynamic Newspaper object being added to our literature list
        addLiterature(VG);
        addLiterature(VG2);
        addLiterature(Aftenposten);
        addLiterature(NRK);

        //Static Literature object with a dynamic Magazine object being added to our literature list
        addLiterature(Vi_menn);
        addLiterature(Donald_duck);
        addLiterature(KK);
        addLiterature(Se_og_Hør);

        //Static Literature object with a dynamic Book object being added to our literature list
        addLiterature(Krim_og_mord);
        addLiterature(Tull_og_tøys);
    }

}

