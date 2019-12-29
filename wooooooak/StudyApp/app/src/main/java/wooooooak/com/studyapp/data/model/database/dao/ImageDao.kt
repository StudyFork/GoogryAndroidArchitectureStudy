package wooooooak.com.studyapp.data.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import wooooooak.com.studyapp.data.model.response.image.Image

@Dao
interface ImageDao {
    @Insert
    fun insertAll(movies: List<Image>)

    @Query("SELECT * from image")
    fun getAll(): List<Image>

    @Query("DELETE from image")
    fun clearAll()
}