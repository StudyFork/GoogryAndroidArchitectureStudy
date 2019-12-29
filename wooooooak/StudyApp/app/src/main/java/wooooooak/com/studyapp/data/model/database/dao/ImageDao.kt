package wooooooak.com.studyapp.data.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import wooooooak.com.studyapp.data.model.response.image.Image

@Dao
interface ImageDao {
    @Insert
    suspend fun insertAll(movies: List<Image>)

    @Query("SELECT * from image")
    suspend fun getAll(): List<Image>

    @Query("DELETE from image")
    suspend fun clearAll()
}