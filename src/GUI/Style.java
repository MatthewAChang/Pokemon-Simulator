package GUI;

import Game.DamageCalculator;

import java.awt.*;

public class Style {
    public static void CreateStyle() {
        /*
        setPlayerName(String name);
        setOpponentName(String name)
        setFont(String font)
        setOutputFont(String font)
        setButtonFont(String font)
        setStatusFont(String font)
        setFontSize(int size)
        setOutputFontSize(int size)
        setButtonFontSize(int size)
        setStatusFontSize(int size)
        setFontColor(Color color)
        setOutputFontColor(Color color)
        setOutputBackgroundColor(Color color)
        setButtonFontColor(Color color)
        setButtonBackgroundColor(Color color)
        setStatusFontColor(Color color)
        setStatusOutlineColor(Color color)
        setStatusBackgroundColor(Color color)
        setBackgroundImage(String name)
        showBackground(boolean show)
        setDamageMultiplier(double multiplier)
        setPokemonSizeMultiplier(double multiplier);
         */

        setPlayerName("Red");
        setOpponentName("Blue");
        setFont("Microsoft Sans Serif");
        setOutputFont("Microsoft Sans Serif");
        setButtonFont("Microsoft Sans Serif");
        setStatusFont("Microsoft Sans Serif");
        setOutputFontSize(16);
        setButtonFontSize(20);
        setStatusFontSize(14);
        setFontColor(Color.BLACK);
        setOutputFontColor(Color.BLACK);
        setOutputBackgroundColor(Color.WHITE);
        setButtonFontColor(Color.BLACK);
        setStatusFontColor(Color.BLACK);
        setStatusOutlineColor(Color.BLACK);
        setStatusBackgroundColor(Color.WHITE);
        setBackgroundImage("Field");
        showBackground(true);
        setDamageMultiplier(1);
        setPokemonSizeMultiplier(1);
    }

    private static void setPlayerName(String name) {
        FrameStyle.getInstance().setPlayerName(name);
    }

    private static void setOpponentName(String name) {
        FrameStyle.getInstance().setOpponentName(name);
    }

    private static void setFont(String font) {
        FrameStyle.getInstance().setFont(font);
    }

    private static void setOutputFont(String font) {
        FrameStyle.getInstance().setOutputFont(font);
    }

    private static void setButtonFont(String font) {
        FrameStyle.getInstance().setButtonFont(font);
    }

    private static void setStatusFont(String font) {
        FrameStyle.getInstance().setStatusFont(font);
    }

    private static void setFontSize(int size) {
        FrameStyle.getInstance().setFontSize(size);
    }

    private static void setOutputFontSize(int size) {
        FrameStyle.getInstance().setOutputFontSize(size);
    }

    private static void setButtonFontSize(int size) {
        FrameStyle.getInstance().setButtonFontSize(size);
    }

    private static void setStatusFontSize(int size) {
        FrameStyle.getInstance().setStatusFontSize(size);
    }

    private static void setFontColor(Color color) {
        FrameStyle.getInstance().setFontColor(color);
    }

    private static void setOutputFontColor(Color color) {
        FrameStyle.getInstance().setOutputFontColor(color);
    }

    private static void setOutputBackgroundColor(Color color) {
        FrameStyle.getInstance().setOutputBackgroundColor(color);
    }

    private static void setButtonFontColor(Color color) {
        FrameStyle.getInstance().setButtonFontColor(color);
    }

    private static void setButtonBackgroundColor(Color color) {
        FrameStyle.getInstance().setButtonBackgroundColor(color);
    }

    private static void setStatusFontColor(Color color) {
        FrameStyle.getInstance().setStatusFontColor(color);
    }

    private static void setStatusOutlineColor(Color color) {
        FrameStyle.getInstance().setStatusOutlineColor(color);
    }

    private static void setStatusBackgroundColor(Color color) {
        FrameStyle.getInstance().setStatusBackgroundColor(color);
    }

    private static void setBackgroundImage(String name) {
        FrameStyle.getInstance().setBackgroundImage(name);
    }

    private static void showBackground(boolean show) {
        FrameStyle.getInstance().showBackground(show);
    }

    private static void setDamageMultiplier(double multiplier) {
        DamageCalculator.setDamageMultiplier(multiplier);
    }

    private static void setPokemonSizeMultiplier(double multiplier) {
        FrameStyle.getInstance().setPokemonSizeMultiplier(multiplier);
    }
}
