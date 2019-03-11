package no.ntnu.trygvew.litratureTypes;

public class SerializedLiterature extends Literature {

    private int yearlyDistributions;
    private String serializedType;
    // un sure if this shold be here but i guess like gossip sport and news ar news categories
    private String genre;


    public SerializedLiterature(String title, String publisher, String literatureType, int numberInStock, float price, int yearlyDistributions, String serializedType, String genre) {
        super(title, publisher, literatureType, numberInStock, price);
        this.yearlyDistributions = yearlyDistributions;
        this.serializedType = serializedType;
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
    public String getSerializedType() {
        return serializedType;
    }

    /**
     *
     * @return
     */
    public String getGenre() {
        return genre;
    }
}
