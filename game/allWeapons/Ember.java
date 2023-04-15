package game.allWeapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A class for the Ember Weapon
 * Created by: Rachit Bhatia
 *
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public class Ember extends WeaponItem {

    /**
     * Constructor
     *
     * calls super class constructor and passes its respective values
     */
    public Ember() {
        super("Ember", '>', 20, "sparks", 90);
    }
}
