package com.example.handnew04.di

import com.example.handnew04.network.NetworkManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkManagerModule =
    module { single { NetworkManager(androidApplication()) } }