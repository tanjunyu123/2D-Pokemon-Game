package game.allWeapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import java.util.HashMap;

/**
 * Stores all the special weapons in a hashmap permanently which is used later by pokemons to access these weapons
 * Created by: Rachit Bhatia
 *
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public class WeaponsManager {

    /**
     * a singleton instance which will be used everywhere
     */
    private static WeaponsManager instance;

    /**
     * hashmap stores all special weapon objects with the key as the weapon character
     */
    private HashMap<Character, WeaponItem> specialWeaponsStorage;

    /**
     * Constructor
     * calls the addSpecialWeapons() method so that the weapons are added into the hashmap as soon as WeaponsManager object is initialised
     */
    private WeaponsManager(){
        specialWeaponsStorage = new HashMap<>();
        //changed weapon array to ArrayList to allow for increase in weapons in future
        addSpecialWeapons();
    }

    /**
     * Access single instance publicly
     *
     * @return this instance
     */
    public static WeaponsManager getInstance() {
        if (instance == null) {
            instance = new WeaponsManager();
        }
        return instance;
    }

    /**
     * adds individual objects of each special weapon class into the hashmap as values and sets the key as the display character of the weapon
     */
    private void addSpecialWeapons(){
        Ember ember = new Ember();
        Bubble bubble = new Bubble();
        VineWhip vineWhip = new VineWhip();
        Blaze blaze = new Blaze();
        FireSpin fireSpin = new FireSpin();

        specialWeaponsStorage.put(ember.getDisplayChar(), new Ember());
        specialWeaponsStorage.put(bubble.getDisplayChar(), new Bubble());
        specialWeaponsStorage.put(vineWhip.getDisplayChar(), new VineWhip());
        specialWeaponsStorage.put(blaze.getDisplayChar(), new Blaze());
        specialWeaponsStorage.put(fireSpin.getDisplayChar(), new FireSpin());
    }

    /**
     * returns the hashmap that stores all the special weapons permanently
     *
     * @return specialWeaponsStorage hashmap
     */
    public HashMap<Character, WeaponItem> getSpecialWeaponsStorage(){
        return specialWeaponsStorage;
    }

}
