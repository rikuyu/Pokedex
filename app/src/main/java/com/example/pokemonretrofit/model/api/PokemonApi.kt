package com.example.pokemonretrofit.model.api

import com.example.pokemonretrofit.model.PokemonList
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {
    @GET("pokedex.json")
    suspend fun getPokemon(): Response<PokemonList>
}