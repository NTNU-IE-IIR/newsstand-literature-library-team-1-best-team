package no.ntnu.trygvew.GUI;

import no.ntnu.trygvew.litratureTypes.Literature;

import javax.swing.*;
import java.util.ArrayList;

class TableMaker {
    static final String[] serializedHead = {"Title", "publisher", "yearly dist", "genre", "Price", "Stock"};
    static final String[] standaloneHead = {"Title", "Publisher", "Edition", "Author", "PublicationDate", "Price", "Stock"};
    static final String[] litratureHead = {"Title", "Publisher", "Price", "Stock", "type"};

    public static JTable makeAllLitratureTable(ArrayList<Literature> literatureList){
        int numCols = litratureHead.length;
        int numRows = literatureList.size();

        ArrayList<String[]> dataRows = new ArrayList<>();

        literatureList.forEach(lit -> {
            String title = lit.getTitle();
            String publisher = lit.getPublisher();
            float price = lit.getPrice();
            int stock = lit.getNumberInStock();
            String[] row = {title, publisher,  Float.toString(price), Integer.toString(stock)};
            dataRows.add(row);
        });
        //System.out.println(dataRows.get(0).getClass());
        //System.out.println(dataRows.toArray(new String[numRows][numCols])[numRows][numCols]);

        return new JTable(new LiteratureTableModel(litratureHead , dataRows.toArray(new String[numRows][numCols])));

    }
}
