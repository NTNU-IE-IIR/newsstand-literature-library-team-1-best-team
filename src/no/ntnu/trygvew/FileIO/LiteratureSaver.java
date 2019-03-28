package no.ntnu.trygvew.FileIO;

import no.ntnu.trygvew.litratureTypes.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.geom.NoninvertibleTransformException;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class LiteratureSaver {

    public static void saveLiteratureStock(ArrayList<Literature> stock, String fp) throws  IOException{


        JSONArray jsonSaveArr = new JSONArray();


        stock.forEach(literature -> {
            JSONObject saveObj = new JSONObject();

            saveObj.put("Type", literature.getLiteratureType());
            saveObj.put("Title", literature.getTitle());
            saveObj.put("Publisher", literature.getPublisher());
            saveObj.put("Price", literature.getPrice());
            saveObj.put("Stock", literature.getNumberInStock());

            if (literature instanceof SerializedLiterature) {
                String serializedType = null;
                if (literature instanceof Paper) {serializedType = "Paper";};
                if (literature instanceof Magazine) {serializedType = "Magazine";};
                SerializedLiterature serialized = (SerializedLiterature) literature;

                saveObj.put("YearlyDistributions", serialized.getYearlyDistributions());
                saveObj.put("SerializedType", serializedType);
                saveObj.put("Genre", serialized.getGenre());

                jsonSaveArr.put(saveObj);
            } else if (literature instanceof Book) {
                StandaloneLiterature standalone = (StandaloneLiterature) literature;

                saveObj.put("Edition", standalone.getEdition());
                saveObj.put("Author", standalone.getAuthor());
                saveObj.put("PublicationDate", standalone.getPublicationDate());

                jsonSaveArr.put(saveObj);
            } else if (literature instanceof BookSeries) {
                BookSeries series = (BookSeries) literature;

                saveObj.put("SeriesAuthor", series.getSeriesAuthor());
                ArrayList<Book> seriesBooks = series.getBooksInSeries();
                String seriesSaveStr = "";
                if (seriesBooks.size()> 0) {
                    String finalSeriesSaveStr = seriesSaveStr;
                    seriesBooks.forEach(book -> finalSeriesSaveStr.concat("%@%" + book.getFullTitle()));
                    seriesSaveStr = finalSeriesSaveStr.substring(3); // cuts of the start new title mark
                }
                saveObj.put("BooksInSeriesFullTiles", seriesSaveStr);

                jsonSaveArr.put(saveObj);
            }

        });

        String saveStr = jsonSaveArr.toString(3);

        DataSaver.saveString(saveStr, fp);


    }

    public static ArrayList<Literature> loadLiteratureStock(String fp) throws IOException{

        ArrayList<Literature> returnArray = null;
        ArrayList<BookSeries> bookSeries = new ArrayList<>();

        // needed to add books to series
        ArrayList<Book> books = new ArrayList<>();

        //TODO: for og kunn implm bok seria må du ha en pass hvor du plukke dem ut først og lage serian etterpå
        try {
            String JsonObjStr = DataSaver.loadString(fp);
            JSONArray jsonArr = new JSONArray(JsonObjStr);
            returnArray = jsonArr.toList()
                    .parallelStream()
                    .map(itm -> LiteratureSaver.makeLitratureFromJsonStr((HashMap) itm))
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        catch (JSONException e) {System.out.println("Json Problem  "); e.printStackTrace();}

        return returnArray;
    }

    private static Literature makeLitratureFromJsonStr(HashMap jsonObjMap){

        JSONObject loadObj = new JSONObject(jsonObjMap);

        String type = loadObj.getString("Type");
        String title = loadObj.getString("Title");
        String publisher = loadObj.getString("Publisher");
        float price = loadObj.getFloat("Price");
        int stock = loadObj.getInt("Stock");

        if (type.equals("Serialized")) {

            int yearlyDist = loadObj.getInt("YearlyDistributions");
            String genere = loadObj.getString("Genre");
            String serializedType = loadObj.getString("SerializedType");

            if (serializedType.equals("Paper")) {
                Paper p = new Paper(title, publisher, stock, price, yearlyDist, genere);
                return p;

            } else if (serializedType.equals("Magazine")) {
                Magazine m = new Magazine(title, publisher, stock, price, yearlyDist, genere);
                return m;
            }
        } else if(type.equals("Standalone")) {
            int edition = loadObj.getInt("Edition");
            String author = loadObj.getString("Author");
            String publicationDate = loadObj.getString("PublicationDate");

            Book newBook = new Book(
                    title, publisher, stock, price, edition, author, publicationDate);
            return newBook;
        }

        /**
        case "BookSeries":
            String seriesAuthor = loadObj.getString("SeriesAuthor");
            String[] seriesStrList = loadObj.getString("BooksInSeriesFullTiles").split("%@%");

            BookSeries newSeries = new BookSeries(title, publisher, stock, price, seriesAuthor, Arrays.asList(seriesStrList));
            //bookSeries.add(newSeries);

            break;
        **/
        return null;
    }


}
