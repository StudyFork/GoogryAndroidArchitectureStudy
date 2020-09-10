package com.camai.archtecherstudy.data.repository

import android.content.Context
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName

interface MovieRepository {

    //  Remote data source
    fun getMovieNameSearch(
        keyword: String,
        display: Int,
        start: Int,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    )

    //  Database Instance Init
    fun getInsatance(context: Context)

    //  local data select list source
    fun getRecentSearchList(namelist: (List<RecentSearchName>) -> Unit)

    //  local data save source
    fun setMovieNameInsert(keyword: String)

    //  delete data all
    fun deteleDb()

}