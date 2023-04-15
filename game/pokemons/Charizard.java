package game.pokemons;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Element;
import game.Status;
import game.allWeapons.WeaponsManager;
import game.newItems.ItemCheck;

/**
 * Created by: Rachit Bhatia
 * A pokemon of fire type and dragon type
 * @author Rachit Bhatia
 * Modified by: Rachit Bhatia
 */
public class Charizard extends Pokemon{
    /**
     * ItemCheck enum instance to determine the type of item
     */
    private ItemCheck check;
    /**
     * Constructor.
     */
    public Charizard() {
        super("Charizard", 'Z', 250, null);
        // HINT: add more relevant behaviours here
        this.addCapability(Element.FIRE);
        this.addCapability(Element.DRAGON);
        this.addCapability(Status.NOT_CATCHABLE);
        this.numOfWeapons = 3;
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
                System.out.println(weaponsManager.getSpecialWeaponsStorage().get('>') + " weapon added to " + this + " inventory");
            }

            else if (random == 1){
                addItemToInventory(weaponsManager.getSpecialWeaponsStorage().get('∑'));
                System.out.println(weaponsManager.getSpecialWeaponsStorage().get('∑') + " weapon added to " + this + " inventory");
            }

            else {
                addItemToInventory(weaponsManager.getSpecialWeaponsStorage().get('ø'));
                System.out.println(weaponsManager.getSpecialWeaponsStorage().get('ø') + " weapon added to " + this + " inventory");
            }
        }

        else{
            if (!this.getInventory().isEmpty()) {
                int lastElementIndex = this.getInventory().size() - 1;    //retrieving the index of the last item in the pokemon's inventory
                Item weaponToRemove = this.getInventory().get(lastElementIndex);    //getting the latest weapon added into the pokemon's inventory

                removeItemFromInventory(weaponToRemove);

                if (weaponToRemove.getDisplayChar() == '>'){
                    System.out.println(weaponsManager.getSpecialWeaponsStorage().get('>') + " weapon removed from " + this + "'s inventory");
                }

                else if (weaponToRemove.getDisplayChar() == '∑'){
                    System.out.println(weaponsManager.getSpecialWeaponsStorage().get('∑') + " weapon removed from " + this + "'s inventory");
                }

                else if (weaponToRemove.getDisplayChar() == 'ø'){
                    System.out.println(weaponsManager.getSpecialWeaponsStorage().get('ø') + " weapon removed from " + this + "'s inventory");
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
