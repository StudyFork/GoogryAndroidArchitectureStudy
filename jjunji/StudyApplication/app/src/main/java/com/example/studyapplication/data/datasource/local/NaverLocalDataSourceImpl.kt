package com.example.studyapplication.data.datasource.local

import com.example.studyapplication.data.model.MovieInfo
import com.example.studyapplication.data.model.cache.CacheMovie
import com.example.studyapplication.util.MyLogger
import io.realm.Realm

class NaverLocalDataSourceImpl : NaverLocalDataSource {

    override fun saveMovieList(arrItem: ArrayList<MovieInfo>) {
        val realm: Realm = Realm.getDefaultInstance()

        try {
            val cacheData = CacheMovie()
            cacheData.movieList.addAll(arrItem)
            realm.executeTransaction { realm.copyToRealmOrUpdate(cacheData) }
        } catch (e: Exception) {
            e.message?.let { MyLogger.e(it) }
        } finally {
            realm.close()
        }

    }

    override fun getCacheMovieList(
        success: (ArrayList<MovieInfo>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        val realm: Realm = Realm.getDefaultInstance()
        try {
            val cacheData: CacheMovie? =
                realm.where(CacheMovie::class.java).equalTo("id", 1).findFirst()
            val convertData: ArrayList<MovieInfo> = ArrayList()
            if (cacheData != null && cacheData.movieList.size > 0) {
                convertData.addAll(realm.copyFromRealm(cacheData.movieList))
            }
            success(convertData)
        } catch (e: Exception) {
            failed(e)
        } finally {
            realm.close()
        }
    }

    override fun deleteMovieList() {
        TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
    }

}