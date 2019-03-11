package no.ntnu.trygvew.messingAround;

/**
 * Represents a order placed in the system
 *
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class Order {

    private String orderID;
    private String costumerUUID;
    private String fullBookTitle;
    private float salePrice;
    private String timeStamp; // uts

    public Order(String costumerUUID, String fullBookTitle, String timeStamp, float salePrice) {
        this.costumerUUID = costumerUUID;
        this.fullBookTitle = fullBookTitle;
        this.salePrice = salePrice;
        this.timeStamp = timeStamp;
    }

    public String getOrderUUID() {
        return orderID;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getCostumerUUID() {
        return costumerUUID;
    }

    public String getFullBookTitle() {
        return fullBookTitle;
    }

    public float getSalePrice() {
        return salePrice;
    }
}
