package wooooooak.com.studyapp.data.model.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import wooooooak.com.studyapp.data.model.response.kin.Kin

@Dao
interface KinDao {
    @Insert
    fun insertAll(movies: List<Kin>)

    @Query("SELECT * from kin")
    fun getAll(): List<Kin>

    @Query("DELETE from kin")
    fun clearAll()
}