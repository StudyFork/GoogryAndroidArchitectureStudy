package com.example.handnew04.di

import android.app.Application
import com.example.handnew04.network.NetworkManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val networkManagerModule =
    module { single<NetworkManager> { NetworkManager(androidContext() as Application) } }