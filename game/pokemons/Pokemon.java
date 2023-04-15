package game.pokemons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.*;
import game.behaviours.*;
import game.grounds.CheckSurroundingGround;
import game.newItems.Fire;
import game.newItems.ItemCheck;
import game.subActions.AttackAction;
import game.subActions.CatchPokemonAction;
import game.subActions.EvolveAction;
import game.subActions.FeedPokemonAction;
import game.subActors.Player;


import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * Created by: Manan
 * Abstract class for all Pokemons
 * @author Manan
 * Modified by: Rachit, Tan Jun Yu, Manan
 */
public abstract class Pokemon extends Actor implements CheckSurroundingGround {
    /**
     * Instance of AffectionManager
     */
    private AffectionManager AP = AffectionManager.getInstance();

    /**
     * TreeMap of a list of behaviours the Pokemon can have, lower priority is prioritised.
     */
    private final Map<Integer, Behaviour> behaviours = new TreeMap<>(); // priority, behaviour

    /**
     * the number of different weapons a pokemon can have
     */
    protected int numOfWeapons;

    /**
     * Random number generator
     */
    protected Random rand = new Random();

    /**
     * pokemon's evolved version
     */
    protected Pokemon evolvedVersion;

    /**
     * treemap key for EvolveBehaviour
     */
    private int evolvePriority;

    /**
     * treemap key for AttackBehaviour
     */
    private int attackPriority;

    /**
     * treemap key for FollowBehaviour
     */
    private int followPriority;

    /**
     * treemap key for WanderBehaviour
     */
    private int wanderPriority;


    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Pokemon(String name, char displayChar, int hitPoints, Pokemon initEvolvedVersion) {
        super(name, displayChar, hitPoints);
        //Register the pokemon
        AffectionManager aff = AffectionManager.getInstance();
        aff.registerPokemon(this);
        this.addCapability(Status.HOSTILE);
        this.addCapability(Status.FEEDABLE);

        //variables have been used to avoid repetition and usage of magic numbers
        evolvePriority = 1;
        attackPriority = 2;
        followPriority = 3;
        wanderPriority = 4;

        this.behaviours.put(evolvePriority, new EvolveBehaviour(initEvolvedVersion));
        this.behaviours.put(attackPriority, new AttackBehaviour());
        this.behaviours.put(wanderPriority, new WanderBehaviour());

        numOfWeapons = 1; //originally set to 1 because most pokemons only have a single special weapon

    }


    /**
     * Select and return an action to perform on the current turn. (Overridden)
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        List<Item> locationItems = map.locationOf(this).getItems();
        //boolean shouldInflictBurn = false;

        for (Item item: locationItems){
            if (item.hasCapability(ItemCheck.FIRE) && (!this.hasCapability(Element.FIRE))){
                System.out.println(this + " has been hurt by fire! (-" + Fire.getDamage() + " HP)");
                this.hurt(Fire.getDamage());

                if (!this.isConscious()){
                    map.removeActor(this);
                    System.out.println(this + " has died from fire damage!");
                }
            }
        }

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();

    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that is performing the action
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();

        //checking if otherActor is Player by checking status
        if (!(otherActor.hasCapability(Status.TRAINER))) {
            actions.add(new AttackAction(this, direction));
        }

        //Feeding action:
        if (!otherActor.getInventory().isEmpty()) {
            for (Item item : otherActor.getInventory()) {
                if (item.hasCapability(ItemCheck.POKEFRUIT)) {
                    actions.add(new FeedPokemonAction(this, direction, item));
                }
            }
        }

        int requiredEvolveAP = 100;
        if ((AffectionManager.getInstance().getAffectionPoint(this, Player.getInstance().toString()) == requiredEvolveAP) && (evolvedVersion != null)){
            actions.add(new EvolveAction(this, this.evolvedVersion, direction));
        }


        //Catching Action;
        if (!this.hasCapability(Status.NOT_CATCHABLE)) {

            if (AP.getAffectionPoint(this, Player.getInstance().toString()) <= -50) {
                this.addCapability(Status.NOT_CATCHABLE);
            }
            else {
                //key 11 is for FollowBehaviour - confirm later if its correct.
                if (AP.getAffectionPoint(this, Player.getInstance().toString()) > -50 && AP.getAffectionPoint(this, Player.getInstance().toString()) < 75 && this.behaviours.containsKey(2)) {
                    this.behaviours.remove(followPriority);
                } else if (AP.getAffectionPoint(this, Player.getInstance().toString()) >= 50 && AP.getAffectionPoint(this, Player.getInstance().toString()) < 75) {
                    this.addCapability(Status.CATCHABLE);
                } else if (AP.getAffectionPoint(this, Player.getInstance().toString()) >= 75) {
                    this.behaviours.put(followPriority, new FollowBehaviour(AP.getTrainer(Player.getInstance().toString())));
                }

                actions.add(new CatchPokemonAction(this, direction));
            }

        }

        return actions;
    }

    /**
     * adds or removes respective pokemon's special weapon into its inventorys
     *
     * @param isEquipping boolean value
     */
    public void toggleWeapon(boolean isEquipping){}


    /**
     * getter for the number of different weapons each pokemon can have
     *
     * @return number of distinct weapons a pokemon has
     */
    public int getNumOfWeapons() {
        return numOfWeapons;
    }


    /**
     * toString method for all Pokemons
     * @return a String in the form of PokemonName (AP: "affection points")
     */
    @Override
    public String toString() {
        return name + printHp() + "(AP :" + AffectionManager.getInstance().getAffectionPoint(this, Player.getInstance().toString()) + ")";
    }

    /**
     * Creates a new EvolveBehaviour object.
     * Used inside EvolveAction to reset the originalTurn variable inside EvolveBehaviour, so that evolved pokemons only evolve again after the set number of turns.
     */
    public void prepareEvolveBehaviour(){
        behaviours.put(evolvePriority, new EvolveBehaviour(this.evolvedVersion));
    }

}
