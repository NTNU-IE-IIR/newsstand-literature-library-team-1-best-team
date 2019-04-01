/**
 *Book class creates a Magazine object with a title, publisher
 * published date and price, where the price can be changed.
 *
 * @author Rune S.B
 * @version 1.0 (25.03.2019)
 */
public class Magazine extends Literature {


    public Magazine(String title, String publisher, String publishedDate, double price, String currency) {
        super(title, publisher, publishedDate, price, currency);
    }

}
