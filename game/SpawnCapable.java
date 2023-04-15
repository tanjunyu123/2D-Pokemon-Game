package game;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.newItems.Pokefruit;
import game.pokemons.Pokemon;

import java.util.List;


/**
 * SpawnCapable
 * An abstract class being inherited from the Grounds that are capable of spawning objects on the map
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by:
 */
public abstract class SpawnCapable extends Ground{
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public SpawnCapable(char displayChar) {
        super(displayChar);
    }

    /**
     * Check whether the randomly generated int from Utils fall within the chance of spawning
     * @param chance chance of spawning
     * @return boolean indicating if the chance to spawn is achieved
     */
    public boolean checkChanceSpawn(int chance){
        int random = Utils.generateRandomChance();
        if ( random < chance){
            return true ;
        }
        return false ;
    }

    /**
     * Spawn Pokefruit of a certain element type given the chance to spawn is achieved
     * @param type element type of the pokefruit to spawn
     * @param chance chance to spawn
     * @return null if chance is not achieved or pokefruit instance if chance to spawn is achieved
     */
    public Pokefruit spawnPokefruit(Element type, int chance){
        if (checkChanceSpawn(chance)){
            return new Pokefruit(type);
        }
        return null;
    }

    /**
     * Spawn Pokemon of a certain element type given the chance to spawn is achieved
     * @param pokemon pokemon to spawn
     * @param chance chance to achieve in order to spawn
     * @param location location to spawn
     * @param element element type of Pokemon to spawn
     * @param required_similar_grounds the number of similar element grounds in the surroundings of the current location
     * @return null if chance is not achieved or Pokemon instance if chance to spawn is achieved
     */
    public Pokemon spawnPokemon(Pokemon pokemon, int chance, Location location, Element element, int required_similar_grounds){
        int sameElementGround = checkSurroundingGroundType(location, element);
        if (checkChanceSpawn(chance)  && (!location.containsAnActor()) && (sameElementGround >= required_similar_grounds)){
            return pokemon;
        }
        return null;
    }

    /**
     * Check number of the grounds in the surroundings which have the same element type to the Actor
     * @param location current location
     * @param element element type of the Actor
     * @return the number of the grounds in the surroundings which have the same element type to the Actor
     */
    public int checkSurroundingGroundType (Location location, Element element) {
        List<Exit> surroundingGrounds = location.getExits();
        int sameElementGround = 0;

        for (Exit exit : surroundingGrounds) {
            Ground surroundingGround = exit.getDestination().getGround();
            if (surroundingGround.hasCapability(element)) {
                sameElementGround++;
            }
        }
        return sameElementGround;
    }
}