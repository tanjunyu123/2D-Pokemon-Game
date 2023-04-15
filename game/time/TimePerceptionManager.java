package game.time;

import edu.monash.fit2099.engine.positions.GameMap;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * A global Singleton manager that gives time perception  on the affected instances.
 * TODO: you may modify (add or remove) methods in this class if you think they are not necessary.
 * HINT: refer to Bootcamp Week 5 about static factory method.
 *
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class TimePerceptionManager {

    /**
     * A list of polymorph instances (any classes that implements TimePerception,
     * such as, a Charmander implements TimePerception, it will be stored in here)
     */
    private final List<TimePerception> timePerceptionList;

    private int turn;

    private TimePeriod shift; // DAY or NIGHT

    /**
     * A singleton instance
     */
    private static TimePerceptionManager instance;

    /**
     * Get the singleton instance of time perception manager
     *
     * @return TimePerceptionManager singleton instance
     *
     * FIXME: create a singleton instance.
     */
    public static TimePerceptionManager getInstance() {
        if ( instance == null ){
            instance = new TimePerceptionManager();
        }
        return instance;
    }

    /**
     * Private constructor
     */
    private TimePerceptionManager() {
        timePerceptionList = new ArrayList<>();
        turn = 0;
    }

    /**
     * getter for number of turns
     * @return the current number of turns
     */
    public int getTurn() {
        return turn;
    }

    /**
     * Traversing through all instances in the list and execute them
     * By doing this way, it will avoid using `instanceof` all over the place.
     *
     * FIXME: write a relevant logic (i.e., increment turns choose day or night) and call this method once at every turn.
     */
    public void run(GameMap map) {

        int last_digit = turn % 10;

        if (last_digit < 5){
            shift = TimePeriod.DAY;
        } else {
            shift = TimePeriod.NIGHT;
        }

        for (int i = 0; i < timePerceptionList.size(); i++) {
            if (shift == TimePeriod.DAY) {
                timePerceptionList.get(i).dayEffect(map);
            }
            else {
                timePerceptionList.get(i).nightEffect(map);
            }
        }
        System.out.println("It is a " + shift + "-time (turn " + turn + ")");

        turn = turn + 1;


    }


    /**
     * Add the TimePerception instance to the list
     * FIXME: add objInstance to the list.
     * @param objInstance any instance that implements TimePerception
     */
    public void append(TimePerception objInstance) {
        timePerceptionList.add(objInstance);
    }


    /**
     * Remove a TimePerception instance from the list
     *
     * FIXME: [OPTIONAL] run cleanUp once every turn if you don't want to
     *        have too many instances in the list (e.g., memory leak)
     * @param objInstance object instance
     */
    public void cleanUp(TimePerception objInstance) {
        timePerceptionList.remove(objInstance);
    }
}
