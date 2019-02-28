package no.ntnu.trygvew;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BookTest {

    private Book b;

    @Before
    public void setUp() {
        b = new Book("tit", "pub", 3, "aut", "1999-09-06", 2, 12);
    }

    @Test
    public void getTitle() {

        assertEquals("tit", b.getTitle());
    }

    @Test
    public void getPublisher() {
        assertEquals("pub", b.getPublisher());
    }

    @Test
    public void getAutor() {
        assertEquals("aut", b.getAutor());
    }

    @Test
    public void getEdition() {
        assertEquals(3, b.getEdition());
    }

    @Test
    public void getNumberInStok() {
        assertEquals(2, b.getNumberInStok());
    }

    @Test
    public void getPrice() {
        assertEquals(12, b.getPrice(), 0.);
    }

    @Test
    public void getPublicationDate() {
        assertEquals("1999-09-06", b.getPublicationDate());
    }

    @Test
    public void getFullTitle() {
        assertEquals("tit 3.ed", b.getFullTitle());
    }


    @Test
    public void isInSeries() {
        assertEquals(false, b.isInSeries());
    }
}