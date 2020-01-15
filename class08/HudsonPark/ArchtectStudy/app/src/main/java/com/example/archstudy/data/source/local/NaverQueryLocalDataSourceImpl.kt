package com.example.archstudy.data.source.local

import com.example.archstudy.MovieData

class NaverQueryLocalDataSourceImpl : NaverQueryLocalDataSource {

    override fun getMovie(query: String): MovieData {
        // TODO : Room에서 저장된 데이터 가져오기
        return MovieData(0, mutableListOf(),0,0)
    }
}