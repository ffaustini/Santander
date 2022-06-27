package br.com.felipefaustini.data.repository

import br.com.felipefaustini.data.api.SantanderApi
import br.com.felipefaustini.data.models.mappers.PokemonMapper
import br.com.felipefaustini.data.utils.handleResponseCall
import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.domain.repository.PokemonRepository
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class SantanderRepositoryImpl(
    private val api: SantanderApi,
    private val coroutineContext: CoroutineContext
): PokemonRepository {

    override fun getPokemons(page: Int, perPage: Int): Flow<Result<List<Pokemon>>> = flow<Result<List<Pokemon>>> {
        val request = api.getPokemons(
            limit = perPage,
            offset = page
        )
        val responsePokemons = handleResponseCall(request) {
            it.results.map { pokemon -> PokemonMapper.map(pokemon) }
        }
        emit(responsePokemons)
    }
        .catch {
            it.printStackTrace()
            emit(Result.Error(ErrorEntity.Unknown))
        }
        .flowOn(coroutineContext)

    override fun getPokemon(url: String): Flow<Result<Pokemon>> = flow {
        val request = api.getPokemon(url)
        val responsePokemon = handleResponseCall(request) {
            PokemonMapper.map(it)
        }
        emit(responsePokemon)
    }
        .catch {
            it.printStackTrace()
            emit(Result.Error(ErrorEntity.Unknown))
        }
        .flowOn(coroutineContext)

}