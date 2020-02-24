package com.example.architecturestudy.data.repository

import android.util.Log
import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.data.model.ImageItem
import com.example.architecturestudy.data.model.KinItem
import com.example.architecturestudy.data.model.MovieItem
import com.example.architecturestudy.data.source.local.NaverSearchLocalDataSource
import com.example.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import com.example.architecturestudy.network.response.BlogData
import com.example.architecturestudy.network.response.ImageData
import com.example.architecturestudy.network.response.KinData
import com.example.architecturestudy.network.response.MovieData
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class NaverSearchRepositoryImpl(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource,
    private val naverSearchLocalDataSource: NaverSearchLocalDataSource
) : NaverSearchRepository {

    override fun getMovie(
        keyword: String
    ): Single<MovieData> =
        naverSearchRemoteDataSource.getMovie(keyword = keyword)
            .flatMap {
                val deleteCompletable = naverSearchLocalDataSource.deleteMovie()
                val insertCompletable = naverSearchLocalDataSource.saveMovieItems(it.items.map { it.toEntity() })

                Completable.concat(mutableListOf(deleteCompletable,insertCompletable)).blockingAwait()

                return@flatMap Single.just(it)
            }

    override fun getBlog(
        keyword: String
    ): Single<BlogData> =
        naverSearchRemoteDataSource.getBlog(keyword = keyword)
            .flatMap {
                val deleteCompletable = naverSearchLocalDataSource.deleteBlog()
                val insertCompletable = naverSearchLocalDataSource.saveBlogItems(it.items.map { it.toEntity() })

                Completable.concat(listOf(deleteCompletable, insertCompletable)).blockingAwait()

                return@flatMap Single.just(it)
            }

    override fun getKin(
        keyword: String
    ): Single<KinData> =
        naverSearchRemoteDataSource.getKin(keyword = keyword)
            .flatMap {
                val deleteCompletable = naverSearchLocalDataSource.deleteKin()
                val insertCompletable = naverSearchLocalDataSource.saveKinItems(it.items.map { it.toEntity() })

                Completable.concat(listOf(deleteCompletable, insertCompletable)).blockingAwait()

                return@flatMap Single.just(it)
            }

    override fun getImage(
        keyword: String
    ): Single<ImageData> =
            naverSearchRemoteDataSource.getImage(keyword = keyword)
                .flatMap {
                    val deleteCompletable = naverSearchLocalDataSource.deleteImage()
                    val insertCompletable = naverSearchLocalDataSource.saveImageItems(it.items.map { it.toEntity() })

                    Completable.concat(listOf(deleteCompletable, insertCompletable)).blockingAwait()

                    return@flatMap Single.just(it)
                }

    override fun getLastMovie(): Single<List<MovieItem>> =
        naverSearchLocalDataSource.getMovieItems()
            .map { it.map { it.toItem() } }

    override fun getLastBlog(): Single<List<BlogItem>> {
        return naverSearchLocalDataSource.getBlogItems()
            .map { it.map { it.toItem() } }
    }

    override fun getLastKin(): Single<List<KinItem>> {
        return naverSearchLocalDataSource.getKiItems()
            .map { it.map { it.toItem() } }
    }

    override fun getLastImage(): Single<List<ImageItem>> {
        return naverSearchLocalDataSource.getImageItems()
            .map { it.map { it.toItem() } }
    }
}