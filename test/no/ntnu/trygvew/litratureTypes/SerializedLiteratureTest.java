package no.ntnu.trygvew.litratureTypes;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SerializedLiteratureTest {

    SerializedLiterature a;
    @Before
    public void setUp() {
        a = new SerializedLiterature(0,"tit", "pub", "t", 2, 222, 22, "gen");
    }

    @Test
    public void getYearlyDistributions() {
        assertTrue(a.getYearlyDistributions() == 22);
    }

    @Test
    public void getGenre() {
        assertTrue(a.getGenre().equals("gen"));
    }
}