package study.architecture.data.local.dao

import androidx.room.*

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Delete
    fun delete(obj: T)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(obj: T)
}