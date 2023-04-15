package game.subActors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

import java.util.HashMap;
import java.util.Map;


/**
 * An abstract class representing Trainer in the Game
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by: -
 */
public abstract class Trainer extends Actor {


    /**
     * affectionPoints of the pokemons towards this Trainer
     */
    private final Map<Actor, Integer> affectionPoints;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Trainer(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.affectionPoints = new HashMap<>();
        this.addCapability(Status.TRAINER);
    }

    /**
     * A method to be overriden by its subclasses (For now, Player and Goh)
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    /**
     * Getter for the affectionPoints HashMap
     * @return the HashMap storing the affectionPoints
     */
    public Map<Actor, Integer> getAffectionPointsHashMap() {
        return affectionPoints;
    }
}
