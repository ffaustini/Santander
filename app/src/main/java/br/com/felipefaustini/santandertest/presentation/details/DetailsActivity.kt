package br.com.felipefaustini.santandertest.presentation.details

import android.content.Context
import android.content.Intent
import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.santandertest.R
import br.com.felipefaustini.santandertest.databinding.ActivityDetailsBinding
import br.com.felipefaustini.santandertest.presentation.BaseActivity
import org.koin.android.ext.android.inject

class DetailsActivity: BaseActivity<ActivityDetailsBinding, DetailsViewModel>(R.layout.activity_details) {

    override val viewModel: DetailsViewModel by inject()

    private val statsAdater = StatsAdapter()

    override fun setupViews() {
        val pokemon = intent.extras?.get(POKEMON_PARAM) as? Pokemon

        pokemon?.let {
            binding?.pokemon = pokemon
            setupRecyclerView(pokemon)
        }
    }

    private fun setupRecyclerView(pokemon: Pokemon) {
        binding?.recyclerStats?.apply {
            adapter = statsAdater
            statsAdater.setData(pokemon.stats)
        }
    }

    companion object {
        private const val POKEMON_PARAM = "pokemonParam"
        fun open(context: Context, pokemon: Pokemon) {
            val intent = Intent(context, DetailsActivity::class.java).apply {
                putExtra(POKEMON_PARAM, pokemon)
            }
            context.startActivity(intent)
        }
    }

}