package com.jay.architecturestudy.di

import android.content.Context
import androidx.room.Room
import com.jay.architecturestudy.data.database.SearchHistoryDatabase
import com.jay.architecturestudy.data.source.local.NaverSearchLocalDataSource
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