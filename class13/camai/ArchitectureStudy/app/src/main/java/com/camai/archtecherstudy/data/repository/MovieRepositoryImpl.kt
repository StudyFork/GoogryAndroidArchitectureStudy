package com.camai.archtecherstudy.data.repository

import android.content.Context
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.source.local.MovieLocalDataSourceImpl
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName
import com.camai.archtecherstudy.data.source.remote.MovieRemoteDataSourceImpl


object MovieRepositoryImpl : MovieRepository {

    override fun getMovieNameSearch(
        keyword: String,
        display: Int,
        start: Int,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    ) {
        //  Data Insert
        setMovieNameInsert(keyword)
        //  retrofit call
        MovieRemoteDataSourceImpl.getSearchMovie(keyword, display, start, success, failed)
    }

    override fun getInsatance(context: Context) {
        MovieLocalDataSourceImpl.getInstance(context)
    }

    override fun getRecentSearchList(
        namelist: (List<RecentSearchName>) -> Unit
    ) {
        MovieLocalDataSourceImpl.getRecentMovieNameList(namelist)
    }


    override fun setMovieNameInsert(keyword: String) {
        MovieLocalDataSourceImpl.saveMovieName(keyword)
    }

    override fun deteleDb() {
        MovieLocalDataSourceImpl.deleteDb()
    }
}