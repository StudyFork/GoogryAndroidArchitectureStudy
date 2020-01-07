package app.ch.study.data.db.dao

import androidx.room.*
import app.ch.study.data.db.entitiy.MovieModel
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM MovieModel")
    fun findAll(): Single<List<MovieModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieModel: MovieModel): Completable

    @Delete
    fun delete(movieModel: MovieModel)

    @Query("DELETE FROM MovieModel")
    fun deleteAll(): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<MovieModel>): Completable
}