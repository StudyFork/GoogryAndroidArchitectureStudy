package com.example.androidarchitecture.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.androidarchitecture.common.StringConst
import com.example.androidarchitecture.data.datasource.database.SearchHistDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageMoudule = module {
    // 룸
    single {
        Room.databaseBuilder(
            androidContext(),
            SearchHistDatabase::class.java, "search_history.db"
        ).build()
    }


    // 쉐어드
    single<SharedPreferences> {
        androidContext().getSharedPreferences(
            StringConst.PREF_KEY,
            Context.MODE_PRIVATE
        )
    }

}