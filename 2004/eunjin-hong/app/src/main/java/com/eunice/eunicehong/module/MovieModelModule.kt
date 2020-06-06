package com.eunice.eunicehong.module

import android.content.Context
import android.provider.SearchRecentSuggestions
import com.eunice.eunicehong.data.source.MovieRepository
import com.eunice.eunicehong.data.source.local.MovieLocalDataSource
import com.eunice.eunicehong.data.source.remote.MovieRemoteDataSource
import com.eunice.eunicehong.data.source.remote.MovieService
import com.eunice.eunicehong.provider.SuggestionProvider
import com.eunice.eunicehong.ui.MoviePreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit

val movieModelModule = module {
    single { provideSearchRecentSuggestions(androidContext()) }

    single<MoviePreferences> { MoviePreferences(androidContext()) }

    single<MovieLocalDataSource> { MovieLocalDataSource(get(), get()) }

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

fun provideMovieService(retrofit: Retrofit): MovieService {
    return retrofit.create(MovieService::class.java)
}
