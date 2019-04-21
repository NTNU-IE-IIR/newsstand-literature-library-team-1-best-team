package no.ntnu.trygvew.litratureTypes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LiteratureTest {
    Literature a;
    @Before
    public void setUp() {
        a = new Literature(0,"tit", "pub", "t", 2, 222);
    }

    @Test
    public void getSaveID() {
        assertTrue(a.getSaveID().equals(0));
    }

    @Test
    public void getTitle() {
        assertTrue(a.getTitle().equals("tit"));
    }

    @Test
    public void getPublisher() {
        assertTrue(a.getPublisher().equals("pub"));
    }

    @Test
    public void getLiteratureType() {
        assertTrue(a.getLiteratureType().equals("t"));
    }

    @Test
    public void getNumberInStock() {
        assertTrue(a.getNumberInStock() == 2);
    }

    @Test
    public void getPrice() {
        assertTrue(a.getPrice() == 222);
    }

    @Test
    public void setNumberInStock() {
        assertFalse(a.getNumberInStock() == 50);
        a.setNumberInStock(50);
        assertFalse(a.getNumberInStock() == 2);
        assertTrue(a.getNumberInStock() == 50);
    }
}