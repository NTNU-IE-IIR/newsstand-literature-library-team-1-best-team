package no.ntnu.trygvew.litratureTypes;

public class SerializedLiterature extends Literature {

    private int yearlyDistributions;
    private String genre;

    /**
     * 
     * @param title                 the title of the Literature
     * @param publisher             the book publisher
     * @param numberInStock         number of units in stok
     * @param price                 the price of the book
     * @param yearlyDistributions   the number of yearly distrebutions
     * @param genre                 the genere of the reading serilized litrature
     */
    public SerializedLiterature(String title, String publisher, int numberInStock, float price, int yearlyDistributions, String genre) {
        super(title, publisher, "Serialized", numberInStock, price);
        this.yearlyDistributions = yearlyDistributions;
        this.genre = genre;
    }

    /**
     *
     * @return
     */
    public int getYearlyDistributions() {
        return yearlyDistributions;
    }

    /**
     *
     * @return
     */
    public String getGenre() {
        return genre;
    }
}
