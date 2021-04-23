package com.example.pokemonretrofit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokemonretrofit.databinding.ActivityPokemonDetailBinding
import com.example.pokemonretrofit.model.DetailPokemon

class PokemonDetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityPokemonDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPokemonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pokemon = intent.getSerializableExtra("POKEMON")


        if (pokemon is DetailPokemon) {
           binding.numPokemon.text = pokemon.num
            Glide.with(this).load(pokemon.img)
                .apply(RequestOptions().placeholder(R.drawable.ic_image))
                .apply(RequestOptions().override(500, 500))
                .into(binding.imgPokemon);
            binding.namePokemon.text = pokemon.name
            binding.heightPokemon.text = pokemon.height
            binding.weightPokemon.text = pokemon.weight
        }

        binding.backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}