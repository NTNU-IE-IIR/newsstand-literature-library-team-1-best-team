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
 * saves and loads the litreature to a database
 */
public class LiteratureDatabase {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./Data/TestDB";



    static final String USER = "sa";
    static final String PASS = "";

    //TODO: make it that one V
    static void makeDb(){

    }


    /*
    public static void main(String[] args)  {

        try {
            System.out.println("DB DEBUG");


            Connection dbCon = LiteratureDatabase.makeDbConnection();


            //*
            //String sql = "SELECT * FROM Literature where id = 1;";
            //dbCon.createStatement().execute("DROP TABLE TEST");

            String sql1 = "SHOW TABLES ";
            ResultSet rs1 = dbCon.createStatement().executeQuery(sql1);
            int columnsNumber = rs1.getMetaData().getColumnCount();
            //System.out.println(columnsNumber);

            while (rs1.next()) {
                //System.out.println();
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs1.getString(i);
                    System.out.print(columnValue + " " + rs1.getMetaData().getColumnName(i));
                }
                System.out.println("");
            }




            System.exit(1);
            Scanner sc = new Scanner(System.in);
            System.out.println("MAKING TEST ITEMS IN DB DO YO WHISH TO PROCEED Y/N");
            String ans = sc.nextLine();
            if (ans.equals("y")){
                String addSerialSql = "INSERT INTO Literature (literatureType, title, publisher, price, stock, yearlyDistributions, genre)" +
                        " VALUES ('%s', '%s', '%s', %f, %d, %d, '%s')";

                String addBooksql = "INSERT INTO Literature (literatureType, title, publisher, price, stock, author, edition, publicationDate, series)" +
                        " VALUES ('%s', '%s', '%s', %f, %d, '%s', %d, '%s', '%s')";


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
                    String returnS = String.format(addBooksql, "book",randomString.apply(5),randomString.apply(5), r.nextFloat(), r.nextInt(), randomString.apply(5), r.nextInt(), randomString.apply(5) ,randomString.apply(5));
                    return returnS;
                };

                Function<Integer, String> getSqlSerialStr = integer -> {
                    String returnS;
                    if (r.nextFloat() > 0.5){
                        returnS = String.format(addSerialSql, "paper",randomString.apply(5),randomString.apply(5), r.nextFloat(), r.nextInt(), r.nextInt(), randomString.apply(5));
                    } else {
                        returnS = String.format(addSerialSql, "magazine",randomString.apply(5),randomString.apply(5), r.nextFloat(), r.nextInt(), r.nextInt(), randomString.apply(5));
                    }


                    return returnS;
                };



                //System.out.println(getSqlSerialStr.apply(5));

                for (int p=0; p <= 100; p++){
                    String callSqlStr = "";
                    if (r.nextFloat() > 0.5){
                        System.out.println("a");
                        callSqlStr = getSqlBookStr.apply(5);
                    } else {
                        System.out.println("b");
                        callSqlStr = getSqlSerialStr.apply(5);
                    }


                    dbCon.createStatement().execute(callSqlStr);
                }
            }
            String sql = "SELECT * FROM Literature";
            ResultSet rs = dbCon.createStatement().executeQuery(sql);
            int columnsNumber1 = rs.getMetaData().getColumnCount();
            //System.out.println(columnsNumber);

            while (rs.next()) {
                //System.out.println();
                for (int i = 1; i <= columnsNumber1; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rs.getMetaData().getColumnName(i));
                }
                System.out.println("");
            }
            //

            //y
            // dbCon.createStatement().execute(addItemsSql);
        } catch (Exception e) {e.printStackTrace();}

    }
    */


    /**
     * Makes a connection to the database and returns the connection
     * @return a connection object
     * @throws Exception
     */
    static Connection makeDbConnection() throws Exception{
        Class.forName(JDBC_DRIVER);

        Connection c = DriverManager.getConnection(DB_URL,USER,PASS);
        return c;
    }

    /**
     * Saves a litrature objet to the db
     * @param saveL the litrature to save to the db
     */
    public static void SaveLiterature(Literature saveL){
        try {
            // makes connection
            Connection conn = LiteratureDatabase.makeDbConnection();
            Boolean litInDb = false;
            int litId = saveL.getSaveID();

            // tests if the value exsists
            Statement st = conn.createStatement();


            ResultSet rs = st.executeQuery("SELECT literatureType FROM Literature where id = " + saveL.getSaveID());
            litInDb = rs.next();

            if (litInDb){
                // the item alredy exsists update price and stock sins those are the only one changable
                String litType = rs.getString("literatureType");
                String sql = "UPDATE Literature SET" +
                        " price= " + saveL.getPrice() +
                        ",stock= " + saveL.getNumberInStock() +
                        " WHERE id = " + saveL.getSaveID();
                st.execute(sql);
            } else {
                // the value is new and have to be added
                String cols = "literatureType, title, publisher, price, stock, ";
                String vals = String.format("'%s', '%s', '%s', %f, %d, ", saveL.getLiteratureType(), saveL.getTitle(), saveL.getPublisher(), saveL.getPrice(), saveL.getNumberInStock());

                if (saveL instanceof Book){
                    Book saveObj = (Book) saveL;
                    cols += " author, edition, publicationDate, series";
                    vals += String.format("'%s', %d, '%s', '%s'", saveObj.getAuthor(), saveObj.getEdition(), saveObj.getPublicationDate(),saveObj.getSeries());

                } else if (saveL instanceof BookSeries){
                    BookSeries saveObj = (BookSeries) saveL;
                    cols += " author, seriesBooksId";
                    String booksId = String.join(",", saveObj.getBooksInSeries().stream().map(Book::getSaveID).toArray(String[]::new));
                    vals += String.format("'%s', %d, '%s', '%s'", saveObj.getSeriesAuthor(), booksId);

                } else if (saveL instanceof Magazine){
                    Magazine saveObj = (Magazine) saveL;
                    cols += " yearlyDistributions, genre";
                    vals += String.format("%d, '%s'", saveObj.getYearlyDistributions(), saveObj.getGenre());

                } else if (saveL instanceof Paper){
                    Paper saveObj = (Paper) saveL;
                    cols += " yearlyDistributions, genre";
                    vals += String.format("%d, '%s'", saveObj.getYearlyDistributions(), saveObj.getGenre());
                }
                String sql = String.format("INSERT INTO Literature (%s) VALUES (%s)", cols, vals);
                st.execute(sql);
            }

        } catch (Exception e) {e.printStackTrace();}
    }

    /**
     * Returns all litrature in the database
     * @return an arraylist with all litrature in the databases
     */
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
                String type = rs.getString("literatureType");
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
                    ((Book) obj).setSeries(series);
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

                    Literature obj = new Paper(id, title, publisher, stock, price, yearlyD, genre);
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

    /**
     * removes a litrature obj from the database
     * @param deleatL the objet to remove from the database
     */
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