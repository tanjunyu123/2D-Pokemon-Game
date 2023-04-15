package game.pokemons;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.allWeapons.WeaponsManager;
import game.newItems.ItemCheck;

/**
 * Created by: Rachit Bhatia
 * A pokemon of fire type
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public class Charmeleon extends Pokemon{
    /**
     * ItemCheck enum instance to determine the type of item
     */
    private ItemCheck check;
    /**
     * Constructor.
     */
    public Charmeleon() {
        super("Charmeleon", 'C', 150, new Charizard());
        // HINT: add more relevant behaviours here
        this.addCapability(Element.FIRE);
        this.addCapability(Status.NOT_CATCHABLE);
        evolvedVersion = new Charizard();
        this.numOfWeapons = 2;
    }

    /**
     * adds or removes respective pokemon's special weapon into its inventory
     * Overriden method from pokemon class
     *
     * @param isEquipping boolean value
     */
    @Override
    public void toggleWeapon(boolean isEquipping) {
        WeaponsManager weaponsManager = WeaponsManager.getInstance();


        if (isEquipping){
            int random = rand.nextInt(this.getNumOfWeapons());

            if (random == 0) {
            addItemToInventory(weaponsManager.getSpecialWeaponsStorage().get('>'));
            System.out.println(weaponsManager.getSpecialWeaponsStorage().get('>') + " weapon added to " + this + "'s inventory");
            }

            else {
                addItemToInventory(weaponsManager.getSpecialWeaponsStorage().get('∑'));
                System.out.println(weaponsManager.getSpecialWeaponsStorage().get('∑') + " weapon added to " + this + "'s inventory");
            }
        }
        else{
            if (!this.getInventory().isEmpty()) {

                int lastElementIndex = this.getInventory().size() - 1;    //retrieving the index of the last item in the pokemon's inventory
                Item weaponToRemove = this.getInventory().get(lastElementIndex);    //getting the latest weapon added into the pokemon's inventory

                removeItemFromInventory(weaponToRemove);

                if (weaponToRemove.getDisplayChar() == '>'){
                    System.out.println(weaponsManager.getSpecialWeaponsStorage().get('>') + " weapon removed from " + this + " inventory");
                }

                else if (weaponToRemove.getDisplayChar() == '∑'){
                    System.out.println(weaponsManager.getSpecialWeaponsStorage().get('∑') + " weapon removed from " + this + " inventory");
                }
            }
        }
    }

    /**
     * Creates and returns an intrinsic weapon. (Overriden)
     *
     * Pokemon has a scratch attack as its intrinsic attack
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(10,"scratch");
    }
}
