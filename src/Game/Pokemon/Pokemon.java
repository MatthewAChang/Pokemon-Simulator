package Game.Pokemon;

import Game.Helpers.Pokemon.PokemonData;
import Game.Helpers.StatusEffectEnum;
import Game.Helpers.TypeEnum;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pokemon implements PokemonData {
    private String name;
    private int level;
    private List<TypeEnum> type;
    private int hp;
    private int hpMax;
    private int attack;
    private int defence;
    private int specialAttack;
    private int specialDefence;
    private int speed;
    private Status status;
    private List<Move> moves;
    private int currentMove;
    private Image front;
    private Image back;
    private boolean fainted;

    public Pokemon(String name, int level, TypeEnum typeOne, TypeEnum typeTwo, int hp, int attack, int defence, int specialAttack, int specialDefence, int speed, List<Move> moves) {
        this.name = name;
        this.level = level;
        this.type = new ArrayList<>();
        this.type.add(typeOne);
        this.type.add(typeTwo);
        this.hp = hp;
        this.hpMax = this.hp;
        this.attack = attack;
        this.defence = defence;
        this.specialAttack = specialAttack;
        this.specialDefence = specialDefence;
        this.speed = speed;
        this.status = new Status();
        this.moves = moves;
        this.currentMove = -1;
        this.front = setImage(System.getProperty("user.dir") + "\\images\\Pokemon\\" + name + "-front.png", true);
        this.back = setImage(System.getProperty("user.dir") + "\\images\\Pokemon\\" + name + "-back.png", false);
        this.fainted = false;
    }

    private Pokemon(String name, int level, List<TypeEnum> type, int hp, int hpMax, int attack, int defence, int specialAttack, int specialDefence, int speed, Status status, List<Move> moves, int currentMove, Image front, Image back, boolean fainted) {
        this.name = name;
        this.level = level;
        this.type = new ArrayList<>();
        this.type.addAll(type);
        this.hp = hp;
        this.hpMax = hpMax;
        this.attack = attack;
        this.defence = defence;
        this.specialAttack = specialAttack;
        this.specialDefence = specialDefence;
        this.speed = speed;
        this.status = Status.copy(status);
        this.moves = new ArrayList<>();
        for (Move m : moves) {
            this.moves.add(Move.copy(m));
        }
        this.currentMove = currentMove;
        this.front = front;
        this.back = back;
        this.fainted = fainted;
    }

    public static Pokemon copy(Pokemon other) {
        return new Pokemon(other.name, other.level, other.type, other.hp, other.hpMax, other.attack, other.defence, other.specialAttack, other.specialDefence, other.speed, other.status, other.moves, other.currentMove, other.front, other.back, other.fainted);
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public TypeEnum getType(int typeIndex) {
        return this.type.get(typeIndex);
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int damage) {
        if (damage > this.hp) {
            hp = 0;
        }
        else {
            hp -= damage;
        }
    }

    public int getHpMax() {
        return this.hpMax;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefence() {
        return this.defence;
    }

    public int getSpecialAttack() {
        return this.specialAttack;
    }

    public int getSpecialDefence() {
        return this.specialDefence;
    }

    public int getSpeed() {
        return this.speed;
    }

    public Status getStatus() {
        return status;
    }

    public boolean isBurned() {
        return status.getStatus() == StatusEffectEnum.BRN;
    }

    public boolean isFrozen() {
        return status.getStatus() == StatusEffectEnum.FRZ;
    }

    public boolean isParalyzed() {
        return status.getStatus() == StatusEffectEnum.PAR;
    }

    public boolean isPoisoned() {
        return status.getStatus() == StatusEffectEnum.BRN;
    }

    public boolean isAsleep() {
        return status.getStatus() == StatusEffectEnum.SLP;
    }

    public Move getMove(int moveIndex) {
        return moves.get(moveIndex);
    }

    public void setCurrentMove(int moveNum) {
        currentMove = moveNum;
    }

    public Move getCurrentMove() {
        return getMove(currentMove);
    }

    public int getCurrentMoveIndex() {
        return currentMove;
    }

    public Image getFrontIcon() {
        return front;
    }

    public Image getBackIcon() {
        return back;
    }

    public void setFainted() {
        fainted = true;
    }

    public boolean hasFainted() {
        return fainted;
    }

    private Image setImage(String path, boolean front) {
        BufferedImage image = null;
        try {

            File f = new File(path);
            image = ImageIO.read(f);
        } catch (IOException e) {
            // Nothing
        }

        if (front) {
            return image.getScaledInstance(POKEMON_FRONT_IMAGE_SIZE, POKEMON_FRONT_IMAGE_SIZE, Image.SCALE_SMOOTH);
        }
        else {
            return image.getScaledInstance(POKEMON_BACK_IMAGE_SIZE, POKEMON_BACK_IMAGE_SIZE, Image.SCALE_SMOOTH);
        }
    }
}
