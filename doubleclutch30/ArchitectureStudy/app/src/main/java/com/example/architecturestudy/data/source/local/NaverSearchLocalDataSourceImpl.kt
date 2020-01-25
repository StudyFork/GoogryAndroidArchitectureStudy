package com.example.architecturestudy.data.source.local

import android.util.Log
import com.example.architecturestudy.data.local.Entity.BlogEntity
import com.example.architecturestudy.data.local.Entity.MovieEntity
import com.example.architecturestudy.data.local.NaverDataBase
import java.util.concurrent.*

class NaverSearchLocalDataSourceImpl(
        private val naverDataBase: NaverDataBase
) : NaverSearchLocalDataSource {

    private val executor = Executors.newSingleThreadExecutor()

    override fun saveMovieItems(items: List<MovieEntity>) {
        executor.submit {
            naverDataBase.movieDao().insertAll((items as? List<MovieEntity>) ?: emptyList())
        }
    }

    override fun getMovieItems(
        success: (items: List<MovieEntity>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        val future: Future<List<MovieEntity>> = executor.submit(Callable<List<MovieEntity>> {
            return@Callable naverDataBase.movieDao().getAll()
        })
        val list = future.get()
        success((list) ?: emptyList())
    }

    override fun deleteMovie(items: List<MovieEntity>) {
        executor.submit {
            naverDataBase.movieDao().deleteAll()
        }
    }

    override fun saveBlogItems(items: List<BlogEntity>) {
        executor.submit {
            naverDataBase.blogDao().insertAll((items as? List<BlogEntity>) ?: emptyList())
        }
    }

    override fun getBlogItems(
        success: (items: List<BlogEntity>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        val future: Future<List<BlogEntity>> = executor.submit(Callable<List<BlogEntity>> {
            return@Callable naverDataBase.blogDao().getAll()
        })
        val list = future.get()
        success((list) ?: emptyList())
    }

    override fun deleteBlog(items: List<BlogEntity>) {
        executor.submit {
            naverDataBase.blogDao().deleteAll()
        }
    }
}