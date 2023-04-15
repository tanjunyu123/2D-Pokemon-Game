package game.time;

import edu.monash.fit2099.engine.positions.GameMap;
import game.Utils;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Tan Jun Yu
 *
 */
public interface TimePerception {
    /**
     * TODO: override this method, and execute this method inside the relevant manager.
     */
    void dayEffect(GameMap map);

    /**
     * TODO: override this method, and execute this method inside the relevant manager.
     */
    void nightEffect(GameMap map);

    public default boolean checkChance(int chance){
        int random = Utils.generateRandomChance();
        if ( random < chance){
            return true ;
        }
        return false ;
    }

    /**
     * a default interface method that register current instance to the Singleton manager.
     * It allows corresponding class uses to be affected by global reset
     * TODO: Use this method at the constructor of the concrete class that implements it (`this` instance).
     *       For example:
     *       Simple(){
     *          // other stuff for constructors.
     *          this.registerInstance()  // add this instance to the relevant manager.
     *       }
     */
    default void registerInstance(){
        TimePerceptionManager.getInstance().append(this);
    }
}
