package com.example.archstudy.data.source.local

class NaverQueryLocalDataSourceImpl(private val itemDao: ItemDao?,
                                    private val searchWordDao: SearchWordDao?) : NaverQueryLocalDataSource {

    override fun requestSearchWord(): String {
        return searchWordDao?.getSearchWord()?:""
    }

    override fun requestLocalData(query: String): MutableList<MovieData>? {
        return itemDao?.getLocalData()
    }

    override fun insertLocalData(query: String, list: MutableList<MovieData>) {
        val searchWord = SearchWord(0, query)
        itemDao?.insertAll(list)
        searchWordDao?.insertSearchWord(searchWord)
    }

}