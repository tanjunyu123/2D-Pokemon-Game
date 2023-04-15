package game;
import java.util.Random;



/**
 * Contains methods for generating random integers for a given range.
 *  @author Tan Jun Yu
 */
public class Utils {

    /**
     * Generates a random integer between 0 to 99.
     * @return an integer
     */
    public static int generateRandomChance() {
        int a = new Random().nextInt(100);
        return a;
    }

}
