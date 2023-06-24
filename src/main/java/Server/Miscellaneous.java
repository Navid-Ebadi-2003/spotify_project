package Server;

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
        Random rand = new Random();
 
        JsonArray newArray = new JsonArray();
        for (int i = 0; i < totalItems; i++) {
 
            // take a random index between 0 to size of given jsonArray
            int randomIndex = rand.nextInt(jsonArray.size());
 
            // add element in temporary jsonArray
            newArray.add(jsonArray.get(randomIndex));
        }
        return newArray;
    }
}