package com.example.pokemonretrofit.model

import java.io.Serializable

data class PokemonList(
    var pokemon: List<Pokemon>? = null
):Serializable
