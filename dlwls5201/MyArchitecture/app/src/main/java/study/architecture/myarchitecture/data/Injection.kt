package study.architecture.myarchitecture.data

import android.content.Context
import study.architecture.myarchitecture.data.repository.UpbitRepository
import study.architecture.myarchitecture.data.repository.UpbitRepositoryImpl
import study.architecture.myarchitecture.data.source.remote.RemoteDataSource

object Injection {

    fun provideFolderRepository(context: Context): UpbitRepository {
        //내후 내부 디비 적용을 위해 context 객체를 받아옴
        return UpbitRepositoryImpl(RemoteDataSource.provideUpbitApi())
    }
}