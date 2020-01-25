package com.example.architecturestudy.data.source.local

import com.example.architecturestudy.data.local.Entity.BlogEntity
import com.example.architecturestudy.data.local.Entity.ImageEntity
import com.example.architecturestudy.data.local.Entity.KinEntity
import com.example.architecturestudy.data.local.Entity.MovieEntity
import com.example.architecturestudy.data.local.NaverDataBase
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

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

    override fun saveKinItems(items: List<KinEntity>) {
        executor.submit {
            naverDataBase.kinDao().inserAll(items as? List<KinEntity> ?: emptyList())
        }
    }

    override fun getKiItems(
        success: (items: List<KinEntity>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        val future: Future<List<KinEntity>> = executor.submit(Callable<List<KinEntity>> {
            return@Callable naverDataBase.kinDao().getAll()
        })
        val list = future.get()
        success((list) ?: emptyList())
    }

    override fun deleteKin(items: List<KinEntity>) {
        executor.submit {
            naverDataBase.kinDao().deleteAll()
        }
    }

    override fun saveImageItems(items: List<ImageEntity>) {
        executor.submit {
            naverDataBase.imageDao().inserAll(items as? List<ImageEntity> ?: emptyList())
        }
    }

    override fun deleteImage(items: List<ImageEntity>) {
        executor.submit {
            naverDataBase.imageDao().deleteAll()
        }
    }

    override fun getImageItems(
        success: (items: List<ImageEntity>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        val future: Future<List<ImageEntity>> = executor.submit(Callable<List<ImageEntity>> {
            return@Callable naverDataBase.imageDao().getAll()
        })
        val list = future.get()
        success((list) ?: emptyList())
    }
}