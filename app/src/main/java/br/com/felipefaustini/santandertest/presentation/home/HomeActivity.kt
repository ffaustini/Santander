package br.com.felipefaustini.santandertest.presentation.home

import androidx.recyclerview.widget.GridLayoutManager
import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.santandertest.R
import br.com.felipefaustini.santandertest.databinding.ActivityHomeBinding
import br.com.felipefaustini.santandertest.presentation.BaseActivity
import br.com.felipefaustini.santandertest.presentation.PaginationListener
import br.com.felipefaustini.santandertest.presentation.details.DetailsActivity
import br.com.felipefaustini.santandertest.utils.extensions.asValue
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(R.layout.activity_home) {

    override val viewModel: HomeViewModel by viewModel()

    private val pokemonsListAdapter = PokemonsListAdapter(
        onClick = ::goToDetails
    )
    
    override fun setupViews() {
        binding?.viewModel = viewModel
        setupRecyclerPokemons()
    }

    override fun setupObservables() {
        super.setupObservables()

        viewModel.pokemonsListLiveData.observe(this) {
            pokemonsListAdapter.setData(it)
        }
    }

    private fun setupRecyclerPokemons() {
        binding?.recyclerPokemons?.apply {
            adapter = pokemonsListAdapter
            addOnScrollListener(object : PaginationListener(this.layoutManager as GridLayoutManager) {
                override fun loadMoreItems() {
                    viewModel.getPokemons()
                }

                override fun isLastPage(): Boolean {
                    return false // TODO("Remove it")
                }

                override fun isLoading(): Boolean {
                    return viewModel.loadingLiveData.asValue()
                }
            })
        }
    }

    private fun goToDetails(pokemon: Pokemon) {
        DetailsActivity.open(this, pokemon)
    }

}