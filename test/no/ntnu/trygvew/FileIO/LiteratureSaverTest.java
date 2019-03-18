package no.ntnu.trygvew.FileIO;

import no.ntnu.trygvew.litratureTypes.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class LiteratureSaverTest {

    private String fp;
    private ArrayList<Literature> testList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        this.fp = "test/storageTest.json";

        Literature a = new Book("aaa", "hhh",  3, 4, 3, "dd", "1999-09-06");
        Literature b = new Book("aaa", "ill", 3, 5, 2, "ss", "1999-09-06");
        Literature c = new Paper("bbb", "lme", 3, 8, 5, "ee");
        Literature d = new Magazine("bbc", "ppp", 3, 2, 6, "gg");

        testList.add(a);
        testList.add(b);
        testList.add(c);
        testList.add(d);
    }

    @Test
    public void saveLoadTest() {
        ArrayList<Literature> loadedList = null;
        try {
            LiteratureSaver.saveLiteratureStock(this.testList, this.fp);
            File file = new File(this.fp);
            loadedList = LiteratureSaver.loadLiteratureStock(this.fp);
        } catch (Exception e) {}



        loadedList.forEach(b-> {
            System.out.println(b.getClass());
            final Boolean[] containsTarget = {false};

            this.testList.forEach(l -> {
                if (b.getLiteratureType().equals(l.getLiteratureType())){containsTarget[0] = true;}
            });
            assertTrue(containsTarget[0]);
        });
    }

    @Test
    public void loadLiteratureStock() {
    }

    @After
    public void tearDown() throws Exception {
        File file = new File(this.fp);
        if (file.exists()){
            file.delete();
        }
    }
}