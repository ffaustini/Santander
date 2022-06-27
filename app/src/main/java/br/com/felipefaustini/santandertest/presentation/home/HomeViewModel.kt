package br.com.felipefaustini.santandertest.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.santandertest.presentation.BaseViewModel
import br.com.felipefaustini.santandertest.utils.extensions.asValue
import kotlinx.coroutines.flow.collect

class HomeViewModel(
    private val homeUseCase: IHomeUseCase
): BaseViewModel() {

    private var offset = 0
    private val limit = 10

    private val _pokemonsListLiveData = MutableLiveData<List<Pokemon>>().apply {
        value = emptyList()
    }
    val pokemonsListLiveData: LiveData<List<Pokemon>> = _pokemonsListLiveData

    init {
        getPokemons()
    }

    fun getPokemons() {
        launchDataLoad {
            homeUseCase.getPokemons(offset = offset, limit = limit).collect {
                when(val result = it) {
                    is Result.Success -> {
                        _pokemonsListLiveData.postValue(_pokemonsListLiveData.asValue() + result.data)
                        offset+=limit
                    }
                    is Result.Error -> {

                    }
                }
            }
        }
    }

}