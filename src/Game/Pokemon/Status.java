package Game.Pokemon;

import Game.Helpers.Enums.StatusEffectEnum;
import Game.Helpers.Enums.TypeEnum;

public class Status {
    private StatusEffectEnum status;
    private int chance;

    public Status() {
        this.status = StatusEffectEnum.NON;
        this.chance = 0;
    }

    public Status(StatusEffectEnum status) {
        this.status = status;
        this.chance = 0;
    }

    public Status(StatusEffectEnum status, int chance) {
        this.status = status;
        this.chance = chance;
    }

    public static Status copy(Status other) {
        return new Status(other.status, other.chance);
    }

    public boolean hasStatus() {
        return this.status != StatusEffectEnum.NON;
    }

    public StatusEffectEnum getStatus() {
        return status;
    }

    public int getChance() {
        return chance;
    }

    public String toString() {
        switch(status) {
            case BRN: return "BRN";
            case FRZ: return "FRZ";
            case PAR: return "PAR";
            case PSN: return "PSN";
            case SLP: return "SLP";
            default: return "NON";
        }
    }

    public void setStatus(StatusEffectEnum status, TypeEnum type1, TypeEnum type2) {
        if(status == StatusEffectEnum.BRN && type1 != TypeEnum.FIR && type2 != TypeEnum.FIR ||
                status == StatusEffectEnum.FRZ && type1 != TypeEnum.ICE && type2 != TypeEnum.ICE ||
                status == StatusEffectEnum.PSN && type1 != TypeEnum.STE && type2 != TypeEnum.STE ||
                status == StatusEffectEnum.PAR && type1 != TypeEnum.ELE && type2 != TypeEnum.ELE ||
                status == StatusEffectEnum.SLP) {
            this.status = status;
        }
    }

    public void resetStatus() {
        this.status = StatusEffectEnum.NON;
        this.chance = 0;
    }
}
