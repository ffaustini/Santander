package br.com.felipefaustini.santandertest

import android.app.Application
import br.com.felipefaustini.data.di.apiModule
import br.com.felipefaustini.data.di.repoModule
import br.com.felipefaustini.domain.di.domainModule
import br.com.felipefaustini.santandertest.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class SantanderApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoinDI()
    }

    private fun startKoinDI() {
        startKoin {
            androidLogger()
            androidContext(this@SantanderApplication)
            modules(
                apiModule,
                repoModule,
                domainModule,
                appModule,
            )
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }

}