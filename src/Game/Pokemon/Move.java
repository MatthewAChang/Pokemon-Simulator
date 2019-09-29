package Game.Pokemon;

import Game.Helpers.Enum.MoveCategoryEnum;
import Game.Helpers.Enum.TypeEnum;

public class Move {
    private String name;
    private TypeEnum type;
    private MoveCategoryEnum category;
    private int damage;
    private int accuracy;
    private int priority;
    private boolean recoil;
    private Status status;

    public Move(String name, TypeEnum type, MoveCategoryEnum category, int damage, int accuracy, int priority, boolean recoil, Status status) {
        this.name = name;
        this.type = type;
        this.category = category;
        this.damage = damage;
        this.accuracy = accuracy;
        this.priority = priority;
        this.recoil = recoil;
        this.status = Status.copy(status);
    }

    public static Move copy(Move other) {
        return new Move(other.name, other.type, other.category, other.damage, other.accuracy, other.priority, other.recoil, other.status);
    }

    public String getName() {
        return this.name;
    }

    public TypeEnum getType() {
        return this.type;
    }

    public boolean isPhysical() {
        return this.category == MoveCategoryEnum.PHYSICAL;
    }

    public boolean isSpecial() {
        return this.category == MoveCategoryEnum.SPECIAL;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public int getPriority() {
        return this.priority;
    }

    public boolean getRecoil() {
        return this.recoil;
    }

    public Status getStatus() {
        return this.status;
    }
}
