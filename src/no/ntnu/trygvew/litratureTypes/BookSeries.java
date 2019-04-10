package no.ntnu.trygvew.litratureTypes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class BookSeries extends StandaloneLiterature {

    private ArrayList<Integer> seriesBooksIdList;
    private ArrayList<Book> booksInSeries;

    public BookSeries(int saveID, String title, String publisher, int numberInStock, float price, String author, ArrayList<Integer> seriesBooksIdList) {
        super(saveID, "bookSeries", title, publisher, numberInStock, price, 1, author, " ");
        this.seriesBooksIdList = seriesBooksIdList;
    }

    public String getSeriesAuthor(){
        return this.getAuthor();
    }

    @Override
    public String getSeries() {
        return this.getTitle();
    }

    @Override
    public void setSeries(String series) {
        this.getTitle();
    }

    public ArrayList<Book> getBooksInSeries() {
        return booksInSeries;
    }

    public ArrayList<String> getPublicationDates(){
        // TODO: Sjekke om man påvirke BooksInSeries herfra trur ikke det
        return booksInSeries.stream().map(StandaloneLiterature::getPublicationDate).collect(Collectors.toCollection(ArrayList::new));
    }

    public void linkSeriesBooks(ArrayList<Literature> literatureList){
        literatureList.forEach(b -> {
            if (seriesBooksIdList.contains(b.getSaveID())){
                booksInSeries.add((Book)b);
                ((Book) b).setSeries(this.getTitle());
            }
        });
    }

}
