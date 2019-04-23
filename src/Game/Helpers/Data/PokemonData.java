package Game.Helpers.Data;

import Game.Helpers.Enums.TypeEnum;

public interface PokemonData {
    int NUM_OF_POKEMON = 12;

    int LEVEL = 50;

    String []TRAINER_NAMES = {"Red", "Blue"};

    String []POKEMON_NAMES = {
        "Venusaur",
        "Ninetales",
        "Mew",
        "Feraligatr",
        "Umbreon",
        "Steelix",
        "Gardevoir",
        "Salamence",
        "Lucario",
        "Weavile",
        "Leafeon",
        "Garbodor",
    };

    TypeEnum[][] POKEMON_TYPES = {
        { TypeEnum.GRA, TypeEnum.POI },
        { TypeEnum.FIR, TypeEnum.NON },
        { TypeEnum.PSY, TypeEnum.NON },
        { TypeEnum.WAT, TypeEnum.NON },
        { TypeEnum.DAR, TypeEnum.NON },
        { TypeEnum.STE, TypeEnum.GRO },
        { TypeEnum.PSY, TypeEnum.FAI },
        { TypeEnum.DRA, TypeEnum.FLY },
        { TypeEnum.FIG, TypeEnum.STE },
        { TypeEnum.DAR, TypeEnum.ICE },
        { TypeEnum.GRA, TypeEnum.NON },
        { TypeEnum.POI, TypeEnum.NON }
    };

    int[][] POKEMON_STATS = {
        { 155, 102, 103, 120, 120, 100 }, // Venusaur
        { 148,  96,  95, 101, 120, 120 }, // Ninetales
        { 175, 120, 120, 120, 120, 120 }, // Mew
        { 160, 125, 120,  99, 103,  98 }, // Feraligatr
        { 170,  85, 130,  80, 150,  85 }, // Umbreon
        { 150, 105, 220,  75,  85,  50 }, // Steelix
        { 143,  85,  85, 145, 135, 100 }, // Gardevoir
        { 170, 155, 100, 130, 100, 120 }, // Salamence
        { 145, 130,  90, 135,  90, 110 }, // Lucario
        { 145, 140,  85,  65, 105, 145 }, // Weavile
        { 140, 130, 150,  80,  85, 115 }, // Leafeon
        { 155, 115, 102,  80, 102,  95 } // Garbodor
    };
}
