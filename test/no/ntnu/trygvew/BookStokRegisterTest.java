package no.ntnu.trygvew;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 *
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class BookStokRegisterTest {
    private BookStokRegister bsr;
    private String fp;

    @Before
    public void setUp() throws Exception {
        this.fp = "test/registerTest.json";
        bsr = new BookStokRegister(this.fp);
        Book a = new Book("tit", "pub", 3, "aut", "1999-09-06", 2, 12);
        Book b = new Book("tit1", "pub1", 3, "aut1", "1999-09-06", 2, 12);
        Book c = new Book("tit2", "pub2", 3, "aut2", "1999-09-06", 2, 12);
        bsr.addBook(a);
        bsr.addBook(b);
        bsr.addBook(c);
    }


    @Test
    public void addBook() {
        Book d = new Book("tit3", "pub2", 3, "aut2", "1999-09-06", 2, 12);
        bsr.addBook(d);
    }

    @Test
    public void removeBooksByTitle() {
        bsr.removeBooksByTitle("tit2");
        bsr.getBookIterator().forEachRemaining(book -> {assertFalse(book.getTitle().equals("tit2"));});
    }

    @Test
    public void removeBook() {
        Book c = new Book("tit1", "pub2", 3, "aut2", "1999-09-06", 2, 12);
        bsr.removeBook(c);
        bsr.getBookIterator().forEachRemaining(book -> {
            assertFalse(book.equals(c));});

    }

    @Test
    public void getBookIterator() {
    }

    @After
    public void tearDown() throws Exception {
        File file = new File(this.fp);
        if (file.exists()){
            file.delete();
        }
    }
}