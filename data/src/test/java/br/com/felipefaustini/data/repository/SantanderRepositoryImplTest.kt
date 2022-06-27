package br.com.felipefaustini.data.repository

import br.com.felipefaustini.data.api.SantanderApi
import br.com.felipefaustini.data.models.response.PokemonResponse
import br.com.felipefaustini.data.models.response.PokemonsDataResponse
import br.com.felipefaustini.data.models.response.PokemonsResponse
import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class SantanderRepositoryImplTest {

    @Mock
    private lateinit var santanderApi: SantanderApi

    private lateinit var repository: SantanderRepositoryImpl

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun beforeEachTest() {
        repository = SantanderRepositoryImpl(
            santanderApi,
            dispatcher
        )
    }

    /* region getPokemons */
    @Test
    fun getPokemons_returnListOfPokemons() = dispatcher.runBlockingTest {
        val expected = Result.Success(
            listOf<Pokemon>(
                Pokemon(id = null, name = "poke1", url = "", image = null, emptyList()),
                Pokemon(id = null, name = "poke2", url = "", image = null, emptyList()),
            )
        )

        whenever(santanderApi.getPokemons(10, 0))
            .thenReturn(
                Response.success(
                    PokemonsResponse(results = listOf(PokemonsDataResponse(name = "poke1", url = ""), PokemonsDataResponse(name = "poke2", url = ""),))
                )
            )

        val result = repository.getPokemons(0, 10).first()

        verify(santanderApi, times(1)).getPokemons(10, 0)
        assertEquals(expected, result)
    }

    @Test
    fun getPokemons_error() = dispatcher.runBlockingTest {
        val expected = Result.Error(ErrorEntity.Unknown)

        whenever(santanderApi.getPokemons(10, 0))
            .thenReturn(
                Response.error(
                    500,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )

        val response = repository.getPokemons(0, 10).first()

        verify(santanderApi, times(1)).getPokemons(10, 0)
        assertEquals(expected, response)
    }
    /* endregion getPokemons */

    /* region getPokemon */
    @Test
    fun getPokemon_returnPokemonData() = dispatcher.runBlockingTest {
        val expected = Result.Success(Pokemon(id = 1, name = "poke1", url = null, image = null, emptyList()))

        whenever(santanderApi.getPokemon("")).thenReturn(
            Response.success(
                PokemonResponse(
                    id = 1,
                    name = "poke1",
                    sprites = null,
                    height = null,
                    weight = null,
                    stats = null
                )
            )
        )

        val result = repository.getPokemon("").first()

        assertEquals(expected, result)
    }

    @Test
    fun getPokemon_returnError() = dispatcher.runBlockingTest {
        val expected = Result.Error(ErrorEntity.Unknown)

        whenever(santanderApi.getPokemon(""))
            .thenReturn(
                Response.error(
                    500,
                    "".toResponseBody("application/json".toMediaTypeOrNull())
                )
            )

        val result = repository.getPokemon("").first()

        assertEquals(expected, result)
    }
    /* endregion getPokemon */

}