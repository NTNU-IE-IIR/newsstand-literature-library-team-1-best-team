package no.ntnu.trygvew.FileIO;

import no.ntnu.trygvew.litratureTypes.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.geom.NoninvertibleTransformException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class LiteratureSaver {

    public static void saveLiteratureStock(ArrayList<Literature> stock, String fp) throws  IOException{
        //ArrayList<SerializedLiterature> serialized = new ArrayList<>();
        //ArrayList<StandaloneLiterature> standalone = new ArrayList<>();

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

        ArrayList<Literature> returnArray = new ArrayList<>();
        ArrayList<Literature> bookSeries = new ArrayList<>();
        try {
            String JsonObjStr = DataSaver.loadString(fp);
            JSONArray jsonArr = new JSONArray(JsonObjStr);
            jsonArr.toList().stream().parallel().forEach(JS ->{
                JSONObject loadObj = new JSONObject((String) JS);


                String type = loadObj.getString("Type");
                String title = loadObj.getString("Title");
                String publisher = loadObj.getString("Publisher");
                float price = loadObj.getFloat("Price");
                int stock = loadObj.getInt("Stock");

                switch (type){
                    case "Serialized":

                        int yearlyDist = loadObj.getInt("YearlyDistributions");
                        String genere = loadObj.getString("Genre");
                        String serializedType = loadObj.getString("SerializedType");
                        SerializedLiterature newLit = null;

                        if (serializedType.equals("Paper")){
                            newLit = new Paper(title, publisher, stock, price, yearlyDist, genere);

                        } else if (serializedType.equals("Magazine")){
                            newLit = new Magazine(title, publisher, stock, price, yearlyDist, genere);
                        }

                        Literature serialized = new SerializedLiterature(
                                title, publisher, stock, price, yearlyDist, genere);
                        returnArray.add(serialized);
                        break;

                    case "Standalone":

                        int edition = loadObj.getInt("Edition");
                        String author = loadObj.getString("Author");
                        String publicationDate = loadObj.getString("PublicationDate");

                        Literature standalone = new StandaloneLiterature(
                                title, publisher, stock, price, edition,  author, publicationDate);
                        returnArray.add(standalone);
                        break;
                    case "BookSeries":
                        String seriesAuthor = loadObj.getString("SeriesAuthor");
                        String[] seriesStrList = loadObj.getString("BooksInSeriesFullTiles").split("%@%");

                        BookSeries newSeries = new BookSeries(title, publisher, stock, price, seriesAuthor)
                        bookSeries.add(L)
                }
            });
        }
        catch (JSONException e) {System.out.println("Json Problem  "); e.printStackTrace();}


        return returnArray;
    }


}
