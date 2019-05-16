package GUI;

import Game.Helpers.Frame.FrameData;
import Game.Pokemon.Trainer;

import javax.swing.*;
import java.awt.*;

public class PokemonComponent extends JComponent implements FrameData {
    private PokemonGraphic playerPokemon;
    private PokemonGraphic opponentPokemon;
    private Image background;

    private Font font;
    private Color fontColor;
    private Color outlineColor;
    private Color backgroundColor;
    private boolean showBackground;

    public PokemonComponent() {
        font = new Font("Microsoft Sans Serif", Font.BOLD, 14);
        fontColor = Color.BLACK;
        outlineColor = Color.BLACK;
        backgroundColor = Color.WHITE;
        showBackground = true;

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

    public void setFont(Font font) {
        this.font = font;
    }

    public void setFontColor(Color color) {
        this.fontColor = color;
    }

    public void setOutlineColor(Color color) {
        this.outlineColor = color;
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
    }

    public void showBackground(boolean show) {
        showBackground = show;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        if (showBackground && this.background != null) {
            g.drawImage(background, 0, 0, null);
        }

        if (this.playerPokemon != null) {
            playerPokemon.draw(g2, font, fontColor, outlineColor, backgroundColor);
        }

        if (this.opponentPokemon != null) {
            opponentPokemon.draw(g2, font, fontColor, outlineColor, backgroundColor);
        }
    }
}
