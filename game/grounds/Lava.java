package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.time.TimePerception;

import java.util.List;

/**
 * A ground class of fire element that perceives time
 * Created by: Riordan D. Alfredo
 * @author Riordan D. Alfredo
 * Modified by: Tan Jun Yu
 *
 */
public class Lava extends Ground implements TimePerception,CheckGroundExpandable {

    /**
     * location of the ground
     */
    private Location groundLocation;
    /**
     * Constructor.
     */
    public Lava() {
        super('^');
        this.addCapability(Element.FIRE);
        this.registerInstance();
    }

    /**
     * Method that runs every turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        groundLocation = location;
    }


    /**
     * Lava behaviour at Day Shift
     */
    @Override
    public void dayEffect(GameMap map) {
        int chance_to_expand = 10;
        if (checkChance(chance_to_expand) && groundLocation!= null){
            List<Exit> exits = groundLocation.getExits();
            for (Exit exit: exits){
                if (checkIfGroundIsExpandable(exit.getDestination(),Element.FIRE)){
                    exit.getDestination().setGround(new Lava());
                }
            }
        }

    }

    /**
     * Lava behaviour at Night Shift
     */
    @Override
    public void nightEffect(GameMap map) {
        int chanceToConvertToDirt = 10;
        if((checkChance(chanceToConvertToDirt)&& groundLocation!= null) && !groundLocation.containsAnActor()){
            groundLocation.setGround(new Dirt());
        }

    }
}