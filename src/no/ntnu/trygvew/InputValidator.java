package no.ntnu.trygvew;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Class to validate inputt strings
 *
 * @author Trygve Woldseth
 * @version 1.0
 */

public class InputValidator {


    /**
     * Validates that a inputt string is a date
     * @param inpString the string to validate
     * @return true if the inpString is a valid date else false
     */
    public static boolean isValidDate(String inpString) {
        boolean dateIsValid = false;
        //System.out.println(inpString);
        if (inpString.length() == 0){
            return false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(inpString);
            dateIsValid = true;
        } catch (ParseException e) {
            dateIsValid = false;
        }
        return dateIsValid;
    }

    public static boolean isValidStingInp(String s){
        if (s.length() > 0){
            return  true;
        } else {
            System.out.println("No Inputt Provided");
            return false;
        }
    }

    public static boolean isValidFloatInp(String f){
        try {
            Float.valueOf(f);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("input is not a number (float)");
            return false;
        }
    }

    public static boolean isValidIntInp(String i){
        try {
            Integer.valueOf(i);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("input is not a number (int)");
            return false;
        }
    }
}


