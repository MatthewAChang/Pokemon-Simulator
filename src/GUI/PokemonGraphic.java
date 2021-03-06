package GUI;

import Game.Helpers.FrameData;
import Game.Helpers.Enum.StatusEffectEnum;
import Game.Pokemon.Trainer;

import java.awt.*;

public class PokemonGraphic implements FrameData {
    private Trainer trainer;
    private boolean player;

    public PokemonGraphic(Trainer trainer, boolean player) {
        this.trainer = trainer;
        this.player = player;
    }

    public void draw(Graphics2D g2, Font font, Color fontColor, Color outlineColor, Color backgroundColor) {
        if (trainer.getCurrentPokemon() != null) {
            if (player) {
                drawPokemon(g2, trainer.getCurrentPokemon().getBackIcon(), PLAYER_POKEMON_X, PLAYER_POKEMON_Y);
                drawPokemonStatus(g2, PLAYER_POKEMON_STATUS_X, PLAYER_POKEMON_STATUS_Y, PLAYER_POKEMON_STATUS_HEIGHT, font, fontColor, outlineColor, backgroundColor);
            }
            else {
                drawPokemon(g2, trainer.getCurrentPokemon().getFrontIcon(), OPPONENT_POKEMON_X, OPPONENT_POKEMON_Y);
                drawPokemonStatus(g2, OPPONENT_POKEMON_STATUS_X, OPPONENT_POKEMON_STATUS_Y, OPPONENT_POKEMON_STATUS_HEIGHT, font, fontColor, outlineColor, backgroundColor);
            }
        }
    }

    private void drawPokemon(Graphics2D g2, Image image, int x, int y) {
        if (!trainer.getCurrentPokemon().hasFainted()) {
            g2.drawImage(image, x, y, null);
        }
    }

    private void drawPokemonStatus(Graphics2D g2, int x, int y, int height, Font font, Color fontColor, Color outlineColor, Color backgroundColor) {
        drawBase(g2, x, y, height, outlineColor, backgroundColor);

        drawBasic(g2, x, y, font, fontColor);

        drawHp(g2, x, y, font, fontColor, outlineColor);

        if (player) {
            drawHpString(g2, x, y, font, fontColor);
        }
    }

    private void drawBase(Graphics2D g2, int x, int y, int height, Color outlineColor, Color backgroundColor) {
        g2.setColor(outlineColor);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(x, y, POKEMON_STATUS_WIDTH, height);

        Rectangle base = new Rectangle(x, y, POKEMON_STATUS_WIDTH, height);
        g2.setColor(backgroundColor);
        g2.fill(base);
    }

    private void drawBasic(Graphics2D g2, int x, int y, Font f, Color fontColor) {
        drawName(g2, x, y, f, fontColor);

        if (trainer.getCurrentPokemon().getStatus().hasStatus()) {
            drawStatusEffect(g2, x, y, f, fontColor);
        }

        drawLv(g2, x, y, f, fontColor);
    }

    private void drawName(Graphics2D g2, int x, int y, Font f, Color fontColor) {
        g2.setFont(f);
        g2.setColor(fontColor);

        g2.drawString(trainer.getCurrentPokemon().getName(), x + POKEMON_NAME_X_OFFSET, y + POKEMON_NAME_Y_OFFSET);
    }

    private void drawStatusEffect(Graphics2D g2, int x, int y, Font f, Color fontColor) {
        g2.setFont(f);
        g2.setColor(getStatusEffectColor(trainer.getCurrentPokemon().getStatus().getStatus()));

        g2.drawString(trainer.getCurrentPokemon().getStatus().toString(), x + POKEMON_LV_X_OFFSET - 40, y + POKEMON_NAME_Y_OFFSET);
    }

    private void drawLv(Graphics2D g2, int x, int y, Font f, Color fontColor) {
        g2.setFont(f);
        g2.setColor(fontColor);

        String s = "Lv: " + trainer.getCurrentPokemon().getLevel();
        g2.drawString(s, x + POKEMON_LV_X_OFFSET, y + POKEMON_NAME_Y_OFFSET);
    }

    private void drawHp(Graphics2D g2, int x, int y, Font f, Color fontColor, Color outlineColor) {
        g2.setColor(outlineColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawRect(x + HP_RECT_X_OFFSET, y + HP_RECT_Y_OFFSET, HP_RECT_WIDTH, HP_RECT_HEIGHT);

        Rectangle lostHp = new Rectangle(x + HP_RECT_X_OFFSET, y + HP_RECT_Y_OFFSET, HP_RECT_WIDTH, HP_RECT_HEIGHT);
        g2.setColor(Color.GRAY);
        g2.fill(lostHp);

        double currentHp = (double)trainer.getCurrentPokemon().getHp() / (double)trainer.getCurrentPokemon().getHpMax();
        double width = currentHp * HP_RECT_WIDTH;

        Rectangle hp = new Rectangle(x + HP_RECT_X_OFFSET, y + HP_RECT_Y_OFFSET, (int)Math.round(width), HP_RECT_HEIGHT);
        g2.setColor(Color.GREEN);
        g2.fill(hp);

        g2.setColor(fontColor);
        g2.setFont(f);
        g2.drawString("HP:", x + HP_TITLE_X_OFFSET, y + HP_TITLE_Y_OFFSET);
    }

    private void drawHpString(Graphics2D g2, int x, int y, Font f, Color fontColor) {
        g2.setFont(f);
        g2.setColor(fontColor);
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

    private Color getStatusEffectColor(StatusEffectEnum statusEffect) {
        switch (statusEffect) {
            case BRN: return Color.RED;
            case FRZ: return Color.BLUE;
            case PAR: return Color.YELLOW;
            case PSN: return Color.PINK;
            case SLP: return Color.GRAY;
            default:
                return Color.BLACK;
        }
    }
}
