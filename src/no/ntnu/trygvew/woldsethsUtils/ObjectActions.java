package no.ntnu.trygvew.woldsethsUtils;

import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.logging.Filter;

public class ObjectActions {



    public static boolean hasSameGetters(Object ob1, Object ob2){

        boolean isEqual = true;
        System.out.println(ob1.getClass());
        System.out.println(ob2.getClass());
        if (ob1.getClass() == ob2.getClass()){
            // the classes are the same and have the same methods
            Method[] ob1Methods = ob1.getClass().getMethods();

            for (Method classMethod: ob1Methods){
                if (classMethod.toString().contains("get")){
                    //int startIndex = str.indexOf("get");
                    //String methodeName = str.substring(startIndex + 3, str.length() - 2);
                    try {
                        Object val1 = classMethod.invoke(ob1, null);
                        Object val2 = classMethod.invoke(ob2, null);
                        if (!val1.equals(val2)){
                            isEqual = false;
                        }
                    }
                    catch (Exception e) {e.printStackTrace();}
                }
            }
        } else {
            isEqual = false;
        }
        return isEqual;
    }
}
