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

    public Order(String costumerUUID, String fullBookTitle, float salePrice) {
        this.costumerUUID = costumerUUID;
        this.fullBookTitle = fullBookTitle;
        this.salePrice = salePrice;
    }

    public String getOrderUUID() {
        return orderID;
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
