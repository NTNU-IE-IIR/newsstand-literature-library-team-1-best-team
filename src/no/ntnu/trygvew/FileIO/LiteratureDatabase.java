package no.ntnu.trygvew.FileIO;

import no.ntnu.trygvew.litratureTypes.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * hvis æ blir og legg inn den her som æ tenke e lurt siden det blir veldig mye mere ryddig så kan æ bruk
 * linken her til og finnut det æ treng og kunn https://www.tutorialspoint.com/h2_database/h2_database_jdbc_connection.htm
 */
public class LiteratureDatabase {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./Data/TestDB";

    static final String USER = "sa";
    static final String PASS = "";

    //TODO: make it that one V
    static void makeDb(){

    }



    public static void main(String[] args)  {

        try {
            System.out.println("DB DEBUG");


            Connection dbCon = LiteratureDatabase.makeDbConnection();


            //*
            //String sql = "SELECT * FROM Literature where id = 1;";
            //dbCon.createStatement().execute("DROP TABLE TEST");

            String sql = "SHOW TABLES ";
            ResultSet rs = dbCon.createStatement().executeQuery(sql);
            int columnsNumber = rs.getMetaData().getColumnCount();
            //System.out.println(columnsNumber);

            while (rs.next()) {
                //System.out.println();
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rs.getMetaData().getColumnName(i));
                }
                System.out.println("");
            }
            //*/



            //System.exit(1);
            Scanner sc = new Scanner(System.in);
            System.out.println("MAKING TEST ITEMS IN DB DO YO WHISH TO PROCEED Y/N");
            String ans = sc.nextLine();
            if (ans.equals("y")){
                String addItemsSql = "INSERT INTO Literature (literatureType, title, publisher, price, stock, yearlyDistributions, genre)" +
                        " VALUES (%s, %s, %s, %f, %d, %d, %s)";

                String addBooksql = "INSERT INTO Literature (literatureType, title, publisher, price, stock, author, edition, publicationDate, series)" +
                        " VALUES (%s, %s, %s, %f, %d, %s, %d, %s, %s)";


                Random r = new Random();

                Function<Integer, String> randomString = integer -> {
                    String al = "abcdefghijklmnopqrstuwxyz";
                    String returnS = "";
                    for(int i = 0; i <= integer; i++){
                        returnS += al.charAt(r.nextInt(25));
                    }
                    return returnS;
                };

                Function<Integer, String> getSqlBookStr = integer -> {
                    String returnS =
                    return returnS;
                };

                s

                System.out.println(randomString.apply(5));

                //char c = (char)(r.nextInt(26) + 'a');
            }
            System.exit(1);
            dbCon.createStatement().execute(addItemsSql);

