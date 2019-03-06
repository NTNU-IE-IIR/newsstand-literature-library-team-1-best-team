package no.ntnu.trygvew.messingAround.encryption;

import org.junit.Test;

import static org.junit.Assert.*;

public class AESTest {

    @Test
    public void encryptDecrypt() {
        String p = Util.getNonce(16);
        String n = Util.getNonce(16);
        System.out.println(n.length());
        System.out.println(AES.decrypt(p, n, AES.encrypt(p, n, "hello World")));
    }
}