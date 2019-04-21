/**
 *Newspaper class creates a newspaper object with a title, publisher
 * published date and price, where the price can be changed.
 *
 * @author Emil Elton Nilsen
 * @version 1.0 (30.01.2019)
 */
public class Newspaper {

    private String title;
    private String publisher;
    private String publishedDate;
    private double price;
    private String currency;

    /**
     * Creates a newspaper object which has different values which is need to give
     * information about a newspaper.
     *
     * @param title gives the title of our newspaper
     * @param publisher gives the publisher of our newspaper
     * @param publishedDate gives the published date of our newspaper
     * @param price gives the chosen price for our newspaper
     */
    public Newspaper (String title, String publisher, String publishedDate, double price, String currency) {
        this.title = title;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.price = price;
        this.currency = currency;
    }

    /**
     * Prints out the title of our newspaper
     *
     * @return title of our chosen newspaper
     */
    public String getTitle() {
        return title;
    }

    /**
     * prints the publisher of our chosen newspaper
     *
     * @return publisher of our chosen newspaper
     */

    public String getPublisher() {
        return publisher;
    }

    /**
     * prints the published date of our chosen newspaper
     *
     * @return publishedDate of our chosen newspaper
     */
    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * returns the price of our chosen newspaper
     *
     * @return price of our chosen newspaper
     */
    public double getPrice() {
        return price;
    }

    /**
     * sets a desired price of a newspaper
     *
     * @param price the desired price of our newspaper
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * sets the desired currency of our price
     *
     * @return currency of the desired price
     */
    public String getCurrency() {
        return currency;
    }

}