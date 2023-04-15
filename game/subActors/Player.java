package game.subActors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.AffectionManager;
import game.Status;
import game.newItems.ItemCheck;
import game.newItems.Pokeball;
import game.pokemons.LocationEnterCapability;
import game.subActions.SummonPokemonAction;
import game.subActions.ViewActorStatsAction;
import game.time.TimePerceptionManager;

/**
 * Class representing the Player.
 * <p>
 * Created by:Riordan D. Alfredo
 *
 * @author Riordan D. Alfredo
 * Modified by: Manan
 */
public class Player extends Trainer {

    /**
     * Instance of menu
     */
    private final Menu menu = new Menu();
    /**
     * Instance of ItemCheck
     */
    private ItemCheck check;

    private static Player instance;

    /**
     * Constructor.
     */

    private Player() {
        super("Ash", '@', 1);
        this.addCapability(Status.IMMUNE);
        this.addCapability(LocationEnterCapability.ENTER_FLOOR);
        this.addCapability(LocationEnterCapability.ENTER_DOOR);

        //Register trainer
        AffectionManager aff = AffectionManager.getInstance();
        aff.registerTrainer(this);
    }


    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        String res = "inventory: [";

        for (int i= 0; i < this.getInventory().size(); i++ ) {
            if ( i == this.getInventory().size()-1){
                res = res + this.getInventory().get(i);
            }else {
                res = res + this.getInventory().get(i) + ", ";
            }
        }

        res = res + "]";
        System.out.println(res);

        TimePerceptionManager.getInstance().run(map);

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();


        //Summon action
        for (Item item : this.getInventory()) {
            if (item.hasCapability(check.POKEBALL)) {
                actions.add(new SummonPokemonAction((Pokeball) item, map.locationOf(this)));
            }
        }

        actions.add(new ViewActorStatsAction(Goh.getInstance()));

        return menu.showMenu(this, actions, display);
    }

    /**
     * Gets the display character of the PLayer
     * @return display character
     */
    @Override
    public char getDisplayChar() {
        return super.getDisplayChar();
    }
}
