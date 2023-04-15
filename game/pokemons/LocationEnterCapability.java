package game.pokemons;

//This class is used to check in floor class to differentiate a pokemon and player to prevent entries (AS OF NOW). - TO FORMAT INTO JAVADOC later!

/**
 * Created by: Rachit
 * An enumeration to check if an actor has capability of enter floor or pass wall
 * Used to check in floor class to differentiate a pokemon and player to prevent entries
 * @author Rachit
 * Modified by:
 */
public enum LocationEnterCapability {
    ENTER_FLOOR,

    ENTER_DOOR,
    PASS_WALL;
}
