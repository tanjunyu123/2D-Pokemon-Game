package game.pokemons;


import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.AffectionManager;
import game.Element;
import game.Status;
import game.allWeapons.WeaponsManager;
import game.newItems.ItemCheck;
import game.time.TimePerception;
import game.time.TimePerceptionManager;

/**
 * Created by:Riordan D. Alfredo
 * A pokemon of fire type
 * @author Rachit, Manan, Tan Jun Yu
 * Modified by: Rachit, Manan, Tan Jun Yu
 */
public class Charmander extends Pokemon implements TimePerception {

    /**
     * ItemCheck enum instance to determine the type of item
     */
    private ItemCheck check;
    /**
     * Constructor.
     */
    public Charmander() {
        super("Charmander", 'c', 100, new Charmeleon());
        // HINT: add more relevant behaviours here
        this.addCapability(Element.FIRE);
        this.registerInstance();
        evolvedVersion = new Charmeleon();
    }

    /**
     * @param otherActor the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        return super.allowableActions(otherActor, direction,map);
    }

    /**
     * By using behaviour loops, it will decide what will be the next action automatically.
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return super.playTurn(actions, lastAction, map, display);
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

        if (isEquipping){
            addItemToInventory(weaponsManager.getSpecialWeaponsStorage().get('>'));
            System.out.println(weaponsManager.getSpecialWeaponsStorage().get('>') + " weapon added to Charmander's inventory");
        }
        else{
            if (!this.getInventory().isEmpty()) {
                removeItemFromInventory(weaponsManager.getSpecialWeaponsStorage().get('>'));
                System.out.println(weaponsManager.getSpecialWeaponsStorage().get('>') + " weapon removed from Charmander's inventory");
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
        return new IntrinsicWeapon(10,"scratch");
    }


    /**
     * Charmander behaviour at Day Shift
     */
    @Override
    public void dayEffect(GameMap map) {
        int hitPointsToIncrease = 10;
        this.heal(hitPointsToIncrease);
    }

    /**
     * Charmander behaviour at Night Shift
     */
    @Override
    public void nightEffect(GameMap map) {
        int hitPointsToDecrease = 10;
        this.hurt(hitPointsToDecrease);
        if (!this.isConscious()){
            map.removeActor(this);
            System.out.println(this + " has died from night effect!");
            TimePerceptionManager.getInstance().cleanUp(this);
        }
    }

}
