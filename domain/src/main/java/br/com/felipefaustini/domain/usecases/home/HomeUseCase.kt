package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.domain.repository.PokemonRepository
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.*

class HomeUseCase(
    private val repository: PokemonRepository
) : IHomeUseCase {

    override suspend fun getPokemons(offset: Int, limit: Int): Flow<Result<List<Pokemon>>> {
        return flow<Result<List<Pokemon>>> {
            repository.getPokemons(page = offset, perPage = limit).collect { pokemons ->
                when(pokemons) {
                    is Result.Success -> {
                        val data = pokemons.data
                        val response = mutableListOf<Pokemon>()
                        data.forEach {
                            getPokemon(it.url).collect {
                                when(val pokemon = it) {
                                    is Result.Success -> response.add(pokemon.data)
                                    is Result.Error -> {}
                                }
                            }
                        }
                        emit(Result.Success(response))
                    }
                    is Result.Error -> {
                        emit(Result.Error(pokemons.error))
                    }
                }
            }
        }
            .catch {
                it.printStackTrace()
                emit(Result.Error(ErrorEntity.Unknown))
            }
    }

    override suspend fun getPokemon(url: String?): Flow<Result<Pokemon>> {
        if (url.isNullOrEmpty()) return flow { Result.Error(ErrorEntity.InvalidFields) }
        return repository.getPokemon(url)
    }

}