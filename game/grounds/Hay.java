package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import game.Element;

/**
 * Hay
 * A ground class of grass element
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by:
 */
public class Hay extends Ground {

    /**
     * constructor
     */
    public Hay() {
        super(',');
        this.addCapability(Element.GRASS);
    }
}
