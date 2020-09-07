package com.camai.archtecherstudy.data.source.local

import android.content.Context
import com.camai.archtecherstudy.data.source.local.room.RecentSearchDatabase
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName

interface MovieLocalDataSource {
    //  Database Instance Init
    fun getInstance(context: Context): RecentSearchDatabase

    //  저장된 모든 데이터 불러오기
    fun getRecentMovieNameList(namelist: (List<RecentSearchName>) -> Unit)

    //  검색된 검색어 저장
    fun saveMovieName(keyword: String)

    //  모든 데이터 삭제
    fun deleteDb()

    //  디비 close
    fun dbclose()
}