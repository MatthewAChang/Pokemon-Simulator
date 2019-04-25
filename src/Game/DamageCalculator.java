package Game;

import GUI.Frame;
import Game.Pokemon.Move;
import Game.Pokemon.Pokemon;
import Game.Helpers.Enums.TypeEnum;

import java.util.concurrent.ThreadLocalRandom;

public class DamageCalculator {
    public static void CalculateDamage(Pokemon attacker, Pokemon defender, Move move) {
        float damage;
        damage = ((2f * attacker.getLevel()) / 5f) + 2f;
        damage *= move.getDamage();
        // Move type
        if (move.isPhysical()) {
            damage *= attacker.getAttack();
            damage /= defender.getDefence();
            if (attacker.isBurned()) {
                damage /= 2f;
            }
        }
        else if (move.isSpecial()) {
            damage *= attacker.getSpecialAttack();
            if (move.getName().equals("Psyshock")) {
                damage /= defender.getDefence();
            }
            else {
                damage /= defender.getSpecialDefence();
            }
        }
        damage = (damage / 50f) + 2f;
        final int rand = ThreadLocalRandom.current().nextInt(85, 101);
        damage *= (float)rand;
        damage /= 100f;
        // STAB
        if (attacker.getType(0) == move.getType() || attacker.getType(1) == move.getType()) {
            damage *= 1.5f;
        }
        // Check type advantage
        float type = TypeModifier(move.getType(), defender.getType(0));
        if (defender.getType(1) != TypeEnum.NON) {
            type *= TypeModifier(move.getType(), defender.getType(1));
        }
        if (type == 0) {
            Frame.getInstance().setTextAndWaitForNext("It had no effect");
        }
        else if (type < 1) {
            Frame.getInstance().setTextAndWaitForNext("It was not very effective");
        }
        else if (type > 1){
            Frame.getInstance().setTextAndWaitForNext("It was super effective.");
        }
        damage *= type;
        int damageInt = Math.round(damage);
        defender.setHp(damageInt);
        System.out.println("-" + damageInt);
        if (move.getRecoil()) {
            RecoilDamage(attacker, move, damage);
        }
    }

    private static void RecoilDamage(Pokemon attacker, Move move, float damage) {
        if (move.getName().equals("Explosion")) {
            attacker.setHp(1000);
        }
        else {
            float recoil;
            if (move.getName().equals("Take Down")) {
                recoil = 0.25f;
            }
            else if (move.getName().equals("Double-Edge")) {
                recoil = 0.33f;
            }
            else {
                recoil = 0.5f;
            }
            int recoilDamage = Math.round(damage * recoil);
            attacker.setHp(recoilDamage);
            System.out.println(attacker.getName() + " was hit by recoil. - " + recoilDamage);
            Frame.getInstance().setTextAndWaitForNext(attacker.getName() + " was hit by recoil.");
        }
    }

