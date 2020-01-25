package com.example.architecturestudy.data.local.Dao

import androidx.room.*
import com.example.architecturestudy.data.local.Entity.BlogEntity

@Dao
interface BlogDao {
    @Query("SELECT * FROM Blog")
    fun getAll() : List<BlogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(item: List<BlogEntity>)

    @Query("DELETE FROM Blog")
    fun deleteAll()
}
