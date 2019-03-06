package no.ntnu.trygvew;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 *
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

import static org.junit.Assert.*;

public class DataSaverTest {

    private String fp;
    private DataSaver dataSaver;
    private ArrayList<Book> testList = new ArrayList<>();
    /*
    @Before
    public void setUp() throws Exception {
        this.fp = "test/storageTest.json";
        dataSaver = new DataSaver();

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
    public void saveLoad() {
        DataSaver.saveBookListToJson(this.testList);
        File file = new File(this.fp);
        ArrayList<Book> loadedList = dataSaver.LoadBookListFromJson();

        loadedList.forEach(b-> {
            final Boolean[] containsTarget = {false};
            this.testList.forEach(l -> {
                if (b.toString().equals(l.toString())){containsTarget[0] = true;}
            });
            assertTrue(containsTarget[0]);
        });




    }

    @After
    public void tearDown() throws Exception {
        File file = new File(this.fp);
        if (file.exists()){
            file.delete();
        }
    }
    */
}