package no.ntnu.trygvew.litratureTypes;


/**
 * A object representing a book litrature type
 */
public class Book extends StandaloneLiterature {
    /**
     * Constructs book object with the parameters given
     *
     * @param title           the title of the book
     * @param publisher       the book publisher
     * @param numberInStock   number of units in stok
     * @param price           the price of the book
     * @param edition         teh edition of the book
     * @param autor           the autor of the book
     * @param publicationDate the date of this books publishing
     */
    public Book(int saveID, String title, String publisher, int numberInStock, float price, int edition, String autor, String publicationDate) {
        super(saveID , "book", title, publisher, numberInStock, price, edition, autor, publicationDate);
    }


}
