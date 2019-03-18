package no.ntnu.trygvew.litratureTypes;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class BookSeries extends Literature {

    private String seriesAuthor;
    private ArrayList<Book> booksInSeries;

    /**
     * @param title          the title of the Literature
     * @param publisher      the book publisher
     * @param numberInStock  number of units in stok
     * @param price          the price of the book
     * @param booksInSeries  the litrature in this series
     */
    public BookSeries(String title, String publisher, int numberInStock, float price,String seriesAuthor, ArrayList<Book> booksInSeries) {
        super(title, publisher, "BookSeries", numberInStock, price);
        this.booksInSeries = booksInSeries;
        this.seriesAuthor = seriesAuthor;
    }

    /**
     * @param title          the title of the Literature
     * @param publisher      the book publisher
     * @param numberInStock  number of units in stok
     * @param price          the price of the book
     */
    public BookSeries(String title, String publisher, int numberInStock, float price,String seriesAuthor) {
        super(title, publisher, "BookSeries", numberInStock, price);
        this.booksInSeries = new ArrayList<Book>();
        this.seriesAuthor = seriesAuthor;
    }

    public String getSeriesAuthor(){
        return seriesAuthor;
    }

    public ArrayList<Book> getBooksInSeries() {
        return booksInSeries;
    }

    public ArrayList<String> getPublicationDates(){
        // TODO: Sjekke om man påvirke BooksInSeries herfra trur ikke det
        return booksInSeries.stream().map(StandaloneLiterature::getPublicationDate).collect(Collectors.toCollection(ArrayList::new));
    }

    public void linkSeriesBooks(ArrayList<Book> bookList, ArrayList<String> seriesTitleList){
        bookList.forEach(b -> {
            if (seriesTitleList.contains(b.getFullTitle())){
                booksInSeries.add(b);
            }
        });
    }

}
