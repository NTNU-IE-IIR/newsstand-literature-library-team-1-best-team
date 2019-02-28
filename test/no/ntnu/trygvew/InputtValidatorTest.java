package no.ntnu.trygvew;

import org.junit.Test;

import static org.junit.Assert.*;

public class InputtValidatorTest {

    @Test
    public void isValidDate() {
        assertEquals(false, InputtValidator.isValidDate("33990288"));
        assertEquals(true, InputtValidator.isValidDate("19990211"));
    }
}