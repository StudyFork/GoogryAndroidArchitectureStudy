package com.jay.architecturestudy.data.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.BlogEntity

interface BlogDao {
    @Insert
    suspend fun insertAll(blogs: List<BlogEntity>)

    @Query("SELECT * from blog")
    suspend fun getAll(): List<BlogEntity>

    @Query("DELETE from blog")
    suspend fun clearAll()
}