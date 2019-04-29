package GUI;

import Game.Helpers.Data.FrameData;
import Game.Pokemon.Trainer;

import javax.swing.*;
import java.awt.*;

public class PokemonComponent extends JComponent implements FrameData {
    private PokemonGraphic playerPokemon;
    private PokemonGraphic opponentPokemon;
    private Image background;

    public PokemonComponent() {
        Dimension size = new Dimension(FRAME_WIDTH, TOP_PANEL_IMAGE_HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void setTrainer(Trainer player, Trainer opponent) {
        playerPokemon = new PokemonGraphic(player, true);
        opponentPokemon = new PokemonGraphic(opponent, false);
    }

    public void update() {
        repaint();
    }

    public void setBackgroundImage(Image background) {
        this.background = background;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        if (this.background != null) {
            g.drawImage(background, 0, 0, null);
        }

        if (this.playerPokemon != null) {
            playerPokemon.draw(g2);
        }

        if (this.opponentPokemon != null) {
            opponentPokemon.draw(g2);
        }
    }
}
