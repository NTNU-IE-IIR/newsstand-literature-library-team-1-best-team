package no.ntnu.trygvew;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class InputValidatorTest {

    @Test
    public void isValidDate() {
        assertEquals(false, InputValidator.isValidDate("33990288"));
        assertEquals(true, InputValidator.isValidDate("19990211"));
    }
}