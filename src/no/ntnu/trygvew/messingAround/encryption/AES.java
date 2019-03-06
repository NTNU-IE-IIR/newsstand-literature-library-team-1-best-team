package no.ntnu.trygvew.messingAround.encryption;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;


/**
 * Interface for encrypting strings with the advanced encryption algorithm
 *
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class AES {

    private static final String encryptionAlgorithm = "AES";

    /**
     * Encrypts a string with the AES algorithm
     * @param encryptionKey a 128 bit encryption key
     * @param nonce a 128 bit nonsensical value
     * @param value the value to be encrypted
     * @return an encrypted string
     */
    public static String encrypt(String encryptionKey, String nonce, String value){
        try {
            System.out.println("nonc " + nonce.length());
            System.out.println("noncb " + nonce.getBytes().length);
            System.out.println("pass " + encryptionKey.length());
            System.out.println("noncb " + nonce.getBytes().length);
            // makes a initialization vector form the provided nonsensical value
            IvParameterSpec initVector = new IvParameterSpec(nonce.getBytes("UTF-8"));
            // makes an encryption key for the AES algorithm form the provided key
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), encryptionAlgorithm);

            // defines the encryption algorithm for the cipher(which is an old word for cryptographic algorithm)
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            // initializes the encryption algorithm
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, initVector);

            // encrypts the given value
            byte[] encrypted = cipher.doFinal(value.getBytes());


            return Base64.getUrlEncoder().withoutPadding().encodeToString(encrypted);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Encrypts a string with the AES algorithm
     * @param encryptionKey a 128 bit decryption key
     * @param nonce the 128 bit nonce used to encrypt value
     * @param value the value to be decrypted
     * @return an encrypted string
     */
    public static String decrypt(String encryptionKey, String nonce, String value){
        try {
            // makes a initialization vector form the provided nonsensical value
            IvParameterSpec initVector = new IvParameterSpec(nonce.getBytes("UTF-8"));
            // makes an encryption key for the AES algorithm form the provided key
            SecretKeySpec secretKeySpec = new SecretKeySpec(encryptionKey.getBytes("UTF-8"), encryptionAlgorithm);

            // defines the encryption algorithm for the cipher(which is an old word for cryptographic algorithm)
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            // initializes the encryption algorithm
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, initVector);

            // encrypts the given value
            byte[] decrypted = cipher.doFinal(Base64.getUrlDecoder().decode(value));


            return new String(decrypted);
        }catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
