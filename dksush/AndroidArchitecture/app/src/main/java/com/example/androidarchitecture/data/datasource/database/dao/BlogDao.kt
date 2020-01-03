package com.example.androidarchitecture.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidarchitecture.data.response.BlogData
import io.reactivex.Single

@Dao
interface BlogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(blogs: List<BlogData>)

    @Query("SELECT * from blog")
    suspend fun getAll(): List<BlogData>

    @Query("DELETE from blog")
    suspend fun clearAll()
}