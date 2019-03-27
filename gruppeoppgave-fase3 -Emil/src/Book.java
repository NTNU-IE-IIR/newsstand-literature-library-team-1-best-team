/**
 *Book class creates a Book object with a title, publisher
 * published date and price, where the price can be changed.
 *
 * @author Emil Elton Nilsen
 * @version 1.0 (27.03.2019)
 */

public class Book extends Literature{

    /**
     * Creates a Book object which has different values which is need to give
     * information about a Book.
     *
     * @param title gives the title of our Book
     * @param publisher gives the publisher of our Book
     * @param publishedDate gives the published date of our Book
     * @param price gives the chosen price for our Book
     * @param currency gives the chosen currency of our price
     * @param typeOfLiterature gives the
     */
    public Book(String title, String publisher, String publishedDate, double price, String currency, String typeOfLiterature) {
        super(title, publisher, publishedDate, price, currency, typeOfLiterature);

    }

}
