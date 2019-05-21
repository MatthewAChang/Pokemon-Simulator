package Game.Helpers.Pokemon;

import Game.Helpers.MoveCategoryEnum;
import Game.Helpers.StatusEffectEnum;
import Game.Helpers.TypeEnum;

public interface PokemonData {
    int NUM_OF_POKEMON = 12;

    int LEVEL = 50;

    String []TRAINER_NAMES = {"Red", "Blue"};

    String []POKEMON_NAMES = {
        "Venusaur",
        "Ninetales",
        "Gengar",
        "Mew",
        "Feraligatr",
        "Steelix",
        "Gardevoir",
        "Salamence",
        "Lucario",
        "Weavile",
        "Leafeon",
        "Garbodor",
        ""
    };

    // Type 1, Type 2
    TypeEnum[][] POKEMON_TYPES = {
        { TypeEnum.GRA, TypeEnum.POI }, // Venusaur
        { TypeEnum.FIR, TypeEnum.NON }, // Ninetales
        { TypeEnum.GHO, TypeEnum.POI }, // Gengar
        { TypeEnum.PSY, TypeEnum.NON }, // Mew
        { TypeEnum.WAT, TypeEnum.NON }, // Feraligatr
        { TypeEnum.STE, TypeEnum.GRO }, // Steelix
        { TypeEnum.PSY, TypeEnum.FAI }, // Gardevoir
        { TypeEnum.DRA, TypeEnum.FLY }, // Salamence
        { TypeEnum.FIG, TypeEnum.STE }, // Lucario
        { TypeEnum.DAR, TypeEnum.ICE }, // Weavile
        { TypeEnum.GRA, TypeEnum.NON }, // Leafeon
        { TypeEnum.POI, TypeEnum.NON },  // Garbodor
        { null, null }  //
    };

    // HP, Attack, Defence, Special Attack, Special Defence, Speed
    int[][] POKEMON_STATS = {
        { 155, 102, 103, 120, 120, 100 }, // Venusaur
        { 148,  96,  95, 101, 120, 120 }, // Ninetales
        { 135,  85,  80, 150,  95, 130 }, // Gengar
        { 175, 120, 120, 120, 120, 120 }, // Mew
        { 160, 125, 120,  99, 103,  98 }, // Feraligatr
        { 150, 105, 220,  75,  85,  50 }, // Steelix
        { 143,  85,  85, 145, 135, 100 }, // Gardevoir
        { 170, 155, 100, 130, 100, 120 }, // Salamence
        { 145, 130,  90, 135,  90, 110 }, // Lucario
        { 145, 140,  85,  65, 105, 145 }, // Weavile
        { 140, 130, 150,  80,  85, 115 }, // Leafeon
        { 155, 115, 102,  80, 102,  95 }, // Garbodor
        { 0, 0, 0, 0, 0, 0 } //
    };

    String[][] MOVE_NAME = {
        {"Earthquake", "Energy Ball", "Sludge Bomb", "Take Down"}, // Venusaur
        {"Flamethrower", "Energy Ball", "Psyshock", "Dark Pulse"}, // Ninetales
        {"Shadow Ball", "Sludge Wave", "Energy Ball", "Thunder Bolt"}, // Gengar
        {"Flamethrower", "Thunder Bolt", "Psychic", "Ice Beam"}, // Mew
        {"Aqua Tail", "Ice Punch", "Rock Slide", "Dragon Claw"}, // Feraligatr
        {"Iron Tail", "Stone Edge", "Earthquake", "Ice Fang"}, // Steelix
        {"Psychic", "Moonblast", "Focus Blast", "Energy Ball"}, // Gardevoir
        {"Dragon Claw", "Zen Headbutt", "Double-Edge", "Shadow Claw"}, // Salamence
        {"Aura Sphere", "Flash Cannon", "Stone Edge", "Extreme Speed"}, // Lucario
        {"Poison Jab", "Night Slash", "Icicle Crash", "Ice Shard"}, // Weavile
        {"Leaf Blade", "Double-Edge", "Aerial Ace", "X-Scissor"}, // Leafeon
        {"Gunk Shot", "Body Slam", "Thief", "Explosion"}, // Garbodor
        {"", "", "", ""} //
    };

    TypeEnum[][] MOVE_TYPE = {
        { TypeEnum.GRO, TypeEnum.GRA, TypeEnum.POI, TypeEnum.NOR }, // Venusaur
        { TypeEnum.FIR, TypeEnum.GRA, TypeEnum.PSY, TypeEnum.DAR }, // Ninetales
        { TypeEnum.GHO, TypeEnum.POI, TypeEnum.GRA, TypeEnum.ELE }, // Gengar
        { TypeEnum.FIR, TypeEnum.ELE, TypeEnum.PSY, TypeEnum.ICE }, // Mew
        { TypeEnum.WAT, TypeEnum.ICE, TypeEnum.ROC, TypeEnum.DRA }, // Feraligatr
        { TypeEnum.STE, TypeEnum.ROC, TypeEnum.GRO, TypeEnum.ICE }, // Steelix
        { TypeEnum.PSY, TypeEnum.FAI, TypeEnum.FIG, TypeEnum.GRA }, // Gardevoir
        { TypeEnum.DRA, TypeEnum.PSY, TypeEnum.NOR, TypeEnum.GHO }, // Salamence
        { TypeEnum.FIG, TypeEnum.STE, TypeEnum.ROC, TypeEnum.NOR }, // Lucario
        { TypeEnum.POI, TypeEnum.DAR, TypeEnum.ICE, TypeEnum.ICE }, // Weavile
        { TypeEnum.GRA, TypeEnum.NOR, TypeEnum.FLY, TypeEnum.BUG }, // Leafeon
        { TypeEnum.POI, TypeEnum.NOR, TypeEnum.DAR, TypeEnum.NOR }, // Garbodor
        { null, null, null, null } //
    };

