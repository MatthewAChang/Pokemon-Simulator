package Game;

import GUI.Frame;
import Game.Helpers.Enums.TypeEnum;
import Game.Pokemon.Move;
import Game.Pokemon.Pokemon;
import Game.Pokemon.Trainer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Battle {
    static void Battle(Trainer player, Trainer opponent) {
        player.setCurrentPokemon(0);
        opponent.setCurrentPokemon(0);
        Pokemon playerPokemon = player.getCurrentPokemon();
        Pokemon opponentPokemon = opponent.getCurrentPokemon();

        Frame.getInstance().update();
        Frame.getInstance().setTextAndWaitForNext(player.getName() + " sends out " + playerPokemon.getName() + ".");
        Frame.getInstance().update();
        Frame.getInstance().setTextAndWaitForNext(opponent.getName() + " sends out " + opponentPokemon.getName() + ".");

        while(true) {
            PlayerMove(playerPokemon);
            OpponentMove(playerPokemon, opponentPokemon);

            if (playerPokemon.getCurrentMoveIndex() == -1) {
                final Pokemon temp =  player.getCurrentPokemon();
                Frame.getInstance().setTextAndWaitForPokemonOne("Who to send out?", true);
                Frame.getInstance().setTextAndWaitForPokemonTwo("Who to send out?");

                if (!temp.getName().equals(player.getCurrentPokemon().getName())) {
                    playerPokemon = player.getCurrentPokemon();
                    Frame.getInstance().update();
                    Frame.getInstance().setTextAndWaitForNext(player.getName() + " sends out " + playerPokemon.getName() + ".");
                    Attack(opponentPokemon, playerPokemon, false);

                    if (playerPokemon.getHp() > 0) {
                        StatusEndOfTurn(playerPokemon, true);
                    }
                    if (opponentPokemon.getHp() > 0) {
                        StatusEndOfTurn(opponentPokemon, false);
                    }
                }
            }
            else {
                final boolean playerAttacksFirst = Priority(playerPokemon, opponentPokemon, playerPokemon.getCurrentMove().getPriority(), opponentPokemon.getCurrentMove().getPriority());

                if (playerAttacksFirst) {
                    Attack(playerPokemon, opponentPokemon, true);
                    if (opponentPokemon.getHp() > 0 && playerPokemon.getHp() > 0) {
                        Attack(opponentPokemon, playerPokemon, false);
                    }
                }
                else {
                    Attack(opponentPokemon, playerPokemon, false);
                    if (playerPokemon.getHp() > 0 && opponentPokemon.getHp() > 0) {
                        Attack(playerPokemon, opponentPokemon, true);
                    }
                }

                if (playerPokemon.getHp() > 0) {
                    StatusEndOfTurn(playerPokemon, true);
                }
                if (opponentPokemon.getHp() > 0) {
                    StatusEndOfTurn(opponentPokemon, false);
                }
            }
            playerPokemon.setCurrentMove(-1);

            boolean playerPokemonFaint = false;
            boolean opponentPokemonFaint = false;

            if (playerPokemon.getHp() <= 0) {
                player.pokemonFaints();
                Frame.getInstance().update();
                Frame.getInstance().setTextAndWaitForNext(playerPokemon.getName() + " has fainted.");
                playerPokemonFaint = true;
            }
            if (opponentPokemon.getHp() <= 0) {
                opponent.pokemonFaints();
                Frame.getInstance().update();
                Frame.getInstance().setTextAndWaitForNext(opponentPokemon.getName() + " has fainted.");
                opponentPokemonFaint = true;
            }

            if (playerPokemonFaint && player.getPokemonAwakeNum() > 0 && opponent.getPokemonAwakeNum() > 0) {

                final Pokemon temp =  player.getCurrentPokemon();
                while (temp.getName().equals(player.getCurrentPokemon().getName())) {
                    Frame.getInstance().setTextAndWaitForPokemonOne("Who to send out?", false);
                    Frame.getInstance().setTextAndWaitForPokemonTwo("Who to send out?");
                }
                playerPokemon = player.getCurrentPokemon();
                Frame.getInstance().update();
                Frame.getInstance().setTextAndWaitForNext(player.getName() + " sends out " + playerPokemon.getName() + ".");
            }

            if (opponentPokemonFaint && opponent.getPokemonAwakeNum() > 0) {
                opponent.setCurrentPokemon(opponent.getCurrentPokemonIndex() + 1);
                opponentPokemon = opponent.getCurrentPokemon();
                Frame.getInstance().update();
                Frame.getInstance().setTextAndWaitForNext(opponent.getName() + " sends out " + opponentPokemon.getName() + ".");
            }

            if (player.getPokemonAwakeNum() == 0 || opponent.getPokemonAwakeNum() == 0) {
                if (player.getPokemonAwakeNum() == 0 && opponent.getPokemonAwakeNum() == 0) {
                    Frame.getInstance().setTextAndWaitForNext("The battle ends in a tie.");
                }
                else if (player.getPokemonAwakeNum() == 0) {
                    Frame.getInstance().setTextAndWaitForNext(opponent.getName() + " wins.");
                }
                else {
                    Frame.getInstance().setTextAndWaitForNext(player.getName() + " wins.");
                }
                break;
            }
        }
    }

    private static void PlayerMove(Pokemon pokemon) {
        Frame.getInstance().setTextAndWaitForMoveOne("What will " + pokemon.getName() + " do?");
        Frame.getInstance().setTextAndWaitForMoveTwo("What will " + pokemon.getName() + " do?");
    }

    private static void OpponentMove(Pokemon playerPokemon, Pokemon opponentPokemon) {
        float []opponentDamage = new float[4];
        List<Integer> highestMultiplierMoves = new ArrayList<>();
        float highest = 0;
        for (int i = 0; i < 4; i++) {
            opponentDamage[i] = DamageCalculator.TypeModifier(opponentPokemon.getMove(i).getType(), playerPokemon.getType(0));
            if (playerPokemon.getType(1) != TypeEnum.NON) {
                opponentDamage[i] *= DamageCalculator.TypeModifier(opponentPokemon.getMove(i).getType(), playerPokemon.getType(1));
            }
            if (opponentDamage[i] > highest) {
                highest = opponentDamage[i];
            }
        }

        for (int i = 0; i < 4; i++) {
            if (opponentDamage[i] == highest) {
                highestMultiplierMoves.add(i);
            }
        }

        while (true) {
            final int opponentMove = ThreadLocalRandom.current().nextInt(0, 5);
            if (highestMultiplierMoves.contains(opponentMove)) {
                opponentPokemon.setCurrentMove(opponentMove);
                return;
            }
        }
    }

    private static boolean Priority(Pokemon playerPokemon, Pokemon opponentPokemon, int playerPokemonPri, int opponentPokemonPri) {
        if (playerPokemonPri > opponentPokemonPri) {
            return true;
        }
        else if (playerPokemonPri < opponentPokemonPri) {
            return false;
        }
        else {
            if (playerPokemon.getSpeed() > opponentPokemon.getSpeed()) {
                return true;
            }
            else if ( playerPokemon.getSpeed() < opponentPokemon.getSpeed()) {
                return false;
            }
            else {
                return ThreadLocalRandom.current().nextInt(0, 2) % 2 == 0;
            }
        }
    }

    private static void Attack(Pokemon attacker, Pokemon defender, boolean player) {
        RemoveStatusBeforeAttack(attacker, player);

        String opposing = "";
        if (!player) {
            opposing = "The opposing ";
        }

        if (attacker.isParalyzed() && ThreadLocalRandom.current().nextInt(1, 101) < 25) {
            Frame.getInstance().setTextAndWaitForNext(opposing + attacker.getName() + " is paralyzed.");
        }
        else if (attacker.isAsleep()) {
            Frame.getInstance().setTextAndWaitForNext(opposing + attacker.getName() + " is fast asleep");
        }
        else if (attacker.isFrozen()) {
            Frame.getInstance().setTextAndWaitForNext(opposing + attacker.getName() + " is frozen solid.");
        }
        else {
            Move move = attacker.getCurrentMove();
            Frame.getInstance().setTextAndWaitForNext(opposing + attacker.getName() + " used " + move.getName() + ".");
            if (ThreadLocalRandom.current().nextInt(1, 101) < move.getAccuracy()) {
                DamageCalculator.CalculateDamage(attacker, defender, move);

                if (defender.getHp() > 0 && move.getStatus().hasStatus() && !defender.getStatus().hasStatus()) {
                    if (ThreadLocalRandom.current().nextInt(1, 101) < move.getStatus().getChance()) {
                        defender.getStatus().setStatus(move.getStatus().getStatus(), defender.getType(0), defender.getType(1));
                        NewStatusEffect(defender, player);
                    }
                }
            }
            else {
                Frame.getInstance().setTextAndWaitForNext(opposing + attacker.getName() + " missed.");
            }
        }
    }

    private static void RemoveStatusBeforeAttack(Pokemon pokemon, boolean player) {
        switch (pokemon.getStatus().getStatus()) {
            case FRZ:
                if (ThreadLocalRandom.current().nextInt(1, 101) < 20) {
                    pokemon.getStatus().resetStatus();
                    Frame.getInstance().update();
                    String opposing = "";
                    if (!player) {
                        opposing = "The opposing ";
                    }
                    Frame.getInstance().setTextAndWaitForNext(opposing + pokemon.getName() + " thawed.");
                }
                break;
            case SLP:
                if (ThreadLocalRandom.current().nextInt(1, 101) < 50) {
                    pokemon.getStatus().resetStatus();
                    Frame.getInstance().update();
                    String opposing = "";
                    if (!player) {
                        opposing = "The opposing ";
                    }
                    Frame.getInstance().setTextAndWaitForNext(opposing + pokemon.getName() + " woke up.");
                }
                break;
        }
    }

    private static void NewStatusEffect(Pokemon pokemon, boolean player) {
        String opposing = "";
        if (!player) {
            opposing = "The opposing ";
        }

        switch (pokemon.getStatus().getStatus()) {
            case BRN:
                Frame.getInstance().setTextAndWaitForNext(opposing + pokemon.getName() + " is now burned.");
                break;
            case FRZ:
                Frame.getInstance().setTextAndWaitForNext(opposing + pokemon.getName() + " is now frozen.");
                break;
            case PAR:
                Frame.getInstance().setTextAndWaitForNext(opposing + pokemon.getName() + " is now paralyzed.");
                break;
            case PSN:
                Frame.getInstance().setTextAndWaitForNext(opposing + pokemon.getName() + " is now poisoned.");
                break;
            case SLP:
                Frame.getInstance().setTextAndWaitForNext(opposing + pokemon.getName() + " is now asleep.");
                break;
        }
    }

    private static void StatusEndOfTurn(Pokemon pokemon, boolean player) {
        switch (pokemon.getStatus().getStatus()) {
            case BRN: {
                StatusDamage(pokemon, "burn", player);
                break;
            }
            case PSN: {
                StatusDamage(pokemon, "poison", player);
                break;
            }
        }
    }

    private static void StatusDamage(Pokemon pokemon, String statusDamage, boolean player) {
        final int damage = Math.round(pokemon.getHpMax() / 16f);
        String opposing = "";
        if (!player) {
            opposing = "The opposing ";
        }
        Frame.getInstance().setTextAndWaitForNext(opposing + pokemon.getName() + " is hurt by its " + statusDamage + ".");
        pokemon.setHp(damage);
        Frame.getInstance().update();
    }
}
