import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<Literature> literatureByPublisherList;
    private ArrayList<Literature> litereatureSeriesList;

    private ObservableList<Literature> literatures;
    /**
     * Create a new ArrayList that stores all of our added newspapers in a list
     */
    public Register () {
        this.literatureList = new ArrayList<Literature>();
        this.literatureByTypeList = new ArrayList<Literature>();
        this.literatureByPublisherList = new ArrayList<Literature>();
        this.litereatureSeriesList = new ArrayList<Literature>();
        addLiteratureToList();
    }

    /**
     * Add a created literature for the literature class to our
     * list of literature.
     *
     * @param literature A newspaper object created from the Newspaper class
     */
    public boolean addLiterature(Literature literature) {
        boolean result = this.literatureList.add(literature);
        return result;
    }

    /**
     * Lets you add a literature to the register, when you
     * dont have one created. A literature object will be created
     * and added to the literatureList arrayList
     *
     * @param title of the literature
     * @param publisher of the literature
     * @param publishedDate of the literature
     * @param price of the literature
     * @param typeOfLiterature of the literature
     */
    public void addLiteratureToRegister(String title, String publisher, String publishedDate, String price, String typeOfLiterature) {
        addLiterature(new Literature(title, publisher, publishedDate, price, typeOfLiterature) {
        });
    }

    public List<Literature> getLiteratureList() {
        return this.literatureList;
    }

    public ObservableList<Literature> getLiteraturesListAsObservarbleList() {
        literatures = FXCollections.observableArrayList(getLiteratureList());
        return literatures;
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

    public Literature findLiteratureByPublisher(String publisher) {
        Literature literaturePublisher = null;
        for (Literature literature : literatureList) {
            if (literature.getPublisher().equals(publisher)) {
                literaturePublisher = literature;
            }
        }
        return literaturePublisher;
    }

    /**
    *Checks if there is a literature in our list with a specific literature type,
     * then adds it to a list
    *
    * @param typeOfLiterature the type of literature you want to find
    */
    public boolean findLiterautreByType(String typeOfLiterature) {
        boolean found = false;
        for (Literature literature : literatureList) {
            if (literature.getTypeOfLiterature().equals(typeOfLiterature)) {
                literatureByTypeList.add(literature);
                found = true;
            }
            else {
                found = false;
            }
        }
        return found;
    }

    /**
     *
     * @param typeOfLiterature
     * @return literatureType
     */
    public String getLiteratureByTypeAsString(String typeOfLiterature) {
        literatureByTypeList.removeAll(literatureByTypeList);
        String literatureType = "";
        int i = 1;
        findLiterautreByType(typeOfLiterature);
        for (Literature literature : literatureByTypeList) {
            if (literatureByTypeList.isEmpty()) {
                return null;
            }
            else {
                literatureType += i++ + ". " + literature.getDescriptionOfLiteratureAsString();
            }
        }
        return literatureType;
    }

    public boolean removeLiteratureFromLiteratureByTypeList() {
           return literatureByTypeList.removeAll(literatureByTypeList);
    }

    public boolean findLiterautreByPublisher(String publisher) {
        boolean found = false;
        for (Literature literature : literatureList) {
            if (literature.getPublisher().equals(publisher)) {
                literatureByPublisherList.add(literature);
                found = true;
            } else {
                found = false;
            }
        }
        return found;
    }


    /**
     *
     * @param publisher
     * @return literatureType
     */
    public String getLiteratureByPublisherAsString(String publisher) {
        literatureByPublisherList.removeAll(literatureByPublisherList);
        String literaturePublisher = "";
        findLiterautreByPublisher(publisher);
        for (Literature literature : literatureByPublisherList) {
            if (literatureByPublisherList.isEmpty()) {
                return null;
            } else {
                literaturePublisher += literature.getDescriptionOfLiteratureAsString();
            }
        }
        return literaturePublisher;
    }

    public boolean removeLiteratureFromLiteratureByPublisherList() {
        return literatureByPublisherList.removeAll(literatureByTypeList);
    }

    public void addLiteratureToSeries(Literature literature) {
        litereatureSeriesList.add(literature);
    }

    public void removeLiterature(Literature literature) {
        literatureList.remove(literature);
    }


    public void addLiteratureToList() {

        //Static Literature object with a dynamic Newspaper object being created
        Literature VG = new Newspaper("VG", "Emil Nilsen", "21.03.18", "40kr", "newspaper");
        Literature VG2 = new Newspaper("VG", "Emil Nilsen", "12.04.18", "40kr", "newspaper");
        Literature Aftenposten = new Newspaper("Aftenposten", "Rune Berg", "18.03.17", "60kr", "newspaper");
        Literature NRK = new Newspaper("NRK", "Trygve Woldseth", "04.04.98", "100kr", "newspaper");

        //Static Literature object with a dynamic Magazine object being created
        Literature Vi_menn = new Magazine("Vi menn", "Egmont", "27.04.17", "90kr", "magazine");
        Literature Donald_duck = new Magazine("Donald duck", "Egmont", "27.04.28", "50kr", "magazine");
        Literature KK = new Magazine("KK", "Egmont", "07.08.17", "2000kr", "magazine");
        Literature Se_og_Hør = new Magazine("Se og hør", "Drama AS", "27.04.17", "200kr", "magazine");

        //Static Literature object with a dynamic Book object being created
        Literature Krim_og_mord = new Book("Krim", "krimmens gangs", "03.03.13", "300kr", "book");
        Literature Tull_og_tøys = new Book("Tull og tøys", "vitsemakerne", "04.04.14", "200 kr", "book");

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

