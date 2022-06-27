package br.com.felipefaustini.santandertest.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.felipefaustini.domain.models.Stats
import br.com.felipefaustini.santandertest.databinding.ItemStatsBinding

class StatsAdapter: ListAdapter<Stats, StatsAdapter.StatsViewHolder>(
    statsDiffUtil()
) {

    fun setData(list: List<Stats>) {
        this.submitList(list.toMutableList())
    }

    override fun submitList(list: MutableList<Stats>?) {
        super.submitList(list?.let { ArrayList(it) })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemStatsBinding.inflate(inflater)
        return StatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StatsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class StatsViewHolder(private val binding: ItemStatsBinding): RecyclerView.ViewHolder(
        binding.root
    ) {
        
        fun bind(stats: Stats) {
            binding.stats = stats
            binding.executePendingBindings()
        }
        
    }

}

fun statsDiffUtil() = object : DiffUtil.ItemCallback<Stats>() {
    override fun areItemsTheSame(oldItem: Stats, newItem: Stats): Boolean {
        return false
    }

    override fun areContentsTheSame(oldItem: Stats, newItem: Stats): Boolean {
        return false
    }
}
