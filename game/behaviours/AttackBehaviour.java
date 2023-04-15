package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.*;
import game.subActions.AttackAction;

/**
 * Class implements an attacking behaviour used by the pokemons
 *
 * Created by: Riordan D. Alfredo
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 *
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Override: checks for a pokemon at nearby locations and returns the AttackAction. Method overridden from Behaviour interface
     *
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an AttackAction
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {

        Location currentActorLocation = map.locationOf(actor);

        for (Exit exit: currentActorLocation.getExits()){
            Location otherActorLocation = exit.getDestination();
            if (otherActorLocation.containsAnActor()){
                Actor actorToAttack = otherActorLocation.getActor();

                if (actorToAttack.hasCapability(Status.HOSTILE) && (!(ElementsHelper.hasAnySimilarElements(actor, actorToAttack.findCapabilitiesByType(Element.class))))){

                    return new AttackAction(actorToAttack, exit.getName());
                }
            }
        }

        return null;

    }
}