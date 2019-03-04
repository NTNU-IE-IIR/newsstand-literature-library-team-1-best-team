package no.ntnu.trygvew;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class InputtValidatorTest {

    @Test
    public void isValidDate() {
        assertEquals(false, InputtValidator.isValidDate("33990288"));
        assertEquals(true, InputtValidator.isValidDate("19990211"));
    }
}