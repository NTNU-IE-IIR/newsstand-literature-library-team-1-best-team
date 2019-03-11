package no.ntnu.trygvew;

import no.ntnu.trygvew.FileIO.DataSaver;
import no.ntnu.trygvew.FileIO.LiteratureSaver;
import no.ntnu.trygvew.litratureTypes.StandaloneLiterature;
import no.ntnu.trygvew.litratureTypes.Literature;
import no.ntnu.trygvew.messingAround.Order;
import no.ntnu.trygvew.messingAround.User;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
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


public class BookStokRegister {
    private ArrayList<Literature> literatureInStock;
    private HashMap<String, Order> orderList;
    private String saveFilePath;

    BookStokRegister(String savePath) {
        this.saveFilePath = savePath;

        this.literatureInStock = this.loadStock();
    }

    /**
     * loads the saved Arraylist
     * @return the loaded arraylist an empty arraylist if non are found
     */
    private ArrayList<Literature> loadStock(){

        ArrayList<Literature> returnArray = null;
        try {
            returnArray = LiteratureSaver.loadLiteratureStock(this.saveFilePath);
        } catch (Exception e) {
            returnArray = new ArrayList<Literature>();
        }

        return returnArray;
    }


    /**
     * saves an
     * @param arrToSave
     */
    private void saveStock(ArrayList<Literature> arrToSave){
        try {
            File f = new File(this.saveFilePath);
            if (!f.exists()){
                f.createNewFile();
            }
            LiteratureSaver.saveLiteratureStock(this.literatureInStock, this.saveFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * adds a new Literature to the book stok register
     * @param newLiterature the Literature to add to the register
     */
    public void addLiterature(Literature newLiterature){
        this.literatureInStock.add(newLiterature);

        this.saveStock(this.literatureInStock);
    }

    /**
     * removes books whom contain the sting key
     * @param fullTitleKey the title to the book to remove
     */
    public void removeLiteratureByTitle(String fullTitleKey){
        ArrayList<Integer> indexToRemove = new ArrayList<>();
        literatureInStock.forEach(b -> {

            if (b.getTitle().contains(fullTitleKey)){
                indexToRemove.add(literatureInStock.indexOf(b));
            }
        });
        // reverse the list to avoid wrong indexing
        Collections.reverse(indexToRemove);

        indexToRemove.forEach(i -> this.literatureInStock.remove(literatureInStock.get(i)));


        this.saveStock(this.literatureInStock);
    }

    /**
     * Removes all instanses of the given objet in the register
     * @param litratureToRemove the objet to remove
     */
    public void removeLiterature(Literature litratureToRemove){
        boolean containBook = this.literatureInStock.contains(litratureToRemove);
        if (containBook){
            ArrayList<Integer> indexToRemove= new ArrayList<>();
            this.literatureInStock.forEach(l -> {
                if (l.equals(litratureToRemove)){
                    indexToRemove.add(literatureInStock.indexOf(l));
                }
            });
            Collections.reverse(indexToRemove);
            indexToRemove.forEach(i -> literatureInStock.remove(i));
        }

        this.saveStock(this.literatureInStock);

    }

    /**
     * returns an iterator for all books
     * @return a iterator for all books
     */
    public ArrayList<StandaloneLiterature> getStock(){
        return (ArrayList<StandaloneLiterature>) this.literatureInStock.clone();
    }

    public void makePurchase(StandaloneLiterature b, User u){
        // TODO: 04.03.19
    }

}

