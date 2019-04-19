/**
 * Magazine class creates a Magazine object with a title, publisher
 *  published date and price, where the price can be changed.
 *
 * @author Emil Elton Nilsen
 * @version 1.0 (27.03.19)
 */
public class Magazine extends Literature {

    /**
     * Creates a magazine object which has different values which is need to give
     * information about a magazine.
     *
     * @param title gives the title of our magazine
     * @param publisher gives the publisher of our magazine
     * @param publishedDate gives the published date of our magazine
     * @param price gives the chosen price for our magazine
     * @param typeOfLiterature gives the
     */
    public Magazine(String title, String publisher, String publishedDate, String price, String typeOfLiterature) {
        super(title, publisher, publishedDate, price, typeOfLiterature);
    }
}
