package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.pokemons.Charizard;

import java.util.List;

/**
 * Interface that contains method for checking the ground's type at the location of the actor
 * Created by: Rachit Bhatia
 *
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public interface CheckSurroundingGround {


    /**
     * Checks for the actor's location's ground element type and matches with the actor's element type
     *
     * @param actor the current pokemon
     * @param targetActor the target pokemon to attack (used by Squirtle class)
     * @param map the current gamemap
     * @return a boolean value indicating if actor should equip a special weapon
     */
    default boolean checkSurroundingGround(Actor actor, Actor targetActor, GameMap map){
        boolean shouldEquipWeapon = false;

        Element actorElement = null;

        for (Element element: actor.findCapabilitiesByType(Element.class)){
            actorElement = element;
            if (actorElement == Element.FIRE || actorElement == Element.WATER || actorElement == Element.GRASS){
                break;
            }
        }

        Location actorLocation =  map.locationOf(actor);
        if (actorLocation.getGround().hasCapability(actorElement)){
            shouldEquipWeapon = true;
        }
        return shouldEquipWeapon;
    }

}
