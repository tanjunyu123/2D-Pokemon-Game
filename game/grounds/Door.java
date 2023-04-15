package game.grounds;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import game.subActions.TeleportAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.pokemons.LocationEnterCapability;
import game.subActions.TeleportAction;

/**
 * @author Manan Niraj Karnik
 *
 * Door class to enter new areas/maps, with the help of TeleportAction.
 */
public class Door extends Ground {
    /**
     * An actionlist containing all the allowable actions of this ground.
     */
    private ActionList allowableActions;

    /***
     * Constructor.
     * @param displayChar the character to use to represent this item if it is on the ground
     */
    public Door(char displayChar) {
        super(displayChar);
        this.allowableActions = new ActionList();
    }

    /**
     * Function to allow specific Actors to enter this type of ground.
     * @param actor the Actor to check
     * @return
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(LocationEnterCapability.ENTER_DOOR)){
            return true;
        }
        return false;
    }

    /**
     * Function to add actions to this ground.
     * @param action
     */
    public void addAction(Action action){
        this.allowableActions.add(action);
    }

    /**
     * Returns an empty Action list.
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return an empty Action list.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        if (location.containsAnActor()){
            return allowableActions;
        }
        return new ActionList();
    }

}
