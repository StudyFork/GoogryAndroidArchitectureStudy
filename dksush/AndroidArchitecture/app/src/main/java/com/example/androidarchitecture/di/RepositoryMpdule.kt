package com.example.androidarchitecture.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.androidarchitecture.common.StringConst
import com.example.androidarchitecture.data.datasource.database.SearchHistDatabase
import com.example.androidarchitecture.data.datasource.local.NaverLocalDataSourceIml
import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDs
import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDsInterface
import com.example.androidarchitecture.data.repository.NaverRepoImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single { NaverRepoImpl(get(), get()) }
    single{ NaverRemoteDs() }
    single{ NaverLocalDataSourceIml(get(), get()) }
    single { SearchHistDatabase }
    //single { SharedPreferences. }

    single {
        Room.databaseBuilder(androidContext(),
            SearchHistDatabase::class.java, "search_history.db").build()
    }


    single<SharedPreferences> { androidContext().getSharedPreferences(StringConst.PREF_KEY, Context.MODE_PRIVATE) }

    //single { NaverRemoteDs() }
//    single { NaverLocalDataSourceIml(get(), get()) }
//    single { SearchHistDatabase }







}