package game.pokemons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.allWeapons.WeaponsManager;
import game.time.TimePerception;
import game.time.TimePerceptionManager;

/**
 * Created by:Rachit
 * A pokemon of water type
 * @author Riordan D. Alfredo
 * Modified by:  Manan, Tan Jun Yu, Rachit Bhatia
 */
public class Squirtle extends Pokemon implements TimePerception {

    /**
     * Constructor
     */
    public Squirtle() {
        super("Squirtle", 's', 100, null);
        // HINT: add more relevant behaviours here
        this.addCapability(Element.WATER);
        this.registerInstance();
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
            addItemToInventory(weaponsManager.getSpecialWeaponsStorage().get(':'));
            System.out.println(weaponsManager.getSpecialWeaponsStorage().get(':') + " weapon added to Squirtle's inventory");
        }
        else{
            if (!this.getInventory().isEmpty()) {
                removeItemFromInventory(weaponsManager.getSpecialWeaponsStorage().get(':'));
                System.out.println(weaponsManager.getSpecialWeaponsStorage().get(':') + " weapon removed from Squirtle's inventory");
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
     * Checks for the actor's location's ground element type and matches with the actor's element type
     * Overriden Method
     *
     * @param actor the current pokemon
     * @param targetActor the target pokemon to attack
     * @param map the current gamemap
     * @return a boolean value indicating if actor should equip a special weapon
     */
    @Override
    public boolean checkSurroundingGround(Actor actor, Actor targetActor, GameMap map){

        boolean shouldEquipWeapon = false;

        Element actorElement = null;

        for (Element element: actor.findCapabilitiesByType(Element.class)){
            actorElement = element;
        }

        Location actorLocation =  map.locationOf(actor);
        if ((actorLocation.getGround().hasCapability(actorElement)) || (targetActor.hasCapability(Element.FIRE))){
            shouldEquipWeapon = true;
        }
        return shouldEquipWeapon;
    }

    /**
     * Squirtle behaviour at Day Shift
     */
    @Override
    public void dayEffect(GameMap map) {
        int hitPointsToDecrease = 10;
        this.hurt(hitPointsToDecrease);
        if (!this.isConscious()){
            map.removeActor(this);
            System.out.println(this + " has died from day effect!");
            TimePerceptionManager.getInstance().cleanUp(this);
        }
    }

    /**
     * Squirtle behaviour at Night Shift
     */
    @Override
    public void nightEffect(GameMap map) {
        int hitPointsToIncrease = 10;
        this.heal(hitPointsToIncrease);
    }

}
