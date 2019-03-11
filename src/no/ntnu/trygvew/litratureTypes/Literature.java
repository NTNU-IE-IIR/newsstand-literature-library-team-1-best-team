package no.ntnu.trygvew.litratureTypes;

public class Literature {

    private String title;
    private String publisher;
    private String literatureType;

    private int numberInStock;
    private float price;

    /**
     *
     * @param title the title of the book
     * @param publisher the book publisher
     * @param literatureType The type of litrature
     * @param numberInStock number of units in stok
     * @param price the price of the book
     */
    public Literature(String title, String publisher, String literatureType, int numberInStock, float price) {
        this.title = title;
        this.publisher = publisher;
        this.literatureType = literatureType;
        this.numberInStock = numberInStock;
        this.price = price;
    }

    /**
     * Returns the title from book
     * @return book title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the publisher from book
     * @return book publisher
     */
    public String getPublisher() {
        return publisher;
    }

    public String getLiteratureType() {
        return literatureType;
    }

    /**
     * Returns the number of books in stock
     * @return number of books in stock
     */
    public int getNumberInStock() {
        return numberInStock;
    }

    /**
     * Returns the book price
     * @return book price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set the new number of this item in the stock
     * @param numberInStock the new numer of this item in stock
     */
    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }
}
