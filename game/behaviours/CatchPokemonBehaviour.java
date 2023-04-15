package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.Status;
import game.pokemons.Pokemon;
import game.subActions.CatchPokemonAction;
import game.subActions.FeedPokemonAction;

/**
 * CatchPokemonBehaviour that can be implemented by actors (eg. Goh)
 * Any actor that has this behaviour will catch the pokemon that is right next to it if the pokemon has 50 affection points towards this actor
 * Created by: Tan Jun Yu
 * @author Tan Jun Yu
 * Modified by: -
 *
 */
public class CatchPokemonBehaviour implements Behaviour {

    /**
     * A method that returns CatchPokemonAction if possible given certain conditions are met
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return a CatchPokemonAction if the affection points towards actor is > 50 . If not, return null
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentActorLocation = map.locationOf(actor);

        for (Exit exit: currentActorLocation.getExits()){
            Location otherActorLocation = exit.getDestination();
            if (otherActorLocation.containsAnActor()){
                Actor actorToCatch = otherActorLocation.getActor();
                AffectionManager affectionManager = AffectionManager.getInstance();
                if (affectionManager.findPokemon(actorToCatch,actor.toString())!= null){
                    int affectionPoint = affectionManager.getAffectionPoint(actorToCatch,actor.toString());
                    if (affectionPoint > 50){
                        return new CatchPokemonAction(actorToCatch,exit.getName());
                    }
                }
            }
        }

        return null;


    }


}
