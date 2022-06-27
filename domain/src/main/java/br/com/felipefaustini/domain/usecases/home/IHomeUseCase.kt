package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface IHomeUseCase {
    suspend fun getPokemons(offset: Int, limit: Int): Flow<Result<List<Pokemon>>>
    suspend fun getPokemon(url: String?): Flow<Result<Pokemon>>
}