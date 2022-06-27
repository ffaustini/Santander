package br.com.felipefaustini.santandertest.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.felipefaustini.domain.models.Pokemon
import br.com.felipefaustini.domain.usecases.home.HomeUseCase
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import br.com.felipefaustini.domain.utils.Result
import br.com.felipefaustini.santandertest.MainCoroutineRule
import br.com.felipefaustini.santandertest.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var homeUseCase: IHomeUseCase

    @InjectMocks
    private lateinit var homeViewModel: HomeViewModel

    /* region getPokemons */
    @Test
    fun getPokemons_shouldReturnSuccessList() = runBlockingTest {
        val expected = listOf<Pokemon>(
            Pokemon(id = 1, name = "POKE", "POKE", null, emptyList())
        )

        Mockito.`when`(homeUseCase.getPokemons(0, 10))
            .thenReturn(flowOf(Result.Success(listOf(Pokemon(id = 1, name = "POKE", "POKE", null, emptyList())))))
        Mockito.`when`(homeUseCase.getPokemon("POKE"))
            .thenReturn(flowOf(Result.Success(Pokemon(id = 1, name = "POKE", null, null, emptyList()))))

        homeViewModel.getPokemons()

        val result = homeViewModel.pokemonsListLiveData.getOrAwaitValue()

        assertEquals(expected, result)
    }
    /* endregion getPokemons */

}