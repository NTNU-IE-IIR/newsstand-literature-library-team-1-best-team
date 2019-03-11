package no.ntnu.trygvew.messingAround;

import no.ntnu.trygvew.FileIO.DataSaver;
import no.ntnu.trygvew.messingAround.encryption.AES;
import no.ntnu.trygvew.messingAround.encryption.Util;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

/**
 * TODO: lag fungsjona for og endre brukernavn passord osv
 * TODO: se om du ska oppdater krypterings nøkkelen ved hver kryptering så man kunn hold en midlertidig nøkkel i minne. alsa man dekryptere krypterings nøkkelen og hold den i minne så man kan krypter å discarde den etterpå
 */
public class UserLoggin {
    // a hashmap containing the UUID-salt:hash pair for all users
    private HashMap<String, String> hashTable;
    // a hashmap containing the UUID-nonce:encryptionKey pair for all users
    private HashMap<String, String> encryptionKeys;

    // a hashmap containing the username-UUID pair for all users
    private HashMap<String, String> userNames;
    // a hashmap containing the UUID-nonce:userData pair for all users
    private HashMap<String, String> userData;

    private String hashListFP;
    private String keyListFP;

    private String userNameFP;
    private String userDataFP;

    public UserLoggin() {
        this.hashListFP = "Data/hashList.json";
        this.keyListFP = "Data/keyList.json";

        this.userNameFP = "Data/userNames.json";
        this.userDataFP = "Data/userData.json";

        this.hashTable = this.loadStringHashmap(this.hashListFP);
        this.encryptionKeys = this.loadStringHashmap(this.keyListFP);

        this.userNames = this.loadStringHashmap(this.userNameFP);
        this.userData = this.loadStringHashmap(this.userDataFP);
    }

    private void saveStringHashMap(String fp, HashMap<String, String> hm){
        File saveFile = new File(fp);
        if (!saveFile.exists()){
            try{saveFile.createNewFile();} catch (Exception e) {};
        }

        try {
            JSONObject j = new JSONObject(hm);
            DataSaver.saveString(j.toString(2) ,fp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HashMap<String, String> loadStringHashmap(String fp){
        HashMap<String, String> newHashTable = new HashMap<>();
        try {
            String savedString = DataSaver.loadString(fp);

            JSONObject jsonObject = new JSONObject(savedString);
            Iterator hashesItr = jsonObject.keys();
            hashesItr.forEachRemaining(u ->{
                newHashTable.put(u.toString(), jsonObject.get(u.toString()).toString());
            });
        } catch (IOException e) {
            // no file found returning blank table
        }
        return  newHashTable;
    }


    /**
     * reloads the saved data in to memory and
     * decypts and makes the user class for the user with the given Username-password combo
     * @param username
     * @param password
     * @return
     */
    public User loadUser(String username, String password){
        String userId = this.userNames.get(username);
        String encryptionKeyKey = Util.getHash(password, ".", 1, 128);

        String[] keyStr = this.encryptionKeys.get(userId).split(":");
        String encNonce = keyStr[0];
        String encryptedEncKey = keyStr[1];

        String encKey = AES.decrypt(encryptionKeyKey, encNonce, encryptedEncKey);



        String[] userDataStr = this.userData.get(userId).split(":");
        String dataNonce = userDataStr[0];
        String encryptedData = userDataStr[1];


        String userData = AES.decrypt(encKey, dataNonce, encryptedData);



        JSONObject userDataobj = new JSONObject(userData);

        String firstName = userDataobj.getString("firstName");
        String lastName = userDataobj.getString("lastName");
        float userFunds = userDataobj.getFloat("userFunds");
        String[] orderIdStringAr = userDataobj.getString("orderIDs").split(":");
        ArrayList<String> userOrders = new ArrayList<>(Arrays.asList(orderIdStringAr));


        User newUser = new User(userId, username, firstName, lastName, userFunds, userOrders, encKey);

        return newUser;
    }

    /**
     * encrypts the user data and saves the user data table
     * @param user
     */
    public void saveUser(User user){
        JSONObject saveObj = new JSONObject();

        String userName = user.getUsername();
        String userId = this.userNames.get(userName);
        String encryptionKey = user.getEncryptionKey();

        saveObj.put("firstName", user.getFirstName());
        saveObj.put("lastName", user.getLastName());
        saveObj.put("userFunds", user.getUserFunds());

        ArrayList<String> userOrders = user.getOrders();
        String orderSaveStr = "";
        for (String o: userOrders){
            orderSaveStr += (o + ":");
        }
        // removes the last ;
        if (orderSaveStr.length() > 2){
            orderSaveStr = orderSaveStr.substring(0, -1);
        }

        saveObj.put("orderIDs", orderSaveStr);

        String nonce = Util.getNonce(16);

        String saveStr = AES.encrypt(encryptionKey, nonce, saveObj.toString(2));

        this.userData.put(userId, nonce + ":" + saveStr);
        this.saveStringHashMap(this.userDataFP,this.userData);
    }


    public boolean isValidLoggin(String username, String pasword){
        Boolean isValid = false;
        try{
            String userID = this.userNames.get(username);
            String[] idHashTableEntry = this.hashTable.get(userID).split(":");
            String idSalt = idHashTableEntry[0];
            String idHash = idHashTableEntry[1];

            // TODO: sjekke om hvordan du skal lagre itr og bitSize mulig æ skal bar appende det på stringen som salt
            String paswordHash = Util.getHash(pasword, idSalt, 500000, 128);
            if (idHash.equals(paswordHash)){
                isValid = true;
            }
        } catch (NullPointerException e){
            // scold not happen
            System.out.println("Invalid username");
            isValid = false;
        }

        return isValid;
    }

    /**
     * cheks if the username is used by another acount
     * @return
     */
    public Boolean isValidUsername(String username){
        return !this.userNames.containsKey(username);
    }



    public User createUser(String userName, String password, String firstName, String lastName, float userFunds){
        // defines a uniqe identefier
        String id = Util.getNonce(16);

        // encrypts the encryprion key using a single pas hash of the password
        String encKey = Util.getNonce(16);
        String encKeyNonce = Util.getNonce(16);
        String encryptedKey = AES.encrypt(Util.getHash(password, ".", 1, 128),encKeyNonce, encKey);

        String salt = Util.getNonce(256);
        String passwordHash = Util.getHash(password, salt, 500000, 128);



        this.userNames.put(userName, id);
        this.hashTable.put(id, salt + ":" + passwordHash);
        this.encryptionKeys.put(id, encKeyNonce + ":" + encryptedKey);
        this.saveStringHashMap(this.userNameFP, this.userNames);
        this.saveStringHashMap(this.hashListFP, this.hashTable);
        this.saveStringHashMap(this.keyListFP, this.encryptionKeys);

        User newUser = new User(id, userName, firstName, lastName, userFunds, new ArrayList<String>(), encKey);
        this.saveUser(newUser);
        return newUser;
    }
}
