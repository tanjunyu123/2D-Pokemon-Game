package game.subActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.CheckSurroundingGroundToSpawnPokemon;
import game.newItems.Pokeball;

import java.util.List;

/**
 * This class implements the Summoning action of Pokemons.
 * @author Manan
 */
public class SummonPokemonAction extends Action implements CheckSurroundingGroundToSpawnPokemon {

    /**
     * The pokeball which has the pokemon stored inside.
     */
    private Pokeball pokeball;

    /**
     * The location of the player, which is used to determine where the pokemon gets spawned, i.e, next to it
     */
    private Location location;

    /**
     * Constructor
     * @param pokeball
     * @param location
     */
    public SummonPokemonAction(Pokeball pokeball, Location location) {
        this.pokeball = pokeball;
        this.location = location;
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

        Location locationToSpawn = checkAvailableSurroundingGroundToSpawnPokemon(location, pokeball.get_pokemon());

        if (locationToSpawn!=null){
            map.addActor(pokeball.get_pokemon(),locationToSpawn);
            ans = "I choose you... " + pokeball.get_pokemon() + "!";
            pokeball.removePokemon();
            actor.removeItemFromInventory(pokeball);
            return ans;
        }

        ans = "No available location to spawn!";

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
        return "Summon " + pokeball.get_pokemon();
    }
}
