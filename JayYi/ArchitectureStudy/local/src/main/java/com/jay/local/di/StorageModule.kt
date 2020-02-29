package com.jay.local.di

import android.content.Context
import androidx.room.Room
import com.jay.local.NaverSearchLocalDataSource
import com.jay.local.database.SearchHistoryDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            SearchHistoryDatabase::class.java,
            "search_history.db"
        )
            .build()
    }

    single {
        androidContext().getSharedPreferences(
            NaverSearchLocalDataSource.PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }
}