package io.github.jesterz91.study.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jesterz91.study.data.local.model.MovieLocal
import io.reactivex.Maybe

@Dao
abstract class MovieDao : BaseDao<MovieLocal> {

    @Query("SELECT * FROM movie WHERE searchQuery = :query ORDER BY id ASC ")
    abstract fun loadMovieInfo(query: String): Maybe<List<MovieLocal>>
}