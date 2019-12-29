package wooooooak.com.studyapp.data.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import wooooooak.com.studyapp.data.model.response.blog.Blog

@Dao
interface BlogDao {
    @Insert
    fun insertAll(movies: List<Blog>)

    @Query("SELECT * from blog")
    fun getAll(): List<Blog>

    @Query("DELETE from blog")
    fun clearAll()
}