package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;
import game.time.TimePerception;

import java.util.List;

/**
 * Puddle
 *  A ground class of water element that perceives time
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by:
 */
public class Puddle extends Ground implements TimePerception,CheckGroundExpandable {

    /**
     * location of ground
     */
    private Location groundLocation;
    /**
     * Constructor.
     *
     */
    public Puddle() {
        super('~');
        this.addCapability(Element.WATER);
        this.registerInstance();
    }

    /**
     * Method that runs at every turn
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location){
        groundLocation = location;
    }


    /**
     * Puddle behaviour at Day Shift
     */
    @Override
    public void dayEffect(GameMap map) {
        int chanceToConvertToDirt = 10;
        if (checkChance(chanceToConvertToDirt)&& groundLocation!= null){
            groundLocation.setGround(new Dirt());
        }
    }

    /**
     * Puddle behaviour at Night Shift
     */
    @Override
    public void nightEffect(GameMap map) {
        int chanceToExpand = 10;
        if (checkChance(chanceToExpand)&& groundLocation!= null){
            List<Exit> exits = groundLocation.getExits();

            for (Exit exit: exits){
                if (checkIfGroundIsExpandable(exit.getDestination(),Element.WATER)){
                    exit.getDestination().setGround(new Puddle());
                }
            }
        }

    }
}