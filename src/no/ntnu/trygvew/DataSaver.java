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
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class DataSaver {
    private File saveFile;

    public static void saveString(String saveStr, String filepath) throws IOException{
        File saveFile = new File(filepath);
        FileWriter filewriter = new FileWriter(saveFile);
        BufferedWriter writer = new BufferedWriter(filewriter);
        writer.write(saveStr);
        writer.close();
    }

    public static String loadString(String filePath) throws IOException{
        File saveFile = new File(filePath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(saveFile));
        Stream<String> lines = bufferedReader.lines();
        String fileContent = lines.collect(Collectors.joining());
        bufferedReader.close();

        return fileContent;
    }
}
