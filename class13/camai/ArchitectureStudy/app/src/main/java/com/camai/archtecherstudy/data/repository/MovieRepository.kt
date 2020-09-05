package com.camai.archtecherstudy.data.repository

import android.content.Context
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.source.local.room.RecentSearchNameList

interface MovieRepository {

    //  Remote data source
    fun getMovieNameSearch(
        keyword: String,
        display: Int,
        start: Int,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    )

    //  local data select list source
    fun getRecentSearchList(namelist: (List<RecentSearchNameList>) -> Unit, context: Context)

    //  local data save source
    fun setMovieNameInsert(keyword: String, context: Context)

    //  delete data all
    fun deteleDb(context: Context)

    // db close
    fun dbclose()
}