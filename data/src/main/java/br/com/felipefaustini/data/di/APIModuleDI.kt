package br.com.felipefaustini.data.di

import br.com.felipefaustini.data.BuildConfig
import br.com.felipefaustini.data.api.SantanderApi
import br.com.felipefaustini.data.utils.DateConverter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return interceptor
    }

    single { provideHttpLoggingInterceptor() }

    fun provideOkhttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
        return client.build()
    }

    single { provideOkhttpClient(get()) }

    fun provideRetrofit(client: OkHttpClient): Retrofit {
        val baseUrl = BuildConfig.BASE_URL

        val moshi = Moshi.Builder()
            .add(DateConverter())
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    single { provideRetrofit(get()) }

    fun provideSantanderApi(retrofit: Retrofit): SantanderApi {
        return retrofit.create(SantanderApi::class.java)
    }

    single { provideSantanderApi(get()) }

}