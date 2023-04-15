package game.grounds;

import edu.monash.fit2099.engine.positions.Location;
import game.Element;
/**
 * CheckGroundExpandable
 * An interface to check if a ground can be expanded
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by:
 */
public interface CheckGroundExpandable {


    /**
     * This method checks if the ground to be expanded is expandable
     * @param locationToExpand location to be expanded
     * @param elementOfGroundToExpandFrom element of the ground to expand from
     * @return a boolean value representing if the ground is expandable
     */
    default boolean checkIfGroundIsExpandable(Location locationToExpand, Element elementOfGroundToExpandFrom){
        char displayCharOfWall ='#';
        char displayCharOfFloor = '_';

        if ( locationToExpand.getGround().getDisplayChar() == displayCharOfFloor || locationToExpand.getGround().getDisplayChar() == displayCharOfWall ||
        locationToExpand.getGround().hasCapability(elementOfGroundToExpandFrom)){
            return false;
        }else{
            return true;
        }
    }
}
