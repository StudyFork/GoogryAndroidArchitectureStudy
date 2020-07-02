package io.github.jesterz91.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jesterz91.local.model.MovieLocal
import io.reactivex.Maybe

@Dao
internal abstract class MovieDao : BaseDao<MovieLocal> {

    @Query("SELECT * FROM movie WHERE searchQuery = :query ORDER BY id ASC")
    abstract fun loadMovieInfo(query: String): Maybe<List<MovieLocal>>
}