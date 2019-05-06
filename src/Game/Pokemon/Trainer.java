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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        getCurrentPokemon().setFainted();
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
