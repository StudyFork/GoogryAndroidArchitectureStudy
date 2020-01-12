package com.example.studyapplication.data.datasource.local

import com.example.studyapplication.data.model.BlogInfo
import com.example.studyapplication.data.model.ImageInfo
import com.example.studyapplication.data.model.KinInfo
import com.example.studyapplication.data.model.MovieInfo
import com.example.studyapplication.data.model.cache.CacheBlog
import com.example.studyapplication.data.model.cache.CacheImage
import com.example.studyapplication.data.model.cache.CacheKin
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

    override fun saveImageList(arrItem: ArrayList<ImageInfo>) {
        val realm: Realm = Realm.getDefaultInstance()

        try {
            val cacheData = CacheImage()
            cacheData.imageList.addAll(arrItem)
            realm.executeTransaction { realm.copyToRealmOrUpdate(cacheData) }
        } catch (e: Exception) {
            e.message?.let { MyLogger.e(it) }
        } finally {
            realm.close()
        }
    }

    override fun saveBlogList(arrItem: ArrayList<BlogInfo>) {
        val realm: Realm = Realm.getDefaultInstance()

        try {
            val cacheData = CacheBlog()
            cacheData.blogList.addAll(arrItem)
            realm.executeTransaction { realm.copyToRealmOrUpdate(cacheData) }
        } catch (e: Exception) {
            e.message?.let { MyLogger.e(it) }
        } finally {
            realm.close()
        }
    }

    override fun saveKinList(arrItem: ArrayList<KinInfo>) {
        val realm: Realm = Realm.getDefaultInstance()

        try {
            val cacheData = CacheKin()
            cacheData.kinList.addAll(arrItem)
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

    override fun getCacheImageList(
        success: (ArrayList<ImageInfo>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        val realm: Realm = Realm.getDefaultInstance()
        try {
            val cacheData: CacheImage? =
                realm.where(CacheImage::class.java).equalTo("id", 1).findFirst()
            val convertData: ArrayList<ImageInfo> = ArrayList()
            if (cacheData != null && cacheData.imageList.size > 0) {
                convertData.addAll(realm.copyFromRealm(cacheData.imageList))
            }
            success(convertData)
        } catch (e: Exception) {
            failed(e)
        } finally {
            realm.close()
        }
    }

    override fun getCacheBlogList(
        success: (ArrayList<BlogInfo>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        val realm: Realm = Realm.getDefaultInstance()
        try {
            val cacheData: CacheBlog? =
                realm.where(CacheBlog::class.java).equalTo("id", 1).findFirst()
            val convertData: ArrayList<BlogInfo> = ArrayList()
            if (cacheData != null && cacheData.blogList.size > 0) {
                convertData.addAll(realm.copyFromRealm(cacheData.blogList))
            }
            success(convertData)
        } catch (e: Exception) {
            failed(e)
        } finally {
            realm.close()
        }
    }

    override fun getCacheKinList(
        success: (ArrayList<KinInfo>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        val realm: Realm = Realm.getDefaultInstance()
        try {
            val cacheData: CacheKin? =
                realm.where(CacheKin::class.java).equalTo("id", 1).findFirst()
            val convertData: ArrayList<KinInfo> = ArrayList()
            if (cacheData != null && cacheData.kinList.size > 0) {
                convertData.addAll(realm.copyFromRealm(cacheData.kinList))
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

    override fun deleteImageList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteBlogList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteKinList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}