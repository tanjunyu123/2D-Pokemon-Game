package game.allWeapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A class for the Bubble Weapon
 * Created by: Rachit Bhatia
 *
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public class Bubble extends WeaponItem {

    /**
     * Constructor
     *
     * calls super class constructor and passes its respective values
     */
    public Bubble (){
        super ("Bubble", ':', 25, "burbles", 80);
    }
}
