package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

/**
 * An interface to check if the surrounding ground can be used to spawn Pokemon
 *
 * Created by: Tan Jun Yu
 * @author Tan Jun Yu
 * Modified by: -
 *
 */

public interface CheckSurroundingGroundToSpawnPokemon {

    /**
     * Return the first available surrounding ground that can be used to spawn the Pokemon
     * @param location current location
     * @param actor the pokemon to be spawned
     * @return return the location to spawn Pokemon if any. If not , return null
     */
    default Location checkAvailableSurroundingGroundToSpawnPokemon(Location location, Actor actor){
        List<Exit> spawningLoc = location.getExits();

        for (int i = 0; i < spawningLoc.size(); i++) {

            if (spawningLoc.get(i).getDestination().canActorEnter(actor)){
                return spawningLoc.get(i).getDestination();
            }
        }

        return null;


    }
}
