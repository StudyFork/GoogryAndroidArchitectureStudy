package com.example.architecturestudy.data.source.local

import com.example.architecturestudy.data.local.Entity.BlogEntity
import com.example.architecturestudy.data.local.Entity.ImageEntity
import com.example.architecturestudy.data.local.Entity.KinEntity
import com.example.architecturestudy.data.local.Entity.MovieEntity
import com.example.architecturestudy.data.local.NaverDataBase
import io.reactivex.Completable
import io.reactivex.Single

class NaverSearchLocalDataSourceImpl(
    private val naverDataBase: NaverDataBase
) : NaverSearchLocalDataSource {

    override fun saveMovieItems(items: List<MovieEntity>): Completable {
        return naverDataBase.movieDao().insertAll(items)
    }

    override fun saveBlogItems(items: List<BlogEntity>): Completable {
        return naverDataBase.blogDao().insertAll(items)
    }

    override fun saveKinItems(items: List<KinEntity>): Completable {
        return naverDataBase.kinDao().insertAll(items)
    }

    override fun saveImageItems(items: List<ImageEntity>): Completable {
        return naverDataBase.imageDao().insertAll(items)
    }

    override fun deleteMovie(): Completable {
        return naverDataBase.movieDao().deleteAll()
    }

    override fun deleteBlog(): Completable {
        return naverDataBase.blogDao().deleteAll()
    }

    override fun deleteKin(): Completable {
        return naverDataBase.kinDao().deleteAll()
    }

    override fun deleteImage(): Completable {
        return naverDataBase.imageDao().deleteAll()
    }

    override fun getMovieItems(): Single<List<MovieEntity>> {
        return naverDataBase.movieDao().getAll()
    }

    override fun getBlogItems(): Single<List<BlogEntity>> {
        return naverDataBase.blogDao().getAll()
    }

    override fun getKiItems(): Single<List<KinEntity>> {
        return naverDataBase.kinDao().getAll()
    }

    override fun getImageItems(): Single<List<ImageEntity>> {
        return naverDataBase.imageDao().getAll()
    }
}