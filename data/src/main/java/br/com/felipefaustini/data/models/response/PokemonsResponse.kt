package br.com.felipefaustini.data.models.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PokemonsResponse(
    @Json(name = "results") val results: List<PokemonsDataResponse>
)

@JsonClass(generateAdapter = true)
class PokemonsDataResponse(
    @Json(name = "name") val name: String,
    @Json(name = "url") val url: String
)
