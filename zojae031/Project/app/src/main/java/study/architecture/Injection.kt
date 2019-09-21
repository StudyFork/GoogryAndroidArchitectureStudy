package study.architecture

import android.content.Context
import android.net.ConnectivityManager
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.data.local.LocalDataSourceImpl
import study.architecture.data.remote.RemoteDataSourceImpl
import study.architecture.data.remote.UpbitRemoteDataSource
import study.architecture.data.repository.RepositoryImpl

class Injection private constructor(context: Context) {

    companion object {
        private var repository: RepositoryImpl? = null

        fun getRepository(context: Context): RepositoryImpl {
            if (repository == null) {
                repository = RepositoryImpl.getInstance(
                    RemoteDataSourceImpl.getInstance(
                        Retrofit
                            .Builder()
                            .baseUrl(RemoteDataSourceImpl.url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build().create(
                                UpbitRemoteDataSource::
                                class.java
                            )
                    ),
                    LocalDataSourceImpl.getInstance(context),
                    context.getSystemService(
                        Context.CONNECTIVITY_SERVICE
                    ) as ConnectivityManager
                )
            }
            return repository!!
        }
    }

}