package game.pokemons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.allWeapons.WeaponsManager;
import game.time.TimePerception;
import game.time.TimePerceptionManager;

/**
 * Bulbasaur
 *  A pokemon of water element
 * Created by: Rachit
 *
 * @author Rachit
 * Modified by: Tan Jun Yu, Manan Karnik, Rachit Bhatia
 */


public class Bulbasaur extends Pokemon implements TimePerception {

    /**
     * Constructor.
     */
    public Bulbasaur() {
        super("Bulbasaur", 'b', 100, null);
        // HINT: add more relevant behaviours here
        this.addCapability(Element.GRASS);

        this.registerInstance();
    }

    /**
     *  Select and return an action to perform on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
    }


    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        //FIXME: allow other actor to attack this Charmander (incl. Player). Please check requirement! :)
        return super.allowableActions(otherActor, direction,map);

    }


    /**
     * adds or removes respective pokemon's special weapon into its inventory
     * Overriden method from pokemon class
     *
     * @param isEquipping boolean value
     */
    @Override
    public void toggleWeapon(boolean isEquipping) {
        WeaponsManager weaponsManager = WeaponsManager.getInstance();

        if (isEquipping) {
            addItemToInventory(weaponsManager.getSpecialWeaponsStorage().get('/'));
            System.out.println(weaponsManager.getSpecialWeaponsStorage().get('/') + " weapon added to Bulbasaur's inventory");
        } else {
            if (!this.getInventory().isEmpty()) {
                removeItemFromInventory(weaponsManager.getSpecialWeaponsStorage().get('/'));
                System.out.println(weaponsManager.getSpecialWeaponsStorage().get('/') + " weapon removed from Bulbasaur's inventory");
            }
        }
    }

    /**
     * Creates and returns an intrinsic weapon. (Overriden)
     *
     * By default, the Actor 'punches' for 5 damage. Override this method to create
     * an Actor with more interesting descriptions and/or different damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(10,"tackle");
    }


    /**
     * Bulbasaur behavior at Day Shift
     */
    @Override
    public void dayEffect(GameMap map) {
        int hitPointsToDecrease = 5;
        this.hurt(hitPointsToDecrease);
        if (!this.isConscious()){
            map.removeActor(this);
            System.out.println(this + " has died from day effect!");
            TimePerceptionManager.getInstance().cleanUp(this);
        }
    }

    /**
     * Bulbasaur behavior at Night Shift
     */
    @Override
    public void nightEffect(GameMap map) {
        int hitPointsToIncrease= 5;
        this.heal(hitPointsToIncrease);
    }

}
