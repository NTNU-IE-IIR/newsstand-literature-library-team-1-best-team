package no.ntnu.trygvew;

import no.ntnu.trygvew.litratureTypes.Literature;
import no.ntnu.trygvew.litratureTypes.SerializedLiterature;
import no.ntnu.trygvew.litratureTypes.StandaloneLiterature;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * mere spesifisert trening
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class BookFilterTest {

    private ArrayList<Literature> testList = new ArrayList();


    @Before
    public void setUp() throws Exception {
        Literature a = new StandaloneLiterature(0,"aaa", "hhh", "Standalone", 3, 4, 3, "dd", "1999-09-06");
        Literature b = new StandaloneLiterature(0,"eee", "ill", "Standalone",3, 5, 2, "ss", "1999-09-06");
        Literature c = new StandaloneLiterature(0,"aaa", "nnn", "Standalone", 3, 4, 3, "dd", "1999-09-06");
        Literature d = new StandaloneLiterature(0,"lll", "ppp", "Standalone",3, 5, 2, "ss", "1999-09-06");


        testList.add(a);
        testList.add(b);
        testList.add(c);
        testList.add(d);
    }

    @Test
    public void filterBookByTitle() {
        String filter = "aaa";

        ArrayList<Literature> filtered = BookFilter.filterLiteratureByTitle(filter, this.testList);
        filtered.forEach(b -> {
            System.out.println(b.getTitle());
            assertTrue(b.getTitle().contains(filter))
        ;});
    }



    @Test
    public void filterBookyBPublisher() {
        String filter = "hhh";

        ArrayList<Literature> filtered = BookFilter.filterLiteratureByPublisher(filter, this.testList);
        filtered.forEach(b -> {
            assertTrue(b.getPublisher().contains(filter));
        });
    }
}
