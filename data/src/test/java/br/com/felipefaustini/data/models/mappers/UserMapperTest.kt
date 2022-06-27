package br.com.felipefaustini.data.models.mappers

import br.com.felipefaustini.data.models.response.PokemonsDataResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class UserMapperTest {

    @Test
    fun map_pokemonsResponseTest() {
        val expected = "Poke"
        val pokemonsResponse = PokemonsDataResponse(name = "Poke", url = "")

        assertEquals(expected, pokemonsResponse.name)
    }

}