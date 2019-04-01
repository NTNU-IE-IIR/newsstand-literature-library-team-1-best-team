import java.util.ArrayList;

/**
 * This PrintLiteratureList creates an instance
 * where when called each subclass will be separated from
 * the original LiteratureList.
 * @author Rune S.B
 * @version 1.0 01.04.2019
 */

public class PrintLiteratureList
{
    public static void listNewspaper(ArrayList<Literature> inp)
    {
        for (Literature lit: inp){
            if (lit instanceof Newspaper){
                Newspaper getnews = (Newspaper) lit;
                System.out.println(getnews.getTitle());
                System.out.println(getnews.getPublisher());
                System.out.println(getnews.getPublishedDate());
                System.out.println(getnews.getPrice());
                System.out.println(getnews.getCurrency());

            }
        }
    }
    public static void listBook(ArrayList<Literature> bpri)
    {
        for (Literature bo: bpri){
            if (bo instanceof Book){
                Book getBook = (Book) bo;
                System.out.println(getBook.getTitle());
                System.out.println(getBook.getPublisher());
                System.out.println(getBook.getPublishedDate());
                System.out.println(getBook.getPrice());
                System.out.println(getBook.getCurrency());
            }
        }
    }
    public static void listMagazine (ArrayList<Literature> magpri)
    {
        for (Literature mag: magpri){
            if (mag instanceof Magazine){
                Magazine getMagazine = (Magazine) mag;
                System.out.println(getMagazine.getTitle());
                System.out.println(getMagazine.getPublisher());
                System.out.println(getMagazine.getPublishedDate());
                System.out.println(getMagazine.getPrice());
                System.out.println(getMagazine.getCurrency());


            }
        }
    }
}
