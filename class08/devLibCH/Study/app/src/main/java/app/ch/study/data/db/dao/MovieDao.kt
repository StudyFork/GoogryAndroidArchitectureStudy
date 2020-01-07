package app.ch.study.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import app.ch.study.data.db.entitiy.MovieModel
import io.reactivex.Completable

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieModel: MovieModel): Completable

    @Delete
    fun delete(movieModel: MovieModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<MovieModel>)
}