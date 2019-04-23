package Game.Helpers;

import Game.Helpers.Data.PokemonData;
import Game.Helpers.Enums.MoveCategoryEnum;
import Game.Helpers.Enums.PokemonEnum;
import Game.Helpers.Enums.StatusEffectEnum;
import Game.Helpers.Enums.TypeEnum;
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
        switch (pokemon) {
            case VENUSAUR:
                moves.add(new Move("Earthquake", TypeEnum.GRO, MoveCategoryEnum.PHYSICAL, 100, 100, 0));
                moves.add(new Move("Energy Ball", TypeEnum.GRA, MoveCategoryEnum.SPECIAL, 90, 100, 0));
                moves.add(new Move("Sludge Bomb", TypeEnum.POI, MoveCategoryEnum.SPECIAL, 90, 100, 0, new Status(StatusEffectEnum.PSN, 30)));
                moves.add(new Move("Take Down", TypeEnum.NOR, MoveCategoryEnum.PHYSICAL, 90, 85, 0, true));
                break;
            case NINETALES:
                moves.add(new Move("Flamethrower", TypeEnum.FIR, MoveCategoryEnum.SPECIAL, 90, 100, 0, new Status(StatusEffectEnum.BRN, 10)));
                moves.add(new Move("Energy Ball", TypeEnum.GRA, MoveCategoryEnum.SPECIAL, 90, 100, 0));
                moves.add(new Move("Psyshock", TypeEnum.PSY, MoveCategoryEnum.SPECIAL, 80, 100, 0));
                moves.add(new Move("Dark Pulse", TypeEnum.DAR, MoveCategoryEnum.SPECIAL, 80, 100, 0));
                break;
            case MEW:
                moves.add(new Move("Flamethrower", TypeEnum.FIR, MoveCategoryEnum.SPECIAL, 90, 100, 0, new Status(StatusEffectEnum.BRN, 10)));
                moves.add(new Move("Thunder Bolt", TypeEnum.ELE, MoveCategoryEnum.SPECIAL, 90, 100, 0, new Status(StatusEffectEnum.PAR, 10)));
                moves.add(new Move("Psychic", TypeEnum.PSY, MoveCategoryEnum.SPECIAL, 90, 100, 0));
                moves.add(new Move("Ice Beam", TypeEnum.ICE, MoveCategoryEnum.SPECIAL, 90, 100, 0, new Status(StatusEffectEnum.FRZ, 10)));
                break;
            case FERALIGATR:
                moves.add(new Move("Aqua Tail", TypeEnum.WAT, MoveCategoryEnum.PHYSICAL, 90, 90, 0));
                moves.add(new Move("Ice Punch", TypeEnum.ICE, MoveCategoryEnum.PHYSICAL, 75, 100, 0, new Status(StatusEffectEnum.FRZ, 10)));
                moves.add(new Move("Rock Slide", TypeEnum.ROC, MoveCategoryEnum.PHYSICAL, 75, 90, 0));
                moves.add(new Move("Dragon Claw", TypeEnum.DRA, MoveCategoryEnum.PHYSICAL, 80, 100, 0));
                break;
            case UMBREON:
                moves.add(new Move("Shadow Ball", TypeEnum.GHO, MoveCategoryEnum.SPECIAL, 80, 100, 0));
                moves.add(new Move("Psychic", TypeEnum.PSY, MoveCategoryEnum.SPECIAL, 90, 100, 0));
                moves.add(new Move("Quick Attack", TypeEnum.NOR, MoveCategoryEnum.PHYSICAL, 40, 100, 1));
                moves.add(new Move("Dark Pulse", TypeEnum.DAR, MoveCategoryEnum.SPECIAL, 80, 100, 0));
                break;
            case STEELIX:
                moves.add(new Move("Iron Tail", TypeEnum.STE, MoveCategoryEnum.PHYSICAL, 100, 75, 0));
                moves.add(new Move("Stone Edge", TypeEnum.ROC, MoveCategoryEnum.PHYSICAL, 100, 80, 0));
                moves.add(new Move("Earthquake", TypeEnum.GRO, MoveCategoryEnum.PHYSICAL, 100, 100, 0));
                moves.add(new Move("Ice Fang", TypeEnum.ICE, MoveCategoryEnum.PHYSICAL, 65, 95, 0, new Status(StatusEffectEnum.FRZ, 10)));
                break;
            case GARDEVOIR:
                moves.add(new Move("Psychic", TypeEnum.PSY, MoveCategoryEnum.SPECIAL, 90, 100, 0));
                moves.add(new Move("Moonblast", TypeEnum.FAI, MoveCategoryEnum.SPECIAL, 95, 100, 0));
                moves.add(new Move("Focus Blast", TypeEnum.FIG, MoveCategoryEnum.SPECIAL, 120, 70, 0));
                moves.add(new Move("Energy Ball", TypeEnum.GRA, MoveCategoryEnum.SPECIAL, 90, 100, 0));
                break;
            case SALAMENCE:
                moves.add(new Move("Dragon Claw", TypeEnum.DRA, MoveCategoryEnum.PHYSICAL, 80, 100, 0));
                moves.add(new Move("Zen Headbutt", TypeEnum.PSY, MoveCategoryEnum.PHYSICAL, 80, 90, 0));
                moves.add(new Move("Double-Edge", TypeEnum.NOR, MoveCategoryEnum.PHYSICAL, 120, 100, 0, true));
                moves.add(new Move("Shadow Claw", TypeEnum.GHO, MoveCategoryEnum.PHYSICAL, 70, 100, 0));
                break;
            case LUCARIO:
                moves.add(new Move("Aura Sphere", TypeEnum.FIG, MoveCategoryEnum.SPECIAL, 80, 1000, 0));
                moves.add(new Move("Flash Cannon", TypeEnum.STE, MoveCategoryEnum.SPECIAL, 80, 100, 0));
                moves.add(new Move("Stone Edge", TypeEnum.ROC, MoveCategoryEnum.PHYSICAL, 100, 80, 0));
                moves.add(new Move("Extreme Speed", TypeEnum.NOR, MoveCategoryEnum.PHYSICAL, 80, 100, 2));
                break;
            case WEAVILE:
                moves.add(new Move("Poison Jab", TypeEnum.POI, MoveCategoryEnum.PHYSICAL, 80, 100, 0, new Status(StatusEffectEnum.PSN, 30)));
                moves.add(new Move("Night Slash", TypeEnum.DAR, MoveCategoryEnum.PHYSICAL, 70, 100, 0));
                moves.add(new Move("Icicle Crash", TypeEnum.ICE, MoveCategoryEnum.PHYSICAL, 85, 90, 0));
                moves.add(new Move("Ice Shard", TypeEnum.ICE, MoveCategoryEnum.PHYSICAL, 40, 100, 1));
                break;
            case LEAFEON:
                moves.add(new Move("Leaf Blade", TypeEnum.GRA, MoveCategoryEnum.PHYSICAL, 90, 100, 0));
                moves.add(new Move("Double-Edge", TypeEnum.NOR, MoveCategoryEnum.PHYSICAL, 120, 100, 0, true));
                moves.add(new Move("Aerial Ace", TypeEnum.FLY, MoveCategoryEnum.PHYSICAL, 60, 1000, 0));
                moves.add(new Move("X-Scissor", TypeEnum.BUG, MoveCategoryEnum.PHYSICAL, 90, 100, 0));
                break;
            case GARBODOR:
                moves.add(new Move("Gunk Shot", TypeEnum.POI, MoveCategoryEnum.PHYSICAL, 120, 80, 0, new Status(StatusEffectEnum.PSN, 30)));
                moves.add(new Move("Body Slam", TypeEnum.NOR, MoveCategoryEnum.PHYSICAL, 85, 100, 0, new Status(StatusEffectEnum.PAR, 30)));
                moves.add(new Move("Thief", TypeEnum.DAR, MoveCategoryEnum.PHYSICAL, 60, 100, 0));
                moves.add(new Move("Explosion", TypeEnum.NOR, MoveCategoryEnum.PHYSICAL, 250, 100, 0, true));
                break;
        }
        return moves;
    }
}
