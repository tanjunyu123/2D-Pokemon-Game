package game.subActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.pokemons.PokemonInEgg;

/**
 * An Action subclass to hatch Pokemon in the Incubator
 * The PokemonEgg will be placed on Incubator
 * Created by: Tan Jun Yu
 * @author Tan Jun Yu
 * Modified by: -
 *
 */
public class HatchPokemonAction extends Action {

    /**
     * The pokemonEgg to be hatched
     */
    private Item pokemonEgg;

    /**
     * location of the Incubator to place the PokemonEgg
     */
    private Location location;

    /**
     * Consturctor
     * @param pokemonEgg pokemonEgg to be placed on the incubator
     * @param location location of incubator
     */
    public HatchPokemonAction(Item pokemonEgg,Location location ){
        this.pokemonEgg = pokemonEgg;
        this.location = location;
    }

    /**
     * Get the pokemon inside the PokemonEgg
     * @return return the pokemon inside the PokemonEgg
     */
    public PokemonInEgg getPokemonInEgg(){
        PokemonInEgg pokemonInEgg = null;

        for (PokemonInEgg pokemon: pokemonEgg.findCapabilitiesByType(PokemonInEgg.class)){
            pokemonInEgg = pokemon;
        }

        return pokemonInEgg;
    }

    /**
     * Execute the HatchPokemonAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string displayed on the console after the PokemonEgg is successfully placed on Incubator
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.removeItemFromInventory(pokemonEgg);
        location.addItem(pokemonEgg);

        return "Pokemon Egg ( " + getPokemonInEgg() + " ) is successfully placed on Incubator";
    }

    /**
     * Console menu
     * @param actor The actor performing the action.
     * @return the string displayed on the console when Player is choosing the action at every turn
     */
    @Override
    public String menuDescription(Actor actor) {

        return "Place Pokemon Egg (" + getPokemonInEgg() + ") on Incubator";
    }
}
