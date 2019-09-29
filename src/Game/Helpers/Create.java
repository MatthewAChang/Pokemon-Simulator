package Game.Helpers;

import Game.Helpers.Enum.PokemonEnum;
import GUI.Prompt;
import Game.Pokemon.Move;
import Game.Pokemon.Pokemon;
import Game.Pokemon.Status;
import Game.Pokemon.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Create implements PokemonData {
    public static List<Trainer> CreateNewGame() {
        Object[] battleTypeOptions = { "Quick Battle", "Custom Battle"};
        int choice;
        do {
            choice = Prompt.ButtonPrompt("Would you like to have a quick battle or a custom battle?", "Battle", battleTypeOptions);
        } while (choice == -1);

        boolean quickBattle = choice == 0;

        boolean random;
        int pokemonToBattle;

        if (quickBattle) {
            pokemonToBattle = 6;
            random = true;
        }
        else {
            Object[] numPokemonOptions = { "2", "4", "6", "8", "10", "12"};
            pokemonToBattle = (Prompt.ButtonPrompt("How many Pokemon do you want to battle?", "Number", numPokemonOptions) + 1) * 2;

            Object[] pickPokemonOptions = { "Pick", "Random"};
            random = Prompt.ButtonPrompt("Do you want to pick your Pokemon?", "Pick or Random", pickPokemonOptions) != 0;
        }

        if (random) {
            quickBattle = true;
        }

        return Create.CreateTrainers(pokemonToBattle, quickBattle);
    }

    private static List<Trainer> CreateTrainers(int pokemonToBattle, boolean quickBattle) {
        List<PokemonEnum> pokemonPicked = new ArrayList<>();

        while(pokemonPicked.size() < pokemonToBattle) {
            int pokemonChosen = -1;

            if (quickBattle || (pokemonToBattle / 2 < pokemonPicked.size() + 1)) {
                pokemonChosen = ThreadLocalRandom.current().nextInt(0, NUM_OF_POKEMON);
            }
            else {
                String temp = Prompt.ChoosePokemonPrompt("Please select a Pokemon to be on your team.\nPokemon: ", null, POKEMON_NAMES, pokemonPicked);
                for (int i = 0; i < POKEMON_NAMES.length; i++) {
                    if (POKEMON_NAMES[i].equals(temp)) {
                        pokemonChosen = i;
                        break;
                    }
                }
            }

            try {
                PokemonEnum pokemonChosenEnum = PokemonEnum.values()[pokemonChosen];
                if (pokemonPicked.contains(pokemonChosenEnum)) {
                    if (!quickBattle && pokemonToBattle / 2 > pokemonPicked.size()) {
                        Prompt.ErrorMessage("You have already picked that Pokemon.");
                    }
                }
                else {
                    pokemonPicked.add(pokemonChosenEnum);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                // Nothing
            }
        }

        List<Pokemon> playerPokemon = new ArrayList<>();
        List<Pokemon> opponentPokemon = new ArrayList<>();
        for (int i = 0; i < pokemonToBattle; i++) {
            if (i < pokemonToBattle / 2) {
                playerPokemon.add(CreatePokemon(pokemonPicked.get(i)));
            }
            else {
                opponentPokemon.add(CreatePokemon(pokemonPicked.get(i)));
            }
        }
        List<Trainer> trainers = new ArrayList<>();
        trainers.add(new Trainer(TRAINER_NAMES[0], playerPokemon));
        trainers.add(new Trainer(TRAINER_NAMES[1], opponentPokemon));

        return trainers;
    }

    private static Pokemon CreatePokemon(PokemonEnum pokemon) {
        return new Pokemon(
            POKEMON_NAMES[pokemon.ordinal()],
            LEVEL,
            POKEMON_TYPES[pokemon.ordinal()][0],
            POKEMON_TYPES[pokemon.ordinal()][1],
            POKEMON_STATS[pokemon.ordinal()][0],
            POKEMON_STATS[pokemon.ordinal()][1],
            POKEMON_STATS[pokemon.ordinal()][2],
            POKEMON_STATS[pokemon.ordinal()][3],
            POKEMON_STATS[pokemon.ordinal()][4],
            POKEMON_STATS[pokemon.ordinal()][5],
            CreateMoves(pokemon)
        );
    }

    private static List<Move> CreateMoves(PokemonEnum pokemon) {
        List<Move> moves = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            moves.add(new Move(
                MOVE_NAME[pokemon.ordinal()][i],
                MOVE_TYPE[pokemon.ordinal()][i],
                MOVE_CATEGORY[pokemon.ordinal()][i],
                MOVE_STATS[pokemon.ordinal()][i][0],
                MOVE_STATS[pokemon.ordinal()][i][1],
                MOVE_STATS[pokemon.ordinal()][i][2],
                MOVE_RECOIL[pokemon.ordinal()][i],
                new Status(MOVE_STATUS_EFFECT[pokemon.ordinal()][i], MOVE_STATUS_EFFECT_CHANCE[pokemon.ordinal()][i])
            ));
        }
        return moves;
    }
}
