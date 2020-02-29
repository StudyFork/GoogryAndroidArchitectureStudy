package com.jay.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.local.model.BlogEntity
import io.reactivex.Single

@Dao
internal interface BlogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(blogs: List<BlogEntity>)

    @Query("SELECT * from blog")
    fun getAll(): Single<List<BlogEntity>>

    @Query("DELETE from blog")
    fun clearAll()
}