package com.example.architecturestudy.data.local.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
