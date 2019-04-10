package no.ntnu.trygvew.litratureTypes;

/**
 * Represens a StandaloneLiterature whom contains information about a book category for a register
 * The Object contains information like title snd publisher and contains sales informatin
 * like number in stock and unit price
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class StandaloneLiterature extends Literature {
    private String author;
    private int edition;
    private  String publicationDate;

    private String series = " ";



    /**
     * Constructs book object with the parameters given
     *
     *
     * @param title             the title of the book
     * @param publisher         the book publisher
     * @param numberInStock     number of units in stok
     * @param price             the price of the book
     * @param author             the author of the book
     * @param edition           teh edition of the book
     * @param publicationDate   the date of this books publishing
     */
    public StandaloneLiterature(int saveID, String type, String title, String publisher, int numberInStock, float price, int edition, String author, String publicationDate){
        super(saveID, title, publisher, type, numberInStock, price);



        this.author = author;
        this.edition = edition;
        this.publicationDate = publicationDate;

    }




    /**
     * Returns the author from book
     * @return book author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the book edition fromm book
     * @return book edition
     */
    public int getEdition() {
        return edition;
    }



    /**
     * returns the publication date of the book
     * @return the publication date for the book
     */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Returns the book title and edition
     * @return book title and edition
     */
    public String getFullTitle() {
        return this.getTitle() + " " + this.edition + ".ed";
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }






}
