package Game;

import GUI.Frame;
import GUI.Style;
import Game.Helpers.Create;
import Game.Pokemon.Trainer;

import java.util.List;

public class Game {
    public static void PokemonGame() {
        NewGame();

        System.exit(0);
    }

    private static void NewGame() {
        List<Trainer> trainers = Create.CreateNewGame();

        Frame.getInstance().setTrainers(trainers.get(0), trainers.get(1));

        Style.CreateStyle();

        Battle.Battle(trainers.get(0), trainers.get(1));
    }
}
