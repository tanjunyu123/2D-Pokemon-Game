package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.SpawnCapable;
import game.newItems.Candy;
import game.newItems.Pokefruit;
import game.pokemons.Bulbasaur;
import game.pokemons.Pokemon;
import game.time.TimePerception;

import java.util.List;

/**
 * Tree
 *  A ground class of grass element and spawn capable that perceives time
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by:
 */

public class Tree extends SpawnCapable implements TimePerception,CheckGroundExpandable {

    /**
     * location of location
     */
    private Location groundLocation;

    /**
     * Constructor.
     *
     */
    public Tree() {
        super('T');
        this.addCapability(Element.GRASS);
    }


    /**
     * Method that runs at every turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){

        Pokefruit pokefruit = spawnPokefruit(Element.GRASS,15);
        Pokemon pokemon = spawnPokemon(new Bulbasaur(),15, location, Element.GRASS, 1);
        if (pokefruit != null){
            location.addItem(pokefruit);
        }
        if (pokemon != null){
            location.addActor(pokemon);
        }

    }

    /**
     * Tree behaviour at Day Shift
     */
    @Override
    public void dayEffect(GameMap map) {
        int chanceToDropCandy = 5;
        if (checkChance(chanceToDropCandy)  && groundLocation!= null){
            Candy candy = new Candy();
            groundLocation.addItem(candy);
        }

    }

    /**
     * Tree behaviour at Night Shift
     */
    @Override
    public void nightEffect(GameMap map) {
        int chanceToExpand = 10;
        if (checkChance(chanceToExpand) && groundLocation!= null){
            List<Exit> exits = groundLocation.getExits();

            int chanceHayOrTree = 50;
            if (checkChance(chanceHayOrTree)){
                for (Exit exit: exits){
                    if (checkIfGroundIsExpandable(exit.getDestination(),Element.GRASS)){
                        exit.getDestination().setGround(new Tree());
                    }
                }
            } else {
                for (Exit exit: exits){
                    if (checkIfGroundIsExpandable(exit.getDestination(),Element.GRASS)){
                        exit.getDestination().setGround(new Hay());
                    }
                }
            }
        }

    }
}