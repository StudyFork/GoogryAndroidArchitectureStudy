package com.ironelder.androidarchitecture.component

import android.app.Application
import com.ironelder.androidarchitecture.data.RetrofitForNaver
import com.ironelder.androidarchitecture.data.database.SearchResultDatabase
import com.ironelder.androidarchitecture.data.repository.SearchDataRepositoryImpl
import com.ironelder.androidarchitecture.data.source.local.LocalSearchDataSourceImpl
import com.ironelder.androidarchitecture.data.source.remote.RemoteSearchDataSourceImpl
import com.ironelder.androidarchitecture.view.mainview.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class MainApplication : Application() {

    private val viewModelModule: Module = module {
        single { MainViewModel(get()) }
    }

    private val repositoryModule: Module = module {
        single { SearchDataRepositoryImpl(get(), get()) }
    }

    private val dataSourceModule: Module = module {
        single { SearchResultDatabase.getInstance(androidApplication()) }
        single { LocalSearchDataSourceImpl(get()) }
        single { RemoteSearchDataSourceImpl(get()) }
    }

    private val serviceModule: Module = module {
        single { RetrofitForNaver() }
    }


    override fun onCreate() {
        super.onCreate()
        initialized()
    }

    private fun initialized() {
        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(serviceModule, dataSourceModule, repositoryModule, viewModelModule))
        }
    }

}