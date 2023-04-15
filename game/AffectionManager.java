package game;

import edu.monash.fit2099.engine.actors.Actor;
import game.pokemons.Charmander;
import game.pokemons.Pokemon;
import game.subActors.Trainer;

import java.util.*;

/**
 * Affection Manager
 * <p>
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by: Manan
 */
public class AffectionManager {

    /**
     * Singleton instance (the one and only for a whole game).
     */
    private static AffectionManager instance;
    /**
     * HINT: is it just for a Charmander?
     */

//    private final Map<Actor, Integer> affectionPoints;


    /**
     * We assume there's only one trainer in this manager.
     * Think about how will you extend it.
     */
    private List<Trainer> trainers;

    /**
     * private singleton constructor
     */
    private AffectionManager() {
//        this.affectionPoints = new HashMap<>();
        trainers = new ArrayList<>();
    }

    /**
     * Access single instance publicly
     *
     * @return this instance
     */
    public static AffectionManager getInstance() {
        if (instance == null) {
            instance = new AffectionManager();
        }
        return instance;
    }

    /**
     * Add a trainer to this class's attribute. Assume there's only one trainer at a time.
     *
     * @param trainer the actor instance
     */
    public void registerTrainer(Trainer trainer) {
        this.trainers.add(trainer);
    }

    /**
     * Add Pokemon to the collection. By default, it has 0 affection point. Ideally, you'll register all instantiated Pokemon
     *
     * @param pokemon
     */

    public void registerPokemon(Actor pokemon) {
//        affectionPoints.put(pokemon, 0);

        for ( Trainer trainer : trainers){
            trainer.getAffectionPointsHashMap().put(pokemon,0);
        }
    }

    /**
     * Get the affection point by using the pokemon instance as the key.
     *
     * @param pokemon Pokemon instance
     * @return integer of affection point.
     */

    public int getAffectionPoint(Actor pokemon,String trainerName) {

        int affPoint = 0;

        for (Trainer _trainer : trainers){
            if(Objects.equals(_trainer.toString(), trainerName)){
                affPoint = _trainer.getAffectionPointsHashMap().get(pokemon);
            }
        }

        return affPoint;

    }


    public Trainer getTrainer(String trainerName){
        Trainer trainer = null;
        for (Trainer _trainer : trainers){
            if(Objects.equals(_trainer.toString(), trainerName)){
                trainer = _trainer;
            }
        }

        return trainer;
    }
    /**
     * Useful method to search a pokemon by using Actor instance.
     *
     * @param actor general actor instance
     * @return the Pokemon instance.
     */
    public Actor findPokemon(Actor actor,String trainerName) {

        Trainer trainer = getTrainer(trainerName);

        for (Actor pokemon : trainer.getAffectionPointsHashMap().keySet()) {
            if (pokemon.equals(actor)) {
                return pokemon;
            }
        }
        return null;
    }

    /**
     * Increase the affection. Work on both cases when there's a Pokemon,
     * or when it doesn't exist in the collection.
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier
     * @return custom message to be printed by Display instance later.
     */
    public String increaseAffection(Actor actor, int point,String trainerName) {

        Trainer trainer = getTrainer(trainerName);

        Integer affP = trainer.getAffectionPointsHashMap().get(actor);

        int newAP = affP + point;
        if (newAP >= 100) {
            newAP = 100;
        }

        trainer.getAffectionPointsHashMap().replace(actor, newAP);

        return ("" + newAP);
    }

    /**
     * Decrease the affection level of the . Work on both cases when it is
     *
     * @param actor Actor instance, but we expect a Pokemon here.
     * @param point positive affection modifier (to be subtracted later)
     * @return custom message to be printed by Display instance later.
     */
    public String decreaseAffection(Actor actor, int point,String trainerName) {

        Trainer trainer = getTrainer(trainerName);

        Integer AP = trainer.getAffectionPointsHashMap().get(actor);
        Integer newAP = AP - point;

        trainer.getAffectionPointsHashMap().replace(actor, newAP);

        return ("" + newAP);
    }

}
