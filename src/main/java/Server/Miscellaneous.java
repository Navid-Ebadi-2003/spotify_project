package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.JsonArray;

public class Miscellaneous {
    public static String hashText(String text) {
        String hashed = BCrypt.hashpw(text, BCrypt.gensalt());
        return hashed;
    }

    public static boolean checkHash(String hashedText, String unhashedText) {
        boolean isEqual = BCrypt.checkpw(unhashedText, hashedText);
        return isEqual;
    }


    // select some random elements from a json array
    public static JsonArray getRandomElements(JsonArray jsonArray, int totalItems) {
        ArrayList<Integer> indexes = new ArrayList<Integer>();

        if(totalItems > jsonArray.size()) {
            totalItems = jsonArray.size();
        }

        for(int i = 0; i < jsonArray.size(); i++) {
            indexes.add(i);
        }
    
        // shuffle array
        Collections.shuffle(indexes);
    
        // adding defined amount of numbers to target array
        JsonArray targetElements = new JsonArray();
        for (int j = 0; j < totalItems; j++) {
            int index = indexes.get(j);
            targetElements.add(jsonArray.get(index)); 
        }
    
        return targetElements;
    }
}