package com.example.datamodule.data.source.local

import android.content.Context
import androidx.core.content.edit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import org.json.JSONArray
import org.json.JSONException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

class LocalDataSourceImpl(
    private val applicationContext: Context
) : LocalDataSource {

    private val compositeDisposable = CompositeDisposable()

    override fun saveQuery(query: String) {
        val queryHistorySharedPreferences =
            applicationContext.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

        getSavedQuery()
            .timeout(5000L, TimeUnit.MILLISECONDS)
            .subscribe({
                val list = it.toMutableList()
                if (!list.remove(query) && it.size > 4) {
                    list.removeFirstOrNull()
                }
                list.add(query)

                queryHistorySharedPreferences.edit {
                    val editArray = JSONArray()
                    list.forEach { query -> editArray.put(query) }
                    putString(sharedPreferencesName, editArray.toString())
                }
            }, {
                it.printStackTrace()
            }).addTo(compositeDisposable)
    }

    override fun getSavedQuery(): Single<List<String>> {
        val queryHistorySharedPreferences =
            applicationContext.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)

        val result = arrayListOf<String>()

        queryHistorySharedPreferences.getString(sharedPreferencesName, null)?.let {
            try {
                val arr = JSONArray(it)
                for (i in 0 until arr.length()) {
                    result.add(arr.getString(i))
                }
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }
        return Single.just(result)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
    }

    companion object {
        const val sharedPreferencesName = "QueryHistory"
    }
}

@Module
@InstallIn(ApplicationComponent::class)
class LocalDataSourceModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext applicationContext: Context): LocalDataSource {
        return LocalDataSourceImpl(applicationContext)
    }
}