    public static float TypeModifier (TypeEnum moveType, TypeEnum defenderType) {
        switch (moveType) {
            case NOR:
                if (defenderType == TypeEnum.ROC || defenderType == TypeEnum.STE) {
                    return 0.5f;
                }
                if (defenderType == TypeEnum.GHO) {
                    return 0;
                }
                return 1;
            case FIR:
                if (defenderType == TypeEnum.ICE || defenderType == TypeEnum.STE || defenderType == TypeEnum.BUG || defenderType == TypeEnum.GRA) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.WAT || defenderType == TypeEnum.ROC || defenderType == TypeEnum.DRA) {
                    return 0.5f;
                }
                return 1;
            case WAT:
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.ROC || defenderType == TypeEnum.GRO) {
                    return 2;
                }
                if (defenderType == TypeEnum.WAT || defenderType == TypeEnum.GRA || defenderType == TypeEnum.DRA) {
                    return 0.5f;
                }
                return 1;
            case ELE:
                if (defenderType == TypeEnum.WAT || defenderType == TypeEnum.FLY) {
                    return 2;
                }
                if (defenderType == TypeEnum.ELE ||defenderType == TypeEnum.GRA || defenderType == TypeEnum.DRA) {
                    return 0.5f;
                }
                if (defenderType == TypeEnum.GRO) {
                    return 0;
                }
                return 1;
            case GRA:
                if (defenderType == TypeEnum.WAT || defenderType == TypeEnum.ROC || defenderType == TypeEnum.GRO) {
                    return 2;
                }
                if (defenderType == TypeEnum.GRA || defenderType == TypeEnum.BUG || defenderType == TypeEnum.FIR || defenderType == TypeEnum.POI ||defenderType == TypeEnum.FLY || defenderType == TypeEnum.STE || defenderType == TypeEnum.DRA) {
                    return 0.5f;
                }
                return 1;
            case ICE:
                if (defenderType == TypeEnum.GRA || defenderType == TypeEnum.GRO || defenderType == TypeEnum.FLY || defenderType == TypeEnum.DRA) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.WAT || defenderType == TypeEnum.STE || defenderType == TypeEnum.ICE) {
                    return 0.5f;
                }
                return 1;
            case FIG:
                if (defenderType == TypeEnum.NOR || defenderType == TypeEnum.ICE || defenderType == TypeEnum.DAR || defenderType == TypeEnum.ROC || defenderType == TypeEnum.STE) {
                    return 2;
                }
                if (defenderType == TypeEnum.POI || defenderType == TypeEnum.FLY || defenderType == TypeEnum.PSY || defenderType == TypeEnum.FAI || defenderType == TypeEnum.BUG) {
                    return 0.5f;
                }
                if (defenderType == TypeEnum.GHO) {
                    return 0;
                }
                return 1;
            case POI:
                if (defenderType == TypeEnum.GRA || defenderType == TypeEnum.FAI || defenderType == TypeEnum.FLY) {
                    return 2;
                }
                if (defenderType == TypeEnum.POI || defenderType == TypeEnum.GRO || defenderType == TypeEnum.ROC || defenderType == TypeEnum.GHO) {
                    return 0.5f;
                }
                if (defenderType == TypeEnum.STE) {
                    return 0;
                }
                return 1;
            case GRO:
                if (defenderType == TypeEnum.POI || defenderType == TypeEnum.FIR || defenderType == TypeEnum.ROC || defenderType == TypeEnum.STE) {
                    return 2;
                }
                if (defenderType == TypeEnum.GRA || defenderType == TypeEnum.BUG) {
                    return 0.5f;
                }
                if (defenderType == TypeEnum.FLY) {
                    return 0;
                }
                return 1;
            case FLY:
                if (defenderType == TypeEnum.GRA || defenderType == TypeEnum.FIG || defenderType == TypeEnum.BUG) {
                    return 2;
                }
                if (defenderType == TypeEnum.ELE || defenderType == TypeEnum.ROC || defenderType == TypeEnum.STE) {
                    return 0.5f;
                }
                return 1;
            case PSY:
                if (defenderType == TypeEnum.FIG || defenderType == TypeEnum.POI) {
                    return 2;
                }
                if (defenderType == TypeEnum.PSY || defenderType == TypeEnum.STE) {
                    return 0.5f;
                }
                if (defenderType == TypeEnum.DAR) {
                    return 0;
                }
                return 1;
            case BUG:
                if (defenderType == TypeEnum.GRA || defenderType == TypeEnum.PSY || defenderType == TypeEnum.DAR) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.FLY || defenderType == TypeEnum.FIG || defenderType == TypeEnum.POI || defenderType == TypeEnum.GHO || defenderType == TypeEnum.STE || defenderType == TypeEnum.FAI) {
                    return 0.5f;
                }
                return 1;
            case ROC:
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.ICE || defenderType == TypeEnum.FLY  || defenderType == TypeEnum.BUG) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIG || defenderType == TypeEnum.STE || defenderType == TypeEnum.GRO) {
                    return 0.5f;
                }
                return 1;
            case GHO:
                if (defenderType == TypeEnum.PSY || defenderType == TypeEnum.GHO) {
                    return 2;
                }
                if (defenderType == TypeEnum.DAR) {
                    return 0.5f;
                }
                if (defenderType == TypeEnum.NOR) {
                    return 0;
                }
                return 1;
            case DRA:
                if (defenderType == TypeEnum.DRA) {
                    return 2;
                }
                if (defenderType == TypeEnum.STE) {
                    return 0.5f;
                }
                if (defenderType == TypeEnum.FAI) {
                    return 0;
                }
                return 1;
            case DAR:
                if (defenderType == TypeEnum.PSY || defenderType == TypeEnum.GHO) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIG || defenderType == TypeEnum.DAR || defenderType == TypeEnum.FAI) {
                    return 0.5f;
                }
                return 1;
            case STE:
                if (defenderType == TypeEnum.ICE || defenderType == TypeEnum.ROC || defenderType == TypeEnum.FAI) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.WAT || defenderType == TypeEnum.ELE || defenderType == TypeEnum.STE) {
                    return 0.5f;
                }
                return 1;
            case FAI:
                if (defenderType == TypeEnum.FIG || defenderType == TypeEnum.DRA || defenderType == TypeEnum.DAR) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.POI || defenderType == TypeEnum.STE) {
                    return 0.5f;
                }
                return 1;
            default:
                return 1;
        }
    }
}
