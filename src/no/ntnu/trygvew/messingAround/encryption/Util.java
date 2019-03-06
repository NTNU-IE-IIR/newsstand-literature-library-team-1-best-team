package no.ntnu.trygvew.messingAround.encryption;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Util {

    /**
     * Returns a random value with the given number of bytes
     * @param NumberOfBytes the number of bytes in the nonce
     * @return a string with the given number of bytes
     */
    public static String getNonce(int NumberOfBytes){
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[NumberOfBytes];
        random.nextBytes(bytes);

        String s = new String(bytes);
        return s;
    }


    /**
     * returns a PBKDF2-Hmac-SHA256 500.000 pass hash from the given password and salt
     * @param str the pasword to hash
     * @param salt the salt to hash
     * @return the hashed password
     */
    public static String getHash(String str, String salt, int itr, int bitSize){

        try{
            // makes the PBE(password based encryption) specs to instruct what to hash and how many iterations to hash
            // the PBE variant adds entropy to the users passwords
            KeySpec spec = new PBEKeySpec(str.toCharArray(), salt.getBytes(), itr, bitSize);// i1 is returned hash size in bits

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            String s = new String(hash); //Base64.getUrlEncoder().withoutPadding().encodeToString(hash);
            //System.out.println(s.getBytes());
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
