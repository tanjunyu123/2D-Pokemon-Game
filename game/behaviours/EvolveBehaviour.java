package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.pokemons.Pokemon;
import game.subActions.EvolveAction;
import game.time.TimePerceptionManager;

/**
 * Created by: Rachit Bhatia
 * Defines the checking required for pokemon evolution and returns the EvolveAction if checks are passed
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public class EvolveBehaviour implements Behaviour{

    /**
     * the evolved version of a pokemon
     */
    private Pokemon evolvedPokemon;

    /**
     * the turn of the game when EvolveBehaviour is initialised
     */
    public int originalTurn;

    /**
     * Constructor
     * @param evolvedPokemon - evolved version of a pokemon
     */
    public EvolveBehaviour(Pokemon evolvedPokemon){
        this.evolvedPokemon = evolvedPokemon;
        originalTurn = TimePerceptionManager.getInstance().getTurn();
    }

    /**
     * Performs checks for the surrounding of the current pokemon and also checks how many turns have passed since the behaviour was initialised.
     *
     * @param actor the Pokemon to be evolved
     * @param map the GameMap containing the Actor
     * @return EvolveAction if checks are passed, null otherwise
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {


        int currentTurn = TimePerceptionManager.getInstance().getTurn(); //updated at every turn since behaviour.getAction is called at every turn
        if (currentTurn - originalTurn >= 20){

            int actorCount = 0;
            Location currentPokemonLocation = map.locationOf(actor);

            //checking if there is an actor in the surrounding of the current pokemon
            for (Exit exit : currentPokemonLocation.getExits()){
                Location surrounding = exit.getDestination();

                if (surrounding.containsAnActor()){
                    actorCount++;
                }
            }

            if (actorCount == 0 && evolvedPokemon != null){
                System.out.println(originalTurn);
                return new EvolveAction(actor, evolvedPokemon, null);
            }
        }
        return null;
    }
}
