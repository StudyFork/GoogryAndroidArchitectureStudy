package study.architecture.model

import androidx.room.*

@Dao
interface BaseDAO<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Delete
    fun delete(obj: T)

    @Update(onConflict = OnConflictStrategy.ABORT)
    fun update(obj: T)
}