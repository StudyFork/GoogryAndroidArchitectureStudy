package study.architecture.data.datasource.local.di

import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import study.architecture.data.datasource.local.CoinDatabase
import study.architecture.data.datasource.local.LocalDataSource
import study.architecture.data.datasource.local.LocalDataSourceImpl

val localModule = module {
    single<LocalDataSource> { LocalDataSourceImpl(get()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            CoinDatabase::class.java,
            "coin"
        ).build()
    }

}