    MoveCategoryEnum[][] MOVE_CATEGORY = {
        { MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.PHYSICAL }, // Venusaur
        { MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL }, // Ninetales
        { MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL }, // Gengar
        { MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL }, // Mew
        { MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL }, // Feraligatr
        { MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL }, // Steelix
        { MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL }, // Gardevoir
        { MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL }, // Salamence
        { MoveCategoryEnum.SPECIAL, MoveCategoryEnum.SPECIAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL }, // Lucario
        { MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL }, // Weavile
        { MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL }, // Leafeon
        { MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL, MoveCategoryEnum.PHYSICAL }, // Garbodor
        { null, null, null, null } //
    };

    // Damage, Accuracy, Priority
    int[][][] MOVE_STATS = {
        { {100, 100, 0}, {90, 100, 0}, {90, 100, 0}, {90, 85, 0} }, // Venusaur
        { {90, 100, 0}, {90, 100, 0}, {80, 100, 0}, {80, 100, 0} }, // Ninetales
        { {80, 100, 0}, {95, 100, 0}, {90, 100, 0}, {90, 100, 0} }, // Gengar
        { {90, 100, 0}, {90, 100, 0}, {90, 100, 0}, {90, 100, 0} }, // Mew
        { {90, 90, 0}, {75, 100, 0}, {75, 90, 0}, {80, 100, 0} }, // Feraligatr
        { {100, 75, 0}, {100, 80, 0}, {100, 100, 0}, {65, 95, 0} }, // Steelix
        { {90, 100, 0}, {95, 100, 0}, {120, 70, 0}, {90, 100, 0} }, // Gardevoir
        { {80, 100, 0}, {80, 90, 0}, {120, 100, 0}, {70, 100, 0} }, // Salamence
        { {80, 1000, 0}, {80, 100, 0}, {100, 80, 0}, {80, 100, 2} }, // Lucario
        { {80, 100, 0}, {70, 100, 0}, {85, 90, 0}, {40, 100, 1} }, // Weavile
        { {90, 100, 0}, {120, 100, 0}, {60, 1000, 0}, {90, 100, 0} }, // Leafeon
        { {120, 80, 0}, {85, 100, 0}, {60, 100, 0}, {250, 100, 0} }, // Garbodor
        { {0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0} } //
    };

    StatusEffectEnum[][] MOVE_STATUS_EFFECT = {
        { StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.PSN, StatusEffectEnum.NON }, // Venusaur
        { StatusEffectEnum.BRN, StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON }, // Ninetales
        { StatusEffectEnum.NON, StatusEffectEnum.PSN, StatusEffectEnum.NON, StatusEffectEnum.PAR }, // Gengar
        { StatusEffectEnum.BRN, StatusEffectEnum.PAR, StatusEffectEnum.NON, StatusEffectEnum.FRZ }, // Mew
        { StatusEffectEnum.NON, StatusEffectEnum.FRZ, StatusEffectEnum.NON, StatusEffectEnum.NON }, // Feraligatr
        { StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.FRZ }, // Steelix
        { StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON }, // Gardevoir
        { StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON }, // Salamence
        { StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON }, // Lucario
        { StatusEffectEnum.PSN, StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON }, // Weavile
        { StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON, StatusEffectEnum.NON }, // Leafeon
        { StatusEffectEnum.PSN, StatusEffectEnum.PAR, StatusEffectEnum.NON, StatusEffectEnum.NON }, // Garbodor
        { null, null, null, null } //
    };

    int[][] MOVE_STATUS_EFFECT_CHANCE = {
        { 0, 0, 30, 0 }, // Venusaur
        { 10, 0, 0, 0 }, // Ninetales
        { 0, 10, 0, 10 }, // Gengar
        { 10, 10, 0, 10 }, // Mew
        { 0, 10, 0, 0 }, // Feraligatr
        { 0, 0, 0, 10 }, // Steelix
        { 0, 0, 0, 0 }, // Gardevoir
        { 0, 0, 0, 0 }, // Salamence
        { 0, 0, 0, 0 }, // Lucario
        { 30, 0, 0, 0 }, // Weavile
        { 0, 0, 0, 0 }, // Leafeon
        { 30, 30, 0, 0 }, // Garbodor
        { 0, 0, 0, 0 } //
    };

    boolean[][] MOVE_RECOIL = {
        { false, false, false, true }, // Venusaur
        { false, false, false, false }, // Ninetales
        { false, false, false, false }, // Gengar
        { false, false, false, false }, // Mew
        { false, false, false, false }, // Feraligatr
        { false, false, false, false }, // Steelix
        { false, false, false, false }, // Gardevoir
        { false, false, true, false }, // Salamence
        { false, false, false, false }, // Lucario
        { false, false, false, false }, // Weavile
        { false, true, false, false }, // Leafeon
        { false, false, false, true }, // Garbodor
        { false, false, false, false } //
    };

    int POKEMON_FRONT_IMAGE_SIZE = 150;
    int POKEMON_BACK_IMAGE_SIZE = 225;
}
