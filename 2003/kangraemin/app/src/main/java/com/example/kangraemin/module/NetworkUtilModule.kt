package com.example.kangraemin.module

import com.example.kangraemin.util.NetworkUtil
import org.koin.dsl.module

val networkUtilModule = module {

    // For NetworkUtil
    single {
        NetworkUtil(get())
    }
}