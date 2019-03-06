package no.ntnu.trygvew.messingAround;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a user of the application. contains information
 * about username, name, funds
 *
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class User {

    private String UUID;
    private String username;
    private String firstName;
    private String lastName;
    private float userFunds;

    private ArrayList<Order> orders;

    private String encryptionKey;

    User(String UUID, String username, String firstName, String lastName, float userFunds, ArrayList<Order> orders, String encryptionKey) {
        this.UUID = UUID;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userFunds = userFunds;
        this.orders = orders;
        this.encryptionKey = encryptionKey;
    }

    public String getUUID() {
        return UUID;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public float getUserFunds() {
        return userFunds;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public String getEncryptionKey() {
        return encryptionKey;
    }
}
