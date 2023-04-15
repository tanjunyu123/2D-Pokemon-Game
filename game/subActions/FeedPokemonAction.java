package game.subActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.AffectionManager;
import game.Element;
import game.ElementsHelper;
import game.pokemons.Pokemon;

/**
 * This class implements the Feeding action for Pokemons
 * @author Manan
 */
public class FeedPokemonAction extends Action {

    /**
     * Direction of the target
     */
    private String direction;

    /**
     * The target to be fed
     */
    private Actor target;


    /**
     * The pokefruit that will be fed to the target
     */
    private Item pokefruit;

    /**
     * Instance of singleton class AffectionManager
     */
    private AffectionManager aff = AffectionManager.getInstance();

    /**
     * Constructor.
     * @param target
     * @param direction
     * @param pokefruit
     */
    public FeedPokemonAction(Actor target, String direction, Item pokefruit) {
        this.target = target;
        this.direction = direction;
        this.pokefruit = pokefruit;
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
        String result = "";

        if (ElementsHelper.hasAnySimilarElements(pokefruit, target.findCapabilitiesByType(Element.class))) {
            aff.increaseAffection(target, 20,actor.toString());
            result = actor + " gives a " + this.pokefruit + " to a " + target  ;
        } else {
            aff.decreaseAffection(target, 10,actor.toString());
            result = target + " dislikes it! -10 affection points. ";
        }

        actor.removeItemFromInventory(pokefruit);

        return result;
    }

    /**
     * Returns a descriptive string
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Feed " + target + " " + pokefruit + " at " + direction;
    }
}
