/**
 *Book class creates a book object with a title, publisher
 * published date and price, where the price can be changed.
 *
 * @author Rune S.B
 * @version 1.0 (30.01.2019)
 */
public class Book extends Literature {


    public Book (String title, String publisher, String publishedDate, double price, String currency) {
        super(title, publisher, publishedDate, price, currency);
    }
}
