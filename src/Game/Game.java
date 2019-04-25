package Game;

import GUI.Frame;
import Game.Helpers.Create;
import Game.Pokemon.Trainer;

import java.awt.event.WindowEvent;
import java.util.List;

public class Game {
    public static void PokemonGame() {
        NewGame();
    }

    private static void NewGame() {

        List<Trainer> trainers = Create.CreateNewGame();

        Frame.getInstance().setTrainer(trainers.get(0), trainers.get(1));

        Battle.Battle(trainers.get(0), trainers.get(1));
    }
}
