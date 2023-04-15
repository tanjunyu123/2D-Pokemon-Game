package game;


/**
 * Element enumeration
 * <p>
 * Created by:Riordan D. Alfredo
 *
 * @author Riordan D. Alfredo
 * Modified by: Rachit
 */

public enum Element {
    WATER("Water"),
    FIRE("Fire"),
    GRASS("Grass"),
    DRAGON("Dragon");

    /**
     * label name
     */
    private final String label;

    /**
     * Constructor
     * @param label
     */
    Element(String label){
        this.label = label;
    }

    /**
     *
     * @return the label text
     */
    @Override
    public String toString() {
        return label;
    }
}
