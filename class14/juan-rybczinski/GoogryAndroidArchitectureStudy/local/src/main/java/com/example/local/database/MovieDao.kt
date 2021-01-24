package com.example.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.database.DatabaseMovie
import com.example.data.model.database.DatabaseRecentSearch

@Dao
interface MovieDao {
    @Query("SELECT * FROM databasemovie WHERE title LIKE :search")
    fun searchMovies(search: String): List<DatabaseMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movies: List<DatabaseMovie>)

    @Query("SELECT * FROM databaserecentsearch ORDER BY searchDate DESC LIMIT :limit")
    fun searchRecentUpTo(limit: Int): List<DatabaseRecentSearch>

    @Insert
    fun insertRecentSearch(recentSearch: DatabaseRecentSearch)
}