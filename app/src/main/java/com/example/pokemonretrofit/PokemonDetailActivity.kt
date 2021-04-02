package com.example.pokemonretrofit

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokemonretrofit.model.DetailPokemon
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

class PokemonDetailActivity : AppCompatActivity() {

    lateinit var imgPokemon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)

        val pokemon = intent.getSerializableExtra("POKEMON")
        val pokemonList = intent.getStringArrayListExtra("ALL")

        imgPokemon = findViewById(R.id.img_pokemon)

        if (pokemon is DetailPokemon) {
            num_pokemon.text = pokemon.num
            Glide.with(this).load(pokemon.img)
                .apply(RequestOptions().placeholder(R.drawable.ic_image))
                .apply(RequestOptions().override(500, 500))
                .into(imgPokemon);
            name_pokemon.text = pokemon.name
            height_pokemon.text = pokemon.height
            weight_pokemon.text = pokemon.weight
        }

        back_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}