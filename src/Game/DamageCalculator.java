package Game;

import GUI.Frame;
import Game.Pokemon.Move;
import Game.Pokemon.Pokemon;
import Game.Helpers.Enum.TypeEnum;

import java.util.concurrent.ThreadLocalRandom;

public class DamageCalculator {
    private static double damageMultiplier = 1;

    public static void CalculateDamage(Pokemon attacker, Pokemon defender, Move move) {
        double damage;
        damage = (((double)(2 * attacker.getLevel())) / 5) + 2;
        damage *= move.getDamage();
        // Move type
        if (move.isPhysical()) {
            damage *= attacker.getAttack();
            damage /= defender.getDefence();
            if (attacker.isBurned()) {
                damage /= 2;
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
        damage = (damage / 50) + 2;
        final int rand = ThreadLocalRandom.current().nextInt(85, 101);
        damage *= rand;
        damage /= 100;
        // STAB
        if (attacker.getType(0) == move.getType() || attacker.getType(1) == move.getType()) {
            damage *= 1.5;
        }
        // Check type advantage
        double type = TypeModifier(move.getType(), defender.getType(0));
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
        damage *= damageMultiplier;
        defender.setHp((int)Math.round(damage));
        Frame.getInstance().update();
        if (move.getRecoil()) {
            RecoilDamage(attacker, move, damage);
        }
    }

    public static void setDamageMultiplier(double multiplier) {
        damageMultiplier = multiplier;
    }

    private static void RecoilDamage(Pokemon attacker, Move move, double damage) {
        if (move.getName().equals("Explosion")) {
            attacker.setHp(1000);
        }
        else {
            double recoil;
            if (move.getName().equals("Take Down")) {
                recoil = 0.25;
            }
            else if (move.getName().equals("Double-Edge")) {
                recoil = 0.33;
            }
            else {
                recoil = 0.5;
            }
            final int recoilDamage = (int)Math.round(damage * recoil);
            Frame.getInstance().setTextAndWaitForNext(attacker.getName() + " was hit by recoil.");
            attacker.setHp(recoilDamage);
            Frame.getInstance().update();
        }
    }

    public static double TypeModifier (TypeEnum moveType, TypeEnum defenderType) {
        switch (moveType) {
            case NOR:
                if (defenderType == TypeEnum.ROC || defenderType == TypeEnum.STE) {
                    return 0.5;
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
                    return 0.5;
                }
                return 1;
            case WAT:
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.ROC || defenderType == TypeEnum.GRO) {
                    return 2;
                }
                if (defenderType == TypeEnum.WAT || defenderType == TypeEnum.GRA || defenderType == TypeEnum.DRA) {
                    return 0.5;
                }
                return 1;
            case ELE:
                if (defenderType == TypeEnum.WAT || defenderType == TypeEnum.FLY) {
                    return 2;
                }
                if (defenderType == TypeEnum.ELE ||defenderType == TypeEnum.GRA || defenderType == TypeEnum.DRA) {
                    return 0.5;
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
                    return 0.5;
                }
                return 1;
            case ICE:
                if (defenderType == TypeEnum.GRA || defenderType == TypeEnum.GRO || defenderType == TypeEnum.FLY || defenderType == TypeEnum.DRA) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.WAT || defenderType == TypeEnum.STE || defenderType == TypeEnum.ICE) {
                    return 0.5;
                }
                return 1;
            case FIG:
                if (defenderType == TypeEnum.NOR || defenderType == TypeEnum.ICE || defenderType == TypeEnum.DAR || defenderType == TypeEnum.ROC || defenderType == TypeEnum.STE) {
                    return 2;
                }
                if (defenderType == TypeEnum.POI || defenderType == TypeEnum.FLY || defenderType == TypeEnum.PSY || defenderType == TypeEnum.FAI || defenderType == TypeEnum.BUG) {
                    return 0.5;
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
                    return 0.5;
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
                    return 0.5;
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
                    return 0.5;
                }
                return 1;
            case PSY:
                if (defenderType == TypeEnum.FIG || defenderType == TypeEnum.POI) {
                    return 2;
                }
                if (defenderType == TypeEnum.PSY || defenderType == TypeEnum.STE) {
                    return 0.5;
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
                    return 0.5;
                }
                return 1;
            case ROC:
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.ICE || defenderType == TypeEnum.FLY  || defenderType == TypeEnum.BUG) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIG || defenderType == TypeEnum.STE || defenderType == TypeEnum.GRO) {
                    return 0.5;
                }
                return 1;
            case GHO:
                if (defenderType == TypeEnum.PSY || defenderType == TypeEnum.GHO) {
                    return 2;
                }
                if (defenderType == TypeEnum.DAR) {
                    return 0.5;
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
                    return 0.5;
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
                    return 0.5;
                }
                return 1;
            case STE:
                if (defenderType == TypeEnum.ICE || defenderType == TypeEnum.ROC || defenderType == TypeEnum.FAI) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.WAT || defenderType == TypeEnum.ELE || defenderType == TypeEnum.STE) {
                    return 0.5;
                }
                return 1;
            case FAI:
                if (defenderType == TypeEnum.FIG || defenderType == TypeEnum.DRA || defenderType == TypeEnum.DAR) {
                    return 2;
                }
                if (defenderType == TypeEnum.FIR || defenderType == TypeEnum.POI || defenderType == TypeEnum.STE) {
                    return 0.5;
                }
                return 1;
            default:
                return 1;
        }
    }
}
