package game.allWeapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Element;

/**
 * Created by: Rachit Bhatia
 * A fire type special weapon
 *
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public class FireSpin extends WeaponItem {

    /**
     * Constructor
     *
     * calls super class constructor and passes its respective values
     */
    public FireSpin() {

        super("Fire Spin", 'ø', 70, "spins", 90);
        this.addCapability(Element.FIRE);

    }
}
