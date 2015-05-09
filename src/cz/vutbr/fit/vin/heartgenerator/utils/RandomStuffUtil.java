package cz.vutbr.fit.vin.heartgenerator.utils;

import java.util.Random;

/**
 *
 * @author Matej Marecek
 */
public class RandomStuffUtil {

    public static double doubleInRange(Random random, double min, double max) {
        return min + (random.nextDouble()*(max-min));
    }
    
}
