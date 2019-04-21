package no.ntnu.trygvew.litratureTypes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * a objet representing a book series litrature type
 */
public class BookSeries extends StandaloneLiterature {

    private ArrayList<Integer> seriesBooksIdList;
    private ArrayList<Book> booksInSeries;

    /**
     *
     * @param title the title of the book
     * @param publisher the publisher of the book
     * @param numberInStock the number of units in stock
     * @param price the price of the book
     * @param author the author of the book
     * @param seriesBooksIdList a list whit the uid of all the books in the series
     */
    public BookSeries(int saveID, String title, String publisher, int numberInStock, float price, String author, ArrayList<Integer> seriesBooksIdList) {
        super(saveID, "bookSeries", title, publisher, numberInStock, price, 1, author, " ");
        this.seriesBooksIdList = seriesBooksIdList;
    }

    /**
     * gets the author of the series
     * @return the series author
     */
    public String getSeriesAuthor(){
        return this.getAuthor();
    }

    /**
     * Returns the name of the series
     * @return the series name
     */
    @Override
    public String getSeries() {
        return this.getTitle();
    }

    public ArrayList<Book> getBooksInSeries() {
        return booksInSeries;
    }

    /**
     * returns a list of publication dates
     * @return
     */
    public ArrayList<String> getPublicationDates(){
        // TODO: Sjekke om man påvirke BooksInSeries herfra trur ikke det
        return booksInSeries.stream().map(StandaloneLiterature::getPublicationDate).collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Links the book objetcs in the inventory to the book series
     * @param literatureList the list to look for series menbers in
     */
    public void linkSeriesBooks(ArrayList<Literature> literatureList){
        literatureList.forEach(b -> {
            if (seriesBooksIdList.contains(b.getSaveID())){
                booksInSeries.add((Book)b);
                ((Book) b).setSeries(this.getTitle());
            }
        });
    }

}
