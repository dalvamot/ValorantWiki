package com.vt.valorantwiki.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vt.valorantwiki.rest.AgentsRepository
import com.vt.valorantwiki.rest.AgentsRepositoryImpl
import com.vt.valorantwiki.rest.ValorantWikiAPI
import com.vt.valorantwiki.viewmodel.AgentsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module{

    //providing the agents repository implementation
    fun provideAgentsRepo(valorantWikiAPI: ValorantWikiAPI): AgentsRepository = AgentsRepositoryImpl(valorantWikiAPI)

    // providing Gson object
    fun provideGson() = GsonBuilder().create()

    //providing logging interceptor
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    // providing okhttp client
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    // providing retrofit builder
    fun provideValorantWikiAPI(okHttpClient: OkHttpClient, gson: Gson): ValorantWikiAPI =
        Retrofit.Builder()
            .baseUrl(ValorantWikiAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(ValorantWikiAPI::class.java)

    single { provideGson() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideValorantWikiAPI(get(), get()) }
    single { provideAgentsRepo(get()) }

}

val viewModelModule = module {
    viewModel { AgentsViewModel(get()) }
}