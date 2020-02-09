package com.example.handnew04.di

import android.app.Application
import com.example.handnew04.data.remote.MovieRemoteDataSource
import com.example.handnew04.data.remote.MovieRemoteDataSourceImpl
import com.example.handnew04.network.NetworkManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val remoteDataModule = module { single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl() } }

val networkManagerModule =
    module { single<NetworkManager> { NetworkManager(androidContext() as Application) } }