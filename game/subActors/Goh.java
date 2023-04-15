package game.subActors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.behaviours.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * An Actor that is a subclass of Trainer
 * This character is uncontrollable because it can decide its action depending on the situation in its surrounding.
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by: -
 */
public class Goh extends Trainer {

    /**
     * The behaviours of Goh
     */
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour


    /**
     * Singleton instance
     */
    private static Goh instance;

    /**
     * Constructor.
     */
    private Goh() {
        super("Goh", 'G', 100);
        AffectionManager.getInstance().registerTrainer(this);

        this.behaviours.put(1, new CatchPokemonBehaviour());
        this.behaviours.put(2, new FeedPokemonBehaviour());
        this.behaviours.put(3, new PickUpBehaviour());
        this.behaviours.put(4, new WanderBehaviour());
    }

    /**
     * @return the singleton instance of Goh
     */
    public static Goh getInstance() {
        if (instance == null) {
            instance = new Goh();
        }
        return instance;
    }

    /**
     * Randomly selects the behaviour of Goh at each turn depending on the situation
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the random action selected to be performed by Goh
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
