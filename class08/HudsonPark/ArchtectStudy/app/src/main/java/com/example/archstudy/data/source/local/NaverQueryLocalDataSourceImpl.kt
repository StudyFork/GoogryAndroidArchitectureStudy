package com.example.archstudy.data.source.local

class NaverQueryLocalDataSourceImpl(private val movieDataDao: MovieDataDao?,
                                    private val searchWordDao: SearchWordDao?) : NaverQueryLocalDataSource {

    override fun requestSearchWord(): String {
        return searchWordDao?.getSearchWord()?:""
    }

    override fun requestLocalData(query: String): MutableList<MovieData>? {
        return movieDataDao?.getLocalData()
    }

    override fun insertLocalData(query: String, list: MutableList<MovieData>) {
        val searchWord = SearchWord(0, query)
        movieDataDao?.insertAll(list) // 모든 영화를 Local DB에 저장
        searchWordDao?.insertSearchWord(searchWord) // 검색어 Local DB에 저장
    }
}