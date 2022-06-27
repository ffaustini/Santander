package br.com.felipefaustini.domain.repository

import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    fun getPokemons(page: Int, perPage: Int): Flow<Result<List<Pokemon>>>
    fun getPokemon(url: String): Flow<Result<Pokemon>>
}