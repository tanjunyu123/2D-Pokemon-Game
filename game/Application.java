package game;

import java.util.Arrays;
import java.util.List;

import game.newItems.Candy;
import game.pokemons.Charizard;
import game.pokemons.Charmander;
import game.pokemons.Squirtle;
import game.subActions.TeleportAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.grounds.*;
import game.subActors.Goh;
import game.subActors.NurseJoy;
import game.subActors.Player;

/**
 * The main class to start the game.
 * Created by:
 *
 * @author Riordan D. Alfredo
 * Modified by:
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Hay(),new Waterfall(),new Crater(),new Dirt(), new Wall(),
                new Floor(), new Tree(),
                new Lava(), new Puddle(),new Incubator());

        List<String> map = Arrays.asList(
                ".............................................^^^^^^^^^^^^^^",
                "............T..................................T...^^^^O^^^",
                ".....................................................^^^^^^",
                ".................X......................................^^^",
                "..............................###........................^^",
                "..............................#.#............T............^",
                ".....................T........#.#..........................",
                "...T.......~..................___..........................",
                "...~~~~~~~~...................................,,,,,,,,.....",
                "....~~~~~..............................,,,,,,,T,,,,,,,,....",
                "~~W~~~~....................................,,,,,,..........",
                "~~~~~~..T.............................T.................###",
                "~~~~~~~~~...............................................#.^");

        List<String> pokemonCenterMap = Arrays.asList(
                "#############",
                "#___________#",
                "#___.._..___#",
                "#___________#",
                "#___________#",
                "#####___#####"
        );


        GameMap gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        GameMap pokemonCenter = new GameMap(groundFactory, pokemonCenterMap);
        world.addGameMap(pokemonCenter);

//        //Add player - Ash

        world.addPlayer(Goh.getInstance(), gameMap.at(12, 2) );
        world.addPlayer(Player.getInstance(), gameMap.at(33,7));


        //Entry door to PokemonCenter
        Door entryDoor = new Door('=');
        entryDoor.addAction(new TeleportAction(pokemonCenter.at(6,5), "Pokemon Center!"));
        gameMap.at(31,5).setGround(entryDoor);

        //Exit door to Pallet
        Door exitDoor = new Door('=');
        exitDoor.addAction(new TeleportAction(gameMap.at(31,5), "Pallet Town!"));
        pokemonCenter.at(6, 5).setGround(exitDoor);

        //Add nursejoy
        Actor nurseJoy = new NurseJoy();
        pokemonCenter.at(6, 2).addActor(nurseJoy);



        world.run();

    }
}




