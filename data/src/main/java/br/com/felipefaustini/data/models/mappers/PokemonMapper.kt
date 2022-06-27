package br.com.felipefaustini.data.models.mappers

import br.com.felipefaustini.data.models.response.PokemonResponse
import br.com.felipefaustini.data.models.response.PokemonsDataResponse
import br.com.felipefaustini.data.models.response.StatsResponse
import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.domain.models.Stats

object PokemonMapper {
    fun map(pokemonsDataResponse: PokemonsDataResponse) = Pokemon(
        id = null,
        name = pokemonsDataResponse.name,
        url = pokemonsDataResponse.url,
        image = null,
        stats = emptyList()
    )

    fun map(pokemonResponse: PokemonResponse) = Pokemon(
        id = pokemonResponse.id,
        name = pokemonResponse.name,
        url = null,
        image = pokemonResponse.sprites?.other?.home?.imageUrl,
        stats = pokemonResponse.stats?.map { map(it) } ?: emptyList()
    )

    fun map(statsResponse: StatsResponse) = Stats(
        value = statsResponse.baseStat,
        name = statsResponse.stat.name
    )
}
