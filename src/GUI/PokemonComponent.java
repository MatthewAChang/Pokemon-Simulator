package GUI;

import Game.Helpers.Data.FrameData;

import javax.swing.*;
import java.awt.*;

public class PokemonComponent extends JComponent implements FrameData {
    private Image playerPokemon;
    private Image opponentPokemon;
    private Image background;

    public PokemonComponent() {
        Dimension size = new Dimension(FRAME_WIDTH, TOP_PANEL_IMAGE_HEIGHT);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        setLayout(null);
    }

    public void setPlayerPokemon(Image pokemon, boolean player) {
        if (player) {
            this.playerPokemon = pokemon;
        }
        else {
            this.opponentPokemon = pokemon;
        }
        repaint();
    }

    public void removePlayerPokemon(boolean player) {
        if (player) {
            this.playerPokemon = null;
        }
        else {
            this.opponentPokemon = null;
        }
        repaint();
    }

    public void setBackgroundImage(Image background) {
        this.background = background;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        if (this.background != null) {
            g.drawImage(background, 0, 0, null);
        }

        if (this.playerPokemon != null) {
            g.drawImage(playerPokemon, PLAYER_POKEMON_X, PLAYER_POKEMON_Y, null);
        }

        if (this.opponentPokemon != null) {
            g.drawImage(opponentPokemon, OPPONENT_POKEMON_X, OPPONENT_POKEMON_Y, null);
        }
    }
}
