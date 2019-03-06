package no.ntnu.trygvew.messingAround.encryption;

import org.junit.Test;

import static org.junit.Assert.*;

public class AESTest {

    @Test
    public void encryptDecrypt() {
        String p = Util.getHash("hhh",".",1, 128);
        String n = Util.getNonce(16);
        assertEquals("test123" ,AES.decrypt(p, n, AES.encrypt(p, n, "test123")));
    }
}