package game.subActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.Status;
import game.newItems.Candy;
import game.newItems.Pokeball;
import game.pokemons.Pokemon;
import game.time.TimePerceptionManager;

/**
 * An Action to catch pokemon
 * Created by: Manan
 *
 * @author Manan
 * Modified by: Tan Jun Yu
 */
public class CatchPokemonAction extends Action {

    /**
     * The target that will be caught
     */
    private Actor target;


    /**
     * The direction of the target
     */
    private String direction;

    /**
     * Instance of AffectionManager
     */
    private AffectionManager aff = AffectionManager.getInstance();

    /**
     * Constructor
     * @param target
     * @param direction
     */
    public CatchPokemonAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ans = "";

        if (aff.getAffectionPoint(target,actor.toString()) < 50) {
            aff.decreaseAffection(target, 10,actor.toString());

            ans = "Failed to capture " + target;
        } else {
            Pokeball pokeball = new Pokeball();
            pokeball.addPokemontoPokeball((Pokemon) target);

            actor.addItemToInventory(pokeball);

            ans = target + " has been captured by " + actor;

            Location oldPokemonLocation = map.locationOf(target);

            oldPokemonLocation.addItem(new Candy());

            map.removeActor(target);

        }


        return ans;
    }
    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Catch " + target + " at " + direction;
    }
}
