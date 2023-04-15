package game.pokemons;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.CheckSurroundingGround;
import game.grounds.CheckSurroundingGroundToSpawnPokemon;

/**
 * PokemonEgg class that is a subclass to Items
 * PokemonEggs can be hatched in the Incubator
 * Created by: Tan Jun Yu
 * @author Tan Jun Yu
 * Modified by: -
 *
 */
public class PokemonEgg extends Item implements CheckSurroundingGroundToSpawnPokemon {


    /**
     * Number of turns left before the PokemonEgg being hatched
     */
    public int turnsRemainingToHatch;

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public PokemonEgg(PokemonInEgg pokemon) {
        super("PokemonEgg", 'g', true);
        this.addCapability(pokemon);

        if ( pokemon == PokemonInEgg.CHARMANDER){
            turnsRemainingToHatch = 4;
        }else if ( pokemon == PokemonInEgg.BULBASAUR){
            turnsRemainingToHatch = 3;
        }else if ( pokemon == PokemonInEgg.SQUIRTLE){
            turnsRemainingToHatch = 2;
        }
    }

    /**
     * Hatch the PokemonEgg into Pokemon
     * @return return Pokemon hatched
     */
    public Pokemon hatchPokemon(){
        PokemonInEgg pokemonInEgg = null;
        Pokemon pokemonSpawned = null;

        for (PokemonInEgg pokemon: this.findCapabilitiesByType(PokemonInEgg.class)){
            pokemonInEgg = pokemon;
        }

        if ( pokemonInEgg == PokemonInEgg.CHARMANDER){
            pokemonSpawned = new Charmander();
        }else if ( pokemonInEgg == PokemonInEgg.BULBASAUR){
            pokemonSpawned = new Bulbasaur();
        }else if ( pokemonInEgg == PokemonInEgg.SQUIRTLE){
            pokemonSpawned = new Squirtle();
        }

        return pokemonSpawned;
    }

    /**
     * Called at every turn to check if Pokemon should be hatched by checking the number of turns remaining
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        char displayCharOfIncubator = 'X';
        if (currentLocation.getGround().getDisplayChar() == displayCharOfIncubator){
            turnsRemainingToHatch -= 1;

            Pokemon pokemonToSpawn = hatchPokemon();

            if ( turnsRemainingToHatch < 0 && !currentLocation.containsAnActor()){
                currentLocation.removeItem(this);
                currentLocation.addActor(pokemonToSpawn);
            } else if (turnsRemainingToHatch < 0 && currentLocation.containsAnActor()){

                Location locationToSpawn = checkAvailableSurroundingGroundToSpawnPokemon(currentLocation,pokemonToSpawn);

                if (locationToSpawn != null){
                    currentLocation.removeItem(this);
                    locationToSpawn.addActor(pokemonToSpawn);
                }

            }
        }
    }

}
