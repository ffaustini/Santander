package br.com.felipefaustini.domain.usecases.home

import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.domain.repository.PokemonRepository
import br.com.felipefaustini.domain.utils.ErrorEntity
import br.com.felipefaustini.domain.utils.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever
import kotlin.math.exp

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class HomeUseCaseTest {

    @Mock
    private lateinit var repository: PokemonRepository

    private lateinit var homeUseCase: HomeUseCase

    private val dispatcher = TestCoroutineDispatcher()

    @Before
    fun beforeEachTest() {
        homeUseCase = HomeUseCase(repository)
    }

    /* region getPokemons */
    @Test
    fun getPokemons_returnListPokemons() = dispatcher.runBlockingTest {
        val expected = Result.Success(
            listOf(
                Pokemon(id = null, name = NAME, "POKE", null, emptyList())
            )
        )

        whenever(repository.getPokemons(0, 10))
            .thenReturn(
                flowOf(
                    Result.Success(
                        listOf(
                            Pokemon(id = null, name = NAME, "POKE", null, emptyList())
                        )
                    )
                )
            )
        whenever(repository.getPokemon("POKE"))
            .thenReturn(
                flowOf(
                    Result.Success(
                        Pokemon(id = null, name = NAME, "POKE", null, emptyList())
                    )
                )
            )

        val result = homeUseCase.getPokemons(0, 10).first()

        verify(repository).getPokemons(0, 10)
        verify(repository).getPokemon("POKE")
        verifyNoMoreInteractions(repository)
        assertEquals(expected, result)
    }
    /* endregion getPokemons */

    /* region getPokemon */
    @Test
    fun getPokemon_returnPokemon() = dispatcher.runBlockingTest {
        val expected = Result.Success(
            Pokemon(id = null, name = NAME, null, null, emptyList())
        )

        whenever(repository.getPokemon("POKE"))
            .thenReturn(
                flowOf(
                    Result.Success(
                        Pokemon(id = null, name = NAME, null, null, emptyList())
                    )
                )
            )

        val result = homeUseCase.getPokemon("POKE").first()

        verify(repository).getPokemon("POKE")
        verifyNoMoreInteractions(repository)
        assertEquals(expected, result)
    }
    /* endregion getPokemon */

    companion object {
        private const val NAME = "poke1"
        private const val ID = 1
    }

}