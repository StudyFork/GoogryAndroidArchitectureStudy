package com.jay.architecturestudy.data.database.dao

import androidx.room.Insert
import androidx.room.Query
import com.jay.architecturestudy.data.database.entity.BlogEntity
import io.reactivex.Single

interface BlogDao {
    @Insert
    fun insertAll(blogs: List<BlogEntity>)

    @Query("SELECT * from blog")
    fun getAll(): Single<List<BlogEntity>>

    @Query("DELETE from blog")
    fun clearAll()
}