package com.jay.architecturestudy.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.BlogEntity
import io.reactivex.Single

@Dao
interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(blogs: List<BlogEntity>)

    @Query("SELECT * from blog")
    fun getAll(): Single<List<BlogEntity>>

    @Query("DELETE from blog")
    fun clearAll()
}