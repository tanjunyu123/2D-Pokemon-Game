package game.subActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.pokemons.Pokemon;
import game.subActors.Goh;
import game.subActors.Trainer;

import java.util.List;

/**
 * ViewActorStatsAction
 * View the details of the actor ( inventory, location , the pokemons' AP and location)
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by: -
 */
public class ViewActorStatsAction extends Action {

    /**
     * The actor who the stats will be shown
     */
    private Actor _actor ;


    /**
     * Constructor
     * @param actor the actor
     */
    public ViewActorStatsAction(Actor actor){
        this._actor = actor;

    }

    /**
     * Execute the ViewActorStatsAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the string representing the stats of the actor
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Location actorLocation = map.locationOf(_actor);

        String res = _actor.toString() + " |  " + actorLocation.x() + "," + actorLocation.y() + "  | " ;

        List<Item> actorInventory = _actor.getInventory();

        res += "inventory: [";

        for (int i= 0; i < actorInventory.size(); i++ ) {
            if ( i == actorInventory.size()-1){
                res = res + actorInventory.get(i);
            }else {
                res = res + actorInventory.get(i) + ", ";
            }
        }

        res = res + "]\n";

        AffectionManager affectionManager = AffectionManager.getInstance();

        Trainer trainer = affectionManager.getTrainer(_actor.toString());

        for (Actor pokemon : trainer.getAffectionPointsHashMap().keySet()) {
            if (map.contains(pokemon)) {
                String pokemonStr = pokemon.toString();
                String[] words= pokemonStr.split("\\(");
                res += "- " + words[0] + " with " + affectionManager.getAffectionPoint(pokemon, _actor.toString()) + " AP at " + map.locationOf(pokemon).x() + "," + map.locationOf(pokemon).y() + "\n";
            }
        }



        return res  ;

    }

    /**
     * Menu description shown on the console when choosing an action to perform at every turn
     * @param actor The actor performing the action.
     * @return the string to View Goh's stats
     */
    @Override
    public String menuDescription(Actor actor) {
        return "View Goh's stats";
    }
}
