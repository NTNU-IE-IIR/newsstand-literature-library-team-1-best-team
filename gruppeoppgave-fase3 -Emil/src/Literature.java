/**
 * Abstract Literature super class which inherits:
 *  Newspaper class
 *  Magazine class
 *  Book class
 *
 *Literature class a Literature object with a title, publisher
 *  published date and price, where the price can be changed
 *
 * @author Emil Elton Nilsen
 * @version 1.0(27.03.19)
 */

public abstract class Literature {

    private String title;
    private String publisher;
    private String publishedDate;
    private double price;
    private String currency;
    private String typeOfLiterature;

    public Literature(String title, String publisher, String publishedDate, double price, String currency, String typeOfLiterature) {
        this.title = title;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.price = price;
        this.currency = currency;
        this.typeOfLiterature = typeOfLiterature;
    }

    /**
     * Prints out the title of our literature
     *
     * @return title of our chosen literature
     */
    public String getTitle() {
        return title;
    }

    /**
     * prints the publisher of our chosen literature
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
     * sets a desired price of a literature
     *
     * @param price the desired price of our literature
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

    /**
     * returns the type of literature that is created
     *
     * @return the type of literature created
     */
    public String getTypeOfLiterature() {
        return this.typeOfLiterature;
    }

    public String getDescriptionOfLiteratureAsString() {
        return getTitle() + ", " + getPublisher() + ", " + getPublishedDate() + ", " +
                getPrice() + getCurrency() + "\n";
    }
}
