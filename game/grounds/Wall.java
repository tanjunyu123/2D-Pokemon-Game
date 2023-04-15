package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.pokemons.LocationEnterCapability;

/**
 * A class which represents the Wall ground type
 * Created by: Riordan D. Alfredo
 *
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public class Wall extends Ground {

	/**
	 * Constructor
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Override: Method overridden from Ground class
	 * checks if the actor can enter the Wall location.
	 *
	 * @param actor the Actor to check
	 * @return boolean true or false
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		if (actor.hasCapability(LocationEnterCapability.PASS_WALL)){
			return true;
		}
		else {
			return false; }
	}

	/**
	 * returns true if the terrain type can block thrown objects
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}