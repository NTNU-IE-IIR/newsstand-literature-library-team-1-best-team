


/**
 * This is the superclass for the classes Book, Newspaper and magazine.
 */
public abstract class Literature
{
    private String title;
    private String publisher;
    private String publishedDate;
    private double price;
    private String currency;

    /**
     *
     * @param title
     * @param publisher
     * @param publishedDate
     * @param price
     * @param currency
     */
    public Literature(String title, String publisher, String publishedDate, double price, String currency) {
        this.title = title;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.price = price;
        this.currency = currency;
    }

    /**
        * Prints out the title of the known literature`s
     *
             * @return title of our chosen literature
     */
    public String getTitle() {
        return title;
    }

    /**
     * prints the publisher of our chosen literature`s
     *
     * @return publisher of our chosen literature
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * prints the published date of our chosen literature
     *
     * @return publishedDate of our chosen literature
     */

    public String getPublishedDate() {
        return publishedDate;
    }

    /**
     * returns the price of our chosen literature
     *
     * @return price of our chosen literature
     */

    public double getPrice() {
        return price;
    }

    /**
     * sets a desired price of a Literature
     *
     * @param price the desired price of our Literature
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
