package br.com.felipefaustini.domain.di

import br.com.felipefaustini.domain.repository.PokemonRepository
import br.com.felipefaustini.domain.usecases.home.HomeUseCase
import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import org.koin.dsl.module

val domainModule = module {
    fun provideHomeUseCase(repository: PokemonRepository): IHomeUseCase {
        return HomeUseCase(repository)
    }

    factory { provideHomeUseCase(repository = get()) }
}
