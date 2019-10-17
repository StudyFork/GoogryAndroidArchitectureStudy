package study.architecture.myarchitecture.di

import androidx.room.Room
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import study.architecture.myarchitecture.data.source.local.LocalDataBase
import study.architecture.myarchitecture.data.source.local.UpbitLocalDataSource
import study.architecture.myarchitecture.data.source.local.UpbitLocalLocalDataSourceImpl

val localModule = module {

    single<UpbitLocalDataSource> {
        UpbitLocalLocalDataSourceImpl(get())
    }

    single {
        get<LocalDataBase>().getUpbitDao()
    }

    single {
        Room.databaseBuilder(
            androidApplication(),
            LocalDataBase::class.java,
            "upbit.db"
        ).fallbackToDestructiveMigration()  //테스트용 : 빌드 시 마다 기존 데이터베이스 삭제
            .build()
    }
}