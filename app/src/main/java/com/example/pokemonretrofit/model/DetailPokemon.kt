package com.example.pokemonretrofit.model

import java.io.Serializable

data class DetailPokemon(
    val num: String? = null,
    val name: String? = null,
    val img: String? = null,
    val height: String? = null,
    val weight: String? = null
) : Serializable
