package no.ntnu.trygvew;

import no.ntnu.trygvew.litratureTypes.*;
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
    private LiteratureStockRegister bsr;
    private String fp;
    Literature a;
    Literature b;
    Literature c;
    Literature d;



    @Before
    public void setUp() throws Exception {
        bsr = new LiteratureStockRegister(this.fp);
        a = new Book(0,"aaa", "hhh",  3, 4, 3, "dd", "1999-09-06");
        b = new Book(1,"aaa", "ill", 3, 5, 2, "ss", "1999-09-06");
        c = new Paper(2,"bbb", "lme", 3, 8, 5, "ee");
        d = new Magazine(3,"bbc", "ppp", 3, 2, 6, "gg");
        bsr.addLiterature(a);
        bsr.addLiterature(b);
        bsr.addLiterature(c);
        bsr.addLiterature(d);
    }


    @Test
    public void removeBook() {

        bsr.removeLiterature(c);
        bsr.getStock().forEach(book -> {
            assertFalse(book.equals(c));});

    }

    @After
    public void tearDown() throws Exception {
        bsr.removeLiterature(a);
        bsr.removeLiterature(b);
        bsr.removeLiterature(c);
        bsr.removeLiterature(d);
    }
}
