package GUI;

import Game.Helpers.Frame.FrameData;
import Game.Pokemon.Trainer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FrameStyle implements FrameData {
    private static FrameStyle instance;

    private Trainer player;
    private Trainer opponent;
    private JTextArea textOutput;
    private ArrayList<Button> buttons;
    private PokemonComponent pokemonComponent;

    public static FrameStyle getInstance() {
        if(instance == null) {
            instance = new FrameStyle();
        }
        return instance;
    }

    public void setFrameObjects(JTextArea textOutput, ArrayList<Button> buttons, PokemonComponent pokemonComponent) {
        this.textOutput = textOutput;
        this.buttons = buttons;
        this.pokemonComponent = pokemonComponent;
    }

    public void setTrainers(Trainer player, Trainer opponent) {
        this.player = player;
        this.opponent = opponent;
    }

    public void setPlayerName(String name) {
        this.player.setName(name);
    }

    public void setOpponentName(String name) {
        this.opponent.setName(name);
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

    public void setOutputBackgroundColor(Color color) {
        textOutput.setBackground(color);
    }

    public void setButtonFontColor(Color color) {
        for (Button b : buttons) {
            b.setForeground(color);
        }
    }

    public void setButtonBackgroundColor(Color color) {
        for (Button b : buttons) {
            b.setBackground(color);
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

    public void setBackgroundImage(String imageName) {
        BufferedImage image = null;
        try {
            String path = System.getProperty("user.dir") + "\\images\\Backgrounds\\" + imageName + ".png";
            File f = new File(path);
            image = ImageIO.read(f);
        } catch (IOException e) {
            try {
                String path = System.getProperty("user.dir") + "\\images\\Backgrounds\\" + imageName + ".jpg";
                File f = new File(path);
                image = ImageIO.read(f);
            } catch (IOException e2) {
                // Nothing
            }
        }
        pokemonComponent.setBackgroundImage(image.getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT / 2, Image.SCALE_SMOOTH));
    }
}
