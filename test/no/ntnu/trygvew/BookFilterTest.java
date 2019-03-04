package no.ntnu.trygvew;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 *
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class BookFilterTest {

    private ArrayList<Book> testList = new ArrayList();


    @Before
    public void setUp() throws Exception {
        Book a = new Book("aaa", "hhh", 3, "ttt", "1999-09-06", 2, 12);
        Book b = new Book("aaa", "ill", 3, "hhh", "1999-09-06", 2, 12);
        Book c = new Book("bbb", "lme", 3, "lri", "1999-09-06", 2, 12);
        Book d = new Book("bbc", "ppp", 3, "irl", "1999-09-06", 2, 12);

        testList.add(a);
        testList.add(b);
        testList.add(c);
        testList.add(d);
    }

    @Test
    public void filterBookByTitle() {
        Iterator<Book> itr = testList.iterator();
        String filter = "aaa";

        Iterator<Book> filteredItr = BookFilter.filterBookByTitle(filter, itr);
        Boolean containsInvalids = false;
        filteredItr.forEachRemaining(b -> {assertTrue(b.getTitle().contains(filter));});
    }

    @Test
    public void filterBookByAuthor() {
        Iterator<Book> itr = testList.iterator();
        String filter = "r";

        Iterator<Book> filteredItr = BookFilter.filterBookByAuthor(filter, itr);
        Boolean containsInvalids = false;
        filteredItr.forEachRemaining(b -> {assertTrue(b.getAutor().contains(filter));});
    }

    @Test
    public void filterBookByPublisher() {
        Iterator<Book> itr = testList.iterator();
        String filter = "hhh";

        Iterator<Book> filteredItr = BookFilter.filterBookByPublisher(filter, itr);
        Boolean containsInvalids = false;
        filteredItr.forEachRemaining(b -> {assertTrue(b.getPublisher().contains(filter));});
    }
}