package com.camai.archtecherstudy.data.source.local

import android.content.Context
import com.camai.archtecherstudy.data.source.local.room.RecentSearchNameList

interface MovieLocalDataSource {
    //  저장된 모든 데이터 불러오기
    fun getRecentMovieNameList(namelist: (List<RecentSearchNameList>) -> Unit, context: Context)
    //  검색된 검색어 저장
    fun saveMovieName(keyword: String, context: Context)
    //  모든 데이터 삭제
    fun deleteDb(context: Context)
    //  디비 close
    fun dbclose()
}