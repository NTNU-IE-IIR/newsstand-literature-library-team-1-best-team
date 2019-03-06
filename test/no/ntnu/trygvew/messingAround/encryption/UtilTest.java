package no.ntnu.trygvew.messingAround.encryption;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void getNonce() {
    }

    @Test
    public void getHash() {
        String nonce = Util.getNonce(16);
        String message = "weak password";

        //Util.getHash(message, nonce);

    }
}