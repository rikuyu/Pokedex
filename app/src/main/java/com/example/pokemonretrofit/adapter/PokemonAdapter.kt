package com.example.pokemonretrofit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokemonretrofit.R
import com.example.pokemonretrofit.model.DetailPokemon
import com.example.pokemonretrofit.model.Pokemon

class PokemonAdapter(private var context: Context, private var pokemonList: List<Pokemon>) :
    RecyclerView.Adapter<PokemonAdapter.PokemonHolder>() {

    lateinit var listener: OnPokemonClickListener

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
        if (position == 31 || position == 28) {
            holder.pokeName.text = pokemonList[position].name!!.take(9)
        } else {
            holder.pokeName.text = pokemonList[position].name
        }
        holder.pokemonNum.text = pokemonList[position].num
        Glide.with(context).load(pokemonList[position].img)
            .apply(RequestOptions().placeholder(R.drawable.ic_image))
            .apply(RequestOptions().override(220, 220))
            .into(holder.pokeImage)

        holder.itemView.setOnClickListener {
            listener.onItemClickListener(
                DetailPokemon(
                    pokemonList[position].num,
                    pokemonList[position].name,
                    pokemonList[position].img,
                    pokemonList[position].height,
                    pokemonList[position].weight
                ),
                pokemonList
            )
        }
    }

    interface OnPokemonClickListener {
        fun onItemClickListener(pokemon: DetailPokemon, pokemonList: List<Pokemon>)
    }

    fun setOnPokemonClickListener(listener: OnPokemonClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}