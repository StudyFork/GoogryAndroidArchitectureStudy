package com.example.architecturestudy.data.source.local

import android.util.Log
import com.example.architecturestudy.data.local.Entity.MovieEntity
import com.example.architecturestudy.data.local.NaverDataBase
import com.example.architecturestudy.data.model.MovieItem
import com.example.architecturestudy.util.AppExecutor
import java.lang.Exception
import java.util.concurrent.*

class NaverSearchLocalDataSourceImpl(
    private val appExecutor: AppExecutor,
    private val naverDataBase: NaverDataBase
) : NaverSearchLocalDataSource {

    override fun <T> saveSearchItems(items: List<T>) {
        val executor = Executors.newSingleThreadExecutor()
        executor.submit {
            naverDataBase.movieDao().insertAll((items as? List<MovieEntity>) ?: emptyList())
        }
    }

    override fun <T> getSearchItems(
        keyword: String?,
        success: (items: List<T>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        val executor = Executors.newSingleThreadExecutor()

        /* runnable */
        /*
            executor.submit {
            // 작업 스레드
            val list = naverDataBase.movieDao().getAll()

            appExecutor.mainThread.execute {
                // 메인 스레드
                success(list as List<T>)
            }
        }*/

        /* future */
        val future: Future<List<MovieEntity>> = executor.submit(Callable<List<MovieEntity>> {
            return@Callable naverDataBase.movieDao().getAll()
        })

        Log.e("future", "${future.isDone}")

        val list = future.get()

        Log.e("future", "${future.isDone}")
        Log.e("future", "$list")

        success((list as? List<T>) ?: emptyList())
    }
}