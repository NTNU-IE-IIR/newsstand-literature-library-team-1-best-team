package no.ntnu.trygvew.UI;

import no.ntnu.trygvew.litratureTypes.*;

import java.util.ArrayList;

public class DisplayLitrature {

    private static final String[] serializedHead = {"Title", "publisher", "yearly dist", "genre", "Price", "Stock"};
    private static final String[] standaloneHead = {"Title", "Publisher", "Edition", "Author", "PublicationDate", "Price", "Stock"};
    private static final String[] litratureHead = {"Title", "Publisher", "Price", "Stock", "type"};

    public static void displayLiterature(ArrayList<Literature> literatureList){
        System.out.println("\n\nAll Items");
        System.out.println("#########################################################################");
        System.out.println("Displaying :" + literatureList.size() + " items\n");
        for (String hed: litratureHead){
            System.out.print(String.format("|  %-17s", hed));
        }

        literatureList.forEach(itm -> {
            System.out.println();


            System.out.print(String.format("|  %-17s", itm.getTitle()));
            System.out.print(String.format("|  %-17s", itm.getPublisher()));
            System.out.print(String.format("|  %-17s", itm.getPrice()));
            System.out.print(String.format("|  %-17s", itm.getNumberInStock()));
            String itmType = "none";
            if (itm instanceof Book){itmType = "Book";}
            if (itm instanceof Paper){itmType = "Paper";}
            if (itm instanceof Magazine){itmType = "Magazine";}

            System.out.print(String.format("|  %-17s", itmType));
        });
    }

    public static void displayPaper(ArrayList<Literature> literatureList){
        System.out.println("\n\nPapers");
        System.out.println("#########################################################################");
        System.out.println("Displaying :" + literatureList.stream().filter(a -> a instanceof Paper).count() + " items\n");
        for (String hed: serializedHead){
            System.out.print(String.format("|  %-17s", hed));
        }


        literatureList.stream().filter(a -> a instanceof Paper).forEach(itm -> {
            Paper p = (Paper) itm;
            System.out.println();

            System.out.print(String.format("|  %-17s", p.getTitle()));
            System.out.print(String.format("|  %-17s", p.getPublisher()));
            System.out.print(String.format("|  %-17s", p.getYearlyDistributions()));
            System.out.print(String.format("|  %-17s", p.getGenre()));
            System.out.print(String.format("|  %-17s", p.getPrice()));
            System.out.print(String.format("|  %-17s", p.getNumberInStock()));
        });
    }

    public static void displayMagazine(ArrayList<Literature> literatureList){
        System.out.println("\n\nMagazine");
        System.out.println("#########################################################################");
        System.out.println("Displaying :" + literatureList.stream().filter(a -> a instanceof Magazine).count() + " items\n");
        for (String hed: serializedHead){
            System.out.print(String.format("|  %-17s", hed));
        }


        literatureList.stream().filter(a -> a instanceof Magazine).forEach(itm -> {
            Magazine b = (Magazine) itm;
            System.out.println();

            System.out.print(String.format("|  %-17s", b.getTitle()));
            System.out.print(String.format("|  %-17s", b.getPublisher()));
            System.out.print(String.format("|  %-17s", b.getYearlyDistributions()));
            System.out.print(String.format("|  %-17s", b.getGenre()));
            System.out.print(String.format("|  %-17s", b.getPrice()));
            System.out.print(String.format("|  %-17s", b.getNumberInStock()));
        });
    }

    public static void displayBook(ArrayList<Literature> literatureList){
        System.out.println("\n\nBooks");
        System.out.println("#########################################################################");
        System.out.println("Displaying :" + literatureList.stream().filter(a -> a instanceof Book).count() + " items\n");
        for (String hed: standaloneHead){
            System.out.print(String.format("|  %-17s", hed));
        }

        literatureList.stream().filter(a -> a instanceof Book).forEach(itm -> {
            Book b = (Book) itm;
            System.out.println();

            System.out.print(String.format("|  %-17s", b.getTitle()));
            System.out.print(String.format("|  %-17s", b.getPublisher()));
            System.out.print(String.format("|  %-17s", b.getEdition()));
            System.out.print(String.format("|  %-17s", b.getAuthor()));
            System.out.print(String.format("|  %-17s", b.getPublicationDate()));
            System.out.print(String.format("|  %-17s", b.getPrice()));
            System.out.print(String.format("|  %-17s", b.getNumberInStock()));
        });
    }

    public static void displayBookSeries(ArrayList<Literature> literatureList){
        System.out.println("\n\nBook Series");
        System.out.println("#########################################################################");
        System.out.println("Displaying :" + literatureList.stream().filter(a -> a instanceof BookSeries).count() + " items\n");
        for (String hed: standaloneHead){
            System.out.print(String.format("|  %-17s", hed));
        }

        literatureList.stream().filter(a -> a instanceof BookSeries).forEach(itm -> {
            Book series = (Book) itm;
            System.out.println();

            System.out.print(String.format("|  %-17s", itm.getTitle()));
            System.out.print(String.format("|  %-17s", itm.getPublisher()));
            //System.out.print(String.format("|  %-17s", itm.getEdition()));
            //System.out.print(String.format("|  %-17s", itm.getAuthor()));

        });
    }


}
