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


/**
 * interface to save and lode litrature objets to json
 */
public class LiteratureSaver {

    /**
     * Saves all litrature in stock to a json file at fp
     * @param stock th lit to save
     * @param fp the filpath to the savvefile
     * @throws IOException
     */
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

    /**
     * Loads all litrature objects saved at fp to a returned arraylist
     * @param fp the path to the load file
     * @return an arraylist with the litrature objects
     * @throws IOException
     */
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

    /**
     * Decodes the jasonstrings int to litrature objects
     * @param jsonObjMap the hashmap to decode
     * @return a litrature obj
     */
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
                Paper p = new Paper(0, title, publisher, stock, price, yearlyDist, genere);
                return p;

            } else if (serializedType.equals("Magazine")) {
                Magazine m = new Magazine(0,title, publisher, stock, price, yearlyDist, genere);
                return m;
            }
        } else if(type.equals("Standalone")) {
            int edition = loadObj.getInt("Edition");
            String author = loadObj.getString("Author");
            String publicationDate = loadObj.getString("PublicationDate");

            Book newBook = new Book(0,title, publisher, stock, price, edition, author, publicationDate);
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
