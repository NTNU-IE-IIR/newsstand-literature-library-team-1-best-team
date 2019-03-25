public class Literature {

    private String title;
    private String publisher;
    private String publishedDate;
    private double price;
    private String currency;

    public Literature(String title, String publisher, String publishedDate, double price, String currency) {
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
    protected String getTitle() {
        return title;
    }

    /**
     * prints the publisher of our chosen newspaper
     *
     * @return publisher of our chosen newspaper
     */

    protected String getPublisher() {
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
