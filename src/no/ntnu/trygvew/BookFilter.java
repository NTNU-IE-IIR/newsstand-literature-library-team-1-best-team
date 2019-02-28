package no.ntnu.trygvew;


import java.util.ArrayList;
import java.util.Iterator;


/**
 * A filter to filter Books in an ArrayList
 */
public class BookFilter {

    /**
     * Returns the inputt iterator filtered by book title
     * @param filter The string to chek if is in the book titles
     * @param itrToFilter The iterator object to filter
     * @return An iterator filtered by filter
     */
    public static Iterator<Book> filterBookByTitle(String filter, Iterator<Book> itrToFilter) {

        ArrayList<Book> filteredlist = new ArrayList<>();
        itrToFilter.forEachRemaining(b -> {if (b.getFullTitle().contains(filter)) {filteredlist.add(b);}});

        return filteredlist.iterator();
    }


    /**
     * Returns the inputt iterator filtered by book authon
     * @param filter The string to chek if is in the book author
     * @param itrToFilter The iterator object to filter
     * @return An iterator filtered by filter
     */
    public static Iterator<Book> filterBookByAuthor(String filter, Iterator<Book> itrToFilter) {

        ArrayList<Book> filteredlist = new ArrayList<>();
        itrToFilter.forEachRemaining(b -> {if (b.getAutor().contains(filter)) {filteredlist.add(b);}});

        return filteredlist.iterator();
    }

    /**
     * Returns the inputt iterator filtered by book publisher
     * @param filter The string to chek if is in the book publisher
     * @param itrToFilter The iterator object to filter
     * @return An iterator filtered by filter
     */
    public static Iterator<Book> filterBookByPublisher(String filter, Iterator<Book> itrToFilter) {

        ArrayList<Book> filteredlist = new ArrayList<>();
        itrToFilter.forEachRemaining(b -> {if (b.getPublisher().contains(filter)) {filteredlist.add(b);}});

        return filteredlist.iterator();
    }
}
