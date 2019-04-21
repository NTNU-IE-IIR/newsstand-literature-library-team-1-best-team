package no.ntnu.trygvew;

import no.ntnu.trygvew.FileIO.LiteratureDatabase;
import no.ntnu.trygvew.FileIO.LiteratureSaver;
import no.ntnu.trygvew.litratureTypes.Book;
import no.ntnu.trygvew.litratureTypes.StandaloneLiterature;
import no.ntnu.trygvew.litratureTypes.Literature;
import no.ntnu.trygvew.messingAround.Transaction;
import no.ntnu.trygvew.messingAround.User;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * A stock register to keep trak of different Books in the register
 * the class provides method for adding, removing and sertching trogh the elements element from the registra.
 *
 * @author Trygve Woldseth
 * @version 1.0
 */


public class LiteratureStockRegister {
    private ArrayList<Literature> literatureInStock;
    private HashMap<String, Transaction> orderList;
    private String saveFilePath;

    public LiteratureStockRegister(String savePath) {
        this.saveFilePath = savePath;

        // init the database to chek it the tables exsist
        LiteratureDatabase.makeTables();
        // TODO: cleenup the save too file system
        this.literatureInStock = this.loadStock();
    }

    /**
     * loads the saved Arraylist
     * @return the loaded arraylist an empty arraylist if non are found
     */
    private ArrayList<Literature> loadStock(){

        ArrayList<Literature> returnArray = null;
        try {
            returnArray = LiteratureDatabase.loadAllLitrature();
        } catch (Exception e) {
            returnArray = new ArrayList<Literature>();
            System.out.println("load error");
            e.printStackTrace();
        }


        return returnArray;
    }


    /**
     * adds a new Literature to the Literature stock register
     * @param newLiterature the Literature to add to the register
     */
    public void addLiterature(Literature newLiterature){
        this.literatureInStock.add(newLiterature);
        LiteratureDatabase.SaveLiterature(newLiterature);
    }


    /**
     * Removes all instanses of the given objet in the register
     * @param literatureToRemove the objet to remove
     */
    public void removeLiterature(Literature literatureToRemove){
        if (literatureInStock.contains(literatureToRemove)){
            literatureInStock.remove(literatureToRemove);
            LiteratureDatabase.removeLiterature(literatureToRemove);
        }
    }

    /**
     * returns an iterator for all books
     * @return a iterator for all books
     */
    public ArrayList<Literature> getStock(){
        return (ArrayList<Literature>) this.literatureInStock.clone();
    }

    public void makePurchase(StandaloneLiterature b, User u){
        // TODO: 04.03.19
    }

    public void setBookSeries(Book selBook, String series){
        literatureInStock.stream().filter(l -> l instanceof Book).filter(b -> b.getSaveID() == selBook.getSaveID()).forEach(b -> {
            ((Book) b).setSeries(series);
            LiteratureDatabase.SaveLiterature(b);
        });
    }

}

