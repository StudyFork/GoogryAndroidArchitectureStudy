package com.jay.architecturestudy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.BlogItemEntity
import io.reactivex.Single

@Dao
interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(blogs: List<BlogItemEntity>)

    @Query("SELECT * from blog")
    fun getAll(): Single<List<BlogItemEntity>>

    @Query("DELETE from blog")
    fun clearAll()
}