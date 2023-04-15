package game.subActions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.newItems.Fire;
import game.pokemons.Pokemon;

/**
 * An Action to attack another Actor.
 * Created by: Rachit Bhatia
 *
 * @author Riordan D. Alfredo
 * Modified by: Rachit Bhatia
 */
public class AttackAction extends Action {

    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     *
     * @param target actor to attack
     * @param direction direction of attack
     */
    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    /**
     * Override: Perform the Action. Method overridden from Action class
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        Pokemon pokemon = (Pokemon) actor;

        if (!pokemon.getInventory().isEmpty()){
            pokemon.toggleWeapon(false);
        }

        pokemon.toggleWeapon(pokemon.checkSurroundingGround(actor, target, map));
        Weapon weapon = actor.getWeapon();

        if (weapon.verb().equals("spins")){
            Location currentActorLocation = map.locationOf(actor);
            Fire.burnNearbyLocations(currentActorLocation);
        }

        if (!(rand.nextInt(100) < weapon.chanceToHit())) {
            pokemon.toggleWeapon(false);
            return actor + " misses " + target + ".";
        }

        int damage = weapon.damage();
        String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
        target.hurt(damage);

        if (!target.isConscious()) {
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : target.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
            result += System.lineSeparator() + target + " is killed.";

        }
        return result;
    }

    /**
     * Override: Returns a descriptive string. Method overridden from Action class
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction;
    }
}
