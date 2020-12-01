package com.example.androidarchitecturestudy.data.datasource

import com.example.androidarchitecturestudy.room.MovieSearchEntity
import com.example.androidarchitecturestudy.room.SearchedDataBase

class MovieLocalDataSourceImpl(
) : MovieLocalDataSource {

    override fun getRecentSearchedMovieList(database: SearchedDataBase):List<String> = database.getMovieSearchedDao().getSearchedQuery()
    override fun saveRecentSearch(searchQuery: String, database: SearchedDataBase) {
          //idx 는 auto_increment적용해서 null 값으로 줌.
         database.getMovieSearchedDao().insertQuery(MovieSearchEntity(null,searchQuery))
    }


}