package com.example.pokemonretrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokemonretrofit.R
import com.example.pokemonretrofit.model.Pokemon
import com.example.pokemonretrofit.model.PokemonList
import retrofit2.Call
import retrofit2.Response

class PokemonAdapter(private var context: Context, private var pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    inner class PokemonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pokeImage: ImageView
        var pokemonNum: TextView
        var pokeName: TextView

        init {
            pokeImage = itemView.findViewById(R.id.img_pokemon)
            pokeName = itemView.findViewById(R.id.name_pokemon)
            pokemonNum = itemView.findViewById(R.id.num_pokemon)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokemonAdapter.PokemonHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_pokemon, parent, false)
        return PokemonHolder(view)
    }

    override fun onBindViewHolder(holder: PokemonAdapter.PokemonHolder, position: Int) {
        holder.pokemonNum.text = pokemonList[position].num
        holder.pokeName.text = pokemonList[position].name
        Glide.with(context).load(pokemonList[position].img).apply(RequestOptions().override(200, 200)).into(holder.pokeImage)
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }

    fun setPokemonList(newList: List<Pokemon>) {
        pokemonList = newList
        notifyDataSetChanged()
    }
}