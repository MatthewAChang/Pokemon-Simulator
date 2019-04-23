package Game.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String name;
    private List<Pokemon> pokemon;
    private int pokemonAwakeNum;
    private int pokemonTotalNum;
    private int currentPokemon;

    public Trainer(String name, List<Pokemon> pokemon) {
        this.name = name;
        this.pokemon = new ArrayList<>();
        for (Pokemon p : pokemon) {
            this.pokemon.add(Pokemon.copy(p));
        }
        this.pokemonAwakeNum = pokemon.size();
        this.pokemonTotalNum = this.pokemonAwakeNum;
        this.currentPokemon = -1;
    }

    private Trainer(String name, List<Pokemon> pokemon, int pokemonAwakeNum, int pokemonTotalNum, int currentPokemon) {
        this.name = name;
        this.pokemon = new ArrayList<>();
        for (Pokemon p : pokemon) {
            this.pokemon.add(Pokemon.copy(p));
        }
        this.pokemonAwakeNum = pokemonAwakeNum;
        this.pokemonTotalNum = pokemonTotalNum;
        this.currentPokemon = currentPokemon;
    }

    public static Trainer copy(Trainer other) {
        return new Trainer(other.name, other.pokemon, other.pokemonAwakeNum, other.pokemonTotalNum, other.currentPokemon);
    }

    public String getName() {
        return name;
    }

    public Pokemon getPokemon(int index) {
        return pokemon.get(index);
    }

    public int getPokemonAwakeNum() {
        return pokemonAwakeNum;
    }

    public int getPokemonTotalNum() {
        return pokemonTotalNum;
    }

    public void pokemonFaints() {
        --pokemonAwakeNum;
    }

    public void setCurrentPokemon(int pokemonNum) {
        currentPokemon = pokemonNum;
    }

    public Pokemon getCurrentPokemon() {
        return getPokemon(currentPokemon);
    }

    public int getCurrentPokemonIndex() {
        return currentPokemon;
    }
}
