package com.studyfork.architecturestudy.di

import com.studyfork.architecturestudy.common.ResourceProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val utilModule = module {
    factory {
        ResourceProvider(androidApplication())
    }
}