package no.ntnu.trygvew;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * A stock register to keep trak of different Books in the register
 * the class provides method for adding, removing and sertching trogh the elements element from the registra.
 *
 * @author Trygve Woldseth
 * @version 1.0
 */


public class BookStokRegister {
    private ArrayList<Book> booksInStok;
    private DataSaver saver;

    BookStokRegister(String savepath) {
        this.saver = new DataSaver(savepath);

        //this.booksInStok = new ArrayList<Book>();
        this.booksInStok = this.loadStock();
    }

    /**
     * loads the saved Arraylist
     * @return the loaded arraylist
     */
    private ArrayList<Book> loadStock(){
        return this.saver.loadJson();
    }

    /**
     * saves the active arraylist
     */
    private void saveStock(ArrayList<Book> arrToSave){
        this.saver.saveToJson(arrToSave);
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

}

