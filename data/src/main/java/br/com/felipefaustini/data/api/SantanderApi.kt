package br.com.felipefaustini.data.api

import br.com.felipefaustini.data.models.response.PokemonResponse
import br.com.felipefaustini.data.models.response.PokemonsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface SantanderApi {

    @GET("api/v2/pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<PokemonsResponse>

    @GET
    suspend fun getPokemon(
        @Url pokemonUrl: String
    ): Response<PokemonResponse>

}