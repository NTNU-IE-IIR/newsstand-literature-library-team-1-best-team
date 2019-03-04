package no.ntnu.trygvew;

/**
 * Represens a book whom contains information about a book category for a register
 * The Object contains information like title snd publisher and contains sales informatin
 * like number in stock and unit price
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class Book {
    private String title;
    private String publisher;
    private String autor;
    private int edition;
    private  String publicationDate;

    private int numberInStok;
    private float price;
    private boolean isInSeries;


    /**
     * Constructs book object with the parameters given
     *
     *
     * @param title - the title of the book
     * @param publisher - the book publisher
     * @param numberInStok - number of units in stok
     * @param price - the price of the book
     * @param autor - the autor of the book
     * @param edition - teh edition of the book
     */
    Book(String title, String publisher, int edition,  String autor, String publicationDate, int numberInStok, float price){
        this.title = title;
        this.publisher = publisher;

        this.numberInStok = numberInStok;
        this.price = price;

        this.autor = autor;
        this.edition = edition;
        this.publicationDate = publicationDate;
        this.isInSeries = false;

    }

    /**
     * Returns the title from book
     * @return book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the publisher from book
     * @return book publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Returns the autor from book
     * @return book autor
     */
    public String getAutor() {
        return autor;
    }

    /**
     * Returns the book edition fromm book
     * @return book edition
     */
    public int getEdition() {
        return edition;
    }


    /**
     * Returns the number of books in stock
     * @return number of books in stock
     */
    public int getNumberInStok() {
        return numberInStok;
    }

    /**
     * Returns the book price
     * @return book price
     */
    public float getPrice() {
        return price;
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
        return this.title + " " + this.edition + ".ed";
    }

    /**
     * Set if the book is in a series
     * @param newState true if the book is in a series false if not
     */
    public void setIsInSeries(boolean newState){
        this.isInSeries = newState;
    }

    /**
     * Retruns true if the book is in a series
     * @return Retruns true if the book is in a series
     */
    public boolean isInSeries(){
        return isInSeries;
    }

    /**
     * A string that contains all the class parameters, used to validate objects
     * @return sting containing all the class parameters
     */
    @Override
    public String toString(){
        return this.getClass().getName() + this.title + this.publisher + this.numberInStok + this.price + this.autor + this.edition + this.publicationDate + this.isInSeries;
    }




}
