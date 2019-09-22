package study.architecture

import android.content.Context
import android.net.ConnectivityManager
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import study.architecture.data.local.CoinDatabase
import study.architecture.data.local.LocalDataSourceImpl
import study.architecture.data.remote.RemoteDataSource
import study.architecture.data.remote.RemoteDataSourceImpl
import study.architecture.data.remote.UpbitApi
import study.architecture.data.repository.Repository
import study.architecture.data.repository.RepositoryImpl

object Injection {

    fun getRepository(context: Context): Repository = RepositoryImpl.getInstance(
        getRemoteDataSource(getRetrofitApi()),
        getLocalDataSource(getDataBase(context)),
        getConnectivityService(context)
    )

    private fun getDataBase(context: Context) = CoinDatabase.getInstance(context)

    private fun getRemoteDataSource(api: UpbitApi): RemoteDataSource =
        RemoteDataSourceImpl.getInstance(api)

    private fun getLocalDataSource(db: CoinDatabase) = LocalDataSourceImpl.getInstance(db)

    private fun getRetrofitApi() = Retrofit
        .Builder()
        .baseUrl(RemoteDataSourceImpl.url)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(
            UpbitApi::
            class.java
        )

    private fun getConnectivityService(context: Context) = context.getSystemService(
        Context.CONNECTIVITY_SERVICE
    ) as ConnectivityManager

}


