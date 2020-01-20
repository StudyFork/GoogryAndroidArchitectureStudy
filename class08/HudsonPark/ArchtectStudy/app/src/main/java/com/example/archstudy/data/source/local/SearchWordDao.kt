package com.example.archstudy.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchWordDao {
    @Query("SELECT searchWord FROM SearchWord WHERE id = 0")
    fun getSearchWord() : String

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchWord(searchWord: SearchWord)
}