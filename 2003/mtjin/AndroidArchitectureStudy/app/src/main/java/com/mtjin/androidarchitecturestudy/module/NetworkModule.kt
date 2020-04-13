package com.mtjin.androidarchitecturestudy.module

import com.mtjin.androidarchitecturestudy.utils.NetworkManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val networkModule: Module = module {
    single { NetworkManager(androidContext()) }
}