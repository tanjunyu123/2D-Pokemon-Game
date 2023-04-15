package game.subActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Element;
import game.newItems.Candy;
import game.newItems.Pokefruit;
import game.pokemons.PokemonEgg;
import game.pokemons.PokemonInEgg;
import game.subActors.NurseJoy;

import java.util.List;
/**
 * Trade Action
 *  Manages the Trade action between player and NurseJoy
 * Created by: Tan Jun Yu
 *
 * @author Tan Jun Yu
 * Modified by:
 */


public class TradeAction extends Action {
    /**
     * The NurseJoy to trade items with
     */
    protected NurseJoy nurseJoy;


    /**
     * trade option
     */
    protected TradeOptions tradeOption;

    /**
     * Enumeration for the 4 trade options
     */
    public enum TradeOptions {
        POKEBALL,
        FIRE_POKEFRUIT,
        WATER_POKEFRUIT,
        GRASS_POKEFRUIT,
        CHARMANDEREGG,
        BULBASAUREGG,
        SQUIRTLEEGG
    }

    /**
     * Constructor.
     *
     * @param nurseJoy the Actor to be traded with
     */
    public TradeAction(NurseJoy nurseJoy, String direction, TradeOptions option) {
        this.nurseJoy = nurseJoy;
        this.tradeOption = option;
    }

    /**
     * Check if the inventory of the actor has enough candies to be traded with the NurseJoy
     * @param actor the actor trading with NurseJoy
     * @param item item to be traded with NurseJoy which is candy
     * @param amount amount of candies to be sufficient to trade with NurseJoy
     * @return boolean indicating if the actor has enough amount of certain item
     */
    public boolean checkInventory(Actor actor, Item item, int amount) {
        List<Item> inventory = actor.getInventory();
        boolean valid = false;
        int sum = 0;

        for (int i = 0; i < inventory.size(); i = i + 1) {

            if (item.getDisplayChar() == inventory.get(i).getDisplayChar()) {
                sum = sum + 1;
            }
        }

        if (sum >= amount) {
            valid = true;
        }

        return valid;
    }

    /**
     * Remove the candies from the actor inventory after the trade
     * @param actor actor(player) to remove candies from
     * @param item item(candy) to remove
     * @param amount amount of item to remove
     */
    public void removeItemFromInventoryAmount(Actor actor, Item item, int amount) {

        List<Item> inventory = actor.getInventory();

        int num_of_items_removed = 0;

        while (num_of_items_removed < amount) {

            for (Item value : inventory) {
                if (value.getDisplayChar() == item.getDisplayChar()) {
                    actor.removeItemFromInventory(value);
                    num_of_items_removed = num_of_items_removed + 1;
                    break;
                }
            }

        }


    }

    /**
     * Add pokemon egg to the inventory
     * @param actor the actor which the egg will be added to the inventory
     * @param tradeOption the trade options selected
     * @return the string representing pokemon inside the egg added into the inventory
     */
    public String addPokemonEggToInventory(Actor actor,TradeOptions tradeOption){
        PokemonEgg pokeEgg = null;
        String pokemonString = null;
        if (tradeOption == TradeOptions.CHARMANDEREGG){
            pokeEgg = new PokemonEgg(PokemonInEgg.CHARMANDER);
            pokemonString = "Charmander";
        } else if (this.tradeOption == TradeOptions.SQUIRTLEEGG) {
            pokeEgg = new PokemonEgg(PokemonInEgg.SQUIRTLE);
            pokemonString = "Squirtle";
        } else if (this.tradeOption == TradeOptions.BULBASAUREGG) {
            pokeEgg = new PokemonEgg(PokemonInEgg.BULBASAUR);
            pokemonString = "Bulbasaur";
        }
        actor.addItemToInventory(pokeEgg);
        return pokemonString;
    }

    /**
     * Add pokefruit to the inventory
     * @param actor the actor which the pokefruit will be added to the inventory
     * @param tradeOption the trade options selected
     * @return the string representing the type of pokefruit added into the inventory
     */
    public String addPokefruitToInventory(Actor actor,TradeOptions tradeOption){
        Pokefruit pokefruit = null;
        String pokefruitType = null;
        if (tradeOption == TradeOptions.FIRE_POKEFRUIT) {
            pokefruit = new Pokefruit(Element.FIRE);
            pokefruitType = "Fire";
        } else if (tradeOption == TradeOptions.WATER_POKEFRUIT) {
            pokefruit = new Pokefruit(Element.WATER);
            pokefruitType = "Water";
        } else if (tradeOption == TradeOptions.GRASS_POKEFRUIT) {
            pokefruit = new Pokefruit(Element.GRASS);
            pokefruitType = "Grass";
        }
        actor.addItemToInventory(pokefruit);
        return pokefruitType;
    }

    /**
     * Method that runs if a trade option is selected
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string about the trade happen between the actor and NurseJoy
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Candy candy = new Candy();
        String res = "Insufficient balance";
        if (tradeOption == TradeOptions.CHARMANDEREGG || tradeOption == TradeOptions.SQUIRTLEEGG || tradeOption == TradeOptions.BULBASAUREGG) {
            int amount_of_candies = 5;
            if (checkInventory(actor, candy, amount_of_candies)) {
                String pokemon = addPokemonEggToInventory(actor, tradeOption);
                res = pokemon + " Pokemon Egg added into inventory";
                removeItemFromInventoryAmount(actor, candy, amount_of_candies);
            }
        }else {
            int amount_of_candies = 1;
            if (checkInventory(actor, candy, amount_of_candies)) {
                String pokefruitType = addPokefruitToInventory(actor,tradeOption);
                res = pokefruitType + " Pokefruit added into inventory";
                removeItemFromInventoryAmount(actor, candy, amount_of_candies);
            }
        }
        return res;
    }

    /**
     * Menu description to be printed
     * @param actor The actor performing the action.
     * @return a string of the 4 trade options
     */
    @Override
    public String menuDescription(Actor actor) {
        if (tradeOption == TradeOptions.CHARMANDEREGG) {
            return actor + " trades Charmander Egg with 5 candies";
        }else if (tradeOption == TradeOptions.BULBASAUREGG){
            return actor + " trades Bulbasaur Egg with 5 candies";
        }else if (tradeOption == TradeOptions.SQUIRTLEEGG){
            return actor + " trades Squirtle Egg with 5 candies";
        }else if (tradeOption == TradeOptions.FIRE_POKEFRUIT) {
            return actor + " trades Fire Fruit with 1 candy";
        }else if (tradeOption == TradeOptions.WATER_POKEFRUIT) {
            return actor + " trades Water Fruit with 1 candy";
        }else if (tradeOption == TradeOptions.GRASS_POKEFRUIT) {
            return actor + " trades Grass Fruit with 1 candy";
        }
        return null;
    }
}