package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.SpawnCapable;
import game.Utils;
import game.newItems.Pokefruit;
import game.pokemons.Charmander;
import game.pokemons.Pokemon;
import game.pokemons.Squirtle;
/**
 * Waterfall
 *  A ground class of water element and spawn capable
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by:
 */
public class Waterfall extends SpawnCapable{
    /**
     * Constructor
     */
    public Waterfall() {
        super('W');
        this.addCapability(Element.WATER);
    }


    /**
     * Method that runs at every turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        Pokefruit pokefruit = spawnPokefruit(Element.WATER,20);
        Pokemon pokemon = spawnPokemon(new Squirtle(),20, location, Element.WATER, 2);
        if (pokefruit != null){
            location.addItem(pokefruit);
        }
        if (pokemon != null){
            location.addActor(pokemon);
        }

    }
}