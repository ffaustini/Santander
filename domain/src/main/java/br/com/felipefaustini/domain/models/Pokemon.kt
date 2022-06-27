package br.com.felipefaustini.domain.models

import java.io.Serializable

data class Pokemon(
    val id: Long?,
    val name: String,
    val url: String?,
    val image: String?,
    val stats: List<Stats>
): Serializable

data class Stats(
    val value: Int,
    val name: String
): Serializable
