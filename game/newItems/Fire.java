package game.newItems;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.time.TimePerceptionManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Rachit Bhatia
 * A fire type Item which burns the ground and non-fire type pokemons
 *
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public class Fire extends Item {

    /**
     * The loss in HP to non-fire type pokemons. Set as static so that it can be accessed by the class itself and fire objects are only created when added to ground
     */
    private static final int DAMAGE = 10;

    /**
     * the original turn on the game when the Fire object was initialised
     */
    private int originalTurn;

    /**
     * Constructor
     */
    public Fire(){

        super("Fire", 'v', false);
        originalTurn = TimePerceptionManager.getInstance().getTurn();
        this.addCapability(ItemCheck.FIRE);
    }

    /**
     * Static method to access damage by the class itself.
     *
     * @return integer which represents damage
     */
    public static int getDamage(){
        return DAMAGE;
    }

    /**
     * Drops Fire items in all surrounding grounds (8 squares or less depending on the location of the pokemon).
     * Method is static so that a fire object is instantiated only when need to drop a fire
     *
     * @param location the current location of the pokemon while attacking
     */
    public static void burnNearbyLocations(Location location){
        for (Exit exit : location.getExits()){
            Location surrounding = exit.getDestination();
            surrounding.addItem(new Fire());
        }
        System.out.println("Nearby grounds have been burnt!!!");
    }

    /**
     * Ticks each fire item on the map and removes it if it has been on the map for 2 turns
     *
     * @param fireLocation the location of each Fire item on the map
     */
    @Override
    public void tick (Location fireLocation){
        int currentTurn = TimePerceptionManager.getInstance().getTurn();

        if ((currentTurn - originalTurn) >= 2){

            List<Item> locationItems = fireLocation.getItems();

            Item fire = null;

            for (Item item : locationItems){
                if (item.hasCapability(ItemCheck.FIRE)){
                    fire = item; }
            }

            if (fire != null){
                fireLocation.removeItem(fire);
            }

        }
    }


}
