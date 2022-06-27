package br.com.felipefaustini.santandertest.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.santandertest.databinding.ItemPokemonBinding

class PokemonsListAdapter(
    private val onClick: (Pokemon) -> Unit
): ListAdapter<Pokemon, PokemonsListAdapter.PokemonViewHolder>(
    pokemonsDiffUtil()
) {

    fun setData(list: List<Pokemon>) {
        this.submitList(list.toMutableList())
    }

    override fun submitList(list: MutableList<Pokemon>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonBinding.inflate(inflater)
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class PokemonViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(
        binding.root
    ) {

        fun bind(pokemon: Pokemon) {
            binding.pokemon = pokemon
            binding.root.setOnClickListener { onClick.invoke(pokemon) }
            binding.executePendingBindings()
        }

    }

}

internal fun pokemonsDiffUtil() = object : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        if (oldItem.id != null && newItem.id != null) {
            return oldItem.id == newItem.id
        }
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}
