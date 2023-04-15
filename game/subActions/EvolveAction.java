package game.subActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.AffectionManager;
import game.pokemons.Pokemon;
import game.subActors.Player;

/**
 * Created by: Rachit Bhatia
 * Defines the checking required for pokemon evolution and returns the EvolveAction if checks are passed
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public class EvolveAction extends Action {

    /**
     * the evolved version of the pokemon
     */
     private Actor evolvedPokemon;

     /**
     * the current pokemon to be evolved
     */
    private Actor currentPokemon;

    /**
     * the direction of the current pokemon
     */
    private String direction;

    /**
     * Constructor
     *
     * @param currentPokemon - pokemon to be evolved
     * @param evolvedPokemon - evolved version of the current pokemon
     * @param direction - direction of the current pokemon
     */
    public EvolveAction(Actor currentPokemon, Pokemon evolvedPokemon, String direction){
        this.currentPokemon = currentPokemon;
        evolvedPokemon.prepareEvolveBehaviour();
        this.evolvedPokemon = evolvedPokemon;
        this.direction = direction;
    }

    /**
     * Performs the action to evolve the current pokemon, by removing the current pokemon and adding the evolved pokemon.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return string printed to display successful evolution status
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Location currentPokemonLocation = map.locationOf(currentPokemon);

        for (Item item: currentPokemon.getInventory()){
            evolvedPokemon.addItemToInventory(item);
        }

        AffectionManager affectionManager = AffectionManager.getInstance();
        affectionManager.increaseAffection(evolvedPokemon, 50, Player.getInstance().toString());

        map.removeActor(currentPokemon);
        map.addActor(evolvedPokemon, currentPokemonLocation);

        String returnMessage = "Yay! " + currentPokemon + " evolved into " + evolvedPokemon;
        return returnMessage;
    }

    /**
     * Returns the string displayed in the menu for the player to evolve a pokemon
     *
     * @param actor The actor performing the action.
     * @return string printed in the menu for the player to evolve a pokemon
     */
    @Override
    public String menuDescription(Actor actor) {
        String[] pokemonName = evolvedPokemon.toString().split("\\(");

        String message = "Evolve " + currentPokemon + " at " + direction + " into " + pokemonName[0];
        return message;
    }



}
