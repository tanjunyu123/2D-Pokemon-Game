package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.SpawnCapable;
import game.Utils;
import game.newItems.Pokefruit;
import game.pokemons.Charmander;
import game.pokemons.Pokemon;
import game.time.TimePerceptionManager;


/**
 * Crater
 *  A ground class of fire element and spawn capable
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by:
 */

public class Crater extends SpawnCapable {

    /**
     * constructor
     */
    public Crater() {
        super('O');
        this.addCapability(Element.FIRE);

    }

    /**
     * An overriding method that runs every turn . Might spawn pokemon and pokefruit of its own type
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Pokefruit pokefruit = spawnPokefruit(Element.FIRE, 25);
        Pokemon pokemon = spawnPokemon(new Charmander(), 10, location, Element.FIRE, 0);
        if (pokefruit != null) {
            location.addItem(pokefruit);
        }
        if (pokemon != null) {
            location.addActor(pokemon);
        }

    }
}


