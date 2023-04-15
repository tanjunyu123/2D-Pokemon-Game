package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.subActions.HatchPokemonAction;

import java.util.List;

/**
 * A ground class use to hatch pokemon eggs
 *
 * Created by: Tan Jun Yu
 * @author Tan Jun Yu
 * Modified by: -
 *
 */
public class Incubator extends Ground {


    /**
     * constructor
     */
    public Incubator(){
        super('X');
    }

    /**
     * Return allowableActions can be performed by the actor standing next to it
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return return HatchPokemonAction if there is any egg inside the inventory of the actor
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        List<Item>  actorInventory = actor.getInventory();
        char displayCharOfEgg = 'g';
        ActionList actions = new ActionList();

        for (int i = 0; i < actorInventory.size() ; i = i+1){
            if (actorInventory.get(i).getDisplayChar() == displayCharOfEgg){
                actions.add(new HatchPokemonAction(actorInventory.get(i),location));
            }
        }
        return actions;
    }
}
