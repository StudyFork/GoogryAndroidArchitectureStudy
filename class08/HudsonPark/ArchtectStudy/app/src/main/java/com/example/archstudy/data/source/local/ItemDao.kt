package com.example.archstudy.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("SELECT * FROM MovieData")
    fun getLocalData() : MutableList<MovieData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setLocalData(localItem: LocalItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: MutableList<MovieData>)

}