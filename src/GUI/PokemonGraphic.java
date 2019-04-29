package GUI;

import Game.Helpers.Data.FrameData;
import Game.Pokemon.Trainer;

import java.awt.*;

public class PokemonGraphic implements FrameData {
    private Trainer trainer;
    private boolean player;

    public PokemonGraphic(Trainer trainer, boolean player) {
        this.trainer = trainer;
        this.player = player;
    }

    public void draw(Graphics2D g2) {
        if (player) {
            drawPokemon(g2, trainer.getCurrentPokemon().getBackIcon(), PLAYER_POKEMON_X, PLAYER_POKEMON_Y);
            drawPokemonStatus(g2, PLAYER_POKEMON_STATUS_X, PLAYER_POKEMON_STATUS_Y, PLAYER_POKEMON_STATUS_HEIGHT);
        }
        else {
            drawPokemon(g2, trainer.getCurrentPokemon().getFrontIcon(), OPPONENT_POKEMON_X, OPPONENT_POKEMON_Y);
            drawPokemonStatus(g2, OPPONENT_POKEMON_STATUS_X, OPPONENT_POKEMON_STATUS_Y, OPPONENT_POKEMON_STATUS_HEIGHT);
        }
    }

    private void drawPokemon(Graphics2D g2, Image image, int x, int y) {
        if (!trainer.getCurrentPokemon().hasFainted()) {
            g2.drawImage(image, x, y, null);
        }
    }

    private void drawPokemonStatus(Graphics2D g2, int x, int y, int height) {
        Font f = new Font("Microsoft Sans Serif", Font.BOLD, 14);

        drawBase(g2, x, y, height);

        drawHp(g2, x, y, f);

        drawName(g2, x, y);

        if (player) {
            drawHpString(g2, x, y, f);
        }
    }

    private void drawBase(Graphics2D g2, int x, int y, int height) {
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(x, y, POKEMON_STATUS_WIDTH, height);

        Rectangle base = new Rectangle(x, y, POKEMON_STATUS_WIDTH, height);
        g2.setColor(Color.WHITE);
        g2.fill(base);
    }

    private void drawName(Graphics2D g2, int x, int y) {
        g2.drawString(trainer.getCurrentPokemon().getName(), x + POKEMON_NAME_X_OFFSET, y + POKEMON_NAME_Y_OFFSET);
        String s = "Lv: " + trainer.getCurrentPokemon().getLevel();
        g2.drawString(s, x + POKEMON_LV_X_OFFSET, y + POKEMON_NAME_Y_OFFSET);
    }

    private void drawHp(Graphics2D g2, int x, int y, Font f) {
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(x + HP_RECT_X_OFFSET, y + HP_RECT_Y_OFFSET, HP_RECT_WIDTH, HP_RECT_HEIGHT);

        Rectangle lostHp = new Rectangle(x + HP_RECT_X_OFFSET, y + HP_RECT_Y_OFFSET, HP_RECT_WIDTH, HP_RECT_HEIGHT);
        g2.setColor(Color.GRAY);
        g2.fill(lostHp);

        float currentHp = (float)trainer.getCurrentPokemon().getHp() / (float)trainer.getCurrentPokemon().getHpMax();
        float width = currentHp * HP_RECT_WIDTH;

        Rectangle hp = new Rectangle(x + HP_RECT_X_OFFSET, y + HP_RECT_Y_OFFSET, Math.round(width), HP_RECT_HEIGHT);
        g2.setColor(Color.GREEN);
        g2.fill(hp);

        g2.setColor(Color.BLACK);

        g2.setFont(f);
        g2.drawString("HP:", x + HP_TITLE_X_OFFSET, y + HP_TITLE_Y_OFFSET);
    }

    private void drawHpString(Graphics2D g2, int x, int y, Font f) {
        g2.setFont(f);
        String currentHp = Integer.toString(trainer.getCurrentPokemon().getHp());
        while (currentHp.length() < 3) {
            currentHp = "0" + currentHp;
        }

        String maxHp = Integer.toString(trainer.getCurrentPokemon().getHpMax());
        while (maxHp.length() < 3) {
            maxHp = "0" + maxHp;
        }

        String hpString = currentHp + "/" + maxHp;

        g2.drawString(hpString, x + HP_STRING_X_OFFSET, y + HP_STRING_Y_OFFSET);
    }
}
