package com.example.model.di

import com.example.model.network.NetworkManager
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkManagerModule =
    module { single { NetworkManager(androidApplication()) } }