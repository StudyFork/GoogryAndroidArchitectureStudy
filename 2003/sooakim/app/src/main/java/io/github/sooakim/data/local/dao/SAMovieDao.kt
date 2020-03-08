package io.github.sooakim.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.sooakim.data.local.model.SAMovieEntity
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface SAMovieDao {
    @Query(
        value = "SELECT * FROM movie WHERE title LIKE ('%' || :query || '%') " +
                "OR subtitle LIKE ('%' || :query || '%') " +
                "OR director LIKE ('%' || :query || '%') " +
                "OR actor LIKE ('%' || :query || '%')"
    )
    fun getSearchMovie(query: String): Flowable<List<SAMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveSearchResult(movies: List<SAMovieEntity>): Completable
}