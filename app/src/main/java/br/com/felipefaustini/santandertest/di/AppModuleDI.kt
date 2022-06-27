package br.com.felipefaustini.santandertest.di

import br.com.felipefaustini.domain.usecases.home.IHomeUseCase
import br.com.felipefaustini.santandertest.presentation.details.DetailsViewModel
import br.com.felipefaustini.santandertest.presentation.home.HomeViewModel
import org.koin.dsl.module

val appModule = module {
    fun provideHomeViewModel(homeUseCase: IHomeUseCase): HomeViewModel {
        return HomeViewModel(homeUseCase)
    }

    single { provideHomeViewModel(homeUseCase = get()) }

    fun provideDetailsViewModel(): DetailsViewModel {
        return DetailsViewModel()
    }

    single { provideDetailsViewModel() }
}