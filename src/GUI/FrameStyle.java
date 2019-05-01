package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FrameStyle {
    private static FrameStyle instance;

    private JTextArea textOutput;
    private ArrayList<Button> buttons;
    private PokemonComponent pokemonComponent;

    public static FrameStyle getInstance() {
        if(instance == null) {
            instance = new FrameStyle();
        }
        return instance;
    }

    public void setObjects(JTextArea textOutput, ArrayList<Button> buttons, PokemonComponent pokemonComponent) {
        this.textOutput = textOutput;
        this.buttons = buttons;
        this.pokemonComponent = pokemonComponent;
    }

    public void setFont(String font) {
        Font f = textOutput.getFont();
        textOutput.setFont(new Font(font, f.getStyle(), f.getSize()));

        for (Button b : buttons) {
            f = b.getFont();
            b.setFont(new Font(font, f.getStyle(), f.getSize()));
        }

        f = pokemonComponent.getFont();
        pokemonComponent.setFont(new Font(font, f.getStyle(), f.getSize()));
    }

    public void setOutputFont(String font) {
        Font f = textOutput.getFont();
        textOutput.setFont(new Font(font, f.getStyle(), f.getSize()));
    }

    public void setButtonFont(String font) {
        for (Button b : buttons) {
            Font f = b.getFont();
            b.setFont(new Font(font, f.getStyle(), f.getSize()));
        }
    }

    public void setStatusFont(String font) {
        Font f = pokemonComponent.getFont();
        pokemonComponent.setFont(new Font(font, f.getStyle(), f.getSize()));
    }

    public void setFontSize(int size) {
        Font f = textOutput.getFont();
        textOutput.setFont(new Font(f.getName(), f.getStyle(), size));

        for (Button b : buttons) {
            f = b.getFont();
            b.setFont(new Font(f.getName(), f.getStyle(), size));
        }
    }

    public void setOutputFontSize(int size) {
        Font f = textOutput.getFont();
        textOutput.setFont(new Font(f.getName(), f.getStyle(), size));
    }

    public void setButtonFontSize(int size) {
        for (Button b : buttons) {
            Font f = b.getFont();
            b.setFont(new Font(f.getName(), f.getStyle(), size));
        }
    }

    public void setStatusFontSize(int size) {
        Font f = pokemonComponent.getFont();
        pokemonComponent.setFont(new Font(f.getName(), f.getStyle(), size));
    }

    public void setFontColor(Color color) {
        textOutput.setForeground(color);

        for (Button b : buttons) {
            b.setForeground(color);
        }

        pokemonComponent.setFontColor(color);
    }

    public void setOutputFontColor(Color color) {
        textOutput.setForeground(color);
    }

    public void setButtonFontColor(Color color) {
        for (Button b : buttons) {
            b.setForeground(color);
        }
    }

    public void setStatusFontColor(Color color) {
        pokemonComponent.setFontColor(color);
    }

    public void setStatusOutlineColor(Color color) {
        pokemonComponent.setOutlineColor(color);
    }

    public void setStatusBackgroundColor(Color color) {
        pokemonComponent.setBackgroundColor(color);
    }
}
