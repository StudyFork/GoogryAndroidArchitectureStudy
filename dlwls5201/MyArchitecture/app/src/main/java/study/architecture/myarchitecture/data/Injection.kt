package study.architecture.myarchitecture.data

import android.content.Context
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.data.repository.UpbitRepositoryImpl
import study.architecture.myarchitecture.data.source.local.LocalDataSource
import study.architecture.myarchitecture.data.source.remote.RemoteDataSource
import study.architecture.myarchitecture.util.NetworkUtil

object Injection {

    fun provideFolderRepository(context: Context): UpbitRepository {
        val localDataSource = LocalDataSource.getInstance(context)
        return UpbitRepositoryImpl(
            RemoteDataSource.provideUpbitApi(),
            localDataSource.getUpbitDao(),
            NetworkUtil.isOnline(context)
        )

    }
}