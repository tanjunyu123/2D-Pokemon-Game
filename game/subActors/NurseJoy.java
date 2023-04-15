package game.subActors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.subActions.AttackAction;
import game.behaviours.Behaviour;
import game.newItems.Candy;
import game.subActions.TradeAction;

/**
 * An Actor subclass
 * This character allows the Player to trade items with
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by: -
 */
public class NurseJoy extends Actor {
    /**
     * Constructor.
     *
     */
    public NurseJoy() {
        super("Nurse Joy", '%', 100);
    }

    /**
     * Actions can be formed by the NurseJoy
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /** List of actions can be performed by another actor on the NurseJoy
     * @param player the Actor that might perform an action.
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return list of actions
     */
    @Override
    public ActionList allowableActions(Actor player, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new TradeAction(this, direction, TradeAction.TradeOptions.CHARMANDEREGG));
        actions.add(new TradeAction(this, direction, TradeAction.TradeOptions.BULBASAUREGG));
        actions.add(new TradeAction(this, direction, TradeAction.TradeOptions.SQUIRTLEEGG));
        actions.add(new TradeAction(this, direction, TradeAction.TradeOptions.FIRE_POKEFRUIT));
        actions.add(new TradeAction(this, direction, TradeAction.TradeOptions.WATER_POKEFRUIT));
        actions.add(new TradeAction(this, direction, TradeAction.TradeOptions.GRASS_POKEFRUIT));
        return actions;
    }


}
