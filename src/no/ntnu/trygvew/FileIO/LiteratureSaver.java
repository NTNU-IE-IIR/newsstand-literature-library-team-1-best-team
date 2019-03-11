package no.ntnu.trygvew.FileIO;

import no.ntnu.trygvew.litratureTypes.Literature;
import no.ntnu.trygvew.litratureTypes.SerializedLiterature;
import no.ntnu.trygvew.litratureTypes.StandaloneLiterature;
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


            switch (literature.getLiteratureType()){
                case "Serialized":

                    SerializedLiterature serialized = (SerializedLiterature) literature;

                    saveObj.put("YearlyDistributions", serialized.getYearlyDistributions());
                    saveObj.put("SerializedType", serialized.getSerializedType());
                    saveObj.put("Genre", serialized.getGenre());

                    jsonSaveArr.put(saveObj);
                    break;

                case "Standalone":
                    StandaloneLiterature standalone = (StandaloneLiterature) literature;

                    saveObj.put("Edition", standalone.getEdition());
                    saveObj.put("Author", standalone.getAuthor());
                    saveObj.put("PublicationDate", standalone.getPublicationDate());

                    jsonSaveArr.put(saveObj);
                    break;
            }
        });

        String saveStr = jsonSaveArr.toString(3);

        DataSaver.saveString(saveStr, fp);


    }

    public static ArrayList<Literature> loadLiteratureStock(String fp) throws IOException{

        ArrayList<Literature> returnArray = new ArrayList<>();
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
                        String serializedType = loadObj.getString("SerializedType");
                        String genere = loadObj.getString("Genre");
                        Literature serialized = new SerializedLiterature(
                                title, publisher, type, stock, price, yearlyDist, serializedType, genere);
                        returnArray.add(serialized);
                        break;

                    case "Standalone":

                        int edition = loadObj.getInt("Edition");
                        String author = loadObj.getString("Author");
                        String publicationDate = loadObj.getString("PublicationDate");

                        Literature standalone = new StandaloneLiterature(
                                title, publisher, type, stock, price, edition,  author, publicationDate);
                        returnArray.add(standalone);
                        break;
                }
            });
        }
        catch (JSONException e) {System.out.println("Json Problem  "); e.printStackTrace();}


        return returnArray;
    }


}
