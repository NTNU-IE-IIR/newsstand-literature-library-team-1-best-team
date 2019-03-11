package no.ntnu.trygvew.messingAround;

/**
 * Represents a order placed in the system
 *
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class Transaction {

    private String transactionID;
    private String costumerID;
    private String fullBookTitle;
    private float salePrice;
    private String timeStamp; // uts

    public Transaction(String transactionID, String costumerID, String fullBookTitle, String timeStamp, float salePrice) {
        this.transactionID = transactionID;
        this.costumerID = costumerID;
        this.fullBookTitle = fullBookTitle;
        this.salePrice = salePrice;
        this.timeStamp = timeStamp;
    }


    public String getTransactionID() {
        return transactionID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getCostumerID() {
        return costumerID;
    }

    public String getFullBookTitle() {
        return fullBookTitle;
    }

    public float getSalePrice() {
        return salePrice;
    }
}
