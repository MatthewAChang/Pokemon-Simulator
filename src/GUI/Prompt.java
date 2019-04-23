package GUI;

import Game.Helpers.Enums.PokemonEnum;
import Game.Helpers.Data.PokemonData;

import javax.swing.*;
import java.util.List;

public class Prompt implements PokemonData {

    public static int ButtonPrompt(String question, String title, Object[] options) {
        int choice;
        do {
            choice = JOptionPane.showOptionDialog(null, question, title, JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, null);
        } while (choice == -1);
        return choice;
    }

    public static String ChoosePokemonPrompt(String question, String title, String[] options, List<PokemonEnum> pokemonPicked) {
        String pokemonPickedList = "";
        for (PokemonEnum pokemonEnum : pokemonPicked) {
            pokemonPickedList += POKEMON_NAMES[pokemonEnum.ordinal()] + " ";
        }

        question += pokemonPickedList;
        Object choice;
        do {
            choice = JOptionPane.showInputDialog(null, question, title,
                    JOptionPane.QUESTION_MESSAGE, null, options, null);
        } while (choice == null);
        return (String)choice;
    }

    public static void ErrorMessage(String error) {
        JOptionPane.showMessageDialog(null, error, null, JOptionPane.ERROR_MESSAGE);
    }
}
