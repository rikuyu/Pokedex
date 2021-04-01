package com.example.pokemonretrofit.repository

import com.example.pokemonretrofit.model.Pokemon
import com.example.pokemonretrofit.model.PokemonList
import com.example.pokemonretrofit.model.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class Repository {

    suspend fun getPokemon(): Response<PokemonList> {
        val response: Response<PokemonList> = RetrofitInstance.api.getPokemon()
        return response
    }
}