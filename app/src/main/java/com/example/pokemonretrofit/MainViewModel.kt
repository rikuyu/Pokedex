package com.example.pokemonretrofit

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonretrofit.model.Pokemon
import com.example.pokemonretrofit.model.PokemonList
import com.example.pokemonretrofit.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val pokemons: MutableLiveData<Response<PokemonList>> = MutableLiveData()

    fun getPokemon(){
        viewModelScope.launch{
            val response: Response<PokemonList> = repository.getPokemon()
            pokemons.value = response
        }
    }
}