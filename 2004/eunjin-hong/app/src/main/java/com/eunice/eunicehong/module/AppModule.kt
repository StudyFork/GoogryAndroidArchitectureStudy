package com.eunice.eunicehong.module

import android.provider.SearchRecentSuggestions
import com.eunice.eunicehong.data.source.MovieRepository
import com.eunice.eunicehong.data.source.local.MovieLocalDataSource
import com.eunice.eunicehong.data.source.remote.MovieRemoteDataSource
import com.eunice.eunicehong.provider.SuggestionProvider
import com.eunice.eunicehong.ui.MoviePreferences
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single {
        SearchRecentSuggestions(
            androidContext(),
            SuggestionProvider.AUTHORITY,
            SuggestionProvider.MODE
        )
    }

    single<MoviePreferences> { MoviePreferences(androidContext()) }

    single<MovieLocalDataSource> { MovieLocalDataSource(get(), get()) }

    single<MovieRemoteDataSource> { MovieRemoteDataSource() }

    single<MovieRepository> { MovieRepository(get(), get()) }

}