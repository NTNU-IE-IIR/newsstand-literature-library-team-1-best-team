package no.ntnu.trygvew;

import no.ntnu.trygvew.litratureTypes.Literature;
import no.ntnu.trygvew.messingAround.Transaction;
import no.ntnu.trygvew.messingAround.User;

import java.util.ArrayList;

public class Sales {

    private ArrayList<Transaction> transactions;
    private ArrayList<Literature> cart; // if multiple simultanius users in the future you can stac this

    public void addToCart(Literature itm){
        this.cart.add(itm);
    }

    public void removeFromCart(Literature itm){
        this.cart.remove(itm);

    }

    public void completeTransaction(User usr){

    }

    public ArrayList<Transaction> getOrderHistory(){
        return null;
    }
}
