package no.ntnu.trygvew.litratureTypes;

public class Magazine extends SerializedLiterature {

    /**
     * @param title               the title of the Literature
     * @param publisher           the book publisher
     * @param numberInStock       number of units in stok
     * @param price               the price of the book
     * @param yearlyDistributions the number of yearly distrebutions
     * @param genre               the genere of the reading serilized litrature
     */
    public Magazine(int saveID, String title, String publisher, int numberInStock, float price, int yearlyDistributions, String genre) {
        super(saveID, "magazine", title, publisher, numberInStock, price, yearlyDistributions, genre);
    }
}
