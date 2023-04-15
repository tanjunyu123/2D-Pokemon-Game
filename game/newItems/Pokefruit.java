package game.newItems;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Element;

/**
 * Pokefruit
 *  An item that can be of different elements used to feeding pokemons
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by: Manan
 */
public class Pokefruit extends Item {

    private Element element;

    /**
     * Constructor
     * @param element element that defines the pokefruit
     */
    public Pokefruit(Element element) {
        super("Pokefruit", 'f', true);
        this.addCapability(element);
        this.addCapability(ItemCheck.POKEFRUIT);
        this.element = element;

    }

    /**
     * toString method
     * @return String
     */
    public String toString() {
        String ans = element.toString() + " Pokefruit";
        return ans;
    }

}
