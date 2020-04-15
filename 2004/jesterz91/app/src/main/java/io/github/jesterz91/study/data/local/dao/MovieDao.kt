package io.github.jesterz91.study.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import io.github.jesterz91.study.data.local.model.MovieLocal

@Dao
abstract class MovieDao : BaseDao<MovieLocal> {

    @Query("SELECT * FROM movie")
    abstract fun selectMovie(): List<MovieLocal>
}