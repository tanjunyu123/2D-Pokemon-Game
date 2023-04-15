package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.ElementsHelper;
import game.Status;
import game.subActions.AttackAction;
import game.subActions.FeedPokemonAction;

import java.util.List;

/**
 * FeedPokemonBehaviour that can be implemented by actors (eg. Goh)
 * Any actor that has this behaviour will feed the pokemon next to it with any pokefruits regardless of the pokefruit's element
 * Created by: Tan Jun Yu
 * @author Tan Jun Yu
 * Modified by: -
 *
 */
public class FeedPokemonBehaviour implements Behaviour{

    /**
     * Return a random pokefruit that will be used to feed the pokemon
     * @param actor Actor in which the inventory will be searched from
     * @return return the first pokefruit found in the inventory of actor
     */
    public Item returnPokefruitToFeed(Actor actor){
        char displayCharOfPokefruit = 'f';

        List<Item> inventory = actor.getInventory();

        for (int i = 0; i < inventory.size(); i = i + 1) {

            if (displayCharOfPokefruit == inventory.get(i).getDisplayChar()) {
                return inventory.get(i);
            }
        }

        return null;

    }


    /**
     * Returns a FeedPokemonAction if possible given certain conditions are met
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return FeedPokemonAction if there is a pokemon next to the actor and a pokefruit is present in the actor's inventory
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location currentActorLocation = map.locationOf(actor);

        for (Exit exit: currentActorLocation.getExits()){
            Location otherActorLocation = exit.getDestination();
            if (otherActorLocation.containsAnActor()){
                Actor actorToFeed = otherActorLocation.getActor();
                if ( actorToFeed.hasCapability(Status.FEEDABLE)){
                    Item pokefruit = returnPokefruitToFeed(actor);
                    if (pokefruit!= null){
                        return new FeedPokemonAction(actorToFeed,exit.getName(),pokefruit);
                    }
                }
            }
        }

        return null;
    }
}
