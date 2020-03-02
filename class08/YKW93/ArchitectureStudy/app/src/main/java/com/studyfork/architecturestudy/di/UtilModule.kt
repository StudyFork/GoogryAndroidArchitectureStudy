package com.studyfork.architecturestudy.di

import com.studyfork.architecturestudy.base.BaseApplication
import com.studyfork.architecturestudy.common.ResourceProvider
import org.koin.dsl.module

val utilModule = module {
    factory {
        ResourceProvider(BaseApplication.applicationContext())
    }
}