package wooooooak.com.studyapp.data.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import wooooooak.com.studyapp.data.model.response.movie.Movie

@Dao
interface MovieDao {
    @Insert
    suspend fun insertAll(movies: List<Movie>)

    @Query("SELECT * from movie")
    suspend fun getAll(): List<Movie>

    @Query("DELETE from movie")
    suspend fun clearAll()
}