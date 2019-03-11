package no.ntnu.trygvew;

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

/*
public class BookFilterTest {

    private ArrayList<StandaloneLiterature> testList = new ArrayList();


    @Before
    public void setUp() throws Exception {
        StandaloneLiterature a = new StandaloneLiterature("aaa", "hhh", 3, "ttt", "1999-09-06", 2, 12);
        StandaloneLiterature b = new StandaloneLiterature("aaa", "ill", 3, "hhh", "1999-09-06", 2, 12);
        StandaloneLiterature c = new StandaloneLiterature("bbb", "lme", 3, "lri", "1999-09-06", 2, 12);
        StandaloneLiterature d = new StandaloneLiterature("bbc", "ppp", 3, "irl", "1999-09-06", 2, 12);

        testList.add(a);
        testList.add(b);
        testList.add(c);
        testList.add(d);
    }

    @Test
    public void filterBookByTitle() {
        String filter = "aaa";

        ArrayList<StandaloneLiterature> filtered = BookFilter.filterBookByTitle(filter, this.testList);
        filtered.forEach(b -> {
            System.out.println(b.getTitle());
            assertTrue(b.getTitle().contains(filter))
        ;});
    }

    @Test
    public void filterBookByAuthor() {
        String filter = "r";

        ArrayList<StandaloneLiterature> filtered = BookFilter.filterBookByAuthor(filter, this.testList);
        filtered.forEach(b -> {
            System.out.println(b.getAuthor());
            assertTrue(b.getAuthor().contains(filter));
        });
    }

    @Test
    public void filterBookyBPublisher() {
        String filter = "hhh";

        ArrayList<StandaloneLiterature> filtered = BookFilter.filterBookByPublisher(filter, this.testList);
        filtered.forEach(b -> {
            assertTrue(b.getPublisher().contains(filter));
        });
    }
}
*/