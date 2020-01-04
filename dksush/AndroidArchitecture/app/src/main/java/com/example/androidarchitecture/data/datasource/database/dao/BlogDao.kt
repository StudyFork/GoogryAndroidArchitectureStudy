package com.example.androidarchitecture.data.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidarchitecture.data.response.BlogData

@Dao
interface BlogDao {

    @Insert
    suspend fun insertAll(blogs: List<BlogData>)

    @Query("SELECT * from blog")
    suspend fun getAll(): List<BlogData>

    @Query("DELETE from blog")
    suspend fun clearAll()
}