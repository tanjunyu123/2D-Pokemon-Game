package game.grounds;


import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.pokemons.LocationEnterCapability;
import game.pokemons.Pokemon;

/**
 * A class that represents the Floor ground type
 *
 * Created by: Riordan D. Alfredo
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 *
 */
public class Floor extends Ground {

	/**
	 * Constructor
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Override: Method overridden from Ground class
	 *
	 * Checks if a particular actor can enter floor
	 * @param actor the Actor to check
	 * @return boolean indicating if the actor can enter
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor.hasCapability(LocationEnterCapability.ENTER_FLOOR)){
			return true;
		}
		else {
			return false; }
	}
}