package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;

/**
 * PickUpBehaviour that can be implemented by actors (eg. Goh)
 * Any actor that has this behaviour will pick up the item on the ground if any
 * Created by: Tan Jun Yu
 * @author Tan Jun Yu
 * Modified by: -
 *
 */
public class PickUpBehaviour implements Behaviour{


    /**
     * If there is an item on the same ground the actor is standing on, return the pickUpItemAction
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return return pickUpItemAction
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location currentActorLocation = map.locationOf(actor);

        List<Item> items = currentActorLocation.getItems();

        if  (items.size() != 0 ){
            return items.get(0).getPickUpAction(actor);
        }

        return null;


    }
}
