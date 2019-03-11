package no.ntnu.trygvew;


import no.ntnu.trygvew.litratureTypes.StandaloneLiterature;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * A filter to filter Books in an ArrayList
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class BookFilter {

    /**
     * filter a given arraylist with the provider filter
     * @param filter the filter to filter the functions
     * @param arrayList the arraylist to be filtered
     * @return an filtered arraylist
     */
    private static ArrayList<StandaloneLiterature> filter(Function<StandaloneLiterature, Boolean> filter, ArrayList<StandaloneLiterature> arrayList){

        return arrayList.stream()
                .parallel() // the overhed probably results in a net loss
                .filter(s -> filter.apply(s))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    /**
     * Returns the inputt iterator filtered by book title
     * @param filter The string to chek if is in the book titles
     * @param bookList The Arraylist object to filter
     * @return An iterator filtered by filter
     */
    public static ArrayList<StandaloneLiterature> filterBookByTitle(String filter, ArrayList<StandaloneLiterature> bookList) {


        Function<StandaloneLiterature, Boolean> f = b -> b.getTitle().contains(filter);
        return BookFilter.filter(f, bookList);
    }


    /**
     * Returns the inputt iterator filtered by book authon
     * @param filter The string to chek if is in the book author
     * @param bookList The Arraylist object to filter
     * @return An iterator filtered by filter
     */
    public static ArrayList<StandaloneLiterature> filterBookByAuthor(String filter, ArrayList<StandaloneLiterature> bookList) {

        Function<StandaloneLiterature, Boolean> f = b -> b.getAuthor().contains(filter);
        return BookFilter.filter(f, bookList);
    }

    /**
     * Returns the inputt iterator filtered by book publisher
     * @param filter The string to chek if is in the book publisher
     * @param bookList The Arraylist object to filter
     * @return An iterator filtered by filter
     */
    public static ArrayList<StandaloneLiterature> filterBookByPublisher(String filter, ArrayList<StandaloneLiterature> bookList) {

        Function<StandaloneLiterature, Boolean> f = b -> b.getPublisher().contains(filter);
        return BookFilter.filter(f, bookList);
    }
}