            dbCon.createStatement().execute(addItemsSql);
        } catch (Exception e) {e.printStackTrace();}

    }

    static Connection makeDbConnection() throws Exception{
        Class.forName(JDBC_DRIVER);

        Connection c = DriverManager.getConnection(DB_URL,USER,PASS);
        return c;
    }

    public static void SaveLiterature(Literature saveL){
        try {
            // makes connection
            Connection conn = LiteratureDatabase.makeDbConnection();
            Boolean litInDb = false;
            int litId = saveL.getSaveID();

            // tests if the value exsists
            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery("SELECT literatureType FROM Literature where id = " + saveL);
            litInDb = rs.next();

            if (litInDb){
                // the item alredy exsists update price and stock sins those are the only one changable
                String litType = rs.getString("litratureType");
                String sql = "UPDATE Literature SET" +
                        " price= " + saveL.getPrice() +
                        ",stock= " + saveL.getNumberInStock() +
                        " WHERE id = " + saveL.getSaveID();
                st.execute(sql);
            } else {
                // the value is new and have to be added
                String cols = "literatureType, title, publisher, price, stock, ";
                String vals = String.format("%s, %s, %s, %f, %d, ", saveL.getLiteratureType(), saveL.getTitle(), saveL.getPublisher(), saveL.getPrice(), saveL.getNumberInStock());

                if (saveL instanceof Book){
                    Book saveObj = (Book) saveL;
                    cols += " author, edition, publicationDate, series";
                    vals += String.format("%s, %d, %s, %s", saveObj.getAuthor(), saveObj.getEdition(), saveObj.getPublicationDate(),saveObj.getSeries());

                } else if (saveL instanceof BookSeries){
                    BookSeries saveObj = (BookSeries) saveL;
                    cols += " author, seriesBooksId";
                    String booksId = String.join(",", saveObj.getBooksInSeries().stream().map(Book::getSaveID).toArray(String[]::new));
                    vals += String.format("%s, %d, %s, %s", saveObj.getSeriesAuthor(), booksId);

                } else if (saveL instanceof Magazine){
                    Magazine saveObj = (Magazine) saveL;
                    cols += " yearlyDistributions, genre";
                    vals += String.format("%d, %s", saveObj.getYearlyDistributions(), saveObj.getGenre());

                } else if (saveL instanceof Paper){
                    Paper saveObj = (Paper) saveL;
                    cols += " yearlyDistributions, genre";
                    vals += String.format("%d, %s", saveObj.getYearlyDistributions(), saveObj.getGenre());
                }
                String sql = String.format("INSERT INTO Literature (%s) VALUES (%s)", cols, vals);
                st.execute(sql);
            }

        } catch (Exception e) {e.printStackTrace();}
    }

    public static ArrayList<Literature> loadAllLitrature(){
        ArrayList<Literature> litratureList = new ArrayList<>();
        ArrayList<BookSeries> bookSeriesList = new ArrayList<>();
        try {
            // makes connection
            Connection conn = LiteratureDatabase.makeDbConnection();

            // tests if the value exsists
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Literature");

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String type = rs.getString("litratureType");
                String title = rs.getString("title");
                String publisher = rs.getString("publisher");
                Float price = rs.getFloat("price");
                Integer stock = rs.getInt("stock");

                if (type.equals("book")){
                    String author = rs.getString("author");
                    Integer edition = rs.getInt("edition");
                    String pubD = rs.getString("publicationDate");
                    String series = rs.getString("series");
                    Literature obj = new Book(id, title, publisher, stock, price, edition, author, pubD);
                    litratureList.add(obj);

                } else if (type.equals("bookSeries")){
                    String author = rs.getString("author");
                    String seriesBooksId = rs.getString("seriesBooksId");
                    ArrayList<Integer> idList = Arrays.asList(seriesBooksId.split(",")).stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
                    Literature obj = new BookSeries(id, title, publisher, stock, price, author, idList);
                    litratureList.add(obj);
                    bookSeriesList.add((BookSeries) obj);

                } else if (type.equals("magazine")){
                    Integer yearlyD = rs.getInt("yearlyDistributions");
                    String genre = rs.getString("genre");
                    Literature obj = new Magazine(id, title, publisher, stock, price, yearlyD, genre);
                    litratureList.add(obj);

                } else if (type.equals("paper")) {
                    Integer yearlyD = rs.getInt("yearlyDistributions");
                    String genre = rs.getString("genre");

                    Literature obj = new Magazine(id, title, publisher, stock, price, yearlyD, genre);
                    litratureList.add(obj);
                }
            }

            // adds the correct books to their series
            bookSeriesList.forEach(bookSeries -> {
                bookSeries.linkSeriesBooks(litratureList);
            });



        } catch (Exception e) {e.printStackTrace();}

        return litratureList;
    }


    public static void removeLiterature(Literature deleatL){
        try {
            // makes connection
            Connection conn = LiteratureDatabase.makeDbConnection();

            // tests if the value exsists
            Statement st = conn.createStatement();
            st.execute("DELETE FROM Literature where id = " + deleatL.getSaveID());
        } catch (Exception e) {e.printStackTrace();}
    }



    /**
     * cheks if the tables are made if makes them
     * @param dbCon the connection to the h2 db
     * @throws Exception
     */
    public static void makeTables(){

        String inventoryTbl = "CREATE TABLE IF NOT EXISTS Literature(" +
                " id INTEGER NOT NULL auto_increment PRIMARY KEY," +
                " literatureType VARCHAR(255)," +
                " title VARCHAR(255)," +
                " publisher VARCHAR(255)," +
                " price FLOAT," +
                " stock INTEGER," +
                // standalone lit cols
                " author VARCHAR(255)," +
                " edition INTEGER," +
                " publicationDate VARCHAR(255)," +
                " series VARCHAR(255)," +
                // serialized lit cols
                " yearlyDistributions INTEGER," +
                " genre VARCHAR(255)," +
                // book series
                " seriesBooksId TEXT)";

        try {
            Connection connection = LiteratureDatabase.makeDbConnection();
            connection.createStatement().execute(inventoryTbl);
        } catch (Exception e) {
            e.printStackTrace();
        }





    }


}