package no.ntnu.trygvew.woldsethsUtils;

import no.ntnu.trygvew.litratureTypes.Literature;
import no.ntnu.trygvew.litratureTypes.Paper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ObjectActionsTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void hasSameGetters() {
        Literature test1 = new Paper("bbb", "lme", 3, 8, 5, "ee");
        Literature test2 = new Paper("bbb", "lme", 3, 8, 5, "ee");
        Literature test3 = new Paper("lll", "lme", 3, 8, 5, "ee");

        assertTrue(ObjectActions.hasSameGetters(test1, test2));
        assertFalse(ObjectActions.hasSameGetters(test1,test3));
    }
}