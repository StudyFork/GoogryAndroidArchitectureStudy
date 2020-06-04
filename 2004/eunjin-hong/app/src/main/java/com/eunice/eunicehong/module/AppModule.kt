package com.eunice.eunicehong.module

import android.content.Context
import android.provider.SearchRecentSuggestions
import com.eunice.eunicehong.BuildConfig
import com.eunice.eunicehong.data.source.MovieRepository
import com.eunice.eunicehong.data.source.local.MovieLocalDataSource
import com.eunice.eunicehong.data.source.remote.MovieRemoteDataSource
import com.eunice.eunicehong.data.source.remote.MovieService
import com.eunice.eunicehong.provider.SuggestionProvider
import com.eunice.eunicehong.ui.MoviePreferences
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single { provideSearchRecentSuggestions(androidContext()) }

    single<MoviePreferences> { MoviePreferences(androidContext()) }

    single<MovieLocalDataSource> { MovieLocalDataSource(get(), get()) }

    single<OkHttpClient> { provideOkHttpClient() }

    single<Retrofit> { provideRetrofit(get()) }

    single<MovieService> { provideMovieService(get()) }

    single<MovieRemoteDataSource> { MovieRemoteDataSource() }

    single<MovieRepository> { MovieRepository(get(), get()) }

}

fun provideSearchRecentSuggestions(context: Context): SearchRecentSuggestions {
    return SearchRecentSuggestions(
        context,
        SuggestionProvider.AUTHORITY,
        SuggestionProvider.MODE
    )
}

fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    level = if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor.Level.BODY
                    } else {
                        HttpLoggingInterceptor.Level.NONE
                    }
                })
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.MOVIE_APP_SERVER_HOST)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideMovieService(retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
}