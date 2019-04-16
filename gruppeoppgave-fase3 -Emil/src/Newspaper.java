/**
 *Newspaper class creates a newspaper object with a title, publisher
 * published date and price, where the price can be changed.
 *
 * @author Emil Elton Nilsen
 * @version 1.0 (30.01.2019)
 */
public class Newspaper extends Literature {

    /**
     * Creates a newspaper object which has different values which is need to give
     * information about a newspaper.
     *
     * @param title gives the title of our newspaper
     * @param publisher gives the publisher of our newspaper
     * @param publishedDate gives the published date of our newspaper
     * @param price gives the chosen price for our newspaper
     * @param currency gives the chosen currency of our price
     * @param typeOfLiterature gives the
     */
    public Newspaper (String title, String publisher, String publishedDate, String price, String currency, String typeOfLiterature) {
        super(title, publisher, publishedDate, price, currency, typeOfLiterature);
    }

}