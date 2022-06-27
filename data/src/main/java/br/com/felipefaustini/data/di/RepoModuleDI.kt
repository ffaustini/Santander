package br.com.felipefaustini.data.di

import br.com.felipefaustini.data.api.SantanderApi
import br.com.felipefaustini.data.repository.SantanderRepositoryImpl
import br.com.felipefaustini.domain.repository.PokemonRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val repoModule = module {
    fun provideRepositoryModule(
        emojiApi: SantanderApi
    ): PokemonRepository {
        return SantanderRepositoryImpl(emojiApi, Dispatchers.IO)
    }

    single { provideRepositoryModule(get()) }
}
