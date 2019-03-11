package no.ntnu.trygvew;


import no.ntnu.trygvew.litratureTypes.Literature;
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
    private static ArrayList<Literature> filter(Function<Literature, Boolean> filter, ArrayList<Literature> arrayList){

        return arrayList.stream()
                .parallel() // the overhed probably results in a net loss
                .filter(s -> filter.apply(s))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    /**
     * Returns the inputt iterator filtered by Literature title
     * @param filter The string to chek if is in the Literature titles
     * @param literatureList The Arraylist object to filter
     * @return An iterator filtered by filter
     */
    public static ArrayList<Literature> filterLiteratureByTitle(String filter, ArrayList<Literature> literatureList) {


        Function<Literature, Boolean> f = l -> l.getTitle().contains(filter);
        return BookFilter.filter(f, literatureList);
    }




    /**
     * Returns the inputt iterator filtered by Literature publisher
     * @param filter The string to chek if is in the Literature publisher
     * @param literatureList The Arraylist object to filter
     * @return An iterator filtered by filter
     */
    public static ArrayList<Literature> filterLiteratureByPublisher(String filter, ArrayList<Literature> literatureList) {

        Function<Literature, Boolean> f = l -> l.getPublisher().contains(filter);
        return BookFilter.filter(f, literatureList);
    }

    /**
     * Returns the inputt iterator filtered by book authon
     * @param filter The string to chek if is in the book author
     * @param literatureList The Arraylist object to filter
     * @return An iterator filtered by filter
     */
    /*
    public static ArrayList<Literature> filterBookByAuthor(String filter, ArrayList<Literature> literatureList) {

        Function<Literature, Boolean> f = b -> b.getAuthor().contains(filter);
        return BookFilter.filter(f, literatureList);
    }
    */
}
