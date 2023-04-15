package game.newItems;

import edu.monash.fit2099.engine.items.Item;
import game.pokemons.Pokemon;
import game.newItems.ItemCheck;
/**
 * Pokeball
 *  An item that is unlimited and be used to capture pokemons
 * Created by: Manan
 *
 * @author Manan
 * Modified by: Tan Jun Yu
 */
public class Pokeball extends Item{
    /**
     * Pokemon instance that can be captured inside the pokeball
     */
    private Pokemon pokemon;

    /**
     * Constructor
     */
    public Pokeball(){
        super("Pokeball", '0', true);
        this.addCapability(ItemCheck.POKEBALL);
    }

    /**
     * Method to add pokemon instance into the pokeball
     * @param pokemon pokemon to be added into the pokeball
     */
    public void addPokemontoPokeball(Pokemon pokemon){
        this.pokemon = pokemon;
    }

    /**
     * Getter to get pokemon instance that has been captured inside the pokeball
     * @return the pokemon instance
     */
    public Pokemon get_pokemon(){
        return this.pokemon;
    }

    /**
     * Remove the pokemon instance from the pokeball
     */
    public void removePokemon(){
        this.pokemon = null;
    }

}
