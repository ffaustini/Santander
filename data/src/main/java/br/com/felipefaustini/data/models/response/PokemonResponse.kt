package br.com.felipefaustini.data.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PokemonResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "sprites") val sprites: SpritesResponse?,
    @Json(name = "height") val height: Long?,
    @Json(name = "weight") val weight: Long?,
    @Json(name = "stats") val stats: List<StatsResponse>?,
)

@JsonClass(generateAdapter = true)
class SpritesResponse(
    @Json(name = "other") val other: OtherResponse
)

@JsonClass(generateAdapter = true)
class OtherResponse(
    @Json(name = "home") val home: HomeResponse
)

@JsonClass(generateAdapter = true)
class HomeResponse(
    @Json(name = "front_default") val imageUrl: String
)

@JsonClass(generateAdapter = true)
class StatsResponse(
    @Json(name = "base_stat") val baseStat: Int,
    @Json(name = "stat") val stat: StatResponse
)

@JsonClass(generateAdapter = true)
class StatResponse(
    @Json(name = "name") val name: String
)
