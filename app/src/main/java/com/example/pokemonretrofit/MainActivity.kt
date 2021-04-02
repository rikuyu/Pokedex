package com.example.pokemonretrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonretrofit.adapter.PokemonAdapter
import com.example.pokemonretrofit.model.DetailPokemon
import com.example.pokemonretrofit.model.Pokemon
import com.example.pokemonretrofit.repository.Repository

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var pokemonAdapter: PokemonAdapter

    private val TAG = "retrofit"

    private lateinit var viewModel: MainViewModel
    var pokemonList: List<Pokemon>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getPokemon()

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager =
            GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)

        viewModel.pokemons.observe(this, { response ->
            if (response.isSuccessful) {
                response.body()!!.pokemon.let { pokemonList = it }
                pokemonAdapter = PokemonAdapter(this, pokemonList ?: emptyList())
                recyclerView.adapter = pokemonAdapter
                Log.d(TAG, "onCreate:" + response.body()?.pokemon)

                pokemonAdapter.setOnPokemonClickListener(
                    object : PokemonAdapter.OnPokemonClickListener {
                        override fun onItemClickListener(
                            pokemon: DetailPokemon,
                            pokemonList: List<Pokemon>
                        ) {
                            val intent = Intent(
                                applicationContext,
                                PokemonDetailActivity::class.java
                            )
                            intent.putExtra("POKEMON", pokemon)
                            intent.putStringArrayListExtra(
                                "ALL",
                                pokemonList as ArrayList<String?>?
                            )
                            startActivity(intent)
                        }
                    }
                )
            }
        })
    }
}