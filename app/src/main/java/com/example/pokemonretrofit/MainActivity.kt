package com.example.pokemonretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonretrofit.adapter.PokemonAdapter
import com.example.pokemonretrofit.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter

    private val TAG = "retrofit"

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getPokemon()

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        viewModel.pokemons.observe(this, { response ->
            if (response.isSuccessful) {
                pokemonAdapter = PokemonAdapter(this, response.body()?.pokemon ?: emptyList())
                recyclerView.adapter = pokemonAdapter
                Log.d(TAG, "onCreate:" + response.body()?.pokemon)
            }
        })
    }
}