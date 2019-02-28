package no.ntnu.trygvew;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * A data saver to save and load Arraylists to Json
 */
public class DataSaver {
    private File saveFile;

    DataSaver(String filePath){
        this.saveFile = new File(filePath);
    }

    /**
     * Saves a arraylist of books to a json file, cheks if file exsists if not creates one
     * @param saveList the arraylist to save
     */
    public void saveToJson(ArrayList<Book> saveList){


        JSONArray jsonSaveArr = new JSONArray();

        for (Book b : saveList){
            JSONObject saveObj = new JSONObject();

            saveObj.put("Title", b.getTitle());
            saveObj.put("Edition", b.getEdition());
            saveObj.put("Author", b.getAutor());
            saveObj.put("PublicationDate", b.getPublicationDate());
            saveObj.put("Publisher", b.getPublisher());
            saveObj.put("Price", b.getPrice());
            saveObj.put("Stock", b.getNumberInStok());

            jsonSaveArr.put(saveObj);
        }


        String saveStr = jsonSaveArr.toString(3);


        try {
            if (!this.saveFile.exists()){
                this.saveFile.createNewFile();
            }
            FileWriter filewriter = new FileWriter(this.saveFile);
            BufferedWriter writer = new BufferedWriter(filewriter);
            writer.write(saveStr);
            writer.close();
        } catch (IOException e) {System.out.println("error IO -" + e);}
    }

    /**
     * Lads the saved jason array and returns the Array list of books
     * @return an arraylist of books
     */
    public ArrayList<Book> loadJson(){

        Stream<String> lines;
        String JsonObjStr;

        ArrayList<Book> returnArray = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.saveFile));
            lines = bufferedReader.lines();
            JsonObjStr = lines.collect(Collectors.joining());
            bufferedReader.close();

            JSONArray jsonArr = new JSONArray(JsonObjStr);

            for (int n = 0; n < jsonArr.length(); n++) {


                JSONObject JsonObject = jsonArr.getJSONObject(n);

                String title = JsonObject.getString("Title");
                int edition = JsonObject.getInt("Edition");
                String author = JsonObject.getString("Author");
                String publicationDate = JsonObject.getString("PublicationDate");
                String publisher = JsonObject.getString("Publisher");
                float price = JsonObject.getFloat("Price");
                int stok = JsonObject.getInt("Stock");

                Book newBook = new Book(title, publisher, edition,  author, publicationDate, stok, price);
                returnArray.add(newBook);
            }
        }
        catch (FileNotFoundException e) {/*System.out.println("error fnf -" + e);*/}
        catch (IOException e) {/*System.out.println("error IO -" + e);*/}
        catch (JSONException e) {
            //System.out.print("Json Problem" + e);
        }
        return returnArray;
    }
}
