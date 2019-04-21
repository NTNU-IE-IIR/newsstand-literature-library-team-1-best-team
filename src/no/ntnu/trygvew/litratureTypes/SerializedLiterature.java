package no.ntnu.trygvew.litratureTypes;

import java.util.ArrayList;

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
    public SerializedLiterature(int saveID, String type, String title, String publisher, int numberInStock, float price, int yearlyDistributions, String genre) {
        super(saveID ,title, publisher, type, numberInStock, price);
        this.yearlyDistributions = yearlyDistributions;
        this.genre = genre;
    }

    /**
     * Returns the number of yearly distrebutions
     * @return the number of yarly distrebutions
     */
    public int getYearlyDistributions() {
        return yearlyDistributions;
    }

    /**
     * gets the series genre
     * @return the genre of the series
     */
    public String getGenre() {
        return genre;
    }


}
