package no.ntnu.trygvew;

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
import java.util.Iterator;
import java.util.stream.Stream;

/**
 * A stock register to keep trak of different Books in the register
 * the class provides method for adding, removing and sertching trogh the elements element from the registra.
 *
 * @author Trygve Woldseth
 * @version 1.0
 */


public class BookStokRegister {
    private ArrayList<Book> booksInStok;
    private HashMap<String, Order> orderList;
    private String saveFilePath;

    BookStokRegister(String savepath) {
        this.saveFilePath = savepath;

        //this.booksInStok = new ArrayList<Book>();
        this.booksInStok = this.loadStock();
    }

    /**
     * loads the saved Arraylist
     * @return the loaded arraylist
     */
    private ArrayList<Book> loadStock(){

        ArrayList<Book> returnArray = new ArrayList<>();
        try {
            String JsonObjStr = DataSaver.loadString(this.saveFilePath);


            JSONArray jsonArr = new JSONArray(JsonObjStr);

            for (int n = 0; n < jsonArr.length(); n++) {


                JSONObject JsonObject = jsonArr.getJSONObject(n);

                String title = JsonObject.getString("Title");
                int edition = JsonObject.getInt("Edition");
                String author = JsonObject.getString("Author");
                String publicationDate = JsonObject.getString("PublicationDate");
                String publisher = JsonObject.getString("Publisher");
                float price = JsonObject.getFloat("Price");
                int stock = JsonObject.getInt("Stock");

                Book newBook = new Book(title, publisher, edition,  author, publicationDate, stock, price);
                returnArray.add(newBook);
            }
        }
        catch (JSONException e) {System.out.print("Json Problem" + e);}
        // suppress exception because the file wil be crated on save
        catch (IOException e) {/*System.out.println("error IO -" + e);*/}



        return returnArray;


    }


    /**
     * saves an
     * @param arrToSave
     */
    private void saveStock(ArrayList<Book> arrToSave){

        JSONArray jsonSaveArr = new JSONArray();

        for (Book b : arrToSave){
            JSONObject saveObj = new JSONObject();

            saveObj.put("Title", b.getTitle());
            saveObj.put("Edition", b.getEdition());
            saveObj.put("Author", b.getAutor());
            saveObj.put("PublicationDate", b.getPublicationDate());
            saveObj.put("Publisher", b.getPublisher());
            saveObj.put("Price", b.getPrice());
            saveObj.put("Stock", b.getNumberInStok());

            jsonSaveArr.put(saveObj);
        }


        String saveStr = jsonSaveArr.toString(3);


        try {
            File f = new File(this.saveFilePath);
            if (!f.exists()){
                f.createNewFile();
            }
            DataSaver.saveString(saveStr, this.saveFilePath);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * adds a new book to the book stok register
     * @param newBook the book to add to the register
     */
    public void addBook(Book newBook){
        this.booksInStok.add(newBook);

        this.saveStock(this.booksInStok);
    }

    /**
     * removes books whom contain the sting key
     * @param fullTitleKey the title to the book to remove
     */
    public void removeBooksByTitle(String fullTitleKey){
        ArrayList<Integer> indexToRemove = new ArrayList<>();
        booksInStok.forEach(b -> {

            if (b.getFullTitle().contains(fullTitleKey)){
                indexToRemove.add(booksInStok.indexOf(b));
            }
        });
        // reverse the list to avoid wrong indexing
        Collections.reverse(indexToRemove);

        indexToRemove.forEach(i -> this.booksInStok.remove(booksInStok.get(i)));


        this.saveStock(this.booksInStok);
    }

    /**
     * Removes all instanses of the given objet in the register
     * @param bookToRemove the objet to remove
     */
    public void removeBook(Book bookToRemove){
        boolean containBook = this.booksInStok.contains(bookToRemove);
        if (containBook){
            ArrayList<Integer> indexToRemove= new ArrayList<>();
            this.booksInStok.forEach(b -> {
                if (b.equals(bookToRemove)){
                    indexToRemove.add(booksInStok.indexOf(b));
                }
            });
            Collections.reverse(indexToRemove);
            indexToRemove.forEach(i -> booksInStok.remove(i));
        }

        this.saveStock(this.booksInStok);

    }

    /**
     * returns an iterator for all books
     * @return a iterator for all books
     */
    public Iterator<Book> getBookIterator(){
        return this.booksInStok.iterator();
    }

    public void makePurchase(Book b, User u){
        // TODO: 04.03.19
    }

}

