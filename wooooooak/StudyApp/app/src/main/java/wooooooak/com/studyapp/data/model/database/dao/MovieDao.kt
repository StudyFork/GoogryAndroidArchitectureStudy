package wooooooak.com.studyapp.data.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import wooooooak.com.studyapp.data.model.response.movie.Movie

@Dao
interface MovieDao {
    @Insert
    fun insertAll(movies: List<Movie>)

    @Query("SELECT * from movie")
    fun getAll(): List<Movie>

    @Query("DELETE from movie")
    fun clearAll()
